
package com.mycompany.projectmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TeamLeaders extends JFrame implements ActionListener{
    
    TeamLeaders() throws FileNotFoundException{
        JFrame frame = new JFrame("User Dashboard");
        
        JLabel appname = new JLabel("Drello");
        appname.setBounds(150, 30, 300, 100); 
        appname.setFont(new Font("Arial", Font.BOLD, 30)); 
        appname.setForeground(Color.black);
        frame.add(appname);

        JLabel title = new JLabel("Project Management System");
        title.setBounds(515, 30, 500, 100); 
        title.setFont(new Font("Arial", Font.BOLD, 30)); 
        title.setForeground(Color.red);
        //title.setForeground(Color.black);
        frame.add(title);
        
        
         JLabel dashboard = new JLabel("ADMIN DASHBOARD");
        dashboard.setBounds(100, 150, 250, 40);
        dashboard.setFont(new Font("Arial", Font.BOLD, 20));
        dashboard.setForeground(Color.red);
        frame.add(dashboard);

        JLabel divider = new JLabel("------------------------------");
        divider.setBounds(100, 170, 300, 40);
        divider.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(divider);
        
        JLabel empolyeesearch = new JLabel("Team Leaders Information");
        empolyeesearch.setBounds(600, 100, 500, 100); 
        empolyeesearch.setFont(new Font("Arial", Font.BOLD, 20)); 
        empolyeesearch.setForeground(Color.blue);
        //title.setForeground(Color.black);
        frame.add(empolyeesearch);
        
        JButton ViewUsers = new JButton("View Empolyees");
        ViewUsers.setBounds(100, 250, 200,50);
        ViewUsers.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(ViewUsers);
        ViewUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //frame.dispose();
                    new ViewUsers();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ViewUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JButton viewTeamLeaders = new JButton("View Team Leaders");
        viewTeamLeaders.setBounds(100,320, 200,50);
        viewTeamLeaders.setFont(new Font("Arial", Font.BOLD, 17));
        frame.add(viewTeamLeaders);
         viewTeamLeaders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    new TeamLeaders();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ViewUsers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        
        JButton ViewTask = new JButton("View Tasks");
        ViewTask.setBounds(100,390, 200,50);
        ViewTask.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(ViewTask);
        ViewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    new Task();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TeamLeaders.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
         JButton back = new JButton("<<Back");
        back.setBounds(150,600, 100, 40);
        back.setFont(new Font("Arial", Font.BOLD, 15));
        frame.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.dispose();
                    new Task();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ViewUsers.class.getName()).log(Level.SEVERE, null, ex);
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
                new AdminLoginPage();
            }
        });
      
        Object[][] userInfo = getTeamLeaderInfo();
        String[] columnNames1 = {"Name","Password", "Email", "Phone"};
        DefaultTableModel model1 = new DefaultTableModel(userInfo, columnNames1); 
        JTable table1 = new JTable(model1);
        JScrollPane scrollPane1 = new JScrollPane(table1);
        scrollPane1.setBounds(460, 250, 500, 400);
       
        frame.add(scrollPane1);
        
        
        JTextField searchField1 = new JTextField();
        searchField1.setBounds(500, 200, 250, 40);
        searchField1.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(searchField1);
        

        JButton search1 = new JButton("Search");
        search1.setBounds(750, 200, 150, 40);
        search1.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(search1);
        
        

        search1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchValue = searchField1.getText();
                if(searchValue.isEmpty()){
                    JOptionPane.showMessageDialog(frame, "Please enter a value to search");
                }else{
                    // Search the table
                    for (int row = 0; row < table1.getRowCount(); row++) {
                        for (int col = 0; col < table1.getColumnCount(); col++) {
                            if (searchValue.equals(table1.getValueAt(row, col))) {
                                // this will automatically set the view of the scroll in the location of the value
                                table1.scrollRectToVisible(table1.getCellRect(row, 0, true));
                                // this will automatically set the focus of the searched/selected row/value
                                table1.setRowSelectionInterval(row, row);
                            }
                        }
                    }
                }
            }
        });
        
        frame.setSize(1920, 1080);
        frame.setLayout(null);
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Show confirmation dialog
                int response = JOptionPane.showConfirmDialog(
                    TeamLeaders.this,
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
    
    public Object[][] getTeamLeaderInfo() throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\teamLeader.txt");
        Scanner sc = new Scanner(file);

        ArrayList<UserInfo> userInfoList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] user = line.split(",");
            UserInfo userInfo = new UserInfo(user[0], user[1], user[2], user[3]);
            userInfoList.add(userInfo);
        }
        // Convert to a 2D array
        Object[][] userInfoArray = new Object[userInfoList.size()][4];
        for (int i = 0; i < userInfoList.size(); i++) {
            userInfoArray[i][0] = userInfoList.get(i).name;
            userInfoArray[i][1] = userInfoList.get(i).password;
            userInfoArray[i][2] = userInfoList.get(i).email;
            userInfoArray[i][3] = userInfoList.get(i).phone;
        }
        // Close the scanner
        sc.close();
        return userInfoArray;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        TeamLeaders viewTeamLeaders = new TeamLeaders();
    }

    
}

