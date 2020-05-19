package uk.gov.ons.census.fsdr.smoketests;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AdeccoHelper {

  @Value("${security.oauth2.resource.baseurl}")
  private String ADECCO_URL;

  @Value("${security.oauth2.client.accessTokenUri}")
  private String accessTokenUri;

  @Value("${security.oauth2.client.clientId}")
  private String clientId;

  @Value("${security.oauth2.client.clientSecret}")
  private String clientSecret;

  @Value("${security.oauth2.client.username}")
  private String username;

  @Value("${security.oauth2.client.password}")
  private String password;

  @Value("${security.oauth2.client.query}")
  private String query;


  public boolean callAdecco() throws IOException {
    HttpClient client = HttpClients.custom().build();
    HttpUriRequest request = RequestBuilder.get()
        .setUri(ADECCO_URL + query)
        .setHeader(HttpHeaders.AUTHORIZATION,"Bearer " + getAccessToken())
        .build();
    HttpResponse response = client.execute(request);

    return isSuccess(response.getStatusLine().getStatusCode());
  }

  private String getAccessToken() throws IOException {
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpPost poster = new HttpPost(accessTokenUri);
    MultipartEntityBuilder mpEntityBuilder = MultipartEntityBuilder.create();
    mpEntityBuilder.addPart("client_id", new StringBody(clientId, ContentType.TEXT_PLAIN));
    mpEntityBuilder.addPart("client_secret", new StringBody(clientSecret, ContentType.TEXT_PLAIN));
    mpEntityBuilder.addPart("username", new StringBody(username, ContentType.TEXT_PLAIN));
    mpEntityBuilder.addPart("password", new StringBody(password, ContentType.TEXT_PLAIN));
    mpEntityBuilder.addPart("grant_type", new StringBody("password", ContentType.TEXT_PLAIN));
    poster.setEntity(mpEntityBuilder .build());
    HttpResponse response = httpClient.execute(poster);

    String result = EntityUtils.toString(response.getEntity());
    return extractToken(result);
  }

  private String extractToken(String json) {
    String[] tmp = json.split("\"");

    String token = tmp[3];

    return token.trim();
  }

  private boolean isSuccess(int response) {
    boolean success = false;

    if(response == 200 || response == 201) {
      success = true;
    }

    return success;

  }
}