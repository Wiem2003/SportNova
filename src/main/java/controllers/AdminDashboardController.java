package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminDashboardController {
    @FXML private Button gubutton;     // Bouton "Gestion Utilisateur"
    @FXML private Button gabutton;     // Bouton "Gestion Abonnement" (optionnel)
    @FXML private Button logoutButton; // Bouton "Déconnexion"

    @FXML
    private void handleGubuttonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserList.fxml"));
        Parent userListPage = loader.load();  // Utilisation de Parent, compatible avec BorderPane
        Stage stage = (Stage) gubutton.getScene().getWindow();
        Scene scene = new Scene(userListPage);
        stage.setScene(scene);
    }


    @FXML
    private void handleGabuttonAction(ActionEvent event) {
        // À implémenter si nécessaire
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleAjouterAdminAction(ActionEvent event) throws IOException {
        // Charger la page pour ajouter un administrateur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddAdmin.fxml"));
        Parent root = loader.load();

        // Obtenir la fenêtre actuelle
        Stage stage = (Stage) gubutton.getScene().getWindow();

        // Ouvrir la fenêtre de création d'un administrateur
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
