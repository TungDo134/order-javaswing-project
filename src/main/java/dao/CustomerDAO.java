package dao;

import model.Customer;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class CustomerDAO implements DAOInterface<Customer> {

    public static CustomerDAO getInstance() {
        return new CustomerDAO();
    }

    @Override
    public int insert(Customer value) {
        int rs = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "INSERT INTO CUSTOMER (id,name, address, dob, phone) VALUES ( ?, ?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, value.getId());
            preparedStatement.setString(2, value.getName());
            preparedStatement.setString(3, value.getAddress());
            preparedStatement.setString(4, value.getDob());
            preparedStatement.setString(5, value.getPhone());

            rs = preparedStatement.executeUpdate();
            System.out.println("Bạn đã thực thi: " + sql);
        } catch (SQLException e) {
            if (e.getErrorCode() == 2627) {
                if (e.getMessage().contains("UNIQUE KEY constraint")) {
                    JOptionPane.showMessageDialog(null, "Error: Duplicate phone number.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (e.getMessage().contains("PRIMARY KEY constraint")) {
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
    public int update(Customer value) {
        return 0;
    }

    @Override
    public int delete(Customer value) {
        int rs = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM CUSTOMER" + "WHERE name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, value.getName());

            System.out.println("Bạn đã thực thi: " + sql);
            rs = preparedStatement.executeUpdate();

            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    @Override
    public ArrayList<Customer> selectAll() {
        ArrayList<Customer> list_Cus = new ArrayList<>();
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM CUSTOMER";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Bạn đã thực thi: " + sql);

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String dob = rs.getString("dob");
                String phone = rs.getString("phone");

                Customer customer = new Customer(id, name, address, dob, phone);
                list_Cus.add(customer);
            }

            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list_Cus;
    }

    @Override
    public Customer selectByID(Customer value) {
        return null;
    }

    @Override
    public ArrayList<Customer> selectByCondition(String condition) {
        return null;
    }


    @Override
    public int deleteByCondition(String condition) {
        int rs = 0;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "DELETE FROM CUSTOMER WHERE id = ? OR name = ? OR address = ? OR dob = ? OR phone = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Thiết lập các giá trị cho PreparedStatement

            preparedStatement.setString(1, condition);
            preparedStatement.setString(2, condition);
            preparedStatement.setString(3, condition);


            try {
                Date dob = parseDate(condition);
                preparedStatement.setDate(4, dob);
            } catch (ParseException e) {
                preparedStatement.setNull(4, java.sql.Types.DATE);
            }

            preparedStatement.setString(5, condition);

            System.out.println("Bạn đã thực thi: " + sql);
            rs = preparedStatement.executeUpdate();

            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public String selectMaxCustomerID() {
        String result = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT Max(id) FROM CUSTOMER";
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

    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(dateStr);
        return new Date(date.getTime());
    }

    @Override
    public String findId(String condition) {
        String result = "";
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT id FROM CUSTOMER WHERE name=? ";
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

}


