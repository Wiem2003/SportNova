package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import models.Abonnement;
import services.AbonnementService;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class ModifierAbonnementController {

    @FXML private TextField typeField;
    @FXML private TextField dateDebutField;
    @FXML private TextField dateFinField;
    @FXML private TextField prixField;
    @FXML private TextField statutField;
    @FXML private Button retourButton;

    private Abonnement abonnement;
    private AbonnementService abonnementService = new AbonnementService();

    // Setter pour l'abonnement sélectionné
    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;

        // Formater les dates au format "yyyy-MM-dd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateDebutField.setText(sdf.format(abonnement.getDateDebut()));
        dateFinField.setText(sdf.format(abonnement.getDateFin()));
        typeField.setText(abonnement.getType());
        prixField.setText(String.valueOf(abonnement.getPrix()));
        statutField.setText(abonnement.getStatut());
    }

    // Sauvegarder les modifications
    @FXML
    private void handleSaveAction(ActionEvent event) {
        // Mise à jour des informations de l'abonnement
        abonnement.setType(typeField.getText());
        abonnement.setDateDebut(Date.valueOf(dateDebutField.getText()));
        abonnement.setDateFin(Date.valueOf(dateFinField.getText()));
        abonnement.setPrix(Double.parseDouble(prixField.getText()));
        abonnement.setStatut(statutField.getText());

        // Appel au service pour modifier l'abonnement
        abonnementService.modifierAbonnement(abonnement);

        // Afficher un message de succès
        showAlert("Succès", "Modification réussie", "L'abonnement a été modifié avec succès.");
    }

    // Supprimer l'abonnement
    @FXML
    private void handleDeleteAction(ActionEvent event) {
        // Appel au service pour supprimer l'abonnement
        abonnementService.supprimerAbonnement(abonnement.getId());

        // Afficher un message de succès
        showAlert("Succès", "Suppression réussie", "L'abonnement a été supprimé avec succès.");
    }

    // Méthode pour l'action "Retour" qui permet de revenir à l'AdminDashboard
    @FXML
    public void handleRetour(ActionEvent event) {
        try {
            // Charger la scène AdminDashboard.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdminDashboard.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène avec AdminDashboard.fxml
            Stage stage = (Stage) retourButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur de navigation", "Impossible de charger le tableau de bord administratif.");
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String header, String content) {
        // Créer une alerte de type INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);  // Titre de l'alerte
        alert.setHeaderText(header);  // En-tête de l'alerte
        alert.setContentText(content);  // Contenu de l'alerte

        // Afficher l'alerte
        alert.showAndWait();
    }
}
