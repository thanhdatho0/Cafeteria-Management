package com.ddt.mycafeteriamanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class FXMLController {

    @FXML
    private AnchorPane si_loginForm;

    @FXML
    private TextField si_username;

    @FXML
    private PasswordField si_password;

    @FXML
    private Hyperlink si_forgotPass;

    @FXML
    private Button si_loginBtn;

    @FXML
    private AnchorPane side_form;

    @FXML
    private Button side_createBtn;

    @FXML
    private Button side_alreadyHaveBtn;

    @FXML
    private AnchorPane su_signupForm;

    @FXML
    private TextField su_username;

    @FXML
    private PasswordField su_password;

    @FXML
    private ComboBox<?> su_question;

    @FXML
    private TextField su_answer;

    @FXML
    private Button su_signupBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void switchForm(ActionEvent event){

        TranslateTransition slider = new TranslateTransition();

        slider.setNode(side_form);

        if(event.getSource() == side_createBtn){
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) ->{
                side_alreadyHaveBtn.setVisible(true);
                side_createBtn.setVisible(false);
            });

            slider.play();
        } else if (event.getSource() == side_alreadyHaveBtn) {
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) ->{
                side_alreadyHaveBtn.setVisible(false);
                side_createBtn.setVisible(true);
            });

            slider.play();
        }


    }

}