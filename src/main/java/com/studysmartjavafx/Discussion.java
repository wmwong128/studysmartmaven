package com.studysmartjavafx;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
import java.util.List;

public class Discussion {
    private String topic;
    private String message;
    private String timestamp;

    public Discussion(String topic, String message, String timestamp) {
        this.topic = topic;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getTopic() {
        return topic;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
    

