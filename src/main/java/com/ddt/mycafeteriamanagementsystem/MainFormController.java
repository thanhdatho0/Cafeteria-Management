package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
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
    private AnchorPane dashBoard_form;











    //Biến trong inventory_form
    @FXML
    private Button inventory_btn_add;

    @FXML
    private Button inventory_reloadBtn;

    @FXML
    private TableColumn<?, ?> inventory_col_change;

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








    //Biến trong menu_form
    @FXML
    private Button menu_all_btn;

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
    private Button menu_fastFood_btn;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private Button menu_mainFood_btn;

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
    //
    @FXML
    private AnchorPane main_form;

    private Alert alert;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image images;
    private ObservableList<ProductData> cardListData = FXCollections.observableArrayList();
    private ObservableList<ProductData> inventoryListData;
    private ObservableList<ProductData> orderListData = FXCollections.observableArrayList();
    private int cID;

    //DashBoard function..............










    //Inventory function
    @FXML
    public void addDisplay_invent() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addInventory.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Cafeteria!");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){e.printStackTrace();}
    }

    // Hien data len bang
    public void inventoryShowData()
    {
        inventoryListData = InventoryDataList();

        inventory_col_id.setCellValueFactory(new PropertyValueFactory<>("productID"));
        inventory_col_name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        inventory_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        inventory_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inventory_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        inventory_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        inventory_tableView.setItems(inventoryListData);
    }

    public void inventoryLoadData(ActionEvent event){
        if(event.getSource() == inventory_reloadBtn) {
            inventoryListData = InventoryDataList();
            inventory_tableView.setItems(inventoryListData);
        }
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
                        result.getString("type"),
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

    //Product function
    public ObservableList<ProductData> menuGetData(){
        String sql = "SELECT * FROM product";

        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData productData;
            while(result.next()){
                productData = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(productData);
            }
        }catch (Exception e){e.printStackTrace();}

        return listData;
    }

    public void menuDisplayCard(){
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

    //Order function
    public ObservableList<ProductData> orderGetData(){
        String sql = "SELECT * FROM product";

        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData productData;
            while(result.next()){
                productData = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(productData);
            }
        }catch (Exception e){e.printStackTrace();}

        return listData;
    }

    public void orderDisplay(){
        orderListData.clear();
        orderListData.addAll(orderGetData());

        int row = 0;
        int column = 0;

        order_gridPane.getChildren().clear();
        order_gridPane.getRowConstraints().clear();
        order_gridPane.getColumnConstraints().clear();

        for(int q = 0; q <  orderListData.size(); q++){

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
//                GridPane.setMargin(pane, new Insets(17));

            }catch (Exception e){e.printStackTrace();}

        }
    }



    //Chuyển Form
    public void switchForm(ActionEvent event){
        if(event.getSource() == dashboard_btn){
            dashBoard_form.setVisible(true);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
//            customers_form.setVisible(false);
        }
        else if(event.getSource() == inventory_btn){
            dashBoard_form.setVisible(false);
            inventory_form.setVisible(true);
            menu_form.setVisible(false);
//            customers_form.setVisible(false);

            inventoryShowData();

        }
        else if(event.getSource() == menu_btn){
            dashBoard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(true);
//            customers_form.setVisible(false);
            menuDisplayCard();
            orderDisplay();

        }
        else if(event.getSource() == customers_btn){
            dashBoard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
//            customers_form.setVisible(true);
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Đưa tất cả trong hàm này vào hàm switch form
        inventoryShowData();
    }
}
