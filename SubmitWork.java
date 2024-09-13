
package com.mycompany.projectmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import static java.awt.PageAttributes.MediaType.C;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
    
public class SubmitWork extends JFrame implements ActionListener {

    JTextField  taskIdField;
    JLabel chooseFileField;
    JButton submit, chooseFile;
    String taskId, username;
    private String doneWorkPath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\doneWorks\\";
    private String taskfilePath = "C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\task.txt";
    JFrame frame = new JFrame("Submit Work Page");

    public SubmitWork(String username) {
        this.username = username;
        //super("");
        JLabel addTaskTitle = new JLabel("Submit Your Work");
        addTaskTitle.setBounds(170, 10, 250, 50);
        addTaskTitle.setFont(new Font("Arial", Font.BOLD, 25));
        addTaskTitle.setForeground(Color.red);
        frame.add(addTaskTitle);

        chooseFile = new JButton("Choose File");
        chooseFile.setBounds(60, 100, 130, 30);
        chooseFile.setFont(new Font("Arial", Font.BOLD, 15));
        chooseFileField = new JLabel();
        chooseFileField.setBounds(200, 100, 250, 30);
        chooseFileField.setFont(new Font("Arial", Font.BOLD, 10));
        frame.add(chooseFile);
        frame.add(chooseFileField);
        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //frame.dispose();
                    uploadFile();
                } catch (IOException ex) {
                    Logger.getLogger(SubmitWork.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
         

        JLabel taskId = new JLabel("Task Id:");
        taskId.setBounds(120, 180, 80, 30);
        taskId.setFont(new Font("Arial", Font.BOLD, 17));
        taskIdField = new JTextField();
        taskIdField.setBounds(200, 180, 200, 30);
        frame.add(taskId);
        frame.add(taskIdField);
        
        
        submit = new JButton("Submit");
        submit.setBounds(230, 300, 80, 30);
        submit.addActionListener(this);
        frame.add(submit);


        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent x) {
        
        try {
            taskId = taskIdField.getText();

            if (taskId.equals("") && this.chooseFileField.getText().equals("")) {
                new MyOptPane("Please fill up all fields");
               
            } else {
                try {
                        String updated = updateTaskStatus(taskId);
                        BufferedWriter bw = new BufferedWriter(new FileWriter(taskfilePath));
                        bw.write(updated);
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
    
    public String updateTaskStatus(String taskId) throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\task.txt");
        Scanner sc = new Scanner(file);
        String overWriteTaskFile = "";
        ArrayList<TaskInfo> taskInfoList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] user = line.split(",");
            if(user[0].equals(taskId)){
                user[2] = "Completed";
            }
            TaskInfo taskInfo = new TaskInfo(user[0], user[1], user[2], user[3]);
            taskInfoList.add(taskInfo);
        } 
        // Convert to a 2D array
        //Object[][] taskInfoArray = new Object[taskInfoList.size()][4];
        for (int i = 0; i < taskInfoList.size(); i++) {
            String tempId, tempTodo, tempStatus, tempAssignedTo;
            tempId = taskInfoList.get(i).id;
            tempTodo = taskInfoList.get(i).to_do;
            tempStatus = taskInfoList.get(i).status;
            tempAssignedTo = taskInfoList.get(i).assigned;
            
            overWriteTaskFile = overWriteTaskFile + tempId +","+ tempTodo +","+ tempStatus +","+ tempAssignedTo +"\n";
        }
        // Close the scanner
        sc.close();
        return overWriteTaskFile;
    }
    
    public void uploadFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
       // fileChooser.setCurrentDirectory(new File(C:\\Users\\ASUS\\Desktop));
        int result = fileChooser.showOpenDialog(null);
        
        // Check if the user selected a file
        if (result == JFileChooser.APPROVE_OPTION) {
            File sourceFile = fileChooser.getSelectedFile();
            String absolutePath = sourceFile.getAbsolutePath();
            String fileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator) + 1);
            //System.out.println("Selected file: " + fileName);
            this.chooseFileField.setText(fileName);
            
            String newPath = "C:" + File.separator + "Users" + File.separator + "ASUS" + File.separator + "Documents" + File.separator + "NetBeansProjects" + File.separator + "ProjectManagementSystem" + File.separator + "src" + File.separator + "doneWorks" + File.separator + this.username + File.separator;
            File destinationDirectory = new File(newPath);
            
            // Create directories if they do not exist
            if (!destinationDirectory.exists()) {
                destinationDirectory.mkdirs();
            }
            
            File destinationFile = new File(newPath + fileName);
            
            try {
                // Copy file to the destination directory
                Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
                
                System.out.println("File copied to: " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error copying file: " + e.getMessage());
            }
        } else {
            System.out.println("File selection cancelled.");
        }
    }


    public static void main(String[] args) {
        SubmitWork submitWork = new SubmitWork("");
    }
}
