/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.studysmartjavafx;

/**
 *
 * @author limhu
 */
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class BookingDestination {
    public static void main(String[] args){
        try{
            PrintWriter out = new PrintWriter(new FileOutputStream("BookingDestination.txt"));
            out.write("Petrosains Science Discovery Centre\n-133.24, -12.33\nTech Dome Penang\n20.87, 9.63\nAgro Technology Park in MARDI\n-23.28, 16.55\nNational Science Centre\n-90.02, 226.48\nMarine Aquarium and Museum\n27.51, -136.98\nPusat Sains & Kreativiti Terengganu\n263.99, -57.31\nBiomedical Museum\n96.68, 127.54\nTelegraph Museum\n7.02, -359.28\nPenang Science Cluster\n21.33, -0.59");
            out.close();
        }catch(IOException e){
            System.out.println("Problem with file output");
        }
    }
}
