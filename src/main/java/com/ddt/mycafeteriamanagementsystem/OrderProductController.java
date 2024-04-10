package com.ddt.mycafeteriamanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class OrderProductController implements Initializable {
    @FXML
    private AnchorPane order_form;

    @FXML
    private Button order_garbage_btn;

    @FXML
    private ImageView order_imageView;

    @FXML
    private Label order_name;

    @FXML
    private TextField order_note;

    @FXML
    private Label order_price;

    @FXML
    private Label order_quantity;

    @FXML
    private Label order_subtotal;

    private ProductData productData;
    private Image image;

    public void setData(ProductData productData){
        this.productData = productData;

        order_name.setText(productData.getProductName());
        order_price.setText(String.valueOf(productData.getPrice()) + " VND");
        String path = "File:" + productData.getImage();
        image = new Image(path, 40, 40, false, true);
        order_imageView.setImage(image);
        order_quantity.setText(String.valueOf(productData.getQuantity()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
