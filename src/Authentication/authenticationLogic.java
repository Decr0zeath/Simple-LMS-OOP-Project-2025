import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.util.Scanner;
import DataSaving.FileHandle; 
import java.io.BufferedReader;
import java.io.FileReader;

public class authenticationLogic {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found", e);
        }
    }

    public static String getStoredPasswordHashFromDB(String userID) {
        try {
            FileHandle fileHandle = new FileHandle(); 
            return fileHandle.getPasswordHash(userID); 
        } catch (IOException e) {
            System.err.println("Error reading user data: " + e.getMessage());
            return null;
        }
    }

    public static String determineRole(String userID) {
        if (!userID.matches("\\d+")) return "Invalid";
        int length = userID.length();
        if (length == 5) return "Faculty";
        if (length == 10) return "Student";
        return "Invalid";
    }

    public static String authenticate(String enteredID, String enteredPassword) {
        String role = determineRole(enteredID);
        if (role.equals("Invalid")) return "Invalid";

        String storedHash = getStoredPasswordHashFromDB(enteredID);
        if (storedHash == null) return "Invalid";

        String enteredHash = hashPassword(enteredPassword);
        if (enteredHash.equals(storedHash)) {
            return role;
        } else {
            return "Invalid";
        }
    }

    public static void handleLogin(String enteredID, String enteredPassword) {
        String result = authenticate(enteredID, enteredPassword);

        switch (result) {
            case "Faculty", "Student" -> System.out.println("Login successful!");
            default -> System.out.println("Invalid ID number or password. Please try again.");
        }
    }

    public static String handleRegistrationPasswordHash(String plainPassword) {
        return hashPassword(plainPassword);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("User ID: ");
        String enteredID = scanner.nextLine().trim();

        System.out.print("Password: ");
        String enteredPassword = scanner.nextLine().trim();

        handleLogin(enteredID, enteredPassword);

        scanner.close();
    }
}
