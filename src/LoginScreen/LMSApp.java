package LoginScreen;

import DataSaving.FileHandle;
import StudentTeacher.Student;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LMSApp {
    private static final String STUDENT_FILE = "newstudent.txt";

    
    public static void initializeFiles() {
        try {
            File file = new File(STUDENT_FILE);
            if (file.createNewFile()) {
                System.out.println("File created: " + STUDENT_FILE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void saveStudent(Student student) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE, true))) {
            writer.write(student.toFileString());
            writer.newLine();
        }
    }

    // Hash password using SHA-512 
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found.");
        }
    }

    // Get stored hashed password for a given user ID (via FileHandle)
    public static String getStoredPasswordHash(String userID) {
        try {
            FileHandle fileHandle = new FileHandle();
            return fileHandle.getPasswordHash(userID);
        } catch (IOException e) {
            System.err.println("Error reading user data: " + e.getMessage());
            return null;
        }
    }

    // Determine role based on userID length (same as authenticationLogic)
    public static String determineRole(String userID) {
        if (!userID.matches("\\d+")) return "Invalid";
        int length = userID.length();
        if (length == 5) return "Faculty";
        if (length == 10) return "Student";
        return "Invalid";
    }

    // Authenticate user credentials; return role or "Invalid"
    public static String authenticate(String enteredID, String enteredPassword) {
        String role = determineRole(enteredID);
        if (role.equals("Invalid")) return "Invalid";

        String storedHash = getStoredPasswordHash(enteredID);
        if (storedHash == null) return "Invalid";

        String enteredHash = hashPassword(enteredPassword);
        if (enteredHash.equals(storedHash)) {
            return role;
        } else {
            return "Invalid";
        }
    }
}
