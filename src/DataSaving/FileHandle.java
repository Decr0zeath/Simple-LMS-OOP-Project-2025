package DataSaving;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;

import javax.security.auth.Subject;

import StudentTeacher.Student;
import StudentTeacher.Teacher;
import Course.Course;
//import Assignment.Assignment;


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

    public void saveStudent(Student student) throws IOException {
        try {
            FileWriter writer = new FileWriter("newStudent.txt", true);
            writer.write(student.toFileString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public void saveTeacherwithSubject(Teacher teacher, Course subject) throws IOException {
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
            saveStudent(new Student(null, null, null, null, null, 0)); 
            // saveStudent(new StudentInfo("Jane", "Smith", "T002", "password456", "BSCS", 3));
            // saveStudent(new StudentInfo("Alice", "Johnson", "T003", "password789", "BSED", 1));
            // saveStudent(new StudentInfo("Bob", "Brown", "T004", "password101", "BSA", 4));
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }

    public void teacherInput() {
        try {
            Teacher teacher1 = new Teacher("John", "Doe", "adm01", "password123");
            Teacher teacher2 = new Teacher("Jane", "Smith", "adm02", "password456");
            Teacher teacher3 = new Teacher("Alice", "Johnson", "adm03", "password789");

            Course subject1 = new Course("SCS101", "Programming 1", teacher1, teacher2);
            Course subject2 = new Course("SCS102", "Web Development", teacher2, teacher3);
            Course subject3 = new Course("SCS103", "Digital Visual Arts", teacher1, teacher3);
            Course subject4 = new Course("SCS104", "Introduction to Cyber Security", teacher3);
            Course subject5 = new Course("SCS105", "Data Structures and Algorithms", teacher1, teacher2);

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
    //change assignmentInfo
    public void saveSubmission(AssignmentInfo assignment, Student student, Course subject) throws IOException {
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
    //assignmentInfo
    public void submissionInput() {
        try {
            Student student = new Student("Ella", "Reyes", "S002", "pass456", "BSCS", 3);
            Teacher teacher = new Teacher("John", "Doe", "T001", "password123");
            Course subject = new Course("CS102", "Computer Science 102", teacher);
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
    //assignmentinfo
    public void saveGrade(float grade, Student student, Teacher teacher, Course subject, AssignmentInfo assignment) throws IOException {
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
    //assignmentinfo
    public void gradeInput() {
        try {
            Student student = new Student("Ella", "Reyes", "S002", "pass456", "BSCS", 3);
            Teacher teacher = new Teacher("John", "Doe", "T001", "password123");
            Course subject = new Course("CS102", "Computer Science 102", teacher);
            AssignmentInfo assignment = new AssignmentInfo("Project 1", "Create a class system", LocalDate.of(2025, 12, 31));
            float grade = 93.5f;

            saveGrade(grade, student, teacher, subject, assignment);
        } catch (Exception e) {
            System.out.println("An error occurred during grade input.");
            e.printStackTrace();
        }
    }
    //assignmentInfo
    public void savePostedAssignment(Teacher teacher, Course subject, AssignmentInfo assignment) throws IOException {
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
    //assignmentInfo
    public void postAssignmentInput() {
        try {
            Teacher teacher = new Teacher("John", "Doe", "T001", "password123");
            Course subject = new Course("CS101", "Introduction to Computer Science", teacher);
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