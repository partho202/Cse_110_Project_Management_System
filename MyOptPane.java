
package com.mycompany.projectmanagementsystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class MyOptPane extends JFrame implements ActionListener{
    JButton ok;
    JLabel l1;
    String msg;
    JFrame frame = new JFrame("Pop Up Message");
    MyOptPane(String msg){
        this.msg = msg;  

        l1 = new JLabel(msg);
        l1.setFont(new Font("Arial", Font.BOLD, 17)); 
        l1.setBounds(20, 30, 250, 30);
        frame.add(l1);
        
        ok = new JButton("OK");
        ok.setFont(new Font("Arial", Font.BOLD, 17));
        ok.setBounds(105, 90, 60, 30);
        ok.addActionListener(this);
        frame.add(ok);
        
        
        frame.setSize(300, 200);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
         frame.setVisible(false);
         
    }
    
    public static void main(String[] args) {
        MyOptPane myOptPane = new MyOptPane("please run from another file");
    }
    
}
