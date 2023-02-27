/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author marti
 */
public class HeapMain extends Application {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Heap.fxml"));
        stage.setTitle("Heap_Pamatky");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
    
}
