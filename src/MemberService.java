import java.sql.*;

public class MemberService {
  public void addMember(String name, String email) {
    try {
      Connection conn = DBConnection.getConnection();

      String query = "INSERT INTO users (name , email) VALUES(?,?)";
      PreparedStatement st = conn.prepareStatement(query);

      st.setString(1, name);
      st.setString(2, email);
      st.executeUpdate();

      System.out.println("Member added successfully");
      conn.close();
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void viewMembers() {
    try {
      Connection conn = DBConnection.getConnection();
      String query = "SELECT * FROM users";

      PreparedStatement st = conn.prepareStatement(query);
      ResultSet rs = st.executeQuery();

      System.out.println("\n All Members:");
      System.out.println("ID | Name | Email");
      System.out.println("--------------------------------");
      while (rs.next()) {
        System.out.println(
            rs.getInt("id") + " | " +
                rs.getString("name") + " | " +
                rs.getString("email"));
      }
      conn.close();
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void removeMember(int id) {
    try {
      Connection conn = DBConnection.getConnection();
      String query = "DELETE FROM users WHERE id = ?";

      PreparedStatement st = conn.prepareStatement(query);
      st.setInt(1, id);
      st.executeUpdate();

      System.out.println("Member removed Successfully!");
      conn.close();
    } catch (SQLException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
