package com.ddt.mycafeteriamanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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


    //Trong slide mon JAVA co nhac toi may cai nay :))
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    public void regisBtn(){
        //validate form dang ky
        if(su_username.getText().isEmpty()
                || su_password.getText().isEmpty()
                || su_question.getSelectionModel().getSelectedItem() == null
                || su_answer.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Massage");
            alert.setHeaderText(null);
            alert.setContentText("May bi mu` ak");
            alert.show();
        }else {
            String regisData = "INSERT INTO employee (username, password, question, answer)" + "VALUES(?, ?, ?, ?)";
            connect = Database.connectDB();

            try {

                prepare = connect.prepareStatement(regisData);
                prepare.setString(1, su_username.getText());
                prepare.setString(2, su_password.getText());
                prepare.setString(3, (String)su_question.getSelectionModel().getSelectedItem());
                prepare.setString(4, su_answer.getText());

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Massage");
                alert.setHeaderText(null);
                alert.setContentText("Successfully!");
                alert.show();

                TranslateTransition slider = new TranslateTransition();

                slider.setNode(side_form);
                slider.setToX(0);
                slider.setDuration(Duration.seconds(.5));

                slider.setOnFinished((ActionEvent e) ->{
                    side_alreadyHaveBtn.setVisible(false);
                    side_createBtn.setVisible(true);
                });

                slider.play();

                su_username.setText("");
                su_password.setText("");
                su_question.getSelectionModel().clearSelection();
                su_answer.setText("");


            }catch (Exception e){e.printStackTrace();}
        }
    }

    private final String[] questionList = {
            "What is your fav color?",
            "How many ex did you have?",
            "What is your bff name?"
    };



    public void showQuestionList(){
        List<String> listOfQuest = new ArrayList<String>();
        //Do du lieu tu mang vao list
        /* Cach 1:
        for(String data : questionList){
            listOfQuest.add(data);
        }
         */
        // Cach 2:
        Collections.addAll(listOfQuest, questionList);
        ObservableList listData = FXCollections.observableArrayList(listOfQuest);
        su_question.setItems(listData);
    }

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

            showQuestionList();

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