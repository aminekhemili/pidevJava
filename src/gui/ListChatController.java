/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Chat;
import entities.Tournois;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import services.ChatServices;
import services.TournoisServices;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ListChatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private TableColumn<Chat, Integer> IdChatCell;

    @FXML
    private TableColumn<Chat, String> MessageChatCell;
    
    @FXML
    private TableColumn<Chat, Date> DateChatCell;

    @FXML
    private Button bntAddChat;

    @FXML
    private Button btnDeleteChat;

    @FXML
    private AnchorPane listChatPane;

    @FXML
    private TableView<Chat> tableChat;

    @FXML
    private TextField txtSearchChat;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherChat();
    }    
    
    ObservableList<Chat> dataChat = FXCollections.observableArrayList();
    
    
    public void AfficherChat()
    {
        ChatServices cs = new ChatServices();
        cs.Show().stream().forEach((p) -> {
            dataChat.add(p);
        });
        IdChatCell.setCellValueFactory(new PropertyValueFactory<>("id_chat"));
        MessageChatCell.setCellValueFactory(new PropertyValueFactory<>("message_chat"));
        MessageChatCell.setCellFactory(TextFieldTableCell.forTableColumn());
        MessageChatCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Chat, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Chat, String> event) {
                Chat a = event.getRowValue();
                a.setMessage_chat(event.getNewValue());
                ChatServices cs = new ChatServices();
                cs.modifier(a);
            }
        });
        
        
        tableChat.setItems(dataChat);
        /*txtSearchAct.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obs, String oldText, String newText) {
                ActiviteService as = new ActiviteService();
                List<Activite> ae = as.Search(newText);
                tableActivite.getItems().setAll(ae);
            }
        });*/
        
    }
    
    @FXML
    private void supprimerChat(ActionEvent event) throws SQLException {
        ChatServices cs = new ChatServices();
        
        if (tableChat.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner le Chat à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce Chat ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de l'activité sélectionnée dans la vue de la table
            int id = tableChat.getSelectionModel().getSelectedItem().getId_chat();

            // Supprimer l'activité de la base de données
            cs.supprimer(id);
            // Rafraîchir la liste de données
            dataChat.clear();
            AfficherChat();
            // Rafraîchir la vue de la table
            tableChat.refresh();
        }
    }
    
}
