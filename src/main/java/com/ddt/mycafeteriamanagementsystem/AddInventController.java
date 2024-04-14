package com.ddt.mycafeteriamanagementsystem;

import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class AddInventController implements Initializable {

    private ProductData productData;
    @FXML
    private AnchorPane addInvent_form;

    @FXML
    private Button invent_add_browBtn;

    @FXML
    private Button invent_add_cancel;

    @FXML
    private TextField invent_add_id;

    @FXML
    private ImageView invent_add_imageView;

    @FXML
    private TextField invent_add_name;

    @FXML
    private TextField invent_add_price;

    @FXML
    private Button invent_add_save;

    @FXML
    private ComboBox<String> invent_add_status;

    @FXML
    private TextField invent_add_stock;

    @FXML
    private ComboBox<String> invent_add_type;
    @FXML
    private Button invent_add_update;
    private Image image;
    private Alert alert;
    private Connection connect;
    private Statement statement;
    private ResultSet result;
    private PreparedStatement prepare;

    private String[]  typeList = {"Drink", "Fast Food", "Main Food"};
    private String[] statusList = {"Available", "Unavailable"};

    public void setAddInvent_form(ProductData productData)
    {

        this.productData = productData;
        invent_add_id.setText(productData.getProductID());
        invent_add_name.setText(productData.getProductName());
        invent_add_stock.setText((String.valueOf(productData.getStock())));
        invent_add_price.setText(String.valueOf(productData.getPrice()));
        invent_add_type.setValue(productData.getType());
        invent_add_status.setValue(productData.getStatus());

        Data.path = productData.getImage();
        Data.id = productData.getId();
        String path = "File:" + productData.getImage();
        image = new Image(path, 137,128,false,true);
        invent_add_imageView.setImage(image);

        invent_add_save.setVisible(false);
        invent_add_update.setVisible(true);
        invent_add_id.setEditable(false);
        invent_add_name.setEditable(false);
    }
    public void inventoryTypeList()
    {
        List<String> typeL = new ArrayList<>();
        for (String data : typeList)
            typeL.add(data);

        ObservableList listData = FXCollections.observableArrayList(typeL);
        invent_add_type.setItems(listData);
    }

    public void inventoryStatusList()
    {
        List<String> statusL = new ArrayList<>();
        for (String data : statusList)
            statusL.add(data);

        ObservableList<String> listData = FXCollections.observableArrayList(statusL);
        invent_add_status.setItems(listData);
    }
    @FXML
    public void close(MouseEvent event)
    {
        invent_add_cancel.getScene().getWindow().hide();
    }

    public void inventoryAdd(MouseEvent event)
    {
        if (invent_add_id.getText().isEmpty() ||
                invent_add_name.getText().isEmpty() ||
                invent_add_stock.getText().isEmpty() ||
                invent_add_price.getText().isEmpty() ||
                invent_add_type.getSelectionModel().getSelectedItem() == null ||
                Data.path == null
        )
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }
        else
        {
            // kiem tra id item
            String checkProdID = "select prod_id from product where prod_id = '"
                    + invent_add_id.getText() + "'";

            connect = Database.connectDB();

            try
            {
                statement = connect.createStatement();
                result = statement.executeQuery(checkProdID);

                if (result.next())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText(invent_add_id.getText() + " is already taken");
                    alert.showAndWait();
                }
                else
                {
                    String insertData = "insert into product "
                            + "(prod_id, prod_name, type, stock, price, status, image, date) "
                            + "values (?, ?, ?, ?, ?, ?, ?, ?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, invent_add_id.getText());
                    prepare.setString(2, invent_add_name.getText());
                    prepare.setString(3, (String) invent_add_type.getSelectionModel().getSelectedItem());
                    prepare.setString(4, invent_add_stock.getText());
                    prepare.setString(5, invent_add_price.getText());
                    prepare.setString(6, invent_add_status.getSelectionModel().getSelectedItem());

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");

                    prepare.setString(7, path);
                    Date date = new Date(System.currentTimeMillis());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    close(event);

                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void updateProduct(MouseEvent event)
    {
        if (invent_add_type.getSelectionModel().getSelectedItem() == null
                || invent_add_stock.getText().isEmpty()
                || invent_add_stock.getText().isEmpty()
                || invent_add_status.getSelectionModel().getSelectedItem() == null
                || Data.path == null || Data.id == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String path = Data.path;
            path = path.replace("\\", "\\\\");

            Date date = new Date(System.currentTimeMillis());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String updateData = "UPDATE product SET "
                    + "prod_id = '" + invent_add_id.getText()
                    + "', prod_name = '" + invent_add_name.getText()
                    + "', type = '" + invent_add_type.getSelectionModel().getSelectedItem()
                    + "', stock = '" + invent_add_stock.getText()
                    + "', price = '" + invent_add_price.getText()
                    + "', status = '" + invent_add_status.getSelectionModel().getSelectedItem()
                    + "', image = '" + path
                    + "', date = '"
                    + sqlDate + "' WHERE id = " + Data.id;

            connect = Database.connectDB();

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Product ID: " + invent_add_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                invent_add_save.setVisible(true);
                invent_add_update.setVisible(false);

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }

                close(event);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void inventoryBrowse()
    {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(null);

        if (file != null) {
            Data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 137, 128, false, true);

            invent_add_imageView.setImage(image);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inventoryTypeList();
        inventoryStatusList();
    }
}