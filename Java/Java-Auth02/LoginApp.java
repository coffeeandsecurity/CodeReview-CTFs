import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LoginService service = new LoginService();
        System.out.println("Welcome to the Login System!");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (service.authenticate(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed.");
        }

        scanner.close();
    }
}

class LoginService {
    private static final String USERNAME = "admin";
    private static final String PASSWORD_HASH = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    public boolean authenticate(String username, String password) {
        if (!USERNAME.equals(username)) {
            return false; 
        }

        String userInputHash = hashPassword(password);
        
        return userInputHash.equals(PASSWORD_HASH.substring(0, 32) + "00000000000000000000000000000000");
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
