package gui;

import model.Account;
import model.Course;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/*
Represents the main page window
 */

public class MainPage {

    JFrame frame;
    JPanel panel;
    JLabel labelWelcome;

    JButton displayList;
    JButton addCourse;
    JButton removeCourse;
    JButton save;
    JButton eventLog;

    Account current;

    private static final String JSON_STORE = "./data/account.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);


    private void setUpFrame() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
    }

    //Requires: account object
    //Modifies: current
    //Effect: Displays main page
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MainPage(Account current) {
        this.current =  current;
        setUpFrame();
        labelWelcome = new JLabel("Welcome!");
        labelWelcome.setBounds(135,10, 80,30);
        panel.add(labelWelcome);

        displayList = new JButton("Display List of Courses");
        displayList.setBounds(10,40,200,25);
        panel.add(displayList);
        displayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new DisplayCourse(current);
            }
        });

        addCourse = new JButton("Add Course");
        addCourse.setBounds(10,70,200,25);
        panel.add(addCourse);
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCourse(current);
            }
        });

        removeCourse = new JButton("Remove Course");
        removeCourse.setBounds(10,100,200,25);
        panel.add(removeCourse);
        removeCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveCourse(current);
            }
        });

        save = new JButton("Save");
        save.setBounds(10,130,200,25);
        panel.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // EventLog.getInstance().logEvent(new Event("Information Saved"));
                saveAccount();
            }
        });

        eventLog = new JButton("Exit");
        eventLog.setBounds(10,160,200,25);
        panel.add(eventLog);
        eventLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Event x : EventLog.getInstance()) {
                    System.out.println(x.toString());
                }
                System.exit(0);
            }
        });


        frame.setVisible(true);
    }

    //Effect: Saves contents of current into json file
    private void saveAccount() {
        try {
            jsonWriter.open();
            jsonWriter.write(current);
            jsonWriter.close();
            //System.out.println("Saved " + current.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


}
