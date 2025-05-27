package GradeAssign;

public class InputValidator {
    public static boolean isValidNumber(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}