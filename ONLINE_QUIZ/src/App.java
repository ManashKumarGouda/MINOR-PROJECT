import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/onlinequiz";
        String user = "root";
        String pass = "manash2006";

        System.out.println("WELCOME TO ONLINE QUIZ QUESTION BANK");
        System.out.println("1. ADD NEW QUESTION");
        System.out.println("2. UPDATE QUESTION");
        System.out.println("3. DELETE QUESTION");
        System.out.println("4. READ ALL QUESTIONS");
        System.out.println("5. EXIT !");

        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER THE OPTION :");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    
                    System.out.println("Enter the question :");
                    String Question =sc.nextLine();
                    System.out.println("Enter the option A :");
                    String A = sc.nextLine();
                    System.out.println("Enter the option B :");
                    String B= sc.nextLine();
                    System.out.println("Enter the option C :");
                    String C = sc.nextLine();
                    System.out.println("Enter the option D :");
                    String D = sc.nextLine();
                    System.out.println("Enter the correct answer :");
                    String Answer = sc.nextLine();
                    String sql = "INSERT INTO ONLINE_QUIZ (Question,A,B,C,D,Answer) VALUES (?,?,?,?,?,?)";
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, Question);
                        ps.setString(2, A);
                        ps.setString(3, B);
                        ps.setString(4, C);
                        ps.setString(5, D);
                        ps.setString(6, Answer);
                        int rows = ps.executeUpdate();
                        System.out.println(rows + " record inserted");
                        
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        sc.close();
        }
                    
                    break;
                case 2:
                    System.out.print("Enter QUESTION id to update: ");
                    int SlNo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new QUESTION name: ");
                    String Question1 = sc.nextLine();
                    System.out.print("Enter new OPTION A: ");
                    String A1 = sc.nextLine();
                    System.out.print("Enter new OPTION B: ");
                    String B1 = sc.nextLine();
                    System.out.print("Enter new OPTION C: ");
                    String C1 = sc.nextLine();
                    System.out.print("Enter new OPTION D: ");
                    String D1 = sc.nextLine();
                    System.out.print("Enter new CORRECT ANSWER: ");
                    String Answer1 = sc.next();
                    String updateSql = "UPDATE ONLINE_QUIZ SET Question = ?, A = ?, B = ?, C = ?, D = ?, Answer = ? WHERE SlNo = ?";
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(updateSql);
                        ps.setString(1, Question1);
                        ps.setString(2, A1);
                        ps.setString(3, B1);
                        ps.setString(4, C1);
                        ps.setString(5, D1);
                        ps.setString(6, Answer1);
                        ps.setInt(7, SlNo);
                        int rows = ps.executeUpdate();
                        System.out.println(rows + " record updated");
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.print("Enter QUESTION id to delete : ");
                    int id = sc.nextInt();
                    String deleteSql = "DELETE FROM ONLINE_QUIZ WHERE SlNo = ?";
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(deleteSql);
                        ps.setInt(1, id);
                        int rows = ps.executeUpdate();
                        System.out.println(rows + " record deleted");
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    String selectSql = "SELECT SlNo, Question, A, B, C, D, Answer FROM ONLINE_QUIZ ORDER BY SlNo ASC";
                    try {
                        Connection con = DriverManager.getConnection(url, user, pass);
                        PreparedStatement ps = con.prepareStatement(selectSql);
                        java.sql.ResultSet rs = ps.executeQuery();
                        System.out.println("ID\tQuestion\tA\tB\tC\tD\tAnswer");
                        while (rs.next()) {
                            int QuestionId = rs.getInt("SlNo");
                            String Question2= rs.getString("Question");
                            String A2= rs.getString("A");
                            String B2= rs.getString("B");
                            String C2= rs.getString("C");
                            String D2= rs.getString("D");
                            String Answer2 = rs.getString("Answer");
                            System.out.println(QuestionId + "\t" + Question2 + "\t" + A2 + "\t" + B2 + "\t" + C2 + "\t" + D2 + "\t" + Answer2);
                        }
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 

                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}