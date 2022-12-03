package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/*
    Represent an Account in Course manager
 */

public class Account {

    private String accountID;
    private String name;
    private String password;
    private ArrayList<Course> courses;

    public Account(String name, String accountID, String password) {
        this.name = name;
        this.accountID = accountID;
        this.password = password;
        courses = new ArrayList<Course>();
    }

    //Effect: returns the list of course associated with this account
    public ArrayList<Course> getCourses() {
        return courses;
    }

    //Modifies: this
    //Effect: Adds a course to the courses array and returns true if it is able to do it successfully
    public boolean addCourse(Course course) {
        EventLog.getInstance().logEvent(new Event("new Course Added"));
        courses.add(course);
        return true;
    }

    //Effect:returns list of courses when the UI needs to display list of courses
    public ArrayList<Course> displayListofCourses() {
        EventLog.getInstance().logEvent(new Event("Course List Displayed"));
        return getCourses();
    }

    //Requires: Course
    //Modfies: this
    //Effect: removes the given course from the courses arraylist
    public boolean dropCourse(Course course) {
        EventLog.getInstance().logEvent(new Event("Course Dropped"));
        return (courses.remove(course));
    }

    //Effect: returns the name of the account
    public String getName() {
        return this.name;
    }

    //Requires: A string
    //Effect: returns the password id the correct accountID is inputed
    public String forgotPassword(String accountID) {
        if (this.accountID == accountID) {
            return "Your Password is: " + this.password;
        } else {
            return "AccountID not found, try again";
        }
    }

    //Requires: An account
    //Effect: checks if a given account is equal to this account
    public boolean equals(Account a) {
        String name = a.name;
        String accountID = a.accountID;
        String password = a.password;
        if (name.equals(this.name)) {
            if (accountID.equals(this.accountID)) {
                if (password.equals(this.password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("name: ", this.name);
        json.put("accountID: ", this.accountID);
        json.put("password: ", this.password);
        json.put("courses: ", coursesToJson());

        return json;
    }

    public JSONArray coursesToJson() {
        JSONArray json = new JSONArray();
        for (Course x : this.courses) {
            json.put(x.toJson());
        }
        return json;
    }

}
