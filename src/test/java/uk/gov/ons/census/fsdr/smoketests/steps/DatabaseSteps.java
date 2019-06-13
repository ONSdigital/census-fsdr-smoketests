package uk.gov.ons.census.fsdr.smoketests.steps;

import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.census.fsdr.smoketests.DatabaseHelper;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class DatabaseSteps {

  @Autowired DatabaseHelper databaseHelper;

  @When("^postgres is running and accessible$")
  public void checkPostgresRunning() throws SQLException {
    assertTrue(databaseHelper.checkDBConnection());
  }
}
