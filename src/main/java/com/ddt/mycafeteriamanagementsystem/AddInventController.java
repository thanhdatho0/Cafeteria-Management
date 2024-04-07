package com.ddt.mycafeteriamanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AddInventController implements Initializable {


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
    private Image image;


    private String[]  typeList = {"Drink", "Fast Food", "Main Food"};
    private String[] statusList = {"Available", "Unvailable"};
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

        ObservableList listData = FXCollections.observableArrayList(statusL);
        invent_add_status.setItems(listData);
    }
    @FXML
    public void close() {
        invent_add_cancel.getScene().getWindow().hide();
    }

    public void inventoryAdd()
    {
        if (invent_add_id.getText().isEmpty() ||
                invent_add_name.getText().isEmpty() ||
                invent_add_stock.getText().isEmpty() ||
                invent_add_price.getText().isEmpty() ||
                invent_add_type.getSelectionModel().getSelectedItem() == null ||
                Data.path == null
        );
        // DANG XEM

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
