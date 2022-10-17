package model;

import java.util.ArrayList;

public class Course {
    String name;
    String lectureType;
    ArrayList<Assignment> assignments;
    String professorName;

    public Course(String name, String lectureType, String professorName) {
        this.name = name;
        this.lectureType = lectureType;
        this.professorName = professorName;
        assignments = new ArrayList<Assignment>();
    }

    public String getName() {
        return this.name;
    }

    public String getProfessorName() {
        return this.professorName;
    }

    public String getLectureType() {
        return this.lectureType;
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public boolean addAssignment(Assignment a1) {
        return (assignments.add(a1));
    }

    public boolean removeAssignment(Assignment assignment) {
        return (assignments.remove(assignment));
    }


//    public ArrayList<Assignment>getPriorityAssignments(int time){
//        for(int i = 0; i < assignments.size(); i++){
//
//        }
//        // retrun list of assingments due within the next__
//        return null;
//    }
//


}
