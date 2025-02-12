package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.UtilisateurService;
import models.Utilisateur;
import java.io.IOException;

public class LoginController {
    @FXML private TextField emailc;
    @FXML private PasswordField passwordc;
    @FXML private Button loginc;
    @FXML private Label createc;

    private final UtilisateurService utilisateurService = new UtilisateurService();

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailc.getText();
        String password = passwordc.getText();
        Utilisateur user = utilisateurService.authentifier(email, password);

        if (user != null) {
            showAlert("Succès", "Connexion réussie !");
            try {
                FXMLLoader loader;
                if ("admin".equalsIgnoreCase(user.getRole())) {
                    loader = new FXMLLoader(getClass().getResource("/views/AdminDashboard.fxml"));
                } else {  // Si ce n'est pas un admin, rediriger vers Client.fxml
                    loader = new FXMLLoader(getClass().getResource("/views/Client.fxml"));
                }
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) loginc.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Erreur", "Email ou mot de passe incorrect !");
        }
    }


    @FXML
    private void handleCreateAccount(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Signup.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) createc.getScene().getWindow();
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
