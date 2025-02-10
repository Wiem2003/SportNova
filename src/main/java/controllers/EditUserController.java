package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Utilisateur;
import services.UtilisateurService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;


public class EditUserController implements Initializable {
    @FXML private TextField txtNom;
    @FXML private TextField txtPrenom;
    @FXML private TextField txtEmail;
    @FXML private TextField txtMotDePasse;
    @FXML private TextField txtRole;
    @FXML private Button btnModifier;
    @FXML private Button btnSupprimer;
    @FXML private Button backButton;

    private UtilisateurService utilisateurService = new UtilisateurService();
    private Utilisateur utilisateurSelectionne;

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateurSelectionne = utilisateur;
        txtNom.setText(utilisateur.getNom());
        txtPrenom.setText(utilisateur.getPrenom());
        txtEmail.setText(utilisateur.getEmail());
        txtMotDePasse.setText(utilisateur.getMotDePasse());
        txtRole.setText(utilisateur.getRole());
    }

    @FXML
    private void modifierUtilisateur() {
        if (utilisateurSelectionne != null) {
            utilisateurSelectionne.setNom(txtNom.getText());
            utilisateurSelectionne.setPrenom(txtPrenom.getText());
            utilisateurSelectionne.setEmail(txtEmail.getText());
            utilisateurSelectionne.setMotDePasse(txtMotDePasse.getText());
            utilisateurSelectionne.setRole(txtRole.getText());

            utilisateurService.modifierUtilisateur(utilisateurSelectionne);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification");
            alert.setHeaderText(null);
            alert.setContentText("L'utilisateur a été modifié avec succès !");
            alert.showAndWait();
        }
    }

    @FXML
    private void supprimerUtilisateur() {
        if (utilisateurSelectionne != null) {
            utilisateurService.supprimerUtilisateur(utilisateurSelectionne.getId());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression");
            alert.setHeaderText(null);
            alert.setContentText("L'utilisateur a été supprimé avec succès !");
            alert.showAndWait();

            fermerFenetre();
        }
    }

    @FXML
    private void retourArriere() {
        try {
            // Charger la nouvelle vue (UserList.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserList.fxml"));
            Parent root = loader.load();

            // Obtenir la fenêtre actuelle (celle où tu es en train de modifier l'utilisateur)
            Stage currentStage = (Stage) btnModifier.getScene().getWindow();

            // Fermer l'ancienne fenêtre (celle de modification)
            currentStage.close();

            // Créer une nouvelle fenêtre pour afficher la page UserList
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Liste des utilisateurs");
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void fermerFenetre() {
        Stage stage = (Stage) btnSupprimer.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnModifier.setOnAction(event -> modifierUtilisateur());
        btnSupprimer.setOnAction(event -> supprimerUtilisateur());
        backButton.setOnAction(event -> retourArriere());
    }
}
