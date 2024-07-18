package controller;

import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Customer;
import model.Order;
import model.Product;
import view.Login_View;
import view.MainSection_View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MainSection_Controller implements ActionListener {
    MainSection_View mainSection_view;
    Login_View login_view;
    Customer cus;
    Product product;
    Order order;
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Product> products_list = new ArrayList<>();
    ArrayList<Order> order_list = new ArrayList<>();
    ArrayList<String> temp = new ArrayList<>();

    public MainSection_Controller(MainSection_View mainSection_view) {
        this.mainSection_view = mainSection_view;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();

        // action listener for show card
        switch (src) {
            case "Product" -> {
                CardLayout cardLayout1 = (CardLayout) mainSection_view.getPanel_cardLayout().getLayout();
                cardLayout1.show(mainSection_view.getPanel_cardLayout(), "Card 1");
            }
            case "Customer" -> {
                CardLayout cardLayout2 = (CardLayout) mainSection_view.getPanel_cardLayout().getLayout();
                cardLayout2.show(mainSection_view.getPanel_cardLayout(), "Card 2");
            }
            case "Orders" -> {
                CardLayout cardLayout3 = (CardLayout) mainSection_view.getPanel_cardLayout().getLayout();
                cardLayout3.show(mainSection_view.getPanel_cardLayout(), "Card 3");
                updateComboBox_Customer();
                updateComboBox_Product();
            }
            case "Copyright" -> {
                CardLayout cardLayout4 = (CardLayout) mainSection_view.getPanel_cardLayout().getLayout();
                cardLayout4.show(mainSection_view.getPanel_cardLayout(), "Card 4");
            }
            case "Bill" -> {
                CardLayout cardLayout5 = (CardLayout) mainSection_view.getPanel_cardLayout().getLayout();
                cardLayout5.show(mainSection_view.getPanel_cardLayout(), "Card 5");
                updateComboBox_Bill();
            }
        }

        // listener for card1
        handleEventsForCard1(src);
        // listener for card2
        handleEventsForCard2(src);
        // listener for card3
        handleEventsForCard3(src);
        // listener for card5
        handleEventsForCard5(src);
    }

    // handle events for card1 (product)
    private void handleEventsForCard1(String src) {

        if (src.equals("Add product")) {
            String productID = mainSection_view.getField_productID().getText().trim();
            String productName = mainSection_view.getField_productName().getText().trim();
            String productDes = mainSection_view.getField_des().getText().trim();
            String productCate = (String) mainSection_view.getComboBox_cate().getSelectedItem();
            String productPrice = mainSection_view.getField_price().getText().trim();

            if (!productID.isEmpty() && !productName.isEmpty() && !productDes.isEmpty()
                    && !productPrice.isEmpty() && !(productCate != null && productCate.isEmpty())) {

                product = new Product(productID, productName, productDes, productCate, productPrice);
                ProductDAO.getInstance().insert(product);

                // up id +1
                String autoID = mainSection_view.getNextProductId(mainSection_view.getField_productID().getText());
                mainSection_view.getField_productID().setText(autoID);

                mainSection_view.getField_productName().setText("");
                mainSection_view.getField_des().setText("");
                mainSection_view.getField_price().setText("");

                resetTableProduct();
            } else {
                JOptionPane.showMessageDialog(mainSection_view, "Please fill full information",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } else if (src.equals("Refresh products")) {
            resetTableProduct();
        }
    }

    // handle events for card2 (customer)
    private void handleEventsForCard2(String src) {
        // action listener for button
        switch (src) {
            case "Log out" -> {
                int n = JOptionPane.showConfirmDialog(
                        null,
                        " Would you want to log out?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    mainSection_view.setVisible(false);
                    login_view = new Login_View();
                }
            }
            case "Add customer" -> {
                String id = mainSection_view.getField_id().getText().trim();
                String name = mainSection_view.getField_name().getText().trim();
                String address = mainSection_view.getField_address().getText().trim();
                String dob = mainSection_view.getField_dob().getText().trim();
                String phone = mainSection_view.getField_phone().getText().trim();

                if (!name.isEmpty() && !address.isEmpty() && !phone.isEmpty()) {
                    // thêm kh mới
                    cus = new Customer(id, name, address, dob, phone);
                    CustomerDAO.getInstance().insert(cus);

                    String autoID = mainSection_view.getNextCustomerId(mainSection_view.getField_id().getText());
                    mainSection_view.getField_id().setText(autoID);

                    mainSection_view.getField_name().setText("");
                    mainSection_view.getField_address().setText("");
                    mainSection_view.getField_dob().setText("");
                    mainSection_view.getField_phone().setText("");

                    resetTableCus();

                } else {
                    JOptionPane.showMessageDialog(mainSection_view, "Please fill full information",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Clear" -> {
                int n = JOptionPane.showConfirmDialog(
                        null,
                        "Would you want to clear all information above",
                        "!",
                        JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    mainSection_view.getField_id().setText("");
                    mainSection_view.getField_name().setText("");
                    mainSection_view.getField_address().setText("");
                    mainSection_view.getField_dob().setText("");
                    mainSection_view.getField_phone().setText("");
                }
            }
            case "Refresh customers" -> resetTableCus();
        }

    }

    // handle events for card3 (order)
    private void handleEventsForCard3(String src) {
        if (src.equals("Add order")) {
            String idCus = mainSection_view.getField_idCus_o().getText().trim();
            String nameCus = mainSection_view.getComboBox_Cus().getSelectedItem().toString();
            String idProduct = mainSection_view.getField_idProduct_o().getText().trim();
            String nameProduct = mainSection_view.getComboBox_Product().getSelectedItem().toString();
            String amount = mainSection_view.getField_amount().getText().trim();

            // Tạo một key để xác định sự tồn tại của đơn hàng đã có

            String orderKey = idCus + "_" + idProduct + "_";

            if (!idCus.isEmpty() && !idProduct.isEmpty() && !amount.isEmpty()) {
                if (temp.contains(orderKey)) {
                    int rs = JOptionPane.showConfirmDialog(mainSection_view, "KH này đã mua mặt hàng này" +
                            ", bạn vẫn muốn mua tương tự");
                    if (rs == JOptionPane.YES_OPTION) {
                        order = new Order(idCus, nameCus, idProduct, nameProduct, amount);
                        OrderDAO.getInstance().insert(order);
                        mainSection_view.getField_amount().setText("");
                        resetTableOrder();
                    }
                } else {
                    // thêm kh mới
                    order = new Order(idCus, nameCus, idProduct, nameProduct, amount);
                    temp.add(orderKey);
                    OrderDAO.getInstance().insert(order);

                    mainSection_view.getField_amount().setText("");
                    resetTableOrder();
                }
            } else {
                JOptionPane.showMessageDialog(mainSection_view, "Please check your information",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else if (src.equals("Refresh order")) {
            resetTableOrder();
        }

    }

    // handle events for card4 (bill)
    private void handleEventsForCard5(String src) {
        if (src.equals("Get total money")) {
            String id = mainSection_view.getField_idCus_Bill().getText();
            int totalPrice = OrderDAO.getInstance().getTotalPrice(id);
            mainSection_view.getField_result_Bill().setText(totalPrice + " VND");
        }
    }

    // reset tables
    private void resetTableCus() {
        customers = CustomerDAO.getInstance().selectAll();
        // Xóa tất cả các hàng hiện có trong bảng
        mainSection_view.getModel().setRowCount(0);
        // Nếu danh sách khách hàng không rỗng, thêm dữ liệu vào bảng
        if (customers != null) {
            for (Customer customer : customers) {
                Object[] row = {
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getDob(),
                        customer.getPhone()
                };
                mainSection_view.getModel().addRow(row);
            }
        }
        if (customers != null && customers.isEmpty()) {
            mainSection_view.getField_id().setText("KH00");
        }
    }

    private void resetTableProduct() {
        products_list = ProductDAO.getInstance().selectAll();

        // Xóa tất cả các hàng hiện có trong bảng
        mainSection_view.getModel_product().setRowCount(0);
        // Nếu danh sách sản phẩm không rỗng, thêm dữ liệu vào bảng
        if (products_list != null) {
            for (Product product : products_list) {
                Object[] row = {
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getCategory(),
                        product.getPrice()
                };

                mainSection_view.getModel_product().addRow(row);
            }
        }
        if (products_list != null && products_list.isEmpty()) {
            mainSection_view.getField_productID().setText("P00");
        }
    }

    private void resetTableOrder() {
        order_list = OrderDAO.getInstance().selectAll();
        // Xóa tất cả các hàng hiện có trong bảng
        mainSection_view.getModel_order().setRowCount(0);
        // Nếu danh sách sản phẩm không rỗng, thêm dữ liệu vào bảng
        if (order_list != null) {
            for (Order orders : order_list) {
                Object[] row = {
                        orders.getIdCus(),
                        orders.getNameCus(),
                        orders.getIdProduct(),
                        orders.getNameProduct(),
                        orders.getAmount()
                };
                mainSection_view.getModel_order().addRow(row);
            }
        }
    }

    // reset data for 2 combobox (customer & product)
    private void updateComboBox_Customer() {
        customers = CustomerDAO.getInstance().selectAll();
        String[] nameCus = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            nameCus[i] = customers.get(i).getName();
        }
        mainSection_view.getComboBox_Cus().setModel(new DefaultComboBoxModel<>(nameCus));
        mainSection_view.getComboBox_Cus().setFont(new Font("Arial", Font.PLAIN, 13));
        mainSection_view.getComboBox_Cus().addActionListener(e -> {
            String src = Objects.requireNonNull(mainSection_view.getComboBox_Cus().getSelectedItem()).toString();
            String id = CustomerDAO.getInstance().findId(src);
            mainSection_view.getField_idCus_o().setText(id);
        });
    }

    private void updateComboBox_Product() {
        products_list = ProductDAO.getInstance().selectAll();

        String[] ProductCus = new String[products_list.size()];
        for (int i = 0; i < products_list.size(); i++) {
            ProductCus[i] = products_list.get(i).getName();
        }
        mainSection_view.getComboBox_Product().setModel(new DefaultComboBoxModel<>(ProductCus));
        mainSection_view.getComboBox_Product().setFont(new Font("Arial", Font.PLAIN, 13));
        mainSection_view.getComboBox_Product().addActionListener(e -> {
            String src = Objects.requireNonNull(mainSection_view.getComboBox_Product().getSelectedItem()).toString();
            String id = CustomerDAO.getInstance().findId(src);
            mainSection_view.getField_idProduct_o().setText(id);
        });
    }

    private void updateComboBox_Bill() {
        customers = CustomerDAO.getInstance().selectAll();

        String[] nameCus = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            nameCus[i] = customers.get(i).getName();
        }
        mainSection_view.getComboBox_Cus_Bill().setModel(new DefaultComboBoxModel<>(nameCus));
        mainSection_view.getComboBox_Cus_Bill().setFont(new Font("Arial", Font.PLAIN, 13));
        mainSection_view.getComboBox_Cus_Bill().addActionListener(e -> {
            String src = Objects.requireNonNull(mainSection_view.getComboBox_Cus_Bill().getSelectedItem()).toString();
            String id = CustomerDAO.getInstance().findId(src);
            mainSection_view.getField_idCus_Bill().setText(id);
        });
    }
}




