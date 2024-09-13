package com.mycompany.projectmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Admin {

    String name;
    String pass;

    public Admin(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
}

public class AdminLoginPage extends JFrame implements ActionListener {
   
    JTextField t1, adminTextField;
    JPasswordField adminPasswordField;
    JLabel label;
    JButton login, back;
    private String adminFilePath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\admin.txt";
    Admin[] adminList = new Admin[99999];
    
    public AdminLoginPage() {
        super("Admin Login Page");

        JLabel title = new JLabel("Project Management System");
        title.setBounds(400, 80, 1920, 100); // Increased width for visibility
        title.setFont(new Font("Arial", Font.BOLD, 50)); // Decreased font size for visibility
        title.setForeground(Color.blue);
        add(title);

        JLabel adminLoginTitle = new JLabel("Team Leader Login");
        adminLoginTitle.setBounds(550, 180, 1920, 100);
        adminLoginTitle.setFont(new Font("Arial", Font.BOLD, 40));
        adminLoginTitle.setForeground(Color.red);
        add(adminLoginTitle);

        JLabel username = new JLabel("Username");
        username.setBounds(600, 300, 100, 30);
        username.setFont(new Font("Arial", Font.BOLD, 20));
        adminTextField = new JTextField();
        adminTextField.setBounds(700, 300, 150, 30);

        JLabel userpassword = new JLabel("Password");
        userpassword.setBounds(600, 350, 100, 30);
        userpassword.setFont(new Font("Arial", Font.BOLD, 20));
        adminPasswordField = new JPasswordField();
        adminPasswordField.setBounds(700, 350, 150, 30);

        label = new JLabel();
        label.setBounds(640, 410, 200, 30);

        login = new JButton("Login");
        login.setBounds(750, 500, 100, 30);
        login.addActionListener(this);
        
        
        
        
        back = new JButton("<<Back");
        back.setBounds(610, 500, 100, 30);
        back.addActionListener(this);

        add(username);
        add(adminTextField);
        add(userpassword);
        add(adminPasswordField);
        add(label);
        add(login);
        add(back);

        setSize(1920, 1024);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Show confirmation dialog
                int response = JOptionPane.showConfirmDialog(
                    AdminLoginPage.this,
                    "Do you really want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                // If user selects "Yes", exit the application
                if (response == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent x) {
       
        try {
            String userName = adminTextField.getText();
            String password = new String(adminPasswordField.getPassword());
            Scanner sc = null;

            
            try (BufferedReader reader = new BufferedReader(new FileReader(adminFilePath))) {
                String line;
                boolean loginSuccessful = false;
                int count = 0;
                while ((line = reader.readLine()) != null) {
                    String[] credentials = line.split(":");
                    adminList[count] = new Admin(credentials[0], credentials[1]);
                    count++;
                    //System.out.println(credentials[0] + "\n" + credentials[1]);
                    if (credentials[0].equals(userName) && credentials[1].equals(password)) {
                        loginSuccessful = true;
                        break;
                    }
                }
                if (loginSuccessful) {
                    System.out.println("Login successful!");
                    setVisible(false);
                    try {
                        Task viewTask = new Task();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AdminLoginPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    //System.out.println("Invalid username or password.");
                    label.setText("Invalid username or password.");
                }

                
            } catch (IOException ea) {
                System.out.println("An error occurred while logging in.");
                ea.printStackTrace();
            }
            if (x.getSource() == back) {
                setVisible(false);
                LoginPage loginPage = new LoginPage();
            }
            
        } catch (Exception ae) {
            System.out.println(ae);
        }
    }

    public static void main(String[] args) {
        AdminLoginPage adminloginPage = new AdminLoginPage();
    }
}
