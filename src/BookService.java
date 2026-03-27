import java.sql.*;

public class BookService {

    // ADD BOOK
    public void addBook(String title, String author, int copies) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO books (title, author, available_copies) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, copies);
            stmt.executeUpdate();
            System.out.println(" Book added successfully!");
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    // VIEW ALL BOOKS
    public void viewBooks() {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT * FROM books";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            System.out.println("\n All Books:");
            System.out.println("ID | Title | Author | Copies");
            System.out.println("--------------------------------");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author") + " | " +
                    rs.getInt("available_copies")
                );
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    // REMOVE BOOK
    public void removeBook(int id) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println(" Book removed successfully!");
            conn.close();
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
