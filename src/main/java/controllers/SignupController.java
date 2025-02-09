package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import services.UtilisateurService;
import models.Utilisateur;

public class SignupController {

    @FXML
    private TextField nomp;

    @FXML
    private TextField prenomp;

    @FXML
    private TextField emailp;

    @FXML
    private TextField passwordp;

    @FXML
    private TextField rolep;

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
