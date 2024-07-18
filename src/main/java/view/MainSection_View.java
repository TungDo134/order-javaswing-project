package view;

import controller.MainSection_Controller;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import model.Customer;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Objects;


public class MainSection_View extends JFrame {
    private JPanel panel_cardLayout;
    private Font font;
    private Font font2;

    // customer in4
    private JTextField field_id;
    private JTextField field_name;
    private JTextField field_address;
    private JTextField field_dob;
    private JTextField field_phone;
    private DefaultTableModel model;

    // product in4
    private JTextField field_productID;
    private JTextField field_productName;
    private JTextField field_des;
    private JComboBox<String> comboBox_cate;
    private JTextField field_price;
    private DefaultTableModel model_product;

    // order in4
    private DefaultTableModel model_order;
    private JComboBox<String> comboBox_Cus;
    private JComboBox<String> comboBox_Product;
    private JTextField field_idCus_o;
    private JTextField field_idProduct_o;
    private JTextField field_amount;

    // bill in4
    private JTextField field_result_Bill;
    private JTextField field_idCus_Bill;
    private JComboBox<String> comboBox_Cus_Bill;
    //
    ArrayList<Customer> customers = CustomerDAO.getInstance().selectAll();
    ArrayList<Product> product_list = ProductDAO.getInstance().selectAll();
    ArrayList<Order> order_list = OrderDAO.getInstance().selectAll();

    // action listener
    ActionListener listener = new MainSection_Controller(this);



