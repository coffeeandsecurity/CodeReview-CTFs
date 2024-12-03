import java.security.MessageDigest;
import java.util.Base64;

public class PasswordHasher {
    public static void main(String[] args) throws Exception {
        System.out.println("admin: " + hashPassword("securepassword123"));
        System.out.println("user: " + hashPassword("password"));
        System.out.println("guest: " + hashPassword("guest"));
    }

    private static String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }
}
