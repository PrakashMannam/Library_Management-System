package services;

import models.Book;
import java.sql.*;

public class BookService extends BaseService {

    @Override
    public void view() {
        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM books";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n All Books:");
            System.out.println("ID | Title | Author | Copies");
            System.out.println("--------------------------------");
            while (rs.next()) {
                Book b = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("available_copies")
                );
                System.out.println(b.toString());
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
            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println(" Book removed!");
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    public void addBook(String title, String author, int copies) {
        try {
            Connection conn = getConnection();
            String query = "INSERT INTO books (title, author, available_copies) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, copies);
            stmt.executeUpdate();
            System.out.println(" Book added!");
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}