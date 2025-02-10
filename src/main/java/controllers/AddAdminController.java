package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import models.Utilisateur;
import services.UtilisateurService;

public class AddAdminController {
    @FXML private TextField txtNom;
    @FXML private TextField txtPrenom;
    @FXML private TextField txtEmail;
    @FXML private TextField txtMotDePasse;
    @FXML private Button ajouterAdminButton;

    private UtilisateurService utilisateurService = new UtilisateurService();

    @FXML
    private void handleAjouterAdminAction() {
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        String email = txtEmail.getText();
        String motDePasse = txtMotDePasse.getText();
        String role = "admin"; // Le rôle doit être "admin" pour ajouter un administrateur

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Tous les champs doivent être remplis.");
            alert.showAndWait();
            return;
        }

        if (utilisateurService.isEmailExist(email)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setHeaderText(null);
            alert.setContentText("L'email est déjà utilisé.");
            alert.showAndWait();
            return;
        }

        Utilisateur nouveauAdmin = new Utilisateur(0, nom, prenom, email, motDePasse, role, null);
        boolean success = utilisateurService.ajouterUtilisateur(nouveauAdmin);

        if (success) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'Administrateur");
            alert.setHeaderText(null);
            alert.setContentText("L'administrateur a été ajouté avec succès.");
            alert.showAndWait();
            Stage stage = (Stage) ajouterAdminButton.getScene().getWindow();
            stage.close();
            openUserListPage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'ajout");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur est survenue lors de l'ajout de l'administrateur.");
            alert.showAndWait();
        }
    }

    private void openUserListPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserList.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void handleRetourAction() {
        // Retourner à l'écran précédent (AdminDashboard)
        Stage stage = (Stage) ajouterAdminButton.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdminDashboard.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
