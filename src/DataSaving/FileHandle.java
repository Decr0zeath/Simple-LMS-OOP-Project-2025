package DataSaving;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;

public class FileHandle {

    // Create a file for student and teacher
    public static void fileCreate() throws IOException {
        File studentFile = new File("newStudent.txt");
        File teacherFile = new File("newTeacher.txt");

        if (studentFile.exists() && teacherFile.exists()) {
            System.out.println("File Name: " + studentFile.getName());
            System.out.println("File Name: " + teacherFile.getName());
        } else {
            if (!studentFile.exists()) {
                studentFile.createNewFile();
                System.out.println("File Name: " + studentFile.getName());
            }
            if (!teacherFile.exists()) {
                teacherFile.createNewFile();
                System.out.println("File Name: " + teacherFile.getName());
            }
        }
    }

    public void saveStudent(StudentInfo student) throws IOException {
        try {
            FileWriter writer = new FileWriter("newStudent.txt", true);
            writer.write(student.toFileString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public void saveTeacherwithSubject(TeacherInfo teacher, SubjectInfo subject) throws IOException {
        try {
            FileWriter writer = new FileWriter("newTeacher.txt", true);
            writer.write(teacher.toFileString() + ", Subject: " + subject.getCourseId() + " - " + subject.getCourseName() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public void StudentInput() {
        try {
            saveStudent(new StudentInfo("John", "Doe", "T001", "password123", "BSIT", 2));
            saveStudent(new StudentInfo("Jane", "Smith", "T002", "password456", "BSCS", 3));
            saveStudent(new StudentInfo("Alice", "Johnson", "T003", "password789", "BSED", 1));
            saveStudent(new StudentInfo("Bob", "Brown", "T004", "password101", "BSA", 4));
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public void teacherInput() {
        try {
            TeacherInfo teacher1 = new TeacherInfo("John", "Doe", "adm01", "password123");
            TeacherInfo teacher2 = new TeacherInfo("Jane", "Smith", "adm02", "password456");
            TeacherInfo teacher3 = new TeacherInfo("Alice", "Johnson", "adm03", "password789");

            SubjectInfo subject1 = new SubjectInfo("SCS101", "Programming 1", teacher1, teacher2);
            SubjectInfo subject2 = new SubjectInfo("SCS102", "Web Development", teacher2, teacher3);
            SubjectInfo subject3 = new SubjectInfo("SCS103", "Digital Visual Arts", teacher1, teacher3);
            SubjectInfo subject4 = new SubjectInfo("SCS104", "Introduction to Cyber Security", teacher3);
            SubjectInfo subject5 = new SubjectInfo("SCS105", "Data Structures and Algorithms", teacher1, teacher2);

            FileWriter writer = new FileWriter("newTeacher.txt", true);
            writer.write(subject1.toFileString() + "\n");
            writer.write(subject2.toFileString() + "\n");
            writer.write(subject3.toFileString() + "\n");
            writer.write(subject4.toFileString() + "\n");
            writer.write(subject5.toFileString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public void saveSubmission(AssignmentInfo assignment, StudentInfo student, SubjectInfo subject) throws IOException {
        try {
            FileWriter studentWriter = new FileWriter("newStudent.txt", true);
            studentWriter.write("Assignment Submitted: " + assignment.toFileString() + "\n"
                    + "By: " + student.getFirstName() + " " + student.getLastName() + "\n"
                    + "Subject: " + subject.getCourseId() + " - " + subject.getCourseName() + "\n\n");
            studentWriter.close();

            FileWriter teacherWriter = new FileWriter("newTeacher.txt", true);
            teacherWriter.write("Assignment Submission Received from: "
                    + student.getFirstName() + " " + student.getLastName() + "\n"
                    + "For Subject: " + subject.getCourseId() + " - " + subject.getCourseName() + "\n\n");
            teacherWriter.close();

            System.out.println("Submission successfully saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the submission.");
            e.printStackTrace();
        }
    }

    public void submissionInput() {
        try {
            StudentInfo student = new StudentInfo("Ella", "Reyes", "S002", "pass456", "BSCS", 3);
            TeacherInfo teacher = new TeacherInfo("John", "Doe", "T001", "password123");
            SubjectInfo subject = new SubjectInfo("CS102", "Computer Science 102", teacher);
            AssignmentInfo assignment = new AssignmentInfo("Project 1", "Create a class system", LocalDate.of(2025, 12, 31));

            if (assignment.getTitle() == null || assignment.getDescription() == null || assignment.getDueDate() == null) {
                System.out.println("Assignment creation failed. Submission not saved.");
                return;
            }

            saveSubmission(assignment, student, subject);
        } catch (IOException e) {
            System.out.println("An error occurred during submission input.");
            e.printStackTrace();
        }
    }

    public void saveGrade(float grade, StudentInfo student, TeacherInfo teacher, SubjectInfo subject, AssignmentInfo assignment) throws IOException {
        try {
            FileWriter teacherWriter = new FileWriter("newTeacher.txt", true);
            teacherWriter.write("Graded Assignment:\n");
            teacherWriter.write("Student: " + student.getFirstName() + " " + student.getLastName() + "\n");
            teacherWriter.write("Subject: " + subject.getCourseId() + " - " + subject.getCourseName() + "\n");
            teacherWriter.write("Assignment: " + assignment.getTitle() + "\n");
            teacherWriter.write("Grade: " + grade + "\n\n");
            teacherWriter.close();

            FileWriter studentWriter = new FileWriter("newStudent.txt", true);
            studentWriter.write("Assignment: " + assignment.getTitle() + "\n");
            studentWriter.write("Subject: " + subject.getCourseId() + " - " + subject.getCourseName() + "\n");
            studentWriter.write("Grade: " + grade + "\n\n");
            studentWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the grade.");
            e.printStackTrace();
        }
    }

    public void gradeInput() {
        try {
            StudentInfo student = new StudentInfo("Ella", "Reyes", "S002", "pass456", "BSCS", 3);
            TeacherInfo teacher = new TeacherInfo("John", "Doe", "T001", "password123");
            SubjectInfo subject = new SubjectInfo("CS102", "Computer Science 102", teacher);
            AssignmentInfo assignment = new AssignmentInfo("Project 1", "Create a class system", LocalDate.of(2025, 12, 31));
            float grade = 93.5f;

            saveGrade(grade, student, teacher, subject, assignment);
        } catch (Exception e) {
            System.out.println("An error occurred during grade input.");
            e.printStackTrace();
        }
    }
    public void savePostedAssignment(TeacherInfo teacher, SubjectInfo subject, AssignmentInfo assignment) throws IOException {
        try {
            FileWriter writer = new FileWriter("newTeacher.txt", true); // append mode

            writer.write("Posted Assignment by: " + teacher.getFirstName() + " " + teacher.getLastName() + "\n");
            writer.write("Subject: " + subject.getCourseId() + " - " + subject.getCourseName() + "\n");
            writer.write("Assignment Details:\n");
            writer.write(assignment.toFileString() + "\n");
            writer.write("--------------------------------------------------\n");

            writer.close();
            System.out.println("Assignment posted successfully and saved to teacher file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the posted assignment.");
            e.printStackTrace();
        }
    }

    public void postAssignmentInput() {
        try {
            TeacherInfo teacher = new TeacherInfo("John", "Doe", "T001", "password123");
            SubjectInfo subject = new SubjectInfo("CS101", "Introduction to Computer Science", teacher);
            AssignmentInfo assignment = new AssignmentInfo("Midterm Project", "Build a simple LMS", LocalDate.of(2025, 11, 15));

            // Save the posted assignment
            savePostedAssignment(teacher, subject, assignment);
        } catch (IOException e) {
            System.out.println("An error occurred during assignment posting.");
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        try {
            fileCreate();
            FileHandle trial = new FileHandle();
            trial.StudentInput();
            trial.teacherInput();
            trial.submissionInput();
            trial.gradeInput();
            trial.postAssignmentInput();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }
}