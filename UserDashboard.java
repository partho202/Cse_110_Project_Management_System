
package com.mycompany.projectmanagementsystem;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class UserDashboard extends JFrame{
    String username;
    UserDashboard(String username) throws FileNotFoundException {
        this.username = username;
        JFrame frame = new JFrame("User Dashboard");

        JLabel title = new JLabel("Project Management System");
        title.setBounds(400, 30, 1920, 100); // Increased width for visibility
        title.setFont(new Font("Arial", Font.BOLD, 50)); // Decreased font size for visibility
       // title.setForeground(Color.blue);
        frame.add(title);

        JLabel dashboard = new JLabel("USER DASHBOARD");
        dashboard.setBounds(100, 150, 250, 40);
        dashboard.setFont(new Font("Arial", Font.BOLD, 20));
        dashboard.setForeground(Color.green);
        frame.add(dashboard);

        JLabel divider = new JLabel("-------------------------------");
        divider.setBounds(100, 170, 300, 40);
        divider.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(divider);

        JLabel name = new JLabel(username);
        name.setBounds(170, 200, 150, 40);
        name.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(name);
        
        JButton submitWork = new JButton("Submit Work");
        submitWork.setBounds(120, 300, 160, 40);
        submitWork.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(submitWork);
        submitWork.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.dispose();
                new SubmitWork(username);
            }
        });

        JButton refresh = new JButton("Refresh");
        refresh.setBounds(120, 400, 160, 40);
        refresh.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(refresh);
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //frame.dispose();
                    new UserDashboard(username);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(UserDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        JButton logout = new JButton("Logout");
        logout.setBounds(150, 700, 100, 40);
        logout.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginPage();
            }
        });

        // get book info
        Object[][] taskInfo = getTaskInfo();
        String[] columnNames1 = {"Id","To Do", "Status","Assisgned To"};
        DefaultTableModel model1 = new DefaultTableModel(taskInfo, columnNames1); 
        JTable table = new JTable(model1);
        JScrollPane scrollPane1 = new JScrollPane(table);
        scrollPane1.setBounds(350, 250, 700, 300);

        frame.add(scrollPane1);

        JTextField searchField = new JTextField();
        searchField.setBounds(380, 200, 410, 40);
        searchField.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(searchField);

        JButton search = new JButton("Search");
        search.setBounds(840, 200, 180, 40);
        search.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(search);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchValue = searchField.getText();
                if(searchValue.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Please enter a value to search");
                }else{
                    // Search the table
                    for (int row = 0; row < table.getRowCount(); row++) {
                        for (int col = 0; col < table.getColumnCount(); col++) {
                            if (searchValue.equals(table.getValueAt(row, col))) {
                                // this will automatically set the view of the scroll in the location of the value
                                table.scrollRectToVisible(table.getCellRect(row, 0, true));
                                // this will automatically set the focus of the searched/selected row/value
                                table.setRowSelectionInterval(row, row);
                            }
                        }
                    }
                }
            }
        });



        // Frame Size
        frame.setSize(1920, 1080);
        frame.setLayout(null);
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Show confirmation dialog
                int response = JOptionPane.showConfirmDialog(
                    UserDashboard.this,
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
    public Object[][] getTaskInfo() throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\task.txt");
        Scanner sc = new Scanner(file);

        ArrayList<TaskInfo> taskInfoList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] user = line.split(",");
            TaskInfo taskInfo = new TaskInfo(user[0], user[1], user[2], user[3]);
            if(user[3].equals(this.username)){
                 taskInfoList.add(taskInfo);
            }
        } 
        // Convert to a 2D array
        Object[][] taskInfoArray = new Object[taskInfoList.size()][4];
        for (int i = 0; i < taskInfoList.size(); i++) {
            taskInfoArray[i][0] = taskInfoList.get(i).id;
            taskInfoArray[i][1] = taskInfoList.get(i).to_do;
            taskInfoArray[i][2] = taskInfoList.get(i).status;
            taskInfoArray[i][3] = taskInfoList.get(i).assigned;
        }
        // Close the scanner
        sc.close();
        return taskInfoArray;
    }

    public static void main(String[] args) {
        try {
            UserDashboard userDashboard = new UserDashboard("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




