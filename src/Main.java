import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BookService bs = new BookService();
    MemberService ms = new MemberService();
    IssueService is = new IssueService();

    while (true) {
      System.out.println("\n=====   Library Management System   ======");
      System.out.println("1. Add Book");
      System.out.println("2. View Books");
      System.out.println("3. Remove Book");
      System.out.println("4. Add Member");
      System.out.println("5. View Member");
      System.out.println("6. Remove Member");
      System.out.println("7. Issue Book");
      System.out.println("8. Return Book");
      System.out.println("9. View Issued Books");
      System.out.println("Enter choice: ");

      int choice = sc.nextInt();
      sc.nextLine();

      switch (choice) {
        case 1:
          System.out.println("Enter title: ");
          String title = sc.nextLine();
          System.out.println("Enter author: ");
          String author = sc.nextLine();
          System.out.println("Enter copies: ");
          int copies = sc.nextInt();
          bs.addBook(title, author, copies);
          break;

        case 2:
          bs.viewBooks();
          break;

        case 3:
          System.out.println("Enter Book Id to remove: ");
          int bookId = sc.nextInt();
          bs.removeBook(bookId);
          break;

        case 4:
          System.out.println("Enter name: ");
          String name = sc.nextLine();
          System.out.println("Enter email: ");
          String email = sc.nextLine();
          ms.addMember(name, email);
          break;

        case 5:
          ms.viewMembers();
          break;

        case 6:
          System.out.println("Enter Member Id to remove: ");
          int memberId = sc.nextInt();
          ms.removeMember(memberId);
          break;
        case 7:
          System.out.print("Enter book ID: ");
          int bId = sc.nextInt();

          System.out.print("Enter member ID: ");
          int mId = sc.nextInt();

          is.issueBook(bId, mId);
          break;
        case 8:
          System.out.println("Enter issue ID: ");
          int issudeId = sc.nextInt();
          is.returnBook(issudeId);
          break;
        case 9:
          is.viewIssuedBooks();
          break;
        case 0:
          System.out.println("Goodbye!");
          sc.close();
          System.exit(0);
        default:
          System.out.println("Invalid Choice!");
      }
    }
  }
}
