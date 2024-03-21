module com.ddt.mycafeteriamanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.ddt.mycafeteriamanagementsystem to javafx.fxml;
    exports com.ddt.mycafeteriamanagementsystem;
}