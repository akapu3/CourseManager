package ui;

import model.Account;
import model.Assignment;
import model.Course;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseManager {

    ArrayList<Account> accounts;
    Account current;


    public CourseManager() {
        accounts = new ArrayList<>();
        addPreExistingCourses();
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


    public void runApp() {
        login();
    }

    //Login

    public void displayLoginMenu() {
        System.out.println("Select from: ");
        System.out.println("Create new Account: CA");
        System.out.println("Log In: LI");
        System.out.println("Exit: E");
    }
    
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public boolean login() {
        displayLoginMenu();
        Scanner in = new Scanner(System.in);
        String input = in.next();
        if (input.equals("CA")) { //look into replacing with switch statement
            Account a10 = creatingAccountFromUserInput();
            accounts.add(a10);
            this.current = a10;
            menu();
            return true;
        } else if (input.equals("LI")) {
            Account a1 = creatingAccountFromUserInput();
            boolean checker = false;
            for (int i = 0; i < accounts.size();i++) {
                if (accounts.get(i).equalsAccount(a1)) {
                    checker = true;
                }
            }
            if (checker) {
                this.current = a1;
                menu();
            } else {
                System.out.println("Invalid details");
                return false;
            }
        } else if (input.equals("E")) {
            System.out.println("Have a good day!");
            return true;
        } else {
            System.out.println("Invalid Input");
            return false;
        }
        return true;
    }


    //Menu
    public void displayMenu() {
        System.out.println("Select from:");
        System.out.println("Display List of Courses: DLC");
        System.out.println("Add Course: AC");
        System.out.println("Remove Course: RC");
        System.out.println("Access Course: ACC");
        System.out.println("Exit: E");
    }

    public void menu() {
        displayMenu();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("Exit")) {
            if (input.equals("DLC")) {
                ArrayList<Course> c1 = current.getCourses();
                for (Course o : c1) {
                    System.out.println(o);
                }
            } else if (input.equals("AC")) {
                current.addCourse(creatingCourseFromUserInput());
            } else if (input.equals("RC")) {
                Course c1 = creatingCourseFromUserInput();
                if (current.getCourses().contains(c1)) {
                    current.dropCourse(c1);
                } else {
                    System.out.println("Course not found");
                }
            } else if (input.equals("ACC")) {
                menuCourses();
            }
        }
        System.out.println("Have a good day!");
    }




    //Display methods



    public void displayMenuCourses() {
        System.out.println("Select from:");
        System.out.println("Display list of assignments: DLA");
        System.out.println("Add Assignment: AA");
        System.out.println("Remove Assignment: RA");
        System.out.println("Exit: E");
    }

    public void menuCourses() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Course currentCourse = creatingCourseFromUserInput();
        if (!current.getCourses().contains(currentCourse)) {
            System.out.println("Invalid course");
        } else {
            displayMenuCourses();
            input = in.next();
            if (input.equals("DLA")) {
                ArrayList<Assignment> temp = currentCourse.getAssignments();
                for (Assignment assignment : temp) {
                    System.out.println(assignment);
                }
            } else if (input.equals("AA")) {
                currentCourse.addAssignment(creatingAssignmetFromUserInput());
            } else if (input.equals("RM")) {
                //Add checker for if assignment is present
                currentCourse.removeAssignment(creatingAssignmetFromUserInput());
            } else if (input.equals("Exit")) {
                System.out.println("Have a good day!");
                return;
            }
        }
    }


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

    public Course creatingCourseFromUserInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter name of course: ");
        String name = in.next();
        System.out.print("Enter type of lecture: ");
        String lectureType = in.next();
        System.out.print("Enter Professor's name: ");
        String professorName = in.next();
        Course c1 = new Course(name, lectureType, professorName);
        in.close();
        return c1;
    }

    public Assignment creatingAssignmetFromUserInput() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.print("Enter name of Assignment: ");
        String name = in.next();
        System.out.print("Enter name of Course: ");
        String nameOfCourse = in.next();
        System.out.print("Enter Due Date: ");
        int dueDate = in.nextInt();
        in.close();
        return new Assignment(name, nameOfCourse, dueDate);
    }

}


