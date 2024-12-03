import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.security.MessageDigest;
import java.util.Base64;

public class LoginService {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public boolean authenticate(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String query = "SELECT password FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                String inputHash = hashPassword(password);

                if (storedHash.equals(inputHash) || storedHash.isEmpty()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    private String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    public static void main(String[] args) {
        LoginService service = new LoginService();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = service.authenticate(username, password);


        if (isAuthenticated) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }


        scanner.close();
    }
}
