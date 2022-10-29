package model;

import org.json.JSONArray;
import org.json.JSONObject;

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
    //Modifies: this
    //Effect: adds an assignment to the assignments arraylist
    public boolean addAssignment(Assignment a1) {
        return (assignments.add(a1));
    }

    //Requires: A value assignment object
    //Modifies: this
    //Effect: removes an assignment to the assignments arraylist
    public boolean removeAssignment(Assignment assignment) {
        return (assignments.remove(assignment));
    }


//    public ArrayList<Assignment>getPriorityAssignments(int weeks) {
//        int timeInMilliseconds = (((((weeks * 7) * 24) * 60) * 60) * 1000);
//        //save the current date everytime program is started?
//        //change assignmnet so that the due date is entered in weeks?
//
//
//        for(int i = 0; i < assignments.size(); i++){
//
//        }
//        // return list of Assignments due within the next__
//        return null;
//    }


//    public boolean equalsCourses(Course c1) {
//        if (c1.getName().equals(getName())) {
//            if (c1.getProfessorName().equals(getProfessorName())) {
//                if (c1.getLectureType().equals(getLectureType())) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name: ", this.name);
        json.put("lectureType: ", this.lectureType);
        json.put("professorName: ", this.professorName);
        json.put("assignments: ", assignmentsToJson());

        return json;
    }

    public JSONArray assignmentsToJson() {
        JSONArray json = new JSONArray();
        for (Assignment x : this.assignments) {
            json.put(x.toJson());
        }
        return json;
    }

    @Override //Didn't check the class of input because there is no way to add anything other than a course object
    public boolean equals(Object course) {
        Course c1 = (Course)course;
        if (c1.getName().equals(getName())) {
            if (c1.getProfessorName().equals(getProfessorName())) {
                return c1.getLectureType().equals(getLectureType());
            }
        }
        return false;
    }

}
