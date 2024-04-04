package com.ddt.mycafeteriamanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainFormController implements Initializable {
    @FXML
    private Button customers_btn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button logout_btn;

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

    }
}
