package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;         // Ajouté
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.UtilisateurService;
import models.Utilisateur;
import java.io.IOException;

public class SignupController {
    @FXML private TextField nomp;
    @FXML private TextField prenomp;
    @FXML private TextField emailp;
    @FXML private PasswordField passwordp;
    @FXML private TextField rolep;
    @FXML private Button signupp;
    @FXML private Label loginlink;   // Assurez-vous que c'est bien un Label

    private final UtilisateurService utilisateurService = new UtilisateurService();

    @FXML
    private void handleSignup(ActionEvent event) {
        String nom = nomp.getText();
        String prenom = prenomp.getText();
        String email = emailp.getText();
        String password = passwordp.getText();
        String role = rolep.getText();

        Utilisateur newUser = new Utilisateur(0, nom, prenom, email, password, role, null);
        if (utilisateurService.ajouterUtilisateur(newUser)) {
            showAlert("Succès", "Compte créé avec succès !");
        } else {
            showAlert("Erreur", "Erreur lors de la création du compte !");
        }
    }

    @FXML
    public void handleLoginLink(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            // loginlink est un Label (qui hérite de Node) : getScene() est disponible
            Stage stage = (Stage) loginlink.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
