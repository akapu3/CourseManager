package gui;

import model.Account;
import model.Course;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourse implements ActionListener  {

    JFrame frame;
    JPanel panel;
    JLabel labelName;
    JLabel labelProfessorName;
    JLabel labelLectureType;
    JTextField courseNameTextField;
    JTextField professorNameTextField;
    JTextField lectureTypeTextField;
    JButton submit;

    Course course;
    Account current;


    //Requires: Account object
    //Effect: Display the add course window

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public AddCourse(Account current) {
        this.current = current;
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        labelName = new JLabel("Course Name: ");
        labelName.setBounds(10,20,120,25);
        panel.add(labelName);

        courseNameTextField = new JTextField(20);
        courseNameTextField.setBounds(120,20,165,25);
        panel.add(courseNameTextField);

        labelLectureType = new JLabel("Lecture Type: ");
        labelLectureType.setBounds(10,50,120,25);
        panel.add(labelLectureType);

        lectureTypeTextField = new JTextField(20);
        lectureTypeTextField.setBounds(120,50,165,25);
        panel.add(lectureTypeTextField);

        labelProfessorName = new JLabel("Professor Name: ");
        labelProfessorName.setBounds(10,80,120,25);
        panel.add(labelProfessorName);

        professorNameTextField = new JTextField(20);
        professorNameTextField.setBounds(120,80,165,25);
        panel.add(professorNameTextField);

        submit = new JButton("Submit");
        submit.setBounds(10,110,80,25);
        panel.add(submit);
        submit.addActionListener(this);

        frame.setVisible(true);
    }

    //Requires: Action Event object
    //Modifies: current
    //Effect: adds course to current and displays main gui page

    @Override
    public void actionPerformed(ActionEvent e) {
        this.course = new Course(courseNameTextField.getText(),lectureTypeTextField.getText(),
                professorNameTextField.getText());
        current.addCourse(course);
        frame.setVisible(false);
        System.out.println(current.getCourses().size());
        new MainPage(current);
    }


    public Course getCourse() {
        return course;
    }
}
