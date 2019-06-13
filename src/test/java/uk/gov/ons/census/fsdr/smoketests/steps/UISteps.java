package uk.gov.ons.census.fsdr.smoketests.steps;

import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.census.fsdr.smoketests.UIHelper;

import static org.junit.Assert.assertTrue;

public class UISteps {

  @Autowired UIHelper uiHelper;

  @When("^the UI is running and accessible$")
  public void checkServiceNowRunning() {
    assertTrue(uiHelper.checkUIStatus());
  }
}
