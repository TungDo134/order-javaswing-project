package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information_Login extends JFrame {
    public Information_Login() throws HeadlessException {
        setTitle("Sign in");
        setLayout(new GridLayout(5, 1, 5, 5));
        Font font = new Font("Arial", Font.PLAIN, 13);
        JLabel label1 = new JLabel("_Under construction ...");
        label1.setFont(font);
        JLabel label2 = new JLabel("_To login copy info here");
        label2.setFont(font);
        JTextField jTextField1 = new JTextField("_ID: admin134 ");
        jTextField1.setFont(font);
        jTextField1.setEditable(false);
        jTextField1.setBorder(null);
        JTextField jTextField2 = new JTextField("_Password: tungdo134 ");
        jTextField2.setFont(font);
        jTextField2.setEditable(false);
        jTextField2.setBorder(null);
        JButton btn_back = new JButton("Back");
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Back")) {
                    setVisible(false);
                }
            }
        });
        add(label1);
        add(label2);
        add(jTextField1);
        add(jTextField2);
        add(btn_back);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
