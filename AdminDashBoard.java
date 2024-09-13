
package com.mycompany.projectmanagementsystem;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class UserInfo {
    String id;
    String name;
    String password;
    String email;
    String phone;
    UserInfo(String name, String pass, String email, String phone){
        this.name = name;
        this.password = pass;
        this.email = email;
        this.phone = phone;
    }
}

class BookInfo {
    String id;
    String name;
    String author;
    String publisher;
    String status;
    BookInfo(String id, String name, String author, String publisher, String status) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.status = status;
    }
}


public class AdminDashBoard{
    AdminDashBoard(String username) throws FileNotFoundException {
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

        JLabel divider = new JLabel("-------------------------------");
        divider.setBounds(100, 170, 300, 40);
        divider.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(divider);

        JLabel name = new JLabel(username);
        name.setBounds(170, 200, 150, 40);
        name.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(name);
        
        JButton ViewEmpolyes = new JButton("View Empolyees");
        ViewEmpolyes.setBounds(100, 250, 200,50);
        ViewEmpolyes.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(ViewEmpolyes);
        ViewEmpolyes.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //setVisible(false);
                    frame.dispose();
                    new ViewUsers();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AdminDashBoard.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(AdminDashBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        
        
        
        JButton logout = new JButton("Logout");
        logout.setBounds(100, 420, 100, 40);
        logout.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LoginPage();
            }
        });

   
        Object[][] bookInfo = getBookInfo();
        String[] columnNames = {"ID", "Name", "Author", "Publisher", "Status"};
        DefaultTableModel model = new DefaultTableModel(bookInfo, columnNames);
       
        JTable table = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(400, 250, 600, 400);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(scrollPane);

        JTextField searchField = new JTextField();
        searchField.setBounds(400, 200, 400, 40);
        searchField.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(searchField);

        JButton search = new JButton("Search");
        search.setBounds(820, 200, 180, 40);
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
    }

    
    public Object[][] getBookInfo() throws FileNotFoundException {
        File file = new File("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\ProjectManagementSystem\\src\\bookList.txt");
        Scanner sc = new Scanner(file);

        ArrayList<BookInfo> bookInfoList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] book = line.split(",");
            BookInfo bookInfo = new BookInfo(book[0], book[1], book[2], book[3], book[4]);
            bookInfoList.add(bookInfo);
        }
        
        Object[][] bookInfoArray = new Object[bookInfoList.size()][5];
        for (int i = 0; i < bookInfoList.size(); i++) {
            bookInfoArray[i][0] = bookInfoList.get(i).id;
            bookInfoArray[i][1] = bookInfoList.get(i).name;
            bookInfoArray[i][2] = bookInfoList.get(i).author;
            bookInfoArray[i][3] = bookInfoList.get(i).publisher;
            bookInfoArray[i][4] = bookInfoList.get(i).status;
        }
        // Close the scanner
        sc.close();
        return bookInfoArray;
    }

    
    

    public static void main(String[] args) {
        try {
            AdminDashBoard adminDashBoard = new AdminDashBoard("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




