/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author amine
 */
public class DashboardController implements Initializable {
    
    /*Containers to View*/
    @FXML
    private AnchorPane viewPages;
    
    
    /*NavBar Buttons*/
    @FXML
    private Button btnHome;
    @FXML
    private Button btnTournois;
    @FXML
    private Button btnChat;
    
    
    
    /*Scroll NavBar Buttons*/
    @FXML
    public void switchForm(ActionEvent event) throws IOException{
        if(event.getSource()== btnTournois){
            Parent fxml= FXMLLoader.load(getClass().getResource("listTournois.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        }else if(event.getSource()==btnChat){
            Parent fxml= FXMLLoader.load(getClass().getResource("listChat.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
