package uk.gov.ons.census.fsdr.smoketests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DatabaseHelper {

  @Value("${db.url}")
  String url;

  @Value("${db.user}")
  String user;

  @Value("${db.pass}")
  String password;

  public boolean checkDBConnection() throws SQLException {
    boolean connected = false;

    try (Connection conn = DriverManager.getConnection(url,user,password)) {
      connected = conn.isValid(10);
    }

    return connected;
  }
}
