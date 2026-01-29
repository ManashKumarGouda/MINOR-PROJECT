import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/maanas";
        String user = "root";
        String pass = "manash2006";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter movie name: ");
        String MOVIE = sc.next();
        System.out.print("Enter genres: ");
        String GENRES = sc.next();
        System.out.print("Enter rating: ");
        int RATING= sc.nextInt();
        System.out.print("Enter status(watched/to watched): ");
        String STATUS = sc.next();
        String sql = "INSERT INTO MOVIE_WATCHLIST_SIMULATOR (MOVIE, GENRES, RATING, STATUS) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DriverManager.getConnection(url, user, pass);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MOVIE);
            ps.setString(2, GENRES);
            ps.setInt(3, RATING);
            ps.setString(4, STATUS);
            int rows = ps.executeUpdate();
            System.out.println(rows + " record inserted");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}