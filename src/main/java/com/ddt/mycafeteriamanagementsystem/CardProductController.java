package com.ddt.mycafeteriamanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CardProductController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Label prod_available;

    @FXML
    private Button prod_card_btn;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    private ProductData productData;
    private Image image;

    public void setData(ProductData productData){
        this.productData = productData;

        prod_name.setText(productData.getProductName());
        prod_price.setText(String.valueOf(productData.getPrice()) + " VND");
        String path = "File:" + productData.getImage();
        image = new Image(path, 160, 102, false, true);
        prod_imageView.setImage(image);
        prod_available.setText(String.valueOf(productData.getStock()) + " Available");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
