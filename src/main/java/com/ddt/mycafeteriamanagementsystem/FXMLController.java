package com.ddt.mycafeteriamanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class FXMLController {

    //Login Form Attribute: Cac thuoc tinh cua form dang nhap
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
    //END

    //Slider Side Attribute
    @FXML
    private AnchorPane side_form;

    @FXML
    private Button side_createBtn;

    @FXML
    private Button side_alreadyHaveBtn;
    //END

    //Sign Up Attribute
    @FXML
    private AnchorPane su_signupForm;

    @FXML
    private TextField su_username;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_fullName;

    @FXML
    private ComboBox<?> su_question;

    @FXML
    private TextField su_answer;

    @FXML
    private Button su_signupBtn;
    //END

    //Forgot Pass Form Attribute
    @FXML
    private AnchorPane fp_form;

    @FXML
    private TextField fp_username;

    @FXML
    private ComboBox<?> fp_question;

    @FXML
    private TextField fp_answer;

    @FXML
    private Button fp_proceedBtn;

    @FXML
    private Button fp_backBtn;
    //END

    //Reset Pass Form Attribute
    @FXML
    private AnchorPane rsp_form;

    @FXML
    private PasswordField rsp_newPassword;

    @FXML
    private PasswordField rsp_confirmPassword;

    @FXML
    private Button rsp_resetBtn;

    @FXML
    private Button rsp_backBtn;
    //END

    //Employee Class



    //Trong slide mon JAVA co nhac toi may cai nay :))
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    public void loginBtn(){
        if(si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Massage");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect username/password");
            alert.showAndWait();
        }else {
            String slctData = "SELECT username, password FROM employee WHERE username = ? and password = ?";
            connect = Database.connectDB();
            try {
                prepare = connect.prepareStatement(slctData);
                prepare.setString(1, si_username.getText());
                prepare.setString(2, si_password.getText());

                result = prepare.executeQuery();

                if(result.next()){
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    employee = new Employee();
                    employee.setName(String.valueOf(su_fullName));

                    // an cua so login de mo cua so moi
                    si_loginBtn.getScene().getWindow().hide();

                    //Load MainFxml
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("mainForm.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Cafeteria!");
                    stage.setResizable(true);
                    stage.setScene(scene);
                    stage.show();

                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username/password");
                    alert.showAndWait();
                }
            }catch (Exception e){e.printStackTrace();}
        }
    }

    public void regisBtn(){
        //validate form dang ky
        if(su_username.getText().isEmpty()
                || su_fullName.getText().isEmpty()
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
            String regisData = "INSERT INTO employee (name, username, password, question, answer, date)" + "VALUES(?, ?, ?, ?, ?, ?)";
            connect = Database.connectDB();

            try {

                String usernameCheck = "SELECT username FROM employee WHERE username = '"+ su_username.getText() + "'";
                prepare = connect.prepareStatement(usernameCheck);
                result = prepare.executeQuery();


                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText(su_username.getText() + " is already taken");
                    alert.showAndWait();
                }else if(su_password.getText().length() < 8){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid password, at least 8 characters needed");
                    alert.showAndWait();
                }else {
                    prepare = connect.prepareStatement(regisData);

                    prepare.setString(1, su_fullName.getText());
                    prepare.setString(2, su_username.getText());
                    prepare.setString(3, su_password.getText());
                    prepare.setString(4, (String)su_question.getSelectionModel().getSelectedItem());
                    prepare.setString(5, su_answer.getText());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully!");
                    alert.show();

                    su_username.setText("");
                    su_password.setText("");
                    su_question.getSelectionModel().clearSelection();
                    su_answer.setText("");

                    TranslateTransition slider = new TranslateTransition();

                    slider.setNode(side_form);
                    slider.setToX(0);
                    slider.setDuration(Duration.seconds(.5));

                    slider.setOnFinished((ActionEvent e) ->{
                        side_alreadyHaveBtn.setVisible(false);
                        side_createBtn.setVisible(true);
                    });

                    slider.play();
                }

            }catch (Exception e){e.printStackTrace();}
        }
    }

    private final String[] questionList = {
            "What is your fav color?",
            "How many ex did you have?",
            "What is your bff name?"
    };



    public void showQuestionList(ComboBox<?> cb){
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
        //su_question.setItems(listData);
        cb.setItems(listData);
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

            showQuestionList(su_question);

            slider.play();
        } else if (event.getSource() == side_alreadyHaveBtn) {
            fp_form.setVisible(false);
            si_loginForm.setVisible(true);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) ->{
                side_alreadyHaveBtn.setVisible(false);
                side_createBtn.setVisible(true);
            });

            slider.play();
        }


    }

    public void forgotPassLink(){
        si_loginForm.setVisible(false);
        fp_form.setVisible(true);
        showQuestionList(fp_question);
    }

    public void backToLogin(){
        backBtn(fp_form, si_loginForm);
    }

    public void backToForgotPassForm(){
        backBtn(rsp_form, fp_form);
    }

    public void backBtn(AnchorPane recent, AnchorPane previous){
        recent.setVisible(false);
        previous.setVisible(true);
    }

    public void proceedBtn(){
        if(fp_username.getText().isEmpty()
           || fp_question.getSelectionModel().getSelectedItem() == null
           || fp_answer.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect username/answer");
            alert.showAndWait();
        }else {
            String checkTrueData = "SELECT username, question, answer FROM employee WHERE username = ? AND question = ? AND answer = ?";
            connect = Database.connectDB();
            try {
                prepare = connect.prepareStatement(checkTrueData);
                prepare.setString(1, fp_username.getText());
                prepare.setString(2, (String) fp_question.getSelectionModel().getSelectedItem());
                prepare.setString(3, fp_answer.getText());
                result = prepare.executeQuery();

                if(result.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully");
                    alert.showAndWait();
                    fp_form.setVisible(false);
                    rsp_form.setVisible(true);
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username/answer");
                    alert.showAndWait();
                }

            }catch (Exception e){e.printStackTrace();}
        }

    }

    public void resetPassBtn(){
        if(rsp_newPassword.getText().isEmpty() || rsp_confirmPassword.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else if (!rsp_confirmPassword.getText().equals(rsp_confirmPassword.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Not match");
            alert.showAndWait();
        }else {
            String getDate = "SELECT date FROM employee WHERE username = '"+ fp_username.getText() +"'";
            connect = Database.connectDB();
            try{
                prepare = connect.prepareStatement(getDate);
                result = prepare.executeQuery();

                String date = "";
                if(result.next()){
                    date = result.getString("date");
                }

                String updatePass = "UPDATE employee SET password = '"+ rsp_newPassword.getText() +"'" +
                        ", date = '"+ date +"' WHERE username = '"+ fp_username.getText() +"'";

                prepare = connect.prepareStatement(updatePass);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Done reset password");
                alert.showAndWait();

                backBtn(rsp_form, si_loginForm);
            }catch (Exception e){e.printStackTrace();}
        }
    }


}