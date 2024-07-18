package dao;

import model.Product;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class ProductDAO implements DAOInterface<Product> {

    public static ProductDAO getInstance() {
        return new ProductDAO();
    }

    @Override
    public int insert(Product value) {
        int rs = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO PRODUCT (productID, productName, description, category,price) VALUES ( ?, ?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, value.getId());
            preparedStatement.setString(2, value.getName());
            preparedStatement.setString(3, value.getDescription());
            preparedStatement.setString(4, value.getCategory());
            preparedStatement.setString(5, value.getPrice());


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
    public int update(Product value) {
        return 0;
    }

    @Override
    public int delete(Product value) {
        return 0;
    }

    @Override
    public ArrayList<Product> selectAll() {
        ArrayList<Product> list_Product = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PRODUCT";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Bạn đã thực thi: " + sql);

            while (rs.next()) {
                String id = rs.getString("productID");
                String name = rs.getString("productName");
                String description = rs.getString("description");
                String category = rs.getString("category");
                String price = rs.getString("price");

                Product product = new Product(id, name, description, category, price);
                list_Product.add(product);
            }

            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list_Product;
    }

    @Override
    public Product selectByID(Product value) {
        return null;
    }

    @Override
    public ArrayList<Product> selectByCondition(String condition) {
        return null;
    }

    @Override
    public int deleteByCondition(String condition) {
        int rs = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM PRODUCT WHERE productID = ? OR productName = ? OR description = ? OR category = ?";
            boolean isConditionNumeric = isNumeric(condition);

            if (isConditionNumeric) {
                sql += " OR price = CAST(? AS money)";
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

    // setting productID
    public String selectMaxProductID() {
        String result = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT Max(productID) FROM PRODUCT";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString(1);
            }

            System.out.println("Bạn đã thực thi: " + sql);

            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String findIdCustomer(String condition) {
        String result = "";
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT productID FROM PRODUCT WHERE productName=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, condition);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result += resultSet.getString(1);
            }

            System.out.println("Bạn đã thực thi: " + sql);

            JDBCUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // cast price to number
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
