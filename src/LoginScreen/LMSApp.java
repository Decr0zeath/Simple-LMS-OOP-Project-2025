package LoginScreen;

import DataSaving.FileHandle;
import StudentTeacher.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LMSApp {

    // Hash the password using SHA-256 and return hex string
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Authenticate user by checking if the ID and hashed password matches stored record
    public static String authenticate(String userId, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("newStudent.txt"))) {
            String line;
            String enteredHash = hashPassword(password);
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String storedId = parts[2].trim();
                    String storedHash = parts[3].trim();
                    if (storedId.equals(userId) && storedHash.equals(enteredHash)) {
                        return "Valid";
                    }
                }
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "Invalid";
    }

    // Save a student with hashed password (used during registration)
    public static void saveStudentWithHashedPassword(Student student) {
        FileHandle fileHandle = new FileHandle();
        fileHandle.saveStudentWithHashedPassword(student);
    }
}
