package controller;

import model.Information_Login;
import view.Login_View;
import view.MainSection_View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Controller implements ActionListener {
    Login_View login_view;
    MainSection_View mainSection_view;
    Information_Login informationLogin;

    public Login_Controller(Login_View login_view) {
        this.login_view = login_view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("Log in")) {
            if (login_view.getField_id().getText().isEmpty() ||
                    login_view.getField_password().getText().isEmpty()) {
                JOptionPane.showMessageDialog(login_view, "Please enter full your info!");
                return;
            }

            if (login_view.getField_id().getText().trim().equals("admin134")
                    && login_view.getField_password().getText().trim().equals("tungdo134")) {
                mainSection_view = new MainSection_View();
                login_view.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(login_view, "Please check your id and password again!");
                return;
            }
        } else if (src.equals("Sign in")) {
            informationLogin = new Information_Login();
        }
    }
}
