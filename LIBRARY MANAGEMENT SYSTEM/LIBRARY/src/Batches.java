import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Batches {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/lms";
        String user = "root";
        String password = "manash2006";

        Connection con = null;
        PreparedStatement ps = null;
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            con = DriverManager.getConnection(url, user, password);

            // Auto commit OFF
            con.setAutoCommit(false);

            while (true) {
                System.out.println(" LIBRARY MANAGEMENT SYSTEM ");
                System.out.println("1. ADD NEW BOOK");
                System.out.println("2. UPDATE BOOK");
                System.out.println("3. TO DISPLAY ALL BOOKS");
                System.out.print("ENTER OPTION: ");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Enter Book ID: ");
                    int ID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Book Name: ");
                    String NAME = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String AUTHOR = sc.nextLine();
                    System.out.print("Enter Genre: ");
                    String GENRE = sc.nextLine();
                    System.out.print("Enter DOP (YYYY-MM-DD): ");
                    String DOP = sc.nextLine();
                    String insertSql = "INSERT INTO BOOKS VALUES (?, ?, ?, ?, ?)";
                    ps = con.prepareStatement(insertSql);

                    ps.setInt(1, ID);
                    ps.setString(2, NAME);
                    ps.setString(3, AUTHOR);
                    ps.setString(4, GENRE);
                    ps.setString(5, DOP);

                    int rows = ps.executeUpdate();
                    con.commit();   

                    System.out.println(rows + " book inserted successfully.");

                } else if (choice == 2) {
                    System.out.print("Enter Book ID to update: ");
                    int ID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Book Name: ");
                    String NAME = sc.nextLine();
                    System.out.print("Enter New Author: ");
                    String AUTHOR = sc.nextLine();
                    System.out.print("Enter New Genre: ");
                    String GENRE = sc.nextLine();
                    System.out.print("Enter New DOP (YYYY-MM-DD): ");
                    String DOP = sc.nextLine();
                    String updateSql = "UPDATE BOOKS SET NAME=?, AUTHOR=?, GENRE=?, DOP=? WHERE ID=?";
                    ps = con.prepareStatement(updateSql);

                    ps.setString(1, NAME);
                    ps.setString(2, AUTHOR);
                    ps.setString(3, GENRE);
                    ps.setString(4, DOP);
                    ps.setInt(5, ID);

                    int rows = ps.executeUpdate();
                    con.commit();   

                    System.out.println(rows + " book updated successfully.");

                } else if (choice == 3) {
                    String selectSql = "SELECT * FROM BOOKS";
                    ps = con.prepareStatement(selectSql);

                    java.sql.ResultSet rs = ps.executeQuery();
                    System.out.println("All Books in Library:");

                    while (rs.next()) {
                        System.out.println(
                            "ID: " + rs.getInt("ID") +
                            ", Name: " + rs.getString("NAME") +
                            ", Author: " + rs.getString("AUTHOR") +
                            ", Genre: " + rs.getString("GENRE") +
                            ", DOP: " + rs.getString("DOP")
                        );
                    }
                } else {
                    System.out.println("ERROR");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
                sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}