package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.spec.ECField;

import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import model.Account;

/*
represents the login page window
 */

@SuppressWarnings({"checkstyle:TypeName", "checkstyle:SuppressWarnings"})
public class LoginPage extends Canvas implements ActionListener {


    private Account current;
    private static final String JSON_STORE = "./data/account.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);


    private JFrame frame;
    private JPanel panel;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JLabel labelName;
    private JTextField textFieldUsername;
    private JPasswordField textFieldPassword;
    private JTextField textFieldName;

    private JButton button;
    private JButton createAccount;

    private String username;
    private String password;
    private String name;

    //Effect: Displays login page
    @SuppressWarnings("checkstyle:MethodLength")
    public LoginPage() throws IOException {
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);


        labelUsername = new JLabel("User");
        labelUsername.setBounds(10,50,80,25);
        panel.add(labelUsername);

        textFieldUsername = new JTextField(20);
        textFieldUsername.setBounds(100,50,165,25);
        panel.add(textFieldUsername);


        labelPassword = new JLabel("Password");
        labelPassword.setBounds(10,80,80,25);
        panel.add(labelPassword);

        textFieldPassword = new JPasswordField(20);
        textFieldPassword.setBounds(100,80,165,25);
        panel.add(textFieldPassword);

        labelName = new JLabel("Name: ");
        labelName.setBounds(10,20,80,25);
        panel.add(labelName);

        textFieldName = new JTextField(20);
        textFieldName.setBounds(100,20,165,25);
        panel.add(textFieldName);


        button = new JButton("Login");
        button.setBounds(10,110,80,25);
        panel.add(button);
        button.addActionListener(this);//

        createAccount = new JButton("Create Account");
        createAccount.setBounds(10,140,140,25);
        panel.add(createAccount);
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                current = new Account(textFieldName.getText(),textFieldUsername.getText(),textFieldPassword.getText());
                new MainPage(current);
            }
        });
        frame.setVisible(true);
    }


    //Requires: ActionEvent object
    //Effect: displays main page if login info is correct otherwise displays image
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        this.username = textFieldUsername.getText();
        this.password = textFieldPassword.getText();
        this.name = textFieldName.getText();
        loadAccount();
        Account temp = new Account(name, username,password);
        if (current.equals(temp)) {
            new MainPage(this.current);
        } else {
            try {
                new Image();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    //Modifies: current
    //Effect: loads the data from json file into current

    private void loadAccount() {
        try {
            current = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
