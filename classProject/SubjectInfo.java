package classProject;

public class SubjectInfo {
    private String courseID;
    private String courseName;


        public SubjectInfo(String courseID, String courseName) {
            this.courseID = courseID;
            this.courseName = courseName;
        }
        public String toFileString() {
            return courseID + "," + courseName;
        }
}
