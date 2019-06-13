package uk.gov.ons.census.fsdr.smoketests.steps;

import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.census.fsdr.smoketests.AirWatchHelper;

import static org.junit.Assert.assertTrue;

public class AirWatchSteps {

  @Autowired AirWatchHelper airWatchHelper;

  @When("^AirWatch is running and accessible$")
  public void checkAirWatchRunning() {
    assertTrue(airWatchHelper.checkAirWatchStatus());
  }
}
