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

    private Customer customer = null;
    private CustomerDAO  customerDAO = null;
    private Product product = null;
    private ProductDAO productDAO = null;

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
                alert.setContentText("The product is out of stock/The product is unavailable, please wait for updates");
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

                if(checkStck == 0){
                    product = new Product(0, prodID, prod_name.getText(), type, 0, pr, "Unavailable", prod_image, productData.getDate());
                    productDAO = new ProductDAOImpl();
                    productDAO.update(product);
                }

                if(checkStck < qty){
                    alert =  new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid. This product is out of stock");
                    alert.showAndWait();
                }
                else{
                    prod_image = prod_image.replace("\\", "\\\\");
                    totalP = (qty * pr);

                    customer = new Customer(0, Data.cID, prodID, prod_name.getText(), type, qty, totalP, Data.username, prod_image);
                    customerDAO = new CusotmerDAOImpl();
                    customerDAO.insert(customer);

                    int upStock = checkStck - qty;

                    product = new Product(0, prodID, prod_name.getText(), type, upStock, pr, check, prod_image, productData.getDate());
                    productDAO = new ProductDAOImpl();
                    productDAO.update(product);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added");
                    alert.showAndWait();

                    mainFormController.menuGetTotal();
                }
            }
        }catch (Exception e){e.printStackTrace();}
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setQuantity();
    }
}