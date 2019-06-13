package uk.gov.ons.census.fsdr.smoketests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Component
public class AirWatchHelper {

  @Value("${airwatch.url}")
  private String airwatchUrl;

  @Value("${airwatch.username}")
  private String user;

  @Value("${airwatch.password}")
  private String pass;

  @Value("${airwatch.tenant-code}")
  private String tenantCode;

  public boolean checkAirWatchStatus() {
    RestTemplate restTemplate = resourcesRestTemplate();
    ResponseEntity<String> response = restTemplate.exchange(airwatchUrl, HttpMethod.GET, createHeaders(), String.class);
    return response.getStatusCode().is2xxSuccessful();
  }

  private RestTemplate resourcesRestTemplate() {
    RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    return restTemplate;
  }

  private HttpEntity<String> createHeaders() {
    String credentials = user + ":" + pass;
    String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

    HttpHeaders headers =  new HttpHeaders();
    headers.set("Authorization", "Basic " + encodedCredentials);
    headers.set("aw-tenant-code", tenantCode);
    headers.set("Media-Type", "Application/JSON");

    HttpEntity<String> entity = new HttpEntity<String>(headers);


    return entity;
  }
}
