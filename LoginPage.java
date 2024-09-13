
package com.mycompany.projectmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.lang.*;

class User {

    String name;
    String pass;

    User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
}

public class LoginPage extends JFrame implements ActionListener{
    JLabel appName, appNameDescription, bgImage, errorMsg, label1;
    JButton login, back, adminLogin, teamLeaderLogin; 
    JTextField username;
    JPasswordField userpass;
    User[] userlist = new User[99999];
    
    private String filePath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\Users.txt";
    LoginPage(){
        super("Login Page");
        
        appName = new JLabel("Drello");
        appName.setFont(new Font("Arial", Font.BOLD, 50));
        appName.setBounds(700, 20, 150, 70);
        
        appNameDescription = new JLabel("Project Management System");
        appNameDescription.setFont(new Font("Arial", Font.BOLD, 20));
        appNameDescription.setBounds(650, 100, 350, 30);
        
        ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\main\\java\\com\\mycompany\\projectmanagementsystem\\images\\loginPageBg.png");
        bgImage = new JLabel("",image,JLabel.CENTER);
        bgImage.setBounds(0, 0, 1540, 881);
        
        JLabel n = new JLabel(" PLEASE LOGIN HERE ");
        n.setFont(new Font("Arial", Font.BOLD, 20));
        n.setBounds(245, 210, 320, 20);
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        usernameLabel.setBounds(200, 268, 150, 20);
        username = new JTextField();
        username.setBounds(290, 265, 230, 30);
        
        JLabel userpassLabel = new JLabel("Password:");
        userpassLabel.setFont(new Font("Arial", Font.BOLD, 15));
        userpassLabel.setBounds(200, 324, 150, 20);
        userpass = new JPasswordField();
        userpass.setBounds(290, 320, 230, 30);
        
        label1 = new JLabel();
        label1.setBounds(250, 370, 250, 20);
        label1.setFont(new Font("Arial", Font.BOLD, 15));
        
        login = new JButton("Log In");
        login.setFont(new Font("Arial", Font.BOLD, 20));
        login.setBounds(300, 450, 120, 40);
        login.addActionListener(this);
        
        errorMsg = new JLabel();
        errorMsg.setFont(new Font("Arial", Font.BOLD, 20));
        errorMsg.setBounds(245, 470, 320, 20);
        
        adminLogin = new JButton("Team Leader Login");
        adminLogin.setFont(new Font("Arial", Font.BOLD, 15));
        adminLogin.setBounds(280, 650, 200, 40);
        adminLogin.addActionListener(this);
        
       
        
        add(appName);
        add(appNameDescription);
        add(n);
        add(usernameLabel);
        add(username);
        add(userpassLabel);
        add(userpass);
        add(label1);
        add(login);
        
        add(adminLogin);
       // add(teamLeaderLogin);
        add(bgImage);
        
        
        setSize(1540, 881);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Show confirmation dialog
                int response = JOptionPane.showConfirmDialog(
                    LoginPage.this,
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
    public void actionPerformed(ActionEvent e) {
        
            String userName = username.getText();
            String userPass = new String (userpass.getPassword());
            Scanner input = null;

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean loginSuccessful = false;
                int count=0;
                while ((line = reader.readLine()) != null) {
                    String[] credentials = line.split(",");
                    userlist[count] = new User(credentials[0], credentials[1]);
                    count++;
                    //System.out.println(credentials[0]+"\n"+credentials[1]);
                    if (credentials[0].equals(userName) && credentials[1].equals(userPass)) {
                        loginSuccessful = true;
                        break;
                    }
                }

                if (loginSuccessful) {
                    System.out.println("Login successful!");
                    setVisible(false);
                    new UserDashboard(userName);
                } else {
                    //System.out.println("Invalid username or password.");
                    //new MyOptPane("Invalid username or password.");
                    this.label1.setText("Invalid username or password.");
                }
            } catch (IOException ea) {
                System.out.println("An error occurred while logging in.");
                ea.printStackTrace();
            }
            if (e.getSource() == adminLogin) {
                setVisible(false);
                new AdminLoginPage();
                
            }
             
    }
    public static void main(String[] args) {
        LoginPage loginpage= new LoginPage();
    } 
}
