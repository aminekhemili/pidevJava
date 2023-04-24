/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Tournois;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import services.TournoisServices;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ListTournoisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherTournois();
    }    
    
    
    
    @FXML
    private TableColumn<Tournois, Integer> ChatIDCell;

    @FXML
    private TableColumn<Tournois, Date> DateCell;

    @FXML
    private TableColumn<Tournois, String> DetailCell;

    @FXML
    private TableColumn<Tournois, Integer> NbJoueurCell;

    @FXML
    private TableColumn<Tournois, String> NomJeuCell;

    @FXML
    private Button bntAddTournois;

    @FXML
    private Button btnDeleteTournois;

    @FXML
    private AnchorPane listTournoisPane;

    @FXML
    private TableView<Tournois> tableTournois;

    @FXML
    private TextField txtSearchTournois;
    
    
    ObservableList<Tournois> dataTournois = FXCollections.observableArrayList();
    
    
    public void AfficherTournois()
    {
        TournoisServices ts = new TournoisServices();
        ts.Show().stream().forEach((p) -> {
            dataTournois.add(p);
        });
        NbJoueurCell.setCellValueFactory(new PropertyValueFactory<>("nombre_joueur"));
        NbJoueurCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        NbJoueurCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tournois, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Tournois, Integer> event) {
                Tournois a = event.getRowValue();
                a.setNombre_joueur(event.getNewValue());
                TournoisServices ts = new TournoisServices();
                ts.modifier(a);
            }
        });
        NomJeuCell.setCellValueFactory(new PropertyValueFactory<>("nom_jeu"));
        NomJeuCell.setCellFactory(TextFieldTableCell.forTableColumn());
        NomJeuCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tournois, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Tournois, String> event) {
                Tournois a = event.getRowValue();
                a.setNom_jeu(event.getNewValue());
                TournoisServices ts = new TournoisServices();
                ts.modifier(a);
            }
        });
        DetailCell.setCellValueFactory(new PropertyValueFactory<>("detail"));
        DetailCell.setCellFactory(TextFieldTableCell.forTableColumn());
        DetailCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tournois, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Tournois, String> event) {
                Tournois a = event.getRowValue();
                a.setDetail(event.getNewValue());
                TournoisServices ts = new TournoisServices();
                ts.modifier(a);
            }
        });
        DateCell.setCellValueFactory(new PropertyValueFactory<>("date_tournoi"));
        DateCell.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
            private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            @Override
            public String toString(Date object) {
                return dateFormat.format(object);
            }

            @Override
            public Date fromString(String string) {
                try {
                    // Parse the string into a Date object using the defined format
                    return dateFormat.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // If the string can't be parsed, return null
                    return null;
                }
            }
        }));
        DateCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tournois, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Tournois, Date> event) {
                Tournois a = event.getRowValue();
                a.setDate_tournoi(event.getNewValue());
                TournoisServices ts = new TournoisServices();
                ts.modifier(a);
            }
        });
        ChatIDCell.setCellValueFactory(new PropertyValueFactory<>("id_chat"));
        ChatIDCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ChatIDCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tournois, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Tournois, Integer> event) {
                Tournois a = event.getRowValue();
                a.setId_chat(event.getNewValue());
                TournoisServices ts = new TournoisServices();
                ts.modifier(a);
            }
        });
        
        tableTournois.setItems(dataTournois);
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
    private void supprimerTournois(ActionEvent event) throws SQLException {
        TournoisServices ts = new TournoisServices();
        
        if (tableTournois.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner le tournois à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce tournois ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de l'activité sélectionnée dans la vue de la table
            int id = tableTournois.getSelectionModel().getSelectedItem().getId_tournoi();

            // Supprimer l'activité de la base de données
            ts.supprimer(id);
            // Rafraîchir la liste de données
            dataTournois.clear();
            AfficherTournois();
            // Rafraîchir la vue de la table
            tableTournois.refresh();
        }
    }
    
    
    @FXML
    void genererPDF(MouseEvent event) {
        // Afficher la boîte de dialogue de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            // Générer le fichier PDF avec l'emplacement de sauvegarde sélectionné
            // Récupérer la liste des produits
            TournoisServices as = new TournoisServices();
            List<Tournois> activiteList = as.Show();

            try {
                // Créer le document PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();


                // Créer une police personnalisée pour la date
                Font fontDate = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                BaseColor color = new BaseColor(114, 0, 0); // Rouge: 114, Vert: 0, Bleu: 0
                fontDate.setColor(color); // Définir la couleur de la police

                // Créer un paragraphe avec le lieu
                Paragraph tunis = new Paragraph("Ariana", fontDate);
                tunis.setIndentationLeft(455); // Définir la position horizontale
                tunis.setSpacingBefore(-30); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(tunis);

                // Obtenir la date d'aujourd'hui
                LocalDate today = LocalDate.now();

                // Créer un paragraphe avec la date
                Paragraph date = new Paragraph(today.toString(), fontDate);

                date.setIndentationLeft(437); // Définir la position horizontale
                date.setSpacingBefore(1); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(date);

                // Créer une police personnalisée
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
                BaseColor titleColor = new BaseColor(114, 0, 0); //
                font.setColor(titleColor);

                // Ajouter le contenu au document
                Paragraph title = new Paragraph("Liste des tournois", font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingBefore(50); // Ajouter une marge avant le titre pour l'éloigner de l'image
                title.setSpacingAfter(20);
                document.add(title);

                PdfPTable table = new PdfPTable(5); // 5 colonnes pour les 5 attributs des activités
                table.setWidthPercentage(100);
                table.setSpacingBefore(30f);
                table.setSpacingAfter(30f);

                // Ajouter les en-têtes de colonnes
                Font hrFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                BaseColor hrColor = new BaseColor(255, 255, 255); //
                hrFont.setColor(hrColor);

                PdfPCell cell1 = new PdfPCell(new Paragraph("Nombre Joueur", hrFont));
                BaseColor bgColor = new BaseColor(114, 0, 0);
                cell1.setBackgroundColor(bgColor);
                cell1.setBorderColor(titleColor);
                cell1.setPaddingTop(20);
                cell1.setPaddingBottom(20);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell2 = new PdfPCell(new Paragraph("Nom Jeu", hrFont));
                cell2.setBackgroundColor(bgColor);
                cell2.setBorderColor(titleColor);
                cell2.setPaddingTop(20);
                cell2.setPaddingBottom(20);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell3 = new PdfPCell(new Paragraph("Detail", hrFont));
                cell3.setBackgroundColor(bgColor);
                cell3.setBorderColor(titleColor);
                cell3.setPaddingTop(20);
                cell3.setPaddingBottom(20);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell4 = new PdfPCell(new Paragraph("Date", hrFont));
                cell4.setBackgroundColor(bgColor);
                cell4.setBorderColor(titleColor);
                cell4.setPaddingTop(20);
                cell4.setPaddingBottom(20);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell5 = new PdfPCell(new Paragraph("Chat", hrFont));
                cell5.setBackgroundColor(bgColor);
                cell5.setBorderColor(titleColor);
                cell5.setPaddingTop(20);
                cell5.setPaddingBottom(20);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);

                Font hdFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
                BaseColor hdColor = new BaseColor(255, 255, 255); //
                hrFont.setColor(hdColor);
                // Ajouter les données des produits
                for (Tournois act : activiteList) {
                    PdfPCell cellR1 = new PdfPCell(new Paragraph(String.valueOf(act.getNombre_joueur()), hdFont));
                    cellR1.setBorderColor(titleColor);
                    cellR1.setPaddingTop(10);
                    cellR1.setPaddingBottom(10);
                    cellR1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR1);

                    PdfPCell cellR2 = new PdfPCell(new Paragraph(act.getNom_jeu(), hdFont));
                    cellR2.setBorderColor(titleColor);
                    cellR2.setPaddingTop(10);
                    cellR2.setPaddingBottom(10);
                    cellR2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR2);

                    PdfPCell cellR3 = new PdfPCell(new Paragraph(act.getDetail(), hdFont));
                    cellR3.setBorderColor(titleColor);
                    cellR3.setPaddingTop(10);
                    cellR3.setPaddingBottom(10);
                    cellR3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR3);

                    PdfPCell cellR4 = new PdfPCell(new Paragraph(String.valueOf(act.getDate_tournoi()), hdFont));
                    cellR4.setBorderColor(titleColor);
                    cellR4.setPaddingTop(10);
                    cellR4.setPaddingBottom(10);
                    cellR4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR4);

                    PdfPCell cellR5 = new PdfPCell(
                            new Paragraph(String.valueOf(act.getId_chat()), hdFont));
                    cellR5.setBorderColor(titleColor);
                    cellR5.setPaddingTop(10);
                    cellR5.setPaddingBottom(10);
                    cellR5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR5);

                }
                table.setSpacingBefore(20);
                document.add(table);
                document.close();

                System.out.println("Le fichier PDF a été généré avec succès.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    
    }
    
    
}
