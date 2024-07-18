package view;

import controller.Login_Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login_View extends JFrame {
    private JTextField field_id;
    private JPasswordField field_password;

    public Login_View() {
        init();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JTextField getField_id() {
        return field_id;
    }

    public JPasswordField getField_password() {
        return field_password;
    }

    // ActionListener
    ActionListener ac = new Login_Controller(this);

    private void init() {
        this.setTitle("LOGIN");
//        this.setSize(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(90, 114, 160));

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // main panel
        JPanel main_Panel = new JPanel(new BorderLayout());
        main_Panel.setOpaque(false);

        // top panel
        JPanel top_Panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        top_Panel.setOpaque(false);
        JPanel sub_TopPanel = new JPanel(new GridLayout());

        JLabel top_Label = new JLabel("Order Management", JLabel.CENTER);

        top_Label.setFont(new Font("Arial", Font.BOLD, 30));
        top_Label.setForeground(Color.WHITE);
        sub_TopPanel.setBackground(new Color(8, 131, 149));
        sub_TopPanel.setPreferredSize(new Dimension(750, 100));
        sub_TopPanel.add(top_Label);
        top_Panel.add(sub_TopPanel);

        // LOGO
//        int newWidth = 200; // Chiều rộng mới
//        int newHeight = 200; // Chiều cao mới
//        ImageIcon image_logo = new ImageIcon("D:\\WorkSpace_IJ\\Swing_Project\\src\\main\\resources\\7u7xewxf_0.png");
//        Image image = image_logo.getImage();
//        Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
//        JLabel label_logo = new JLabel(new ImageIcon(scaledImage));
//        sub_TopPanel.add(label_logo);

        // bottom panel
        JPanel bot_Panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        bot_Panel.setOpaque(false);
        JPanel sub_BotPanel = new JPanel(new GridLayout());
        sub_BotPanel.setBackground(new Color(8, 131, 149));
        sub_BotPanel.setPreferredSize(new Dimension(750, 100));
        bot_Panel.add(sub_BotPanel);

        // center panel
        JPanel center_Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel sub_CenterPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        center_Panel.setBackground(Color.WHITE);
        sub_CenterPanel.setBackground(Color.WHITE);
        TitledBorder border = BorderFactory.createTitledBorder("Login");
        border.setTitleColor(new Color(8, 131, 149));
        center_Panel.setBorder(border);


        JLabel label_Id = new JLabel("ID");
        label_Id.setForeground(new Color(8, 131, 149));
        field_id = new JTextField(30);
        JLabel label_Password = new JLabel("PASSWORD");
        field_password = new JPasswordField(30);
        label_Password.setForeground(new Color(8, 131, 149));
        JPanel btn_Panel = new JPanel(new GridLayout(1, 1, 10, 10));
        JButton btn_login = new JButton("Log in");
        btn_login.setBackground(new Color(8, 131, 149));
        btn_login.setForeground(Color.WHITE);
        btn_login.setOpaque(true);
        btn_login.setBorderPainted(false);
        JButton btn_signin = new JButton("Sign in");
        btn_signin.setBackground(new Color(8, 131, 149));
        btn_signin.setForeground(Color.WHITE);
        btn_signin.setOpaque(true);
        btn_signin.setBorderPainted(false);
        btn_Panel.add(btn_login);
        btn_Panel.add(btn_signin);

        JPanel padding_Panel = new JPanel();
        padding_Panel.setBackground(Color.WHITE);
        btn_login.addActionListener(ac);
        btn_signin.addActionListener(ac);


        sub_CenterPanel.add(label_Id);
        sub_CenterPanel.add(field_id);
        sub_CenterPanel.add(label_Password);
        sub_CenterPanel.add(field_password);
        sub_CenterPanel.add(padding_Panel);
        sub_CenterPanel.add(btn_Panel);


        center_Panel.add(sub_CenterPanel);


        // add to main
        main_Panel.add(top_Panel, BorderLayout.NORTH);
        main_Panel.add(center_Panel, BorderLayout.CENTER);
        main_Panel.add(bot_Panel, BorderLayout.SOUTH);

        // add to JFrame
        this.add(main_Panel);

    }

}
