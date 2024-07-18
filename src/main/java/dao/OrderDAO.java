package dao;

import model.Order;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO implements DAOInterface<Order> {

    public static OrderDAO getInstance() {
        return new OrderDAO();
    }

    @Override
    public int insert(Order value) {
        int rs = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO ORDERS (CustomerID,CustomerName,ProductID, ProductName,Amount) VALUES ( ?, ?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, value.getIdCus());
            preparedStatement.setString(2, value.getNameCus());
            preparedStatement.setString(3, value.getIdProduct());
            preparedStatement.setString(4, value.getNameProduct());

            try {
                int amount = Integer.parseInt(value.getAmount());
                preparedStatement.setInt(5, amount);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            rs = preparedStatement.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                if (e.getMessage().contains("PRIMARY KEY constraint")) {
                    JOptionPane.showMessageDialog(null, "Error: Duplicate customer ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    JDBCUtil.closeConnection(connection);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rs;
    }

    @Override
    public int update(Order value) {
        return 0;
    }

    @Override
    public int delete(Order value) {
        return 0;
    }

    @Override
    public ArrayList<Order> selectAll() {
        ArrayList<Order> list_order = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM ORDERS";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Bạn đã thực thi: " + sql);

            while (rs.next()) {
                String idCus = rs.getString("CustomerID");
                String nameCus = rs.getString("CustomerName");
                String idProduct = rs.getString("ProductID");
                String nameProduct = rs.getString("ProductName");
                String amount = rs.getString("Amount");

                Order order = new Order(idCus, nameCus, idProduct, nameProduct, amount);
                list_order.add(order);
            }

            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list_order;
    }

    @Override
    public Order selectByID(Order value) {
        return null;
    }

    @Override
    public ArrayList<Order> selectByCondition(String condition) {
        return null;
    }

    @Override
    public int deleteByCondition(String condition) {
        int rs = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM ORDERS WHERE CustomerID = ? OR CustomerName = ? OR ProductID = ? OR ProductName = ? ";

            boolean isConditionNumeric = isNumeric(condition);
            if (isConditionNumeric) {
                sql += " OR Amount = ?";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Thiết lập các giá trị cho PreparedStatement
            preparedStatement.setString(1, condition);
            preparedStatement.setString(2, condition);
            preparedStatement.setString(3, condition);
            preparedStatement.setString(4, condition);

            if (isConditionNumeric) {
                preparedStatement.setString(5, condition);
            }
            rs = preparedStatement.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);

            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int getTotalPrice(String idCustomer){
        int result=0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT  SUM(P.[price]*O.[Amount]) AS TOTAL_PRICE\n" +
                    "FROM ORDERS O\n" +
                    "INNER JOIN PRODUCT P\n" +
                    "ON P.[productID]=O.[ProductID]\n" +
                    "WHERE O.CustomerID=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,idCustomer);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result += resultSet.getInt(1);
            }
            System.out.println("Bạn đã thực thi: " + sql);

            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String findId(String condition) {
        return "";
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
