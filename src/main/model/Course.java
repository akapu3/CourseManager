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

    //Requires: A value assignment object
    //Modifes: this
    //Effect: adds an assignment to the assignments arraylist
    public boolean addAssignment(Assignment a1) {
        return (assignments.add(a1));
    }

    //Requires: A value assignment object
    //Modifes: this
    //Effect: removes an assignment to the assignments arraylist
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

    public boolean equalsCourses(Course c1) {
        if (c1.getName().equals(getName())) {
            if (c1.getProfessorName().equals(getProfessorName())) {
                if (c1.getLectureType().equals(getLectureType())) {
                    return true;
                }
            }
        }
        return false;
    }


}
