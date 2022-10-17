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

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public boolean addCourse(Course course) {
        return (courses.add(course));
    }

    public boolean dropCourse(Course course) {
        return (courses.remove(course));
    }

    public String getName() {
        return this.name;
    }

    public String forgotPassword(String accountID) {
        if (this.accountID == accountID) {
            return "Your Password is: " + this.password;
        } else {
            return "AccountID not found, try again";
        }
    }

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
