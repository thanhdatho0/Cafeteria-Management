package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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
    private Label monthIncome;

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
    private TableColumn<Product, Void> inventory_col_change;

    @FXML
    private TableColumn<Product, String> inventory_col_date;

    @FXML
    private TableColumn<Product, String> inventory_col_id;

    @FXML
    private TableColumn<Product, String> inventory_col_name;

    @FXML
    private TableColumn<Product, String> inventory_col_price;

    @FXML
    private TableColumn<Product, String> inventory_col_status;

    @FXML
    private TableColumn<Product, String> inventory_col_stock;

    @FXML
    private TableColumn<Product, String> inventory_col_type;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private TableView<Product> inventory_tableView;

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
    private AnchorPane menu_tableView;

    @FXML
    private Label menu_total;

    @FXML
    private GridPane order_gridPane;

    @FXML
    private AnchorPane main_form;

    //Biến trong customer_form
    @FXML
    private TableColumn<CustomerDetails, String> customer_col_cashier;

    @FXML
    private TableColumn<CustomerDetails, String> customer_col_customerID;

    @FXML
    private TableColumn<CustomerDetails, String> customer_col_date;

    @FXML
    private TableColumn<CustomerDetails, String> customer_col_total;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private TableView<CustomerDetails> customer_tableView;

    private Alert alert;
    private Connection connect;
    private ObservableList<Product> cardListData = FXCollections.observableArrayList();
    private ObservableList<Product> inventoryListData;
