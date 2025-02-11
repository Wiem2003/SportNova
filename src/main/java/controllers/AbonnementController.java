package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;  // Importer Button pour lier les boutons
import javafx.scene.control.Alert;

import java.io.IOException;

public class AbonnementController {

    // Lier les boutons avec @FXML
    @FXML private Button creerAbonnementButton; // Lier le bouton "Créer Abonnement"
    @FXML private Button abonnementsExistantsButton; // Lier le bouton "Abonnements Existants"
    @FXML private Button retourButton; // Lier le bouton "Retour"

    // Méthode pour l'action "Créer Abonnement"
    @FXML
    private void handleCreerAbonnementAction(ActionEvent event) throws IOException {
        // Charger la page "AffecterAbonnement.fxml"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AffecterAbonnement.fxml"));
        Parent affecterAbonnementPage = loader.load();
        // Récupérer la scène du bouton "Créer Abonnement" pour changer la scène
        Stage stage = (Stage) creerAbonnementButton.getScene().getWindow();
        Scene scene = new Scene(affecterAbonnementPage);
        stage.setScene(scene);
    }

    // Méthode pour l'action "Abonnements Existants"
    @FXML
    private void handleAbonnementsExistantsAction(ActionEvent event) throws IOException {
        // Charger la page "AbonnementsExistants.fxml"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AbonnementsExistants.fxml"));
        Parent abonnementsExistantsPage = loader.load();
        // Récupérer la scène du bouton "Abonnements Existants" pour changer la scène
        Stage stage = (Stage) abonnementsExistantsButton.getScene().getWindow();
        Scene scene = new Scene(abonnementsExistantsPage);
        stage.setScene(scene);
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
        // Créer une alerte
        Alert alert = new Alert(Alert.AlertType.ERROR);  // Type d'alerte, ici erreur
        alert.setTitle(title);  // Titre de l'alerte
        alert.setHeaderText(header);  // En-tête de l'alerte
        alert.setContentText(content);  // Contenu de l'alerte

        // Afficher l'alerte
        alert.showAndWait();
    }
}
