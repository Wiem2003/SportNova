package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Abonnement;
import services.AbonnementService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AbonnementsExistantsController {

    @FXML private ListView<Abonnement> abonnementsList;  // Liste des abonnements
    @FXML private Button modifierSupprimerButton;  // Bouton pour modifier ou supprimer l'abonnement sélectionné
    @FXML private Button retourButton;  // Bouton retour

    private AbonnementService abonnementService = new AbonnementService();

    @FXML
    private void initialize() {
        // Charger les abonnements existants
        List<Abonnement> abonnements = abonnementService.getAllAbonnements();
        abonnementsList.getItems().addAll(abonnements);
    }

    // Action de modifier/supprimer un abonnement
    @FXML
    private void handleModifierSupprimerAction(ActionEvent event) throws IOException {
        Abonnement selectedAbonnement = abonnementsList.getSelectionModel().getSelectedItem();
        if (selectedAbonnement != null) {
            // Rediriger vers la page de modification/suppression
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ModifierAbonnement.fxml"));
            Parent root = loader.load();
            ModifierAbonnementController controller = loader.getController();
            controller.setAbonnement(selectedAbonnement);  // Passer l'abonnement sélectionné au contrôleur de la nouvelle page

            // Ouvrir la nouvelle fenêtre sans fermer la fenêtre actuelle
            Stage stage = (Stage) modifierSupprimerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            // Afficher un message d'alerte si aucun abonnement n'est sélectionné
            showAlert("Alerte", "Aucun abonnement sélectionné", "Veuillez sélectionner un abonnement avant de modifier ou supprimer.");
        }
    }

    // Méthode pour l'action Retour (revenir à AdminDashboard)
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
        // Créer une alerte
        Alert alert = new Alert(Alert.AlertType.ERROR);  // Type d'alerte, ici erreur
        alert.setTitle(title);  // Titre de l'alerte
        alert.setHeaderText(header);  // En-tête de l'alerte
        alert.setContentText(content);  // Contenu de l'alerte

        // Afficher l'alerte
        alert.showAndWait();
    }
}
