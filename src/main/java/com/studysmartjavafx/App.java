package com.studysmartjavafx;

import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
            System.out.println();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

       
    }

}