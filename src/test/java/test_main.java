import dao.CustomerDAO;
import dao.JDBCUtil;
import dao.OrderDAO;
import view.Login_View;
import view.MainSection_View;

import javax.swing.*;
import java.sql.Connection;

public class test_main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){

        }
//        new Login_View();
        new MainSection_View();


    }


}
