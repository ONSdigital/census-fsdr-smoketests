package uk.gov.ons.census.fsdr.smoketests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UIHelper {

  @Value("${ui.url}")
  private String uiUrl;

  public boolean checkUIStatus() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity(uiUrl, String.class);
    return response.getStatusCode().is2xxSuccessful();
  }
}
