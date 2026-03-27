import java.sql.*;

public class DBConnection {

  private static final String URL = "jdbc:mysql://localhost:3306/library_db";
  private static final String USER = "root";
  private static final String PASSWORD = "root";

  public static Connection getConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(URL, USER, PASSWORD);
      // System.out.println("Connected to database Successfully");
    } catch (SQLException e) {
      System.out.println("Connection failed: "+e.getMessage());
    }
    return conn;
  }

  
}