//    private int cID;

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
    public void editForm(Product product) {
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
            addIn.setAddInvent_form(product);
            stage.showAndWait();

        }catch (Exception e){e.printStackTrace();}
    }

    // Hien data len bang
    public void inventoryShowData() throws SQLException {
        inventoryListData = InventoryDataList();

        inventory_col_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
        inventory_col_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
        inventory_col_type.setCellValueFactory(new PropertyValueFactory<>("categories"));
        inventory_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inventory_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        inventory_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));


        // Tạo một hàm callback để tạo các ô chứa nút cho mỗi hàng trong cột "Action"
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                final TableCell<Product, Void> cell = new TableCell<>() {
                    private final Button editButton = new Button();
                    private final Button deleteButton = new Button();

                    {
                        // Xử lý sự kiện khi ấn nút 'Edit'
                        editButton.setOnAction(event -> {
                            Product product = getTableView().getItems().get(getIndex());
                            editForm(product);
                            try {
                                inventoryLoadData();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        // Xử lý sự kiện khi ấn nút 'Delete'
                        deleteButton.setOnAction(event -> {
                            Product product = getTableView().getItems().get(getIndex());
                            DeleteProduct(product);
                            try {
                                inventoryLoadData();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
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


    public void DeleteProduct(Product prod)
    {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Product ID: " + prod.getProd_id() + " ?");
        Optional<ButtonType> option =  alert.showAndWait();

        if (option.get().equals(ButtonType.OK))
        {
            try {
                ProductDAOImpl.getInstance().delete(new Product(prod));
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

    public void inventoryLoadData() throws SQLException {
            inventoryListData = InventoryDataList();
            inventory_tableView.setItems(inventoryListData);
    }

    public ObservableList<Product> InventoryDataList() throws SQLException {
        return ProductDAOImpl.getInstance().DataList();
    }

    public void searchInventory(){
        FilteredList<Product> filter = new FilteredList<>(inventoryListData, e -> true);
        inventory_search.textProperty().addListener((Observable, oldValue, newValue)->{
            filter.setPredicate(inven ->{
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                String searchKey = newValue;

                if(inven.getProd_name().toString().contains(searchKey)){
                    return true;
                }
                else return false;
            });
        });

        SortedList<Product> sortedList = new SortedList<>(filter);
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
        return ProductDAOImpl.getInstance().DataMenu();
    }
    public ObservableList<Product> menuDrinkData() throws SQLException {
        return ProductDAOImpl.getInstance().DataTypeList(1);
    }

    public ObservableList<Product> menuFastFoodData() throws SQLException {
        return ProductDAOImpl.getInstance().DataTypeList(2);
    }

    public ObservableList<Product> menuMainFoodData() throws SQLException {
        return ProductDAOImpl.getInstance().DataTypeList(3);
    }

    public void menuDisplay(ObservableList<Product> menuType) {
        menu_scrollPane.setFocusTraversable(false);
        cardListData.clear();
        cardListData.addAll(menuType);

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
    public void menuDisplayCard() throws SQLException {
        menuDisplay(menuGetData());
    }


    public void menuAllBtn() throws SQLException {
        menuDisplayCard();
        menu_all_btn.getStyleClass().add("btn_clicked");
        menu_drink_btn.getStyleClass().remove("btn_clicked");
        menu_mainFood_btn.getStyleClass().remove("btn_clicked");
        menu_fastFood_btn.getStyleClass().remove("btn_clicked");
    }

    public void menuDrinkBtn() throws SQLException {
        menuDisplay(menuDrinkData());
        menu_all_btn.getStyleClass().remove("btn_clicked");
        menu_drink_btn.getStyleClass().add("btn_clicked");
        menu_mainFood_btn.getStyleClass().remove("btn_clicked");
        menu_fastFood_btn.getStyleClass().remove("btn_clicked");
    }

    public void menuMainFoodBtn() throws SQLException {
        menuDisplay(menuMainFoodData());
        menu_all_btn.getStyleClass().remove("btn_clicked");
        menu_drink_btn.getStyleClass().remove("btn_clicked");
        menu_mainFood_btn.getStyleClass().add("btn_clicked");
        menu_fastFood_btn.getStyleClass().remove("btn_clicked");
    }

    public void menuFastFoodBtn() throws SQLException {
        menuDisplay(menuFastFoodData());
        menu_all_btn.getStyleClass().remove("btn_clicked");
        menu_drink_btn.getStyleClass().remove("btn_clicked");
        menu_mainFood_btn.getStyleClass().remove("btn_clicked");
        menu_fastFood_btn.getStyleClass().add("btn_clicked");
    }

    public void orderDisplay(){
        order_gridPane.getChildren().clear();
        order_gridPane.getRowConstraints().clear();
        order_gridPane.getColumnConstraints().clear();

        int row = 0;
        int column = 0;

        for(int q = 0; q < Order.items.size(); q++){

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("ordersProduct.fxml"));
                AnchorPane pane = load.load();
                OrderProductController oderC = load.getController();
                oderC.setData(Order.items.get(q));

                if(column == 1){
                    column = 0;
                    row += 1;
                }

                order_gridPane.add(pane, column++, row);

            }catch (Exception e){e.printStackTrace();}
        }
        orderInfo();
    }
    private int qty;
    private double discount;
    private double totalP;

    public void orderInfo(){
        double total = 0;
        double discounted = 0;
        int amount = 0;
        int count = 0;
        for(OrderDetails orderDetails : Order.items){
            amount += orderDetails.getQuantity();
            total += orderDetails.getQuantity()*orderDetails.getProduct().getPrice();
        }
        qty = amount;
        if(amount >= 10){
            discounted = (total * 15) / 100;
            discount = discounted;
            totalP = total - discounted;
            menu_discount.setText("-" + String.valueOf(discount) + " VND");
            menu_amount.setText(String.valueOf(amount));
            menu_total.setText(String.valueOf(totalP) + " VND");
        }
        else{
            totalP = total;
            menu_amount.setText(String.valueOf(amount));
            menu_total.setText(String.valueOf(totalP) + " VND");
        }
    }

    public void payBtn() throws SQLException {
        if(Order.items.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error Message");
            alert.setContentText("404 NOT FOUND");
            alert.showAndWait();
            return;
        }
        //Khai bao - Lay du lieu cua Nhan Vien
        Employee employee = new Employee();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        employee = employeeDAO.getEmployeeByUserName(Data.username);

        System.out.println(discount);
        //Khoi tao 1 Order moi voi cac thuoc tinh co ban
        Order order = new Order();
        order.setEmployee(employee);
        order.setDiscount(discount);
        OrderDAO orderDAO = new OrderDAOImpl();
        orderDAO.insert(order);
        order.setId(orderDAO.getOrderId());

        //Khoi tao, luu cac Chi tiet hoa don vao db tu du lieu co san
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
        Order.items.forEach(item->{
            item.setOrder(order);
            try {
                orderDetailsDAO.insert(item);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        //Cap nhat lai so hang trong kho
        ProductDAO productDAO = new ProductDAOImpl();
        Order.items.forEach(item ->{
            int stock = 0;
            try {
                stock = productDAO.getProdStock(item.getProduct());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            item.getProduct().setStock(stock - item.getQuantity());
            try {
                productDAO.updateProdStock(item.getProduct());
                if(item.getProduct().getStock() == 0) {
                    System.out.println(item.getProduct().getStock());
                    productDAO.switchStatus(item.getProduct());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        //Ghi du lieu vao Customers
        Customers customers = new Customers();
        customers.setOrder(order);
        customers.setTotal(order.total_amount());
        CustomersDAO customersDAO = new CustomersDAOImpl();
        customersDAO.insert(customers);

        Order.items.clear();
        order_gridPane.getChildren().clear();
        System.out.println(Order.items);
//        menuReset();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Information Message");
        alert.setContentText("Successfully Create Order");
        alert.show();
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

    public void customerShowData() throws SQLException {
        ///customerListData = customerDataList();
        ObservableList<CustomerDetails> customerDetails = null;
        CustomersDAO customersDAO = new CustomersDAOImpl();
        customerDetails = customersDAO.customerDetailsDataList();

        customer_col_customerID.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        customer_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customer_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customer_col_cashier.setCellValueFactory(new PropertyValueFactory<>("cashier"));

        customer_tableView.setItems(customerDetails);
    }

    public void reportBtn(){
        if(totalP == 0 || menu_amount.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please order first");
            alert.showAndWait();
        }
        else{
            OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAOImpl();
            int orderID;
            try {
                orderID = orderDetailsDAO.getOrderDetailId();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            HashMap map = new HashMap();
            map.put("getReceipt", orderID);

            try {
                JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/com/ddt/mycafeteriamanagementsystem/report.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, Database.connectDB());

                JasperViewer.viewReport(jasperPrint, false);
                menuReset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Chuyển Form
    public void switchForm(ActionEvent event) throws SQLException {
        if(event.getSource() == dashboard_btn){
            dashBoard_form.setVisible(true);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customer_form.setVisible(false);
            statistics();
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
            //orderDisplay();
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
    private XYChart.Series barChartData = new XYChart.Series();
    public void displayBarChart() throws SQLException {
        customerBarChart.getData().clear();
        StatisticDAO statisticDAO = new StatisticDAOImpl();
        ResultSet result = statisticDAO.dayCustomersStatistic();
        while (result.next()){
            barChartData.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));

        }
        customerBarChart.getData().add(barChartData);
    }

    private XYChart.Series lineChartData = new XYChart.Series();
    public void displayLineChart() throws SQLException {
        dayLineChart.getData().clear();
        StatisticDAO statisticDAO = new StatisticDAOImpl();
        ResultSet result = statisticDAO.dayIncomesStatistic();
        while (result.next()){
            lineChartData.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
        }
        dayLineChart.getData().add(lineChartData);
    }

    public void statistics() throws SQLException {
        StatisticDAO statisticDAO = new StatisticDAOImpl();
        numOfCustomer.setText(String.valueOf(statisticDAO.getNumberOfCustomer()));
        dayIncome.setText("$" + String.valueOf(statisticDAO.getDayIncome()));
        monthIncome.setText("$" + String.valueOf(statisticDAO.getMonthIncome()));
        numOfProSold.setText(String.valueOf(statisticDAO.getSoldNumber()));
        displayBarChart();
        displayLineChart();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Đưa tất cả trong hàm này vào hàm switch form
        try {
            inventoryShowData();
            customerShowData();
            statistics();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        menuTime();
    }
}
