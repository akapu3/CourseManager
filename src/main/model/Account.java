package model;

import java.util.ArrayList;

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
        return (courses.add(course));
    }

    //Requires: Course
    //Modfies: this
    //Effect: removes the given course from the courses arraylist
    public boolean dropCourse(Course course) {
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
    public boolean equalsAccount(Account a) {
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

}
