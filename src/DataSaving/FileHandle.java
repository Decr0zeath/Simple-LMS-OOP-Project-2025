package DataSaving;

import java.io.File;
import java.io.IOException; 
import java.io.FileWriter;

 
public class FileHandle {
    //Create a file for student and teacher
	public static void fileCreate() throws IOException{
		File studentFile = new File("newStudent.txt");
		File teacherFile = new File("newTeacher.txt");
		
		if(studentFile.exists() && teacherFile.exists()) {
			System.out.println("File Name: " + studentFile.getName());
			System.out.println("File Name: " + teacherFile.getName());
		}
		else {
			if(!studentFile.exists()) {
				studentFile.createNewFile();
				System.out.println("File Name: " + studentFile.getName());
			}
			if(!teacherFile.exists()){
				teacherFile.createNewFile();
				System.out.println("File Name: " + teacherFile.getName());
			}
		}
	}
    //Function nga istore sa file ang infos sa student (First Name, Last Name, Account ID, Password Degree, Year), append mode ni sya
	public void saveStudent(StudentInfo student) throws IOException {
        try{
            FileWriter writer = new FileWriter("newStudent.txt", true); 
		    writer.write(student.toFileString() + "\n");
		    writer.close();
        } catch(IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }		
	}
	//Function nga istore sa file ang infos sa teacher together with their respective subject
    public void saveTeacherwithSubject(TeacherInfo teacher, SubjectInfo subject) throws IOException {
        try{
            FileWriter writer = new FileWriter("newTeacher.txt", true); 
            writer.write(teacher.toFileString() + ",Subject"  + subject.getCourseId() + " - " + subject.getCourseName() + "\n");
            writer.close();
        } catch(IOException e) {    
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }
    //Gitake ang info sa teacher and gigamit ang saveTeacher function para ma store sya sa file.
    final void StudentInput(){
        try{
            StudentInfo student1 = new StudentInfo("John", "Doe", "T001", "password123", "BSIT", 2); 
                saveStudent(student1);
            StudentInfo student2 = new StudentInfo("Jane", "Smith", "T002", "password456", "BSCS", 3); 
                saveStudent(student2);
            StudentInfo student3 = new StudentInfo("Alice", "Johnson", "T003", "password789", "BSED", 1);
                saveStudent(student3);
            StudentInfo student4 = new StudentInfo("Bob", "Brown", "T004", "password101", "BSA", 4); 
                saveStudent(student4);
        } catch(IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }
        
    final void teacherInput() {
        try {
            // Create teacher objects
            TeacherInfo teacher1 = new TeacherInfo("John", "Doe", "T001", "password123");
            TeacherInfo teacher2 = new TeacherInfo("Jane", "Smith", "T002", "password456");
            TeacherInfo teacher3 = new TeacherInfo("Alice", "Johnson", "T003", "password789");

            // Assign subjects to teachers
            SubjectInfo subject1 = new SubjectInfo("SCS101", "Programming 1", teacher1, teacher2);
            SubjectInfo subject2 = new SubjectInfo("SCS102", "Web Development", teacher2, teacher3);
            SubjectInfo subject3 = new SubjectInfo("SCS103", "Digital Visual Arts", teacher1, teacher3);
            SubjectInfo subject4 = new SubjectInfo("SCS104", "Introduction to Cyber Security", teacher3);
            SubjectInfo subject5 = new SubjectInfo("SCS105", "Data Structures and Algorithms", teacher1, teacher2);

            // Save subject info to teacher file
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
    //Function nga ma save sa student file (assignment nga gisubmit, info sa student and info sa assignment, og asa nga subject)
    public void submissionFunc(assignmentInfo assignment, StudentInfo student, SubjectInfo subject) throws IOException {
    try {
        // Append the assignment submission to the student file
        FileWriter studentWriter = new FileWriter("newStudent.txt", true);
        studentWriter.write("Assignment Submitted: " + assignment.toFileString() + "\n"
                + "By: " + student.getFirstName() + " " + student.getLastName() + "\n"
                + "Subject: " + subject.getCourseId() + " - " + subject.getCourseName() + "\n\n");
        studentWriter.close();

        // Append the student's name and subject info to the teacher file
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


    // public void saveGrade(float grade, StudentInfo student, TeacherInfo teacher ) throws IOException {
    //     try{
    //         FileWriter writer = new FileWriter("newTeacher.txt", true); 
    //         writer.write("Grade: " + "\n");
    //         writer.close();
    //     } catch(IOException e) {
    //         System.out.println("An error has occured.");
    //         e.printStackTrace();
    //     }
    // }  


	public static void main(String[] args){
		try {
			fileCreate();
            FileHandle trial = new FileHandle();
            trial.StudentInput();
            trial.teacherInput();
		} catch(IOException e) {
			System.out.println("An error has occured.");
			e.printStackTrace();
		}
	}
	
}