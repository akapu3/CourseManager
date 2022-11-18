package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import persistence.JsonReader;
import persistence.JsonWriter;

import model.Account;


@SuppressWarnings({"checkstyle:TypeName", "checkstyle:SuppressWarnings"})
public class LoginPage implements ActionListener {


    Account current;
    private static final String JSON_STORE = "./data/account.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    JFrame frame;
    JPanel panel;
    JLabel labelUsername;
    JLabel labelPassword;
    JLabel labelName;
    JTextField textFieldUsername;
    JPasswordField textFieldPassword;
    JTextField textFieldName;

    JButton button;
    JButton createAccount;

    String username;
    String password;
    String name;


    @SuppressWarnings("checkstyle:MethodLength")
    public LoginPage() {
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);


        labelUsername = new JLabel("User");
        labelUsername.setBounds(10,20,80,25);
        panel.add(labelUsername);

        textFieldUsername = new JTextField(20);
        textFieldUsername.setBounds(100,20,165,25);
        panel.add(textFieldUsername);


        labelPassword = new JLabel("Password");
        labelPassword.setBounds(10,50,80,25);
        panel.add(labelPassword);

        textFieldPassword = new JPasswordField(20);
        textFieldPassword.setBounds(100,50,165,25);
        panel.add(textFieldPassword);

        labelName = new JLabel("Name: ");
        labelName.setBounds(10,80,80,25);
        panel.add(labelName);

        textFieldName = new JTextField(20);
        textFieldName.setBounds(100,80,165,25);
        panel.add(textFieldName);


        button = new JButton("Login");
        button.setBounds(10,110,80,25);
        panel.add(button);
        button.addActionListener(this);//

        createAccount = new JButton("Create Account");
        createAccount.setBounds(10,140,80,25);
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

    public static void main(String[] args) {
        new LoginPage();
    }

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
            //display image and prompt user to try again

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