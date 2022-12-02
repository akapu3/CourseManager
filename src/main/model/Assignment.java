package model;

import org.json.JSONArray;

import org.json.JSONObject;

public class Assignment extends Observable {
    private String course;
    private int dueDate;
    private String name;

    public Assignment(String name, String course, int dueDate) {
        this.name = name;
        this.course = course;
        this.dueDate = dueDate;
    }

    public String getCourse() {
        return course;
    }

    public int getDueDate() {
        return dueDate;
    }

    public String getName() {
        return name;
    }

    //Requires: an integer
    //Modifies: this
    //Effect: changes the due date to the given due date;
    public void changeDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("course: ", this.course);
        json.put("name : ", this.name);
        json.put("duedate: ", this.dueDate);

        return json;
    }

    @Override
    public boolean equals(Object assignment) {
        Assignment a1 = (Assignment) assignment;
        if (a1.getName().equals(this.getName())) {
            if (a1.getCourse().equals(this.getCourse())) {
                return (a1.getDueDate() == this.getDueDate());
            }
        }
        return false;
    }


    //COME BACK TO THIS
    @Override
    public void notifyObservers() {

    }
}
