package GradeAssign;

import java.util.HashMap;
import java.util.Map;

public class StudentGradeManager {
    private Map<String, Object[][]> studentGrades = new HashMap<>();

    public void addStudent(String name, Object[][] grades) {
        studentGrades.put(name, grades);
    }

    public Object[][] getGrades(String name) {
        return studentGrades.get(name);
    }

    public void updateGrades(String name, Object[][] newGrades) {
        studentGrades.put(name, newGrades);
    }

    public String[] getAllStudents() {
        return studentGrades.keySet().toArray(new String[0]);
    }
}