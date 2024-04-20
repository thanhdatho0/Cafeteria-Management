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

    private Alert alert;

    private Employee employee = null;
    private EmployeeDAO employeeDAO = null;

    public void loginBtn(){
        if(si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Massage");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect username/password");
            alert.showAndWait();
        }else {
            employee = new Employee();
            employee.setUsername(si_username.getText());
            employee.setPassword(si_password.getText());

            employeeDAO = new EmployeeDAOImpl();
            try {
                if(employeeDAO.login(employee).next()){
                    Data.username = si_username.getText();
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
            employee = new Employee(0,
                    su_username.getText(),
                    su_password.getText(),
                    (String)su_question.getSelectionModel().getSelectedItem(),
                    su_answer.getText());
            employeeDAO = new EmployeeDAOImpl();
            try {
                if(employeeDAO.isUserExist(employee).next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText(su_username.getText() + " is already taken");
                    alert.showAndWait();
                }else if(employee.getPassword().length() < 8){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid password, at least 8 characters needed");
                    alert.showAndWait();
                }else {
                    employeeDAO.insert(employee);

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
            employee = new Employee();
            employee.setUsername(fp_username.getText());
            employee.setQuestion((String)fp_question.getSelectionModel().getSelectedItem());
            employee.setAnswer(fp_answer.getText());
            try {
                employeeDAO = new EmployeeDAOImpl();
                if(employeeDAO.isTrueInfo(employee).next()){
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
//            String getDate = "SELECT date FROM employee WHERE username = '"+ fp_username.getText() +"'";
//            connect = Database.connectDB();
            employee = new Employee();
            employee.setPassword(rsp_newPassword.getText());
            employee.setUsername(fp_username.getText());
            try{
                new EmployeeDAOImpl().updatePass(employee);

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