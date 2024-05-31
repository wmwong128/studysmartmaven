package com.studysmartjavafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class RegisterPageController implements Initializable{

    @FXML
    private TextField signUpUsernameTextField;
    @FXML
    private TextField signUpEmailTextField;
    @FXML
    private TextField signUpPasswordTextField;
    @FXML
    private Text AlreadyHaveAnAccountLabel;
    @FXML
    private ChoiceBox<String> signUpRoleChoiceBox;

    Scanner scanner ;
    // LoginRegister loginRegister;
    // EventManager eventManager;
    // BookingManager bookingManager;
    // FriendManager friendManager;
    // ViewProfile viewProfile;
    // QuizManager quizManager;
    // ExtendedFriendManager friendManagerS;
    // EventManager createEvent;




    private Stage stage;
    private Scene scene;
    private Parent root;

    // public void setDependencies(LoginRegister loginRegister, EventManager eventManager, BookingManager bookingManager,FriendManager friendManager,ViewProfile viewProfile,QuizManager quizManager,ExtendedFriendManager friendManagerS,EventManager createEvent){
    //     this.loginRegister = loginRegister;
    //     this.eventManager = eventManager;
    //     this.bookingManager = bookingManager;
    //     this.friendManager = friendManager;
    //     this.viewProfile = viewProfile;
    //     this.quizManager = quizManager;
    //     this.friendManagerS = friendManagerS;
    //     this.createEvent = createEvent;
    // }

    @FXML
    public void switchToMainPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LoginSignUpMain.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    public void makeUser(ActionEvent event) throws IOException{

        String email = signUpEmailTextField.getText();
        String userName = signUpUsernameTextField.getText();
        String password = signUpPasswordTextField.getText();
        String role = signUpRoleChoiceBox.getValue();

        User currentUser = new User(email, userName, password, role,s,s,s);
        double x = Math.random() * 1000 - 500;
        double y = Math.random() * 1000 - 500;
        currentUser.setLocationCoordinate("(" + x + ", " + y + ")");
        currentUser.setCurrentPoints(0);


        System.out.println(role);

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String[] roleChoices = {"Student","Educator", "Parent"};
        signUpRoleChoiceBox.getItems().setAll(roleChoices);
    }
}
