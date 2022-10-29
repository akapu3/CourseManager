package ui;

import model.Account;
import model.Assignment;
import model.Course;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseManager {

    ArrayList<Account> accounts;
    Account current;
    private static final String JSON_STORE = "./data/account.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public CourseManager() {
        accounts = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadAccount();
        addPreExistingCourses(); //only for test purposes
        runApp();
    }

    // have to create these account as I can't implement a load feature in this phase
    Account a1 = new Account("Jake", "12345", "Hello");
    Account a2 = new Account("Gloria", "2245", "ModernFamily");
    Account a3 = new Account("Harry", "6789", "HarryPotter");

    public void addPreExistingCourses() {
        accounts.add(a1);
        accounts.add(a2);
        accounts.add(a3);
    }

    //Effect: runs the login page
    public void runApp() {
        login();
    }

    //Login
    //Effect: displays the login menu
    public void displayLoginMenu() {
        System.out.println("Select from: ");
        System.out.println("Create new Account: CA");
        System.out.println("Log In: LI");
        System.out.println("Exit: E");
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void login() {
        Scanner in = new Scanner(System.in);
        String input;
        do {
            displayLoginMenu();
            input = in.next();
            if (input.equals("CA")) { //look into replacing with switch statement
                Account a10 = creatingAccountFromUserInput();
                accounts.add(a10);
                this.current = a10;
                menu();
            } else if (input.equals("LI")) {
                Account a1 = creatingAccountFromUserInput();
                boolean checker = false;
                if (current.equals(a1)) {
                    menu();
                } else {
                    System.out.println("Invalid details");
                }
//                for (Account account : accounts) {
//                    if (account.equalsAccount(a1)) {
//                        checker = true;
//                    }
//                }
//                if (checker) {
//                    this.current = a1;
//                    menu();
//                } else {
//                    System.out.println("Invalid details");
//                }
            } else {
                System.out.println("Invalid Input");
            }
        } while (!input.equals("E"));
    }


    //Menu
    //Effect: displays courses menu
    public void displayMenu() {
        System.out.println("Select from:");
        System.out.println("Display List of Courses: DLC");
        System.out.println("Add Course: AC");
        System.out.println("Remove Course: RC");
        System.out.println("Access Course: ACC");
        System.out.println("Save: S");
        System.out.println("Exit: E");
    }

    //Requires: An instance of Course
    //Effect: compares 2 courses and returns true if they're the same
    public boolean courseCompare(Course c1) {
        ArrayList<Course> temp = current.getCourses();
        for (Course course : temp) {
            if (course.equals(c1)) {
                return true;
            }
        }
        return false;
    }

    //Modifies: this
    //Effect: Allows users to modify the courses associated with their account and access their assignments
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void menu() {
        Scanner in = new Scanner(System.in);
        String input;
        do {
            displayMenu();
            input = in.next();
            if (input.equals("DLC")) {
                System.out.println("You have these courses: ");
                for (Course o : current.getCourses()) {
                    System.out.println(o.getName());
                }
                System.out.println("_________");
            } else if (input.equals("AC")) {
                current.addCourse(creatingCourseFromUserInput());
                System.out.println("Course Added!");
            } else if (input.equals("RC")) {
                Course c1 = creatingCourseFromUserInput();
                boolean checker = courseCompare(c1);
                if (checker) {
                    current.dropCourse(c1);
                    System.out.println("Course Dropped!");
                } else {
                    System.out.println("Course not found");
                }
            } else if (input.equals("ACC")) {
                menuCourses();
            } else if (input.equals("S")) {
                saveAccount();
            }
        } while (!input.equals("E"));
    }

    //Effect: Displays the options for modifying courses
    public void displayMenuCourses() {
        System.out.println("Select from:");
        System.out.println("Display list of assignments: DLA");
        System.out.println("Add Assignment: AA");
        System.out.println("Remove Assignment: RA");
        System.out.println("Exit: E");
    }

    //Modifies: this
    //Effect: allows user to modify assignments for a given course
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void menuCourses() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Course Details:");
        Course currentCourse = creatingCourseFromUserInput();
        boolean checker = courseCompare(currentCourse);
        if (!checker) {
            System.out.println("Invalid course");
        } else {
            String input;
            do {
                displayMenuCourses();
                input = in.next();
                if (input.equals("DLA")) {
                    ArrayList<Assignment> temp = currentCourse.getAssignments();
                    for (Assignment assignment : temp) {
                        System.out.println(assignment.getName());
                    }
                } else if (input.equals("AA")) {
                    currentCourse.addAssignment(creatingAssignmentFromUserInput());
                    System.out.println("Assignment Added!");
                    System.out.println("");

                } else if (input.equals("RA")) {
                    //Add checker for if assignment is present
                    currentCourse.removeAssignment(creatingAssignmentFromUserInput());
                    System.out.println("Assignment Removed!");
                    System.out.println("");
                }
            } while (!input.equals("E"));
        }
    }


    //Effect:Gets input from user for creating account and creating an account object
    public Account creatingAccountFromUserInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = in.next();
        System.out.print("Enter AccountID: ");
        String accountID = in.next();
        System.out.print("Enter password: ");
        String password = in.next();
        return new Account(name, accountID, password);
    }

    //Effect:Gets input from user for creating a new course and returns a course object
    public Course creatingCourseFromUserInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter name of course: ");
        String name = in.next();
        System.out.print("Enter type of lecture: ");
        String lectureType = in.next();
        System.out.print("Enter Professor's name: ");
        String professorName = in.next();
        Course c1 = new Course(name, lectureType, professorName);
        return c1;
    }

    //Effect:Gets input from user for creating a new assignment course and returns an assignment object
    public Assignment creatingAssignmentFromUserInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter name of Assignment: ");
        String name = in.next();
        System.out.print("Enter name of Course: ");
        String nameOfCourse = in.next();
        System.out.print("Enter Due Date: ");
        int dueDate = in.nextInt();
        return new Assignment(name, nameOfCourse, dueDate);
    }

    private void saveAccount() {
        try {
            jsonWriter.open();
            jsonWriter.write(current);
            jsonWriter.close();
            System.out.println("Saved " + current.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadAccount() {
        try {
            current = jsonReader.read();
            System.out.println("Loaded");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}