    public MainSection_View() {
        init();
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private void init() {
        this.setTitle("APPLICATION");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        font = new Font("Arial", Font.PLAIN, 15);
        font2 = new Font("Arial", Font.PLAIN, 13);

        JPanel panel_right = new JPanel(new BorderLayout());
        panel_right.setBackground(Color.CYAN);


        // create 4 maintenance (4 cardLayout)
        panel_cardLayout = new JPanel(new CardLayout());


        panel_cardLayout.add(createCard1(), "Card 1");
        panel_cardLayout.add(createCard2(), "Card 2");
        panel_cardLayout.add(createCard3(), "Card 3");
        panel_cardLayout.add(createCard4(), "Card 4");
        panel_cardLayout.add(createCard5(), "Card 5");

        // create button ( added by panel_left )
        JPanel panel_left = new JPanel();
        panel_left.setBackground(new Color(196, 228, 255, 100));

        JPanel panel_left_component = new JPanel();
        panel_left_component.setLayout(new BoxLayout(panel_left_component, BoxLayout.Y_AXIS));

        // create Icon
        Icon icon_1 = new ImageIcon("D:\\WorkSpace_IJ\\Swing_Project\\src\\main\\resources\\order.png");
        Icon icon_2 = new ImageIcon("D:\\WorkSpace_IJ\\Swing_Project\\src\\main\\resources\\user.png");
        Icon icon_3 = new ImageIcon("D:\\WorkSpace_IJ\\Swing_Project\\src\\main\\resources\\online-shopping.png");
        Icon icon_4 = new ImageIcon("D:\\WorkSpace_IJ\\Swing_Project\\src\\main\\resources\\copyright.png");
        Icon icon_5 = new ImageIcon("D:\\WorkSpace_IJ\\Swing_Project\\src\\main\\resources\\arrow.png");
        Icon icon_6 = new ImageIcon("D:\\WorkSpace_IJ\\Swing_Project\\src\\main\\resources\\icons8-bill-40.png");

        // Tạo các JButton với kích thước cố định
        JButton button1 = new JButton("Product", icon_3);
        button1.addActionListener(listener);
        button1.setIconTextGap(15);
        button1.setHorizontalAlignment(SwingConstants.LEFT);
        button1.setBackground(new Color(238, 237, 235));
        button1.setBorder(null);
        button1.setFont(font);

        JButton button2 = new JButton("Customer", icon_2);
        button2.addActionListener(listener);
        button2.setIconTextGap(15);
        button2.setHorizontalAlignment(SwingConstants.LEFT);
        button2.setBackground(new Color(238, 237, 235));
        button2.setBorder(null);
        button2.setFont(font);

        JButton button3 = new JButton("Orders", icon_1);
        button3.addActionListener(listener);
        button3.setIconTextGap(15);
        button3.setHorizontalAlignment(SwingConstants.LEFT);
        button3.setBackground(new Color(238, 237, 235));
        button3.setBorder(null);
        button3.setFont(font);


        JButton button4 = new JButton("Bill", icon_6);
        button4.addActionListener(listener);
        button4.setIconTextGap(15);
        button4.setHorizontalAlignment(SwingConstants.LEFT);
        button4.setBackground(new Color(238, 237, 235));
        button4.setBorder(null);
        button4.setFont(font);


        Dimension big_size = new Dimension(200, 100);
        JButton button_top = new JButton("Copyright", icon_4);
        button_top.addActionListener(listener);
        button_top.setVerticalTextPosition(SwingConstants.BOTTOM);
        button_top.setHorizontalTextPosition(SwingConstants.CENTER);
        button_top.setBackground(new Color(238, 237, 235));
        button_top.setPreferredSize(big_size);
        button_top.setMaximumSize(big_size);
        button_top.setMinimumSize(big_size);

        JButton button_bot = new JButton("Log out", icon_5);
        button_bot.addActionListener(listener);
        button_bot.setVerticalTextPosition(SwingConstants.BOTTOM);
        button_bot.setHorizontalTextPosition(SwingConstants.CENTER);
        button_bot.setBackground(new Color(238, 237, 235));
        button_bot.setPreferredSize(big_size);
        button_bot.setMaximumSize(big_size);
        button_bot.setMinimumSize(big_size);

        Dimension buttonSize = new Dimension(200, 50); // Đặt kích thước cố định cho các nút
        button1.setPreferredSize(buttonSize);
        button1.setMaximumSize(buttonSize);
        button1.setMinimumSize(buttonSize);

        button2.setPreferredSize(buttonSize);
        button2.setMaximumSize(buttonSize);
        button2.setMinimumSize(buttonSize);

        button3.setPreferredSize(buttonSize);
        button3.setMaximumSize(buttonSize);
        button3.setMinimumSize(buttonSize);

        button4.setPreferredSize(buttonSize);
        button4.setMaximumSize(buttonSize);
        button4.setMinimumSize(buttonSize);

        JSeparator jSeparator1 = new JSeparator(SwingConstants.HORIZONTAL);
        jSeparator1.setForeground(Color.BLACK);
        JSeparator jSeparator2 = new JSeparator(SwingConstants.HORIZONTAL);
        jSeparator2.setForeground(Color.BLACK);
        JSeparator jSeparator3 = new JSeparator(SwingConstants.HORIZONTAL);
        jSeparator3.setForeground(Color.BLACK);
        JSeparator jSeparator4 = new JSeparator(SwingConstants.HORIZONTAL);
        jSeparator4.setForeground(Color.BLACK);

        JLabel label_title = new JLabel("HỆ THỐNG QUẢN LÍ ĐƠN ĐẶT HÀNG");
        label_title.setFont(new Font("Arial", Font.PLAIN, 13));

        panel_left_component.add(label_title);
        panel_left_component.add(Box.createVerticalStrut(10));
        panel_left_component.add(button_top);
        panel_left_component.add(Box.createVerticalStrut(10));
        panel_left_component.add(button1);
        panel_left_component.add(Box.createVerticalStrut(10)); // khoảng cách giữa các button
        panel_left_component.add(jSeparator1);
        panel_left_component.add(Box.createVerticalStrut(10)); // khoảng cách giữa các button
        panel_left_component.add(button2);
        panel_left_component.add(Box.createVerticalStrut(10)); // khoảng cách giữa các button
        panel_left_component.add(jSeparator2);
        panel_left_component.add(Box.createVerticalStrut(10)); // khoảng cách giữa các button
        panel_left_component.add(button3);
        panel_left_component.add(Box.createVerticalStrut(10)); // khoảng cách giữa các button
        panel_left_component.add(jSeparator3);
        panel_left_component.add(Box.createVerticalStrut(10)); // khoảng cách giữa các button

        panel_left_component.add(button4);
        panel_left_component.add(Box.createVerticalStrut(10)); // khoảng cách giữa các button
        panel_left_component.add(jSeparator4);
        panel_left_component.add(Box.createVerticalStrut(10)); // khoảng cách giữa các button

        panel_left_component.add(button_bot);
        panel_left_component.add(Box.createVerticalStrut(10)); // khoảng cách giữa các button
        panel_left_component.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel_left.add(panel_left_component);

        panel_right.add(panel_cardLayout, BorderLayout.CENTER);

        // add to JFrame
        this.add(panel_left, BorderLayout.WEST);
        this.add(panel_right, BorderLayout.CENTER);
    }


    // card 1 (add products)
    public JPanel createCard1() {
        JPanel panel_card1 = new JPanel(new FlowLayout());

        //  Add and show customer
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black, 1));
        JPanel sub_panel_Padding = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        sub_panel_Padding.setBorder(border);

