package com.ddt.mycafeteriamanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class CardProductController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Button prod_add_btn;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    @FXML
    private Spinner<Integer> prod_spinner;

//    private Product product;
    private Image image;
    private SpinnerValueFactory<Integer> spin;
    private String prodID;
    private String prod_image;
    private int categories_id;
    private String prod_date;

    private double pr;
    private Product product = null;


    public void setData(Product product){
        this.product = product;

        prod_image = product.getImage();
        prod_date = String.valueOf(product.getDate());
        categories_id = product.getCategories().getId();
        prodID = product.getProd_id();

        prod_name.setText(product.getProd_name());
        prod_price.setText(String.valueOf(product.getPrice()) + " VND");
        String path = "File:" + product.getImage();
        image = new Image(path, 160, 102, false, true);
        prod_imageView.setImage(image);
        pr = product.getPrice();
    }

    public void setQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
        prod_spinner.setEditable(true);
    }

    public void addCard() throws SQLException {
        if(prod_spinner.getValue() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error Message");
            alert.setContentText("Invalid number of Product");
            alert.showAndWait();
            return;
        }
        Product product;
        ProductDAO productDAO = new ProductDAOImpl();
        product = productDAO.getProductByName(prod_name.getText());

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setProduct(product);
        orderDetails.setQuantity(prod_spinner.getValue());

        if(Order.items.isEmpty()) {
            Order.items.add(orderDetails);
            System.out.println(Order.items);
            return;
        }
        AtomicBoolean check = new AtomicBoolean(false);
        Order.items.forEach(item ->{
           if(item.getProduct().getId() == orderDetails.getProduct().getId()){
               check.set(true);
               item.setQuantity(item.getQuantity()+orderDetails.getQuantity());
               System.out.println(Order.items);
           }
        });
        if(!check.get()){
            Order.items.add(orderDetails);
            System.out.println(Order.items);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();
    }
}