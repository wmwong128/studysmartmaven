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

public class MainPageController implements Initializable{

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

    @FXML
    public void switchToLogin(ActionEvent event) {
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
    

    @FXML
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


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("this is run");
        System.out.println("MainPageController initialized");
    }

}