        JPanel sub_panel = new JPanel();
        sub_panel.setLayout(new GridLayout(7, 2, 10, 25));

        sub_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sub_panel.setPreferredSize(new Dimension(350, 350));

        JLabel label_ProductID = new JLabel("Product's ID: ");
        JLabel label_Product = new JLabel("Product: ");
        JLabel label_Des = new JLabel("Description:");
        JLabel label_Cate = new JLabel("Category:");
        JLabel label_Price = new JLabel("Price:");


        field_productID = new JTextField(30);
        field_productID.setFont(font2);
        field_productID.setEditable(false);

        if (product_list != null && product_list.isEmpty()) {
            field_productID.setText("P00");
        } else {
            field_productID.setText(getNextProductId(ProductDAO.getInstance().selectMaxProductID()));
        }

        field_productName = new JTextField(30);
        field_productName.setFont(font2);
        field_des = new JTextField(30);
        field_des.setFont(font2);
        field_price = new JTextField(30);
        field_price.setFont(font2);

        // JComboBox
        String[] category = {"Trang sức", "Gấu bông", "Đồ trang trí"};
        comboBox_cate = new JComboBox<>(category);
//        comboBox_cate.addActionListener(listener);
        comboBox_cate.setFont(font2);


        JButton btn = new JButton("Add product");
        btn.addActionListener(listener);
        btn.setFont(font);
        btn.setBackground(new Color(187, 233, 255));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        Dimension buttonSize = new Dimension(15, 15); // Đặt kích thước cố định cho các nút
        btn.setPreferredSize(buttonSize);
        btn.setMaximumSize(buttonSize);
        btn.setMinimumSize(buttonSize);

        JButton btn_reset = new JButton("Refresh products");
        btn_reset.addActionListener(listener);
        btn_reset.setFont(font);
        btn_reset.setBackground(new Color(187, 233, 255));
        btn_reset.setOpaque(true);
        btn_reset.setBorderPainted(false);
        btn_reset.setPreferredSize(buttonSize);
        btn_reset.setMaximumSize(buttonSize);
        btn_reset.setMinimumSize(buttonSize);

        //
        sub_panel.add(label_ProductID);
        sub_panel.add(field_productID);
        sub_panel.add(label_Product);
        sub_panel.add(field_productName);
        sub_panel.add(label_Des);
        sub_panel.add(field_des);
        sub_panel.add(label_Cate);
        sub_panel.add(comboBox_cate);
        sub_panel.add(label_Price);
        sub_panel.add(field_price);
        sub_panel.add(btn);
        sub_panel.add(btn_reset);

