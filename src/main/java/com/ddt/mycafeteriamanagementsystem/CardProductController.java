package com.ddt.mycafeteriamanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

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

    private ProductData productData;
    private Image image;
    private SpinnerValueFactory<Integer> spin;
    private int qty;
    private String prodID;
    private String prod_image;
    private String type;
    private String prod_date;


    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;
    private double totalP;
    private double pr;



    public void setData(ProductData productData){
        this.productData = productData;

        prod_image = productData.getImage();
        prod_date = String.valueOf(productData.getDate());
        type = productData.getType();
        prodID = productData.getProductID();

        prod_name.setText(productData.getProductName());
        prod_price.setText(String.valueOf(productData.getPrice()) + " VND");
        String path = "File:" + productData.getImage();
        image = new Image(path, 160, 102, false, true);
        prod_imageView.setImage(image);
        pr = productData.getPrice();

    }

    public void setQuantity(){
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
        prod_spinner.setEditable(true);
    }

    public void addCard(){
        MainFormController mainFormController = new MainFormController();
        mainFormController.customerID();

        qty = prod_spinner.getValue();
        String check = "";
        String checkAvailable = "SELECT status FROM product WHERE prod_id = '" + prodID + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(checkAvailable);
            result = prepare.executeQuery();

            if(result.next()){
                check = result.getString("status");
            }
            if(!check.equals("Available") || qty == 0){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Some thing wrong");
                alert.showAndWait();
            }
            else{
                int checkStck = 0;
                String checkStock = "SELECT stock FROM product WHERE prod_id = '"
                        + prodID + "'";

                prepare = connect.prepareStatement(checkStock);
                result = prepare.executeQuery();

                if(result.next()){
                    checkStck = result.getInt("stock");
                }
                if(checkStck < qty){
                    alert =  new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid. This product is out of stock");
                    alert.showAndWait();
                }
                else{
                    String insertData = "INSERT INTO customer"
                            + "(customer_id, prod_name, quantity, price, date/*, em_username*/)"
                            + "VALUES(?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(Data.cID));
                    prepare.setString(2, prod_name.getText());
                    prepare.setString(3, String.valueOf(qty));
                    totalP = (qty * pr);
                    prepare.setString(4, String.valueOf(totalP));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(5, String.valueOf(sqlDate));

//                    prepare.setString(6, Data.username);
                    prepare.executeUpdate();

                    int upStock = checkStck - qty;
                    prod_image = prod_image.replace("\\", "\\\\");

                    String updateStock = "UPDATE product SET prod_name = '"
                            +  prod_name.getText() + "', type = '"
                            + type + "', stock = " + upStock + ", price = " + pr
                            + ", status = '"
                            + check + "', image = '"
                            + prod_image + "', date = '"
                            + prod_date + "' WHERE prod_id = '"
                            + prodID + "'";
                    prepare = connect.prepareStatement(updateStock);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();
    }
}