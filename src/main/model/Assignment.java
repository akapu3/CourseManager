package model;

public class Assignment {
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
}