        // create table product
        String[] columnNames = {"ID", "Product", "Description", "Category", "Price"};
        model_product = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô đều o thể chỉnh sửa trực tiếp trên JTable
            }
        };
        JTable table_product = new JTable(model_product);
        table_product.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table_product.getColumnModel().getColumn(0).setMaxWidth(60);
        table_product.getColumnModel().getColumn(2).setMinWidth(150);
        table_product.getColumnModel().getColumn(3).setMaxWidth(100);
        table_product.getColumnModel().getColumn(4).setMinWidth(60);


        table_product.setPreferredSize(new Dimension(500, 500));
        table_product.setBackground(new Color(238, 237, 235));
        JScrollPane jScrollPane = new JScrollPane(table_product);


        if (product_list != null) {
            for (Product product : product_list) {
                Object[] row = {product.getId(), product.getName(), product.getDescription(), product.getCategory(),
                        product.getPrice()
                };
                model_product.addRow(row);
            }
        }

        // create JPopupMenu và JMenuItem
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Xóa");
        jPopupMenu.add(deleteItem);

        // event handel right click for table
        table_product.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table_product.rowAtPoint(evt.getPoint());
                int col = table_product.columnAtPoint(evt.getPoint());
                if (row >= 0 && SwingUtilities.isRightMouseButton(evt)) {
                    table_product.setRowSelectionInterval(row, row); // Chọn dòng
                    jPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());

                    String condition = table_product.getModel().getValueAt(row, col).toString();

                    // event handle JMenuItem
                    deleteItem.addActionListener(e -> {
                        int selectedRow = table_product.getSelectedRow();
                        if (selectedRow >= 0) {
                            model_product.removeRow(selectedRow); // Xóa dòng được chọn
                            ProductDAO.getInstance().deleteByCondition(condition);
                        }
                    });
                }
            }
        });

        sub_panel_Padding.add(sub_panel);
        panel_card1.add(sub_panel_Padding);
        panel_card1.add(jScrollPane);

        return panel_card1;
    }

    // card 2 (add customers )
    public JPanel createCard2() {
        JPanel panel_card2 = new JPanel(new FlowLayout());


        // create table
        String[] columnNames = {"ID", "Name", "Address", "Date of birth", "Phone number"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô đều o thể chỉnh sửa trực tiếp trên JTable
            }
        };
        JTable table_cus = new JTable(model);

        table_cus.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table_cus.getColumnModel().getColumn(0).setMaxWidth(60);
        table_cus.getColumnModel().getColumn(1).setMinWidth(60);
        table_cus.getColumnModel().getColumn(2).setMinWidth(60);
        table_cus.getColumnModel().getColumn(3).setMaxWidth(80);
        table_cus.getColumnModel().getColumn(4).setMaxWidth(80);

        table_cus.setPreferredSize(new Dimension(700, 500));
        table_cus.setBackground(new Color(238, 237, 235));

        // create JPopupMenu và JMenuItem
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Xóa");
        jPopupMenu.add(deleteItem);

        // event handel right click for table
        table_cus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table_cus.rowAtPoint(evt.getPoint());
                int col = table_cus.columnAtPoint(evt.getPoint());
                if (row >= 0 && SwingUtilities.isRightMouseButton(evt)) {
                    table_cus.setRowSelectionInterval(row, row); // Chọn dòng
                    jPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());

                    String condition = table_cus.getModel().getValueAt(row, col).toString();

                    // event handle JMenuItem
                    deleteItem.addActionListener(e -> {
                        int selectedRow = table_cus.getSelectedRow();
                        if (selectedRow >= 0) {
                            model.removeRow(selectedRow); // Xóa dòng được chọn
                            CustomerDAO.getInstance().deleteByCondition(condition);

                        }
                    });
                }
            }
        });
        JScrollPane jScrollPane = new JScrollPane(table_cus);

        if (customers != null) {
            for (Customer customer : customers) {
                Object[] row = {customer.getId(), customer.getName(), customer.getAddress(),
                        customer.getDob(), customer.getPhone()};
                model.addRow(row);
            }
        }
        //  Add and show customer
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black, 1));
        JPanel sub_panel_Padding = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        sub_panel_Padding.setBorder(border);

        JPanel sub_panel = new JPanel();
        sub_panel.setLayout(new GridLayout(7, 2, 10, 25));

        sub_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sub_panel.setPreferredSize(new Dimension(350, 350));

        JLabel label_id_C2 = new JLabel("Customer's id: ");
        JLabel label_fullName_C2 = new JLabel("Name: ");
        JLabel label_address_C2 = new JLabel("Address:");
        JLabel label_dob_C2 = new JLabel("Date of birth (yyyy/mm/dd):");
        JLabel label_phone_C2 = new JLabel("Phone number:");

        field_id = new JTextField(30);
        field_id.setFont(font2);
        field_id.setEditable(false);

        if (customers != null && customers.isEmpty()) {
            field_id.setText("KH00");
        } else {
            field_id.setText(getNextCustomerId(CustomerDAO.getInstance().selectMaxCustomerID()));
        }

        field_name = new JTextField(30);
        field_name.setFont(font2);
        field_address = new JTextField(30);
        field_address.setFont(font2);
        field_dob = new JTextField(30);
        field_dob.setFont(font2);
        field_phone = new JTextField(30);
        field_phone.setFont(font2);

        JButton btn = new JButton("Add customer");
        btn.addActionListener(listener);
        btn.setFont(font);
        btn.setBackground(new Color(187, 233, 255));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        Dimension buttonSize = new Dimension(15, 15); // Đặt kích thước cố định cho các nút
        btn.setPreferredSize(buttonSize);
        btn.setMaximumSize(buttonSize);
        btn.setMinimumSize(buttonSize);

        JButton btn2 = new JButton("Clear");
        btn2.addActionListener(listener);
        btn2.setFont(font);
        btn2.setBackground(new Color(231, 111, 81));
        btn2.setOpaque(true);
        btn2.setBorderPainted(false);
        btn2.setPreferredSize(buttonSize);
        btn2.setMaximumSize(buttonSize);
        btn2.setMinimumSize(buttonSize);

        JButton btn3 = new JButton("Refresh customers");
        btn3.addActionListener(listener);
        btn3.setFont(font);
        btn3.setBackground(new Color(187, 233, 255));
        btn3.setOpaque(true);
        btn3.setBorderPainted(false);
        btn3.setPreferredSize(buttonSize);
        btn3.setMaximumSize(buttonSize);
        btn3.setMinimumSize(buttonSize);


        sub_panel.add(label_id_C2);
        sub_panel.add(field_id);
        sub_panel.add(label_fullName_C2);
        sub_panel.add(field_name);
        sub_panel.add(label_address_C2);
        sub_panel.add(field_address);
        sub_panel.add(label_dob_C2);
        sub_panel.add(field_dob);
        sub_panel.add(label_phone_C2);
        sub_panel.add(field_phone);
        sub_panel.add(btn);
        sub_panel.add(btn2);
        sub_panel.add(btn3);

        sub_panel_Padding.add(sub_panel);
        panel_card2.add(sub_panel_Padding);
        panel_card2.add(jScrollPane);
        return panel_card2;
    }

    // card 3 (order)
    public JPanel createCard3() {
        JPanel panel_card3 = new JPanel();

        // create table
        String[] columnNames = {"ID Cus", "Name", "ID Product", "Product", "Amount"};
        model_order = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô đều o thể chỉnh sửa trực tiếp trên JTable
            }
        };
        JTable table_order = new JTable(model_order);
        table_order.setPreferredSize(new Dimension(700, 500));
        table_order.setBackground(new Color(238, 237, 235));

        // create JPopupMenu và JMenuItem
        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Xóa");
        jPopupMenu.add(deleteItem);

        // event handel right click for table
        table_order.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table_order.rowAtPoint(evt.getPoint());
                int col = table_order.columnAtPoint(evt.getPoint());
                if (row >= 0 && SwingUtilities.isRightMouseButton(evt)) {
                    table_order.setRowSelectionInterval(row, row); // Chọn dòng
                    jPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                    String condition = table_order.getModel().getValueAt(row, col).toString();

                    // event handle JMenuItem
                    deleteItem.addActionListener(e -> {
                        int selectedRow = table_order.getSelectedRow();
                        if (selectedRow >= 0) {
                            model_order.removeRow(selectedRow); // Xóa dòng được chọn
                            OrderDAO.getInstance().deleteByCondition(condition);

                        }
                    });
                }
            }
        });
        if (order_list != null) {
            for (Order orders : order_list) {
                Object[] row = {orders.getIdCus(), orders.getNameCus(), orders.getIdProduct(),
                        orders.getNameProduct(), orders.getAmount()};
                model_order.addRow(row);
            }
        }
        JScrollPane jScrollPane = new JScrollPane(table_order);

        //  Add and show customer
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black, 1));
        JPanel sub_panel_Padding = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        sub_panel_Padding.setBorder(border);

        JPanel sub_panel = new JPanel();
        sub_panel.setLayout(new GridLayout(7, 2, 10, 25));

        sub_panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sub_panel.setPreferredSize(new Dimension(350, 350));

        JLabel label_idCus_o = new JLabel("Customer's id: ");
        JLabel label_nameCus_o = new JLabel("Name: ");
        JLabel label_idProduct_o = new JLabel("Product's id:");
        JLabel label_nameProduct_o = new JLabel("Product name:");
        JLabel label_amount = new JLabel("Amount:");

        field_idCus_o = new JTextField(30);
        field_idCus_o.setEditable(false);
        field_idCus_o.setFont(font2);
        field_idProduct_o = new JTextField(30);
        field_idProduct_o.setEditable(false);
        field_idProduct_o.setFont(font2);
        field_amount = new JTextField(30);
        field_amount.setFont(font2);


        Dimension buttonSize = new Dimension(15, 15); // Đặt kích thước cố định cho các nút

        JButton btn1 = new JButton("Add order");
        btn1.addActionListener(listener);
        btn1.setFont(font);
        btn1.setBackground(new Color(187, 233, 255));
        btn1.setOpaque(true);
        btn1.setBorderPainted(false);
        btn1.setPreferredSize(buttonSize);
        btn1.setMaximumSize(buttonSize);
        btn1.setMinimumSize(buttonSize);

        JButton btn2 = new JButton("Refresh order");
        btn2.addActionListener(listener);
        btn2.setFont(font);
        btn2.setBackground(new Color(187, 233, 255));
        btn2.setOpaque(true);
        btn2.setBorderPainted(false);
        btn2.setPreferredSize(buttonSize);
        btn2.setMaximumSize(buttonSize);
        btn2.setMinimumSize(buttonSize);

        // COMBOBOX CUSTOMER
        String[] nameCus = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            nameCus[i] = customers.get(i).getName();
        }
        comboBox_Cus = new JComboBox<>(nameCus);
        comboBox_Cus.setFont(font2);
        comboBox_Cus.addActionListener(e -> {
            String src = Objects.requireNonNull(comboBox_Cus.getSelectedItem()).toString();
            String id = CustomerDAO.getInstance().findId(src);
            field_idCus_o.setText(id);
        });

        // COMBOBOX PRODUCT
        String[] nameProduct = new String[product_list.size()];
        for (int i = 0; i < product_list.size(); i++) {
            nameProduct[i] = product_list.get(i).getName();
        }

        comboBox_Product = new JComboBox<>(nameProduct);
        comboBox_Product.setFont(font2);
        comboBox_Product.addActionListener(e -> {
            String src = Objects.requireNonNull(comboBox_Product.getSelectedItem()).toString();
            String id = ProductDAO.getInstance().findId(src);
            field_idProduct_o.setText(id);
        });

        sub_panel.add(label_nameCus_o);
        sub_panel.add(comboBox_Cus);
        sub_panel.add(label_idCus_o);
        sub_panel.add(field_idCus_o);
        sub_panel.add(label_nameProduct_o);
        sub_panel.add(comboBox_Product);
        sub_panel.add(label_idProduct_o);
        sub_panel.add(field_idProduct_o);
        sub_panel.add(label_amount);
        sub_panel.add(field_amount);

        sub_panel.add(btn1);
        sub_panel.add(btn2);

        sub_panel_Padding.add(sub_panel);

        panel_card3.add(sub_panel_Padding);
        panel_card3.add(jScrollPane);

        return panel_card3;
    }

    // Copyright
    private JPanel createCard4() {
        JPanel panel_card4 = new JPanel();
        panel_card4.setLayout(new BoxLayout(panel_card4, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("APPLICATION MAKE BY TUNGDO");
        label.setFont(new Font("Arial", Font.PLAIN, 20));

        //
        JPanel sub_panel = new JPanel(new GridLayout(3, 2));

        // label information
        JPanel panel_label = new JPanel(new GridLayout(3, 2));
        TitledBorder border = BorderFactory.createTitledBorder("My information");
        border.setTitleColor(Color.LIGHT_GRAY);
        panel_label.setBorder(border);
        JLabel label_name1 = new JLabel("Full name: ");
        JLabel label_dob1 = new JLabel("Day of birth: ");
        JLabel label_contact1 = new JLabel("Contact:");

        JLabel label_name = new JLabel("Đỗ Sơn Tùng");
        JLabel label_dob = new JLabel("2004-04-13");
        JLabel label_contact = new JLabel("0969412xxx");

        panel_label.add(label_name1);
        panel_label.add(label_name);
        panel_label.add(label_dob1);
        panel_label.add(label_dob);
        panel_label.add(label_contact1);
        panel_label.add(label_contact);

        sub_panel.add(panel_label);

        panel_card4.add(Box.createVerticalStrut(10));
        panel_card4.add(label);
        panel_card4.add(sub_panel);
        return panel_card4;

    }

    // Bill
    private JPanel createCard5() {
        JPanel panel_card5 = new JPanel(new BorderLayout());
        JPanel sub_panelPadding = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createTitledBorder("Bill"));

        JPanel sub_panel = new JPanel(new GridLayout(3, 2, 5, 10));
        sub_panel.setBorder(border);
        sub_panel.setPreferredSize(new Dimension(400,150));
        sub_panelPadding.add(sub_panel);

        JLabel label_nameCus = new JLabel("Customer name:");
        JLabel label_idCus = new JLabel("Customer id:");
        field_idCus_Bill = new JTextField(30);

        field_result_Bill = new JTextField(30);
        field_result_Bill.setEditable(false);
        field_result_Bill.setFont(font2);

        // button
        Dimension buttonSize = new Dimension(15, 15);
        JButton btn = new JButton("Get total money");
        btn.addActionListener(listener);
        btn.setFont(font);
        btn.setBackground(new Color(187, 233, 255));
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setPreferredSize(buttonSize);
        btn.setMaximumSize(buttonSize);
        btn.setMinimumSize(buttonSize);

        // COMBOBOX CUSTOMER
        String[] nameCus = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            nameCus[i] = customers.get(i).getName();
        }
        comboBox_Cus_Bill = new JComboBox<>(nameCus);
        Font font3 = new Font("Arial", Font.PLAIN, 15);
        comboBox_Cus_Bill.setFont(font2);
        comboBox_Cus_Bill.addActionListener(e -> {
            String src = Objects.requireNonNull(comboBox_Cus_Bill.getSelectedItem()).toString();
            String id = CustomerDAO.getInstance().findId(src);
            field_idCus_Bill.setText(id);
        });

        sub_panel.add(label_nameCus);
        sub_panel.add(comboBox_Cus_Bill);
        sub_panel.add(label_idCus);
        sub_panel.add(field_idCus_Bill);
        sub_panel.add(btn);
        sub_panel.add(field_result_Bill);

        panel_card5.add(sub_panelPadding, BorderLayout.WEST);

        return panel_card5;
    }

    // GET SET
    public JPanel getPanel_cardLayout() {
        return panel_cardLayout;
    }

    // get card 1

    public JTextField getField_productID() {
        return field_productID;
    }

    public JTextField getField_productName() {
        return field_productName;
    }

    public JTextField getField_des() {
        return field_des;
    }

    public JComboBox<String> getComboBox_cate() {
        return comboBox_cate;
    }

    public JTextField getField_price() {
        return field_price;
    }

    public DefaultTableModel getModel_product() {
        return model_product;
    }

    // get card 2
    public DefaultTableModel getModel() {
        return model;
    }

    public JTextField getField_id() {
        return field_id;
    }

    public JTextField getField_name() {
        return field_name;
    }

    public JTextField getField_address() {
        return field_address;
    }

    public JTextField getField_dob() {
        return field_dob;
    }

    public JTextField getField_phone() {
        return field_phone;
    }

    public JTextField getField_idCus_o() {
        return field_idCus_o;
    }

    public JTextField getField_idProduct_o() {
        return field_idProduct_o;
    }

    public JTextField getField_amount() {
        return field_amount;
    }

    public DefaultTableModel getModel_order() {
        return model_order;
    }

    public JComboBox<String> getComboBox_Cus() {
        return comboBox_Cus;
    }

    public JComboBox<String> getComboBox_Cus_Bill() {
        return comboBox_Cus_Bill;
    }

    public JComboBox<String> getComboBox_Product() {
        return comboBox_Product;
    }

    public JTextField getField_result_Bill() {
        return field_result_Bill;
    }

    public JTextField getField_idCus_Bill() {
        return field_idCus_Bill;
    }

    public String getNextProductId(String currentProductId) {
        int lastIdNumber = Integer.parseInt(currentProductId.substring(1));
        int nextIdNumber = lastIdNumber + 1;
        return "P" + String.format("%02d", nextIdNumber);
    }

    public String getNextCustomerId(String currentCustomerId) {
        int lastIdNumber = Integer.parseInt(currentCustomerId.substring(2));
        int nextIdNumber = lastIdNumber + 1;
        return "KH" + String.format("%02d", nextIdNumber);
    }


}


