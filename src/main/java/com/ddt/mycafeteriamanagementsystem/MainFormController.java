package com.ddt.mycafeteriamanagementsystem;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.function.Consumer;

public class MainFormController implements Initializable {
    //Cây thư mục điều hướng
    @FXML
    private Button customers_btn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button logout_btn;

    //Biến trong dashBoard_form
    @FXML
    private Label numOfCustomer;

    @FXML
    private Label dayIncome;

    @FXML
    private Label totalIncome;

    @FXML
    private Label numOfProSold;

    @FXML
    private AnchorPane dashBoard_form;

    @FXML
    private BarChart<?, ?> customerBarChart;

    @FXML
    private LineChart<?, ?> dayLineChart;



    //Biến trong inventory_form
    @FXML
    private Button inventory_btn_add;

    @FXML
    private Button inventory_reloadBtn;

    @FXML
    private TableColumn<ProductData, Void> inventory_col_change;

    @FXML
    private TableColumn<ProductData, String> inventory_col_date;

    @FXML
    private TableColumn<ProductData, String> inventory_col_id;

    @FXML
    private TableColumn<ProductData, String> inventory_col_name;

    @FXML
    private TableColumn<ProductData, String> inventory_col_price;

    @FXML
    private TableColumn<ProductData, String> inventory_col_status;

    @FXML
    private TableColumn<ProductData, String> inventory_col_stock;

    @FXML
    private TableColumn<ProductData, String> inventory_col_type;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private TableView<ProductData> inventory_tableView;

    @FXML
    private TextField inventory_search;

    //Biến trong menu_form
    @FXML
    private Label menu_time;

    @FXML
    private Button menu_all_btn;

    @FXML
    private Button menu_drink_btn;

    @FXML
    private Button menu_fastFood_btn;

    @FXML
    private Button menu_mainFood_btn;

    @FXML
    private Label menu_amount;

    @FXML
    private Button menu_btn;

    @FXML
    private Label menu_col_item;

    @FXML
    private Label menu_col_price;

    @FXML
    private Label menu_col_quantity;

    @FXML
    private Label menu_discount;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private Label menu_orders;

    @FXML
    private Button menu_pay_btn;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private ImageView menu_search_icon;

    @FXML
    private TextField menu_search_text;

    @FXML
    private AnchorPane menu_tableView;

    @FXML
    private Label menu_total;

    @FXML
    private GridPane order_gridPane;

    @FXML
    private AnchorPane main_form;

    //Biến trong customer_form
    @FXML
    private TableColumn<CustomerData, String> customer_col_cashier;

    @FXML
    private TableColumn<CustomerData, String> customer_col_customerID;

    @FXML
    private TableColumn<CustomerData, String> customer_col_date;

    @FXML
    private TableColumn<CustomerData, String> customer_col_total;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private TableView<CustomerData> customer_tableView;

    private Alert alert;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image images;
    private ObservableList<Product> cardListData = FXCollections.observableArrayList();

    private ObservableList<ProductData> inventoryListData;
    private int cID;
    private Receipt receipt = null;
    private ReceiptDAO receiptDAO = null;
    private OrderDetails orderDetails = null;
    private OrderDetailsDAO orderDetailsDAO = null;
    private Order order = null;
    private OrderDAO orderDAO = null;
    private Employee employee = null;
    private EmployeeDAOImpl employeeDAO = null;
    private Product product = null;
    private ProductCardDAO productCardDAO = null;
    private Customer customer = null;
    private CusotmerDAOImpl cusotmerDAO = null;
    private javafx.event.ActionEvent ActionEvent;

    //DashBoard function..............

    //Inventory function
    @FXML
    public void addDisplay_invent() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addInventory.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Cafeteria!");
            stage.setResizable(false);

