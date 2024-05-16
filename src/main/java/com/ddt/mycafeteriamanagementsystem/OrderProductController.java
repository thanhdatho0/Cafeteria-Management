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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    private Product product;
    private Image image;
    private Alert alert;
    private Connection connect;
    private PreparedStatement prepare;
    private int getID;


    public void setData(OrderDetails orderDetails){
        Product product = orderDetails.getProduct();
        getID = product.getId();
        order_name.setText(product.getProd_name());
        String path = "File:" + product.getImage();
        image = new Image(path, 40, 40, false, true);
        order_imageView.setImage(image);
        order_quantity.setText(String.valueOf(orderDetails.getQuantity()));
        order_subtotal.setText(String.valueOf(product.getPrice()) + " VND");
    }

    public void orderGarbageBtn(){
        Order.items = Order.items.stream()
                .filter(item -> item.getProduct().getId() != getID)
                .collect(Collectors.toList());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Information Message");
        alert.setContentText("Deleted out of Order! Please reload!");
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
