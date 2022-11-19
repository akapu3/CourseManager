package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.Account;
import model.Course;


public class DisplayCourse implements ActionListener {
    ArrayList<JLabel> courses;
    JFrame frame;
    JPanel panel;
    JButton back;


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public DisplayCourse(Account current) {
        courses = new ArrayList<>();
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        ArrayList<Course> course = current.getCourses();

        int y = 40;
        for (int i = 0; i < course.size(); i++) {
            JLabel temp = new JLabel(course.get(i).getName());
            temp.setBounds(10,y,200,25);
            panel.add(temp);
            courses.add(temp);
            y += 30;
        }

        back = new JButton("Back");
        back.setBounds(10,110,80,25);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MainPage(current);
            }
        });

        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
