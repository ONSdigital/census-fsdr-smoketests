package uk.gov.ons.census.fsdr.smoketests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ServiceNowHelper {

  @Value("${servicenow.user}")
  private String serviceNowUser;

  @Value("${servicenow.password}")
  private String serviceNowPassword;

  @Value("${servicenow.url}")
  private String serviceNowUrl;

  public boolean checkServiceNowStatus() {
    RestTemplate restTemplate = resourcesRestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(serviceNowUrl, String.class);
    return response.getStatusCode().is2xxSuccessful();
  }

  private RestTemplate resourcesRestTemplate() {
    RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    restTemplate.setInterceptors(List.of(new BasicAuthenticationInterceptor(serviceNowUser,serviceNowPassword)));
    return restTemplate;
  }
}
