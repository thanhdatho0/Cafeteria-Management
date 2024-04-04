package com.ddt.mycafeteriamanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class mainFormController implements Initializable {
    @FXML
    private Button customers_btn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button logOut_btn;

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

//    private
//    public void logout(){
//        try {
//
//        }catch (Exception e){e.printStackTrace();}
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
