package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class AddInventController implements Initializable {
    private Product product;
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
    private ResultSet result;
    private String Path;
    private String[]  typeList = {"Drink", "Fast Food", "Main Food"};
    private String[] statusList = {"Available", "Unavailable"};
    private Categories categories = null;
    private CategoriesDAOImpl categoriesDAO = null;
    int categories_id[];

    public void setAddInvent_form(Product product)
    {
        this.product = product;
        invent_add_id.setText(product.getProd_id());
        invent_add_name.setText(product.getProd_name());
        invent_add_stock.setText((String.valueOf(product.getStock())));
        invent_add_price.setText(String.valueOf(product.getPrice()));
        invent_add_type.setValue(product.getCategories().getTypeName());
        invent_add_status.setValue(product.getStatus());

        this.Path = product.getImage();

        String path = "File:" + product.getImage();
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
        for (String data : typeList) {
            try {
                categories = new Categories(0, data);
                if(!CategoriesDAOImpl.checkCategories(categories)){
                    categoriesDAO = new CategoriesDAOImpl();
                    categoriesDAO.insert(categories);
                }
                else continue;

            } catch (Exception e) {}
        }

        int index = 0;
        categories_id = new int[100];

        categories = new Categories();
        try {
            categoriesDAO = new CategoriesDAOImpl();
            ResultSet resultSet = categoriesDAO.getAllCate(categories);
            while (resultSet.next()){
                categories_id[index] = resultSet.getInt("id");
                index++;
                typeL.add(resultSet.getString("typeName"));
            }
        } catch (SQLException e) {e.printStackTrace();}
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
                invent_add_status.getSelectionModel().getSelectedItem() == null ||
                invent_add_imageView.getImage() == null
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
            try
            {
                result = ProductDAOimpl.getInstance().check(invent_add_id.getText());

                if (result.next())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setContentText(invent_add_id.getText() + " is already taken");
                    alert.showAndWait();
                }
                else
                {
                    /* thay '\' trong link thanh '\\'
                     do trong '\' trong java dc hieu la ky tu dac biet
                     nen phem 1 dau '\' de tranh nham lan voi ky tu dac biet
                     \ = \\ va \\ = \\\\
                     */
                    String path = this.Path;
                    path = path.replace("\\", "\\\\");

                    Date date = new Date(System.currentTimeMillis());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    ProductDAOimpl.getInstance().insert(new Product(invent_add_id.getText(), invent_add_name.getText(),
                            new Categories(categories_id[invent_add_type.getSelectionModel().getSelectedIndex()]),
                            Integer.parseInt(invent_add_stock.getText()),
                            Double.parseDouble(invent_add_price.getText()),
                            invent_add_status.getSelectionModel().getSelectedItem(),
                            path, sqlDate));

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
                || invent_add_imageView == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String path = this.product.getImage();
            path = path.replace("\\", "\\\\");

            Date date = new Date(System.currentTimeMillis());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Product ID: " + invent_add_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                invent_add_save.setVisible(true);
                invent_add_update.setVisible(false);

                if (option.get().equals(ButtonType.OK)) {

                    ProductDAOimpl.getInstance().update(new Product(invent_add_id.getText(), invent_add_name.getText(),
                            new Categories(categories_id[invent_add_type.getSelectionModel().getSelectedIndex()]),
                            Integer.parseInt(invent_add_stock.getText()),
                            Double.parseDouble(invent_add_price.getText()),
                            invent_add_status.getSelectionModel().getSelectedItem(),
                            path, sqlDate));

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
          this.Path = file.getAbsolutePath();
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