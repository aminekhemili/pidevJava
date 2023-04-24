/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Chat;
import entities.Tournois;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ChatServices;
import services.TournoisServices;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AjoutTournoisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    ChatServices chs=new ChatServices();
    List<Chat> chat=chs.Show();
    private int chatId =-1;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String,Integer> valuesMap=new HashMap<>();
        for(Chat c : chat){
            textChat.getItems().add(c.getMessage_chat());
            valuesMap.put(c.getMessage_chat(), c.getId_chat());
        }
        
        textChat.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = textChat.getValue();
            int SelectedValue =0;
            SelectedValue=valuesMap.get(SelectedOption);
            chatId = SelectedValue;
        });
    }   
    
    
    @FXML
    private AnchorPane addTournoisPane;

    @FXML
    private Button btnAddTournois;

    @FXML
    private Button btnReturnTournois;

    @FXML
    private ComboBox<String> textChat;

    @FXML
    private DatePicker textDateTournois;

    @FXML
    private TextField textDetail;

    @FXML
    private TextField textNbJoueur;

    @FXML
    private TextField textNomJeu;
    
    
    
    @FXML
    private void AjoutTournois(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddTournois){
            if (textNomJeu.getText().isEmpty() || textNbJoueur.getText().isEmpty() 
                    || textDetail.getText().isEmpty() || chatId == -1) 
            {    
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre tournois.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {    
                ajouterTournois();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre Tournois a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                send_SMS();
                clearFieldsTournois();
            }
        }
        
    }
    
    
    private void clearFieldsTournois() {
        textNomJeu.clear();
        textNbJoueur.clear();
        textDetail.clear();
    }
    
    
    private void ajouterTournois() {
        
         // From Formulaire
        int nbJoueur = Integer.parseInt(textNbJoueur.getText());
        String nomJeu = textNomJeu.getText();
        String detail = textDetail.getText();
        Date dateT = null;
        try{
            LocalDate localDate= textDateTournois.getValue();
            if(localDate != null){
                Instant instant=Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                dateT=Date.from(instant);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        int idChat = chatId;
       
        
        MyDB db = MyDB.getInstance();
        Tournois act = new Tournois(
                nbJoueur, nomJeu, detail, dateT, idChat);
        TournoisServices as = new TournoisServices();
        as.ajouter(act);
    }
    
    
    void send_SMS (){
        // Initialisation de la bibliothèque Twilio avec les informations de votre compte
        String ACCOUNT_SID = "AC6494db05b526dfe087b015382fbb19e3";
        String AUTH_TOKEN = "93f189853de582eb92de44a75e4b203c";
             
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String recipientNumber = "+21620456529";
            String message = "Bonjour Mr ,\n"
                    + "Nous sommes ravis de vous informer qu'un tournois a été ajouté.\n "
                    + "Veuillez contactez l'administration pour plus de details.\n "
                    + "Merci de votre fidélité et à bientôt chez DevUp.\n"
                    + "Cordialement,\n"
                    + "DevUp";
                
            Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("+16813223646"),message).create();
                
            System.out.println("SMS envoyé : " + twilioMessage.getSid());
        
         
     }
    
}
