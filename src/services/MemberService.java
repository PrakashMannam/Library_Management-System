package services;

import models.Member;
import java.sql.*;

public class MemberService extends BaseService {

    @Override
    public void view() {
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM users";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n👤 All Members:");
            System.out.println("ID | Name | Email");
            System.out.println("--------------------------------");
            while (rs.next()) {
                Member m = new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
                System.out.println(m.toString());
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    @Override
    public void remove(int id) {
        try {
            Connection conn = getConnection();
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println(" Member removed!");
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    public void addMember(String name, String email) {
        try {
            Connection conn = getConnection();
            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println(" Member added!");
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}