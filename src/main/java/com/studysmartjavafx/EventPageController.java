package com.studysmartjavafx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class EventPageController implements Initializable {

    @FXML
    private ImageView eventImageViewer;

    @FXML
    private Label eventNameLabel;

    @FXML
    private Button joinEventButton;

    public void Stringbruh(){
        System.out.println("bruh");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

}
