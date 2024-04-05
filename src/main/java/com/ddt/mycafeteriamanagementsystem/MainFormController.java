package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

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

    //Biến trong inventory_form





























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

    private Alert alert;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public ObservableList<ProductData> cardListData = FXCollections.observableArrayList();

    //Inventory function.........











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
                        result.getDouble("price"),
                        result.getString("image"));

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

            }catch (Exception e){e.printStackTrace();}
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        menuDisplayCard();
    }
}
