package classProject;
import java.io.File;
import java.io.IOException; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

 
public class FileHandle {
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
	public void saveStudent(Student student) throws IOException {
        try{
            FileWriter writer = new FileWriter("newStudent.txt", true); 
		    writer.write(student.toFileString() + "\n");
		    writer.close();
        } catch(IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }		
	}
    //Function nga istore sa file ang infos sa teacher (First Name, Last Name, Account ID, Password), append mode ni sya
    public void saveTeacher(TeacherInfo teacher) throws IOException {
        try{
            FileWriter writer = new FileWriter("newTeacher.txt", true); 
            writer.write(teacher.toFileString() + "\n");
            writer.close();
        } catch(IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }	
    //Gitake ang info sa teacher and gigamit ang saveTeacher function para ma store sya sa file.
    final void StudentInput(){
        try{
            Student student1 = new Student("John", "Doe", "T001", "password123", "BSIT", 2); 
                saveStudent(student1);
            Student student2 = new Student("Jane", "Smith", "T002", "password456", "BSCS", 3); 
                saveStudent(student2);
            Student student3 = new Student("Alice", "Johnson", "T003", "password789", "BSED", 1);
                saveStudent(student3);
            Student student4 = new Student("Bob", "Brown", "T004", "password101", "BSA", 4); 
                saveStudent(student4);
        } catch(IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }
        
    final void teacherInput(){
        try{
            TeacherInfo teacher1 = new TeacherInfo("John", "Doe", "T001", "password123"); 
               saveTeacher(teacher1);
            TeacherInfo teacher2 = new TeacherInfo("Jane", "Smith", "T002", "password456"); 
                saveTeacher(teacher2);
            TeacherInfo teacher3 = new TeacherInfo("Alice", "Johnson", "T003", "password789");
                saveTeacher(teacher3);
        } catch(IOException e) {
                System.out.println("An error has occured.");
                e.printStackTrace();
            }
    }	

    public void saveGrade(float grade, StudentInfo student ) throws IOException {
        try{
            FileWriter writer = new FileWriter("newTeacher.txt", true); 
            writer.write("Grade: " + "\n");
            writer.close();
        } catch(IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }  


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