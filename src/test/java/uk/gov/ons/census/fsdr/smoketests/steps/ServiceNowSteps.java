package uk.gov.ons.census.fsdr.smoketests.steps;

import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.census.fsdr.smoketests.ServiceNowHelper;

import static org.junit.Assert.assertTrue;

public class ServiceNowSteps {

  @Autowired ServiceNowHelper serviceNowHelper;

  @When("^ServiceNow is running and accessible$")
  public void checkServiceNowRunning() {
    assertTrue(serviceNowHelper.checkServiceNowStatus());
  }
}
