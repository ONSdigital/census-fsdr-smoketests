package uk.gov.ons.census.fsdr.smoketests.steps;

import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.census.fsdr.smoketests.AdeccoHelper;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class AdeccoSteps {

  @Autowired AdeccoHelper adecco;

  @When("^Adecco is running and accessible$")
  public void checkAdeccoRunning() throws IOException {
   assertTrue(adecco.callAdecco());
  }
}
