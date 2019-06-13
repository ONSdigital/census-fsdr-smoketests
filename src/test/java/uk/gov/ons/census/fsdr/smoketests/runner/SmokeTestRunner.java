package uk.gov.ons.census.fsdr.smoketests.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/test/resources/smoketest.feature"}, glue = {
    "uk.gov.ons.census.fsdr.smoketests.steps"})
public class SmokeTestRunner {

}
