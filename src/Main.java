import services.BookService;
import services.MemberService;
import services.IssueService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService bs = new BookService();
        MemberService ms = new MemberService();
        IssueService is = new IssueService();

        while (true) {
            System.out.println("\n===== 📚 Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Remove Book");
            System.out.println("4. Add Member");
            System.out.println("5. View Members");
            System.out.println("6. Remove Member");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. View Issued Books");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Copies: ");
                    int copies = sc.nextInt();
                    bs.addBook(title, author, copies);
                    break;
                case 2:
                    bs.view();
                    break;
                case 3:
                    System.out.print("Book ID: ");
                    bs.remove(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    ms.addMember(name, email);
                    break;
                case 5:
                    ms.view();
                    break;
                case 6:
                    System.out.print("Member ID: ");
                    ms.remove(sc.nextInt());
                    break;
                case 7:
                    System.out.print("Book ID: ");
                    int bId = sc.nextInt();
                    System.out.print("Member ID: ");
                    int mId = sc.nextInt();
                    is.issueBook(bId, mId);
                    break;
                case 8:
                    System.out.print("Issue ID: ");
                    is.returnBook(sc.nextInt());
                    break;
                case 9:
                    is.view();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println(" Invalid choice!");
            }
        }
    }
}