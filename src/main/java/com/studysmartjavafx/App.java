package com.studysmartjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Scanner scanner = new Scanner(System.in);
        LoginRegister loginRegister = new LoginRegister();
        EventManager eventManager = new EventManager();
        BookingManager bookingManager = new BookingManager();
        FriendManager friendManager = new FriendManager();
        ViewProfile viewProfile = new ViewProfile(eventManager, bookingManager, friendManager);
        QuizManager quizManager = new QuizManager(); 
        ExtendedFriendManager friendManagerS = new ExtendedFriendManager();
        EventManager createEvent = new EventManager();

        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginSignUpMain.fxml"));
            Parent root = loader.load();
            MainPageController mainPageController = loader.getController();
            mainPageController.setDependencies(loginRegister, eventManager, bookingManager, friendManager, viewProfile, quizManager, friendManagerS, createEvent);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("StudySmart");
            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

       
    }

}