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
import java.util.Optional;
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
    private Alert alert;
    private Connection connect;
    private PreparedStatement prepare;
    private int getID;


    public void setData(ProductData productData){
        this.productData = productData;

        getID = productData.getId();
        order_name.setText(productData.getProductName());
        order_price.setText(String.valueOf(productData.getPrice()) + " VND");
        String path = "File:" + productData.getImage();
        image = new Image(path, 40, 40, false, true);
        order_imageView.setImage(image);
        order_quantity.setText(String.valueOf(productData.getQuantity()));
    }

    public void orderGarbageBtn(){
        String deleteData = "DELETE FROM customer WHERE id = " + getID;
        connect = Database.connectDB();
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete this order");
            Optional<ButtonType> option = alert.showAndWait();
            if(option.get().equals(ButtonType.OK)){
                prepare = connect.prepareStatement(deleteData);
                prepare.executeUpdate();
            }
            else{

            }

        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
