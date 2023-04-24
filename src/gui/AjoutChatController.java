/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Chat;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import services.ChatServices;
import services.TournoisServices;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutChatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private AnchorPane addChatPane;

    @FXML
    private Button btnAddChat;

    @FXML
    private TextArea textMessage;
    
    
    
    
    @FXML
    private void AjoutChat(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddChat){
            if (textMessage.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre chat.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {    
                ajouterChat();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre chat a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsChat();
            }
        }
        
    }
    
    
    private void clearFieldsChat() {
        textMessage.clear();
    }
    
    
    private void ajouterChat() {
        
         // From Formulaire
        String msg = textMessage.getText();
        MyDB db = MyDB.getInstance();
        Chat act = new Chat(msg);
        ChatServices as = new ChatServices();
        as.ajouter(act);
    }
    
}