            stage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();

        }catch (Exception e){e.printStackTrace();}
    }

    @FXML
    public void editForm(ProductData productData) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addInventory.fxml"));

            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Cafeteria!");
            stage.setResizable(false);

            stage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            AddInventController addIn = fxmlLoader.getController();
            addIn.setAddInvent_form(productData);
            stage.showAndWait();

        }catch (Exception e){e.printStackTrace();}
    }


    // Hien data len bang
    public void inventoryShowData() {
        inventoryListData = InventoryDataList();

        inventory_col_id.setCellValueFactory(new PropertyValueFactory<>("productID"));
        inventory_col_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        inventory_col_type.setCellValueFactory(new PropertyValueFactory<>("categories_id"));
        inventory_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inventory_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        inventory_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Tạo một hàm callback để tạo các ô chứa nút cho mỗi hàng trong cột "Action"
        Callback<TableColumn<ProductData, Void>, TableCell<ProductData, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<ProductData, Void> call(final TableColumn<ProductData, Void> param) {
                final TableCell<ProductData, Void> cell = new TableCell<>() {
                    private final Button editButton = new Button();
                    private final Button deleteButton = new Button();

                    {
                        // Xử lý sự kiện khi ấn nút 'Edit'
                        editButton.setOnAction(event -> {
                            ProductData product = getTableView().getItems().get(getIndex());
                            editForm(product);
                            inventoryLoadData();
                        });

                        // Xử lý sự kiện khi ấn nút 'Delete'
                        deleteButton.setOnAction(event -> {
                            ProductData product = getTableView().getItems().get(getIndex());
                            DeleteProduct(product);
                            inventoryLoadData();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        // Kiểm tra hàng trống hoặc không
                        if (empty) {
                            setGraphic(null);
                        } else {
                            deleteButton.getStyleClass().addAll("butTon", "deLete");
                            editButton.getStyleClass().addAll("butTon", "eDit");
                            // Nếu hàng không trống, hiển thị hai nút 'Edit' và 'Delete'
                            HBox buttons = new HBox(editButton, deleteButton);
                            buttons.setSpacing(5);
                            setGraphic(buttons);
                        }
                    }
                };
                return cell;
            }
        };

// Thiết lập cellFactory cho cột 'Action'
        inventory_col_change.setCellFactory(cellFactory );

        inventory_tableView.setItems(inventoryListData);
    }


    public void DeleteProduct(ProductData prod)
    {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Product ID: " + prod.getProductID() + " ?");
        Optional<ButtonType> option =  alert.showAndWait();

        if (option.get().equals(ButtonType.OK))
        {
            try {
                InventoryDAOimpl.getInstance().delete(prod);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("successfully Deleted!");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventoryLoadData(){
            inventoryListData = InventoryDataList();
            inventory_tableView.setItems(inventoryListData);
    }

    public ObservableList<ProductData> InventoryDataList() {
        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData prodData;
            while(result.next()){
                prodData = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getInt("categories_id"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(prodData);
            }
        }catch (Exception e){e.printStackTrace();}

        return listData;
    }

    public void searchInventory(){
        FilteredList<ProductData> filter = new FilteredList<>(inventoryListData, e -> true);
        inventory_search.textProperty().addListener((Observable, oldValue, newValue)->{
            filter.setPredicate(inven ->{
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue;

                if(inven.getProductName().toString().contains(searchKey)){
                    return true;
                }
                else return false;
            });
        });

        SortedList<ProductData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(inventory_tableView.comparatorProperty());
        inventory_tableView.setItems(sortedList);
    }

    //Product function
    public void menuTime(){
        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMM, yyyy");
        String formattedDate = dateFormat.format(date);

        menu_time.setText(formattedDate);
    }

    public ObservableList<Product> menuGetData() throws SQLException {
        String sql = "SELECT * FROM product";

        ObservableList<Product> listData = FXCollections.observableArrayList();
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Product product;
            ProductCardDAO productCardDAO = new ProductCardDAOImpl();
            while(result.next()){
                product = new Product(
                        result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        productCardDAO.getCategories(result.getInt("categories_id")),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(product);
            }
        }catch (Exception e){e.printStackTrace();}

        return listData;
    }
    public ObservableList<Product> menuDrinkData(){
        String sql = "SELECT * FROM product WHERE categories_id = 1";

        ObservableList<Product> listData = FXCollections.observableArrayList();
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Product product;
            ProductCardDAO productCardDAO = new ProductCardDAOImpl();
            while(result.next()){
                product = new Product(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        productCardDAO.getCategories(result.getInt("categories_id")),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(product);
            }
        }catch (Exception e){e.printStackTrace();}

        return listData;
    }

    public ObservableList<Product> menuFastFoodData(){
        String sql = "SELECT * FROM product WHERE categories_id = 2";

        ObservableList<Product> listData = FXCollections.observableArrayList();
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Product product;
            ProductCardDAO productCardDAO = new ProductCardDAOImpl();
            while(result.next()){
                product = new Product(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        productCardDAO.getCategories(result.getInt("id")),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(product);
            }
        }catch (Exception e){e.printStackTrace();}

        return listData;
    }

    public ObservableList<Product> menuMainFoodData(){
        String sql = "SELECT * FROM product WHERE categories_id = 3";

        ObservableList<Product> listData = FXCollections.observableArrayList();
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Product product;
            ProductCardDAO productCardDAO = new ProductCardDAOImpl();
            while(result.next()){
                product = new Product(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        productCardDAO.getCategories(result.getInt("categories_id")),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(product);
            }
        }catch (Exception e){e.printStackTrace();}

        return listData;
    }

    public ObservableList<Product> getData2(ActionEvent event) throws SQLException{
        String name = null;
        System.out.println("here");
        if(event.getSource() == menu_all_btn){
            name = menu_all_btn.getText();
            System.out.println("all");
        } else if (event.getSource() == menu_drink_btn) {
            name = menu_drink_btn.getText();
            System.out.println("drink");
        } else if (event.getSource() == menu_fastFood_btn) {
            name = menu_fastFood_btn.getText();
        } else if (event.getSource() == menu_mainFood_btn) {
            name = menu_mainFood_btn.getText();
        }
        ProductCardDAO productCardDAO = new ProductCardDAOImpl();
        return productCardDAO.getProductsByType(name);
    }

    public void menuDisplay(ActionEvent event) throws SQLException{
        cardListData.clear();
        cardListData.addAll(getData2(event));

        int row = 0;
        int column = 0;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for(int q = 0; q < cardListData.size(); q++){

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if(column == 3){
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(15));

            }catch (Exception e){e.printStackTrace();}
        }
        menu_all_btn.getStyleClass().add("btn_clicked");
        menu_drink_btn.getStyleClass().remove("btn_clicked");
        menu_mainFood_btn.getStyleClass().remove("btn_clicked");
        menu_fastFood_btn.getStyleClass().remove("btn_clicked");
    }

    public void menuDisplayCard() throws SQLException {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for(int q = 0; q < cardListData.size(); q++){

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if(column == 3){
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(15));

            }catch (Exception e){e.printStackTrace();}
        }
    }

    public void menuAllBtn() throws SQLException {
        menuDisplayCard();
        menu_all_btn.getStyleClass().add("btn_clicked");
        menu_drink_btn.getStyleClass().remove("btn_clicked");
        menu_mainFood_btn.getStyleClass().remove("btn_clicked");
        menu_fastFood_btn.getStyleClass().remove("btn_clicked");
    }

    public void menuDrinkBtn(){
        cardListData.clear();
        cardListData.addAll(menuDrinkData());

        int row = 0;
        int column = 0;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for(int q = 0; q < cardListData.size(); q++){

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if(column == 3){
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(15));

            }catch (Exception e){e.printStackTrace();}
        }
        menu_all_btn.getStyleClass().remove("btn_clicked");
        menu_drink_btn.getStyleClass().add("btn_clicked");
        menu_mainFood_btn.getStyleClass().remove("btn_clicked");
        menu_fastFood_btn.getStyleClass().remove("btn_clicked");
    }

    public void menuMainFoodBtn(){
        cardListData.clear();
        cardListData.addAll(menuMainFoodData());

        int row = 0;
        int column = 0;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for(int q = 0; q < cardListData.size(); q++){

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if(column == 3){
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(15));

            }catch (Exception e){e.printStackTrace();}
        }
        menu_all_btn.getStyleClass().remove("btn_clicked");
        menu_drink_btn.getStyleClass().remove("btn_clicked");
        menu_mainFood_btn.getStyleClass().add("btn_clicked");
        menu_fastFood_btn.getStyleClass().remove("btn_clicked");
    }

    public void menuFastFoodBtn(){
        cardListData.clear();
        cardListData.addAll(menuFastFoodData());

        int row = 0;
        int column = 0;

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for(int q = 0; q < cardListData.size(); q++){

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProductController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if(column == 3){
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(15));

            }catch (Exception e){e.printStackTrace();}
        }
        menu_all_btn.getStyleClass().remove("btn_clicked");
        menu_drink_btn.getStyleClass().remove("btn_clicked");
        menu_mainFood_btn.getStyleClass().remove("btn_clicked");
        menu_fastFood_btn.getStyleClass().add("btn_clicked");
    }

    public ObservableList<ProductData> orderGetData(){
        customerID();
        String sql = "SELECT * FROM customer WHERE customer_id = " + cID;

        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData productData;
            while(result.next()){
                productData = new ProductData(
                        result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getInt("quantity"),
                        result.getDouble("price"),
                        result.getString("image"),
                        result.getDate("date"));
                productData.setPr(result.getDouble("price") / result.getInt("quantity"));

                listData.add(productData);
            }
        }catch (Exception e){e.printStackTrace();}

        return listData;
    }

    private ObservableList<ProductData> orderListData = FXCollections.observableArrayList();
    public void orderDisplay(){
        orderListData.clear();
        orderListData.addAll(orderGetData());

        int row = 0;
        int column = 0;

        order_gridPane.getChildren().clear();
        order_gridPane.getRowConstraints().clear();
        order_gridPane.getColumnConstraints().clear();

        for(int q = 0; q < orderListData.size(); q++){

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("ordersProduct.fxml"));
                AnchorPane pane = load.load();
                OrderProductController oderC = load.getController();
                oderC.setData(orderListData.get(q));

                if(column == 1){
                    column = 0;
                    row += 1;
                }

                order_gridPane.add(pane, column++, row);

            }catch (Exception e){e.printStackTrace();}
        }
    }

    private double totalP;
    private int qty;
    public void menuGetTotal(){
        customerID();
        String total = "SELECT SUM(price) FROM customer WHERE customer_id = " + cID;

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(total);
            result  = prepare.executeQuery();

            if(result.next()){
                totalP = result.getDouble("SUM(price)");
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void menuGetAmount(){
        customerID();
        String amount = "SELECT SUM(quantity) FROM customer WHERE customer_id = " + cID;

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(amount);
            result  = prepare.executeQuery();

            if(result.next()){
                qty = result.getInt("SUM(quantity)");
            }

        }catch (Exception e){e.printStackTrace();}
    }

    private double discount;
    public void menuGetDiscount(){
        menuGetAmount();
        menuGetTotal();

        if(qty < 10){
            discount = totalP;
            menu_total.setText(discount + " VND");
        }
        else{
            discount = totalP - 20000;
            menu_discount.setText("-" + 20000 + " VND");
            menu_total.setText(discount + " VND");
        }
    }

    public void menuDisplayTotal(){
        menuGetAmount();
        menu_amount.setText(String.valueOf(qty));
        menuGetDiscount();
    }

    int employee_id;
    int prodID;
    String prodNameCus;
    int quantityCus;
    int order_id;

    public void menuPayBtn(){
        if(totalP == 0){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please choose the product");
            alert.showAndWait();
        }
        else{

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                employee_id = 0;
                order_id = 0;

                if(option.get().equals(ButtonType.OK)){
                    customerID();
                    menuGetTotal();
                    menuGetDiscount();
                    customerID();

                    //Lay ID la primary key cua employee de insert vao order
                    employee = new Employee();
                    employeeDAO = new EmployeeDAOImpl();
                    ResultSet resultSetEpl = employeeDAO.getIDEmployee(employee);
                    if(resultSetEpl.next()){
                        employee_id = resultSetEpl.getInt("id");
                    }

                    //Dien du lieu vao table order khi co khoa ngoai la ID cua employee
                    order = new Order();
                    order.setEmployee_id(employee_id);
                    orderDAO = new OrderDAOImpl();
                    orderDAO.insert(order);
                    ResultSet resultSetOrder = orderDAO.getAllOrder(order);
                    //Lay id cua order_id la primary key de insert vao orderDetail
                    if(resultSetOrder.next()){
                        order_id = resultSetOrder.getInt("MAX(id)");
                    }

                    /**
                     * OrderDetail can prod_id va quantity => lay thong qua customer
                     */

                    //Lay prod_name va quantity cua customer da mua
                    customer = new Customer();
                    customer.setCustomer_id(cID);
                    cusotmerDAO = new CusotmerDAOImpl();
                    ResultSet resultSetCus = cusotmerDAO.getAllCustomer(customer);

                    try {
                        //Dung vong lap de lay het ten mon va so luong cua 1 khach hang
                        while(resultSetCus.next()){
                            prodNameCus = resultSetCus.getString("prod_name");
                            quantityCus = resultSetCus.getInt("quantity");

                            //Lay dc prod_name thi se lay dc ID la primary key cua product
                            product = new Product();
                            product.setProd_name(prodNameCus);
                            productCardDAO = new ProductCardDAOImpl();
                            ResultSet resultSetProd = productCardDAO.getIDProduct(product);
                            if(resultSetProd.next()){
                                prodID = resultSetProd.getInt("id");
                            }

                            //Dien vao order detail
                            orderDetails = new OrderDetails(order_id, prodID, quantityCus);
                            orderDetailsDAO = new OrderDetailsDAOImpl();
                            orderDetailsDAO.insert(orderDetails);
                        }
                    }catch (Exception e){e.printStackTrace();}

                    receipt = new Receipt(0, cID, discount, Data.username);
                    receiptDAO = new ReceiptDAOImpl();
                    receiptDAO.insert(receipt);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful");
                    alert.showAndWait();

                    orderDisplay();
                    menuReset();
                }
                else{
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled");
                    alert.showAndWait();
                }

            }catch (Exception e){e.printStackTrace();}
        }
    }

    public void menuReset(){
        totalP = 0;
        discount = 0;
        qty = 0;
        menu_total.setText("0 VND");
        menu_amount.setText("0");
        menu_discount.setText("0 VND");
    }

    //Customer_form
    public ObservableList<CustomerData> customerDataList(){
        ObservableList<CustomerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receipt";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            CustomerData cData;

            while (result.next()){
                cData = new CustomerData(
                        result.getInt("id"),
                        result.getInt("customer_id"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getString("em_username"));

                listData.add(cData);
            }

        }catch (Exception e){e.printStackTrace();}
        return listData;
    }

    private ObservableList<CustomerData> customerListData;
    public void customerShowData(){
        customerListData = customerDataList();

        customer_col_customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customer_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customer_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customer_col_cashier.setCellValueFactory(new PropertyValueFactory<>("emUsername"));

        customer_tableView.setItems(customerListData);
    }

    public void customerID(){
        String sql = "SELECT MAX(customer_id) from customer";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                cID = result.getInt("MAX(customer_id)");
            }

            String checkCID = "SELECT MAX(customer_id) FROM receipt";
            prepare = connect.prepareStatement(checkCID);
            result = prepare.executeQuery();

            int checkID = 0;
            if(result.next()){
                checkID = result.getInt("MAX(customer_id)");
            }

            if(cID == 0){
                cID += 1;
            }
            else if(cID == checkID){
                cID += 1;
            }

            Data.cID = cID;

        }catch (Exception e){e.printStackTrace();}
    }

    //Chuyển Form
    public void switchForm(ActionEvent event) throws SQLException {
        if(event.getSource() == dashboard_btn){
            dashBoard_form.setVisible(true);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customer_form.setVisible(false);
        }
        else if(event.getSource() == inventory_btn){
            dashBoard_form.setVisible(false);
            inventory_form.setVisible(true);
            menu_form.setVisible(false);
            customer_form.setVisible(false);

            inventoryShowData();
            searchInventory();

        }
        else if(event.getSource() == menu_btn){
            dashBoard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(true);
            customer_form.setVisible(false);

            menuTime();
            menuDisplayCard();
            menuDisplayTotal();
            orderDisplay();
//            searchMenu();
        }
        else if(event.getSource() == customers_btn){
            dashBoard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customer_form.setVisible(true);

            customerShowData();
        }
    }

    public void logout(){
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                //Ẩn gioa
                logout_btn.getScene().getWindow().hide();

                //Limk lại trang đăng nhập
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setTitle("Cafe Shop");

                stage.setScene(scene);;
                stage.show();
            }
        }catch (Exception e){e.printStackTrace();}
    }


    //Chart
    public void displayBarChart() throws SQLException {
        String sql = "SELECT date, count(id) FROM customer GROUP BY date ORDER BY TIMESTAMP(date) ASC";
        XYChart.Series chartData = new XYChart.Series();
        connect = Database.connectDB();
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        while (result.next()){
            chartData.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));

        }
        customerBarChart.getData().add(chartData);
    }

    public void displayLineChart() throws SQLException {
        String sql = "SELECT date, count(id) FROM customer GROUP BY date ORDER BY TIMESTAMP(date) ASC";
        XYChart.Series chartData = new XYChart.Series();
        connect = Database.connectDB();
        prepare = connect.prepareStatement(sql);
        result = prepare.executeQuery();
        while (result.next()){
            chartData.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));

        }
        dayLineChart.getData().add(chartData);
    }

    public void statistics() throws SQLException {
        StatisticDAO statisticDAO = new StatisticDAOImpl();
        numOfCustomer.setText(String.valueOf(statisticDAO.getNumberOfCustomer()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Đưa tất cả trong hàm này vào hàm switch form
        inventoryShowData();
        menuTime();
        menuDisplayTotal();
        orderDisplay();
        customerShowData();
        try {
            displayBarChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            displayLineChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statistics();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
