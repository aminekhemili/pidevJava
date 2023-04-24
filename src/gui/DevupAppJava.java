/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author amine
 */
public class DevupAppJava extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("AjoutTournois.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("AjoutChat.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("listTournois.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("listChat.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
