import java.sql.*;
import java.time.LocalDate;

public class IssueService {

  // ISSUE BOOK
  public void issueBook(int bookId, int memberId) {
    try {
      Connection conn = DBConnection.getConnection();

      // Check if copies available
      String checkQuery = "SELECT available_copies FROM books WHERE id = ?";
      PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
      checkStmt.setInt(1, bookId);
      ResultSet rs = checkStmt.executeQuery();

      if (rs.next() && rs.getInt("available_copies") > 0) {

        // Insert into issued_books
        String issueQuery = "INSERT INTO issued_books (book_id, member_id, due_date) VALUES (?, ?, ?)";
        PreparedStatement issueStmt = conn.prepareStatement(issueQuery);
        issueStmt.setInt(1, bookId);
        issueStmt.setInt(2, memberId);
        issueStmt.setDate(3, Date.valueOf(LocalDate.now().plusDays(14)));
        issueStmt.executeUpdate();

        // Reduce available copies by 1
        String updateQuery = "UPDATE books SET available_copies = available_copies - 1 WHERE id = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setInt(1, bookId);
        updateStmt.executeUpdate();

        System.out.println(" Book issued! Due date: " + LocalDate.now().plusDays(14));
      } else {
        System.out.println(" Book not available!");
      }
      conn.close();
    } catch (SQLException e) {
      System.out.println(" Error: " + e.getMessage());
    }
  }

  // RETURN BOOK
  public void returnBook(int issueId) {
    try {
      Connection conn = DBConnection.getConnection();

      // Get book_id first
      String getQuery = "SELECT book_id, due_date FROM issued_books WHERE id = ?";
      PreparedStatement getStmt = conn.prepareStatement(getQuery);
      getStmt.setInt(1, issueId);
      ResultSet rs = getStmt.executeQuery();

      if (rs.next()) {
        int bookId = rs.getInt("book_id");
        Date dueDate = rs.getDate("due_date");

        // Mark as returned
        String returnQuery = "UPDATE issued_books SET returned = TRUE WHERE id = ?";
        PreparedStatement returnStmt = conn.prepareStatement(returnQuery);
        returnStmt.setInt(1, issueId);
        returnStmt.executeUpdate();

        // Increase available copies by 1
        String updateQuery = "UPDATE books SET available_copies = available_copies + 1 WHERE id = ?";
        PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
        updateStmt.setInt(1, bookId);
        updateStmt.executeUpdate();

        // Calculate fine
        LocalDate today = LocalDate.now();
        LocalDate due = dueDate.toLocalDate();
        if (today.isAfter(due)) {
          long daysLate = java.time.temporal.ChronoUnit.DAYS.between(due, today);
          System.out.println(" Book returned! Fine: â‚¹" + (daysLate * 5));
        } else {
          System.out.println(" Book returned! No fine.");
        }
      }
      conn.close();
    } catch (SQLException e) {
      System.out.println(" Error: " + e.getMessage());
    }
  }

  // VIEW ALL ISSUED BOOKS
  public void viewIssuedBooks() {
    try {
      Connection conn = DBConnection.getConnection();
      String query = "SELECT ib.id, b.title, u.name, ib.issue_date, ib.due_date, ib.returned " +
          "FROM issued_books ib " +
          "JOIN books b ON ib.book_id = b.id " +
          "JOIN users u ON ib.member_id = u.id";
      PreparedStatement stmt = conn.prepareStatement(query);
      ResultSet rs = stmt.executeQuery();
      System.out.println("\n Issued Books:");
      System.out.println("ID | Book | Member | Issue Date | Due Date | Returned");
      System.out.println("----------------------------------------------------------");
      while (rs.next()) {
        System.out.println(
            rs.getInt("id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("name") + " | " +
                rs.getDate("issue_date") + " | " +
                rs.getDate("due_date") + " | " +
                (rs.getBoolean("returned") ? " Yes" : " No"));
      }
      conn.close();
    } catch (SQLException e) {
      System.out.println(" Error: " + e.getMessage());
    }
  }
}
