package com.mycompany.projectmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/*
class Admin {

    String name;
    String pass;

    public Admin(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
}
 */
public class AddTask extends JFrame implements ActionListener {

    JTextField t1, taskIdField, todoField, statusField, assigntoField;
    JPasswordField adminPasswordField;
    JLabel label;
    JButton login, back;
    private String filePath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\task.txt";
    JFrame frame = new JFrame("Add Task Page");

    public AddTask() {

        //super("");
        JLabel addTaskTitle = new JLabel("Add New Task");
        addTaskTitle.setBounds(200, 10, 250, 50);
        addTaskTitle.setFont(new Font("Arial", Font.BOLD, 25));
        addTaskTitle.setForeground(Color.red);
        frame.add(addTaskTitle);

        JLabel taskId = new JLabel("Task Id:");
        taskId.setBounds(80, 80, 80, 30);
        taskId.setFont(new Font("Arial", Font.BOLD, 20));
        taskIdField = new JTextField();
        taskIdField.setBounds(200, 80, 150, 30);
        frame.add(taskId);
        frame.add(taskIdField);

        JLabel todo = new JLabel("To Do:");
        todo.setBounds(80, 150, 80, 30);
        todo.setFont(new Font("Arial", Font.BOLD, 17));
        todoField = new JTextField();
        todoField.setBounds(200, 150, 150, 30);
        frame.add(todo);
        frame.add(todoField);

        JLabel status = new JLabel("Status:");
        status.setBounds(80, 220, 80, 30);
        status.setFont(new Font("Arial", Font.BOLD, 17));
        statusField = new JTextField();
        statusField.setBounds(200, 220, 150, 30);
        frame.add(status);
        frame.add(statusField);

        JLabel assignedTo = new JLabel("Assigned To:");
        assignedTo.setBounds(80, 290, 120, 30);
        assignedTo.setFont(new Font("Arial", Font.BOLD, 17));
        assigntoField = new JTextField();
        assigntoField.setBounds(200, 290, 150, 30);
        frame.add(assignedTo);
        frame.add(assigntoField);

        login = new JButton("Submit");
        login.setBounds(230, 380, 80, 30);
        login.addActionListener(this);
        frame.add(login);

        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent x) {
        
        try {
            String taskId = taskIdField.getText();
            String todo = todoField.getText();
            String status = statusField.getText();
            String assignTo = assigntoField.getText();

            if (taskId.equals("") && todo.equals("") && status.equals("") && assignTo.equals("")) {
                new MyOptPane("Please fill up all fields");
               
            } else {
                try {
                    String line = "\n"+taskId + "," + todo + "," + status + "," + assignTo;
                    File file = new File(filePath);
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true));
                    bw.append(line);
                    bw.close();
                    

                } catch (IOException ea) {
                    System.out.println("An error occurred while logging in.");
                    ea.printStackTrace();
                }
                new MyOptPane("Successfully Added");
                
            }

        } catch (Exception ae) {
            System.out.println(ae);
        }
        //frame.dispose();
        //frame.setVisible(false);
        
    }
    

    public static void main(String[] args) {
        AddTask addTask = new AddTask();
    }
}
