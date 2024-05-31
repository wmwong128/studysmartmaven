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

public class LogInPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    Scanner scanner ;
    LoginRegister loginRegister;
    EventManager eventManager;
    BookingManager bookingManager;
    FriendManager friendManager;
    ViewProfile viewProfile;
    QuizManager quizManager;
    ExtendedFriendManager friendManagerS;
    EventManager createEvent;

    @FXML
    private TextField emailTextField;
    private TextField passwordTextField;


    public void setDependencies(LoginRegister loginRegister, EventManager eventManager, BookingManager bookingManager,FriendManager friendManager,ViewProfile viewProfile,QuizManager quizManager,ExtendedFriendManager friendManagerS,EventManager createEvent){
        this.loginRegister = loginRegister;
        this.eventManager = eventManager;
        this.bookingManager = bookingManager;
        this.friendManager = friendManager;
        this.viewProfile = viewProfile;
        this.quizManager = quizManager;
        this.friendManagerS = friendManagerS;
        this.createEvent = createEvent;
    }
    
    public void switchToSignUp(ActionEvent event) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
            Parent root = loader.load();
            // RegisterPageController registerPageController = loader.getController();
            // registerPageController.setDependencies(loginRegister, eventManager, bookingManager, friendManager, viewProfile, quizManager, friendManagerS, createEvent);
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("error occured");
            e.printStackTrace(); // Print the stack trace of any IOException that occurs
        } catch (Exception e) {
            System.out.println("error occured");
            e.printStackTrace(); // Print the stack trace of any other exception that occurs
        }
        
    }

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            LogInPageController logInPageController = loader.getController();
            logInPageController.setDependencies(loginRegister, eventManager, bookingManager, friendManager, viewProfile, quizManager, friendManagerS, createEvent);
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("no error occured");
            e.printStackTrace(); // Print the stack trace of any IOException that occurs
        } catch (Exception e) {
            System.out.println("error occured");
            e.printStackTrace(); // Print the stack trace of any other exception that occurs
        }
        
    }

    public void switchToMainPage(ActionEvent event) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSignUpMain.fxml"));
            Parent root = loader.load();
            MainPageController mainPageController = loader.getController();
            mainPageController.setDependencies(loginRegister, eventManager, bookingManager, friendManager, viewProfile, quizManager, friendManagerS, createEvent);
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("error occured");
            e.printStackTrace(); // Print the stack trace of any IOException that occurs
        } catch (Exception e) {
            System.out.println("error occured");
            e.printStackTrace(); // Print the stack trace of any other exception that occurs
        }
    }

    public void pressLoginButtomButton(ActionEvent event) throws IOException{
        try {
            String enteredEmail = emailTextField.getText();
            String enteredPassword = emailTextField.getText();

            PasswordHashing passwordHashing = new PasswordHashing();
            
            String hashedPassword = passwordHashing.hashPassword(enteredPassword);

            System.out.println(enteredEmail);
            System.out.println(enteredPassword);

            //input user checking method here

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
