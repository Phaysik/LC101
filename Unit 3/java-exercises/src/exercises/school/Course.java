package exercises.school;

import java.util.ArrayList;

public class Course {

    private String courseName;
    private ArrayList<Student> studentsEnrolled = new ArrayList<>();
    private int creditsPerSemester;
    private int semesterLength;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    public void setStudentsEnrolled(ArrayList<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public int getCreditsPerSemester() {
        return creditsPerSemester;
    }

    public void setCreditsPerSemester(int creditsPerSemester) {
        this.creditsPerSemester = creditsPerSemester;
    }

    public int getSemesterLength() {
        return semesterLength;
    }

    public void setSemesterLength(int semesterLength) {
        this.semesterLength = semesterLength;
    }
}
