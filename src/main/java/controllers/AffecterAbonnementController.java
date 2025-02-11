package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Abonnement;
import models.Utilisateur;
import services.AbonnementService;
import services.UtilisateurService;

import java.sql.Date;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class AffecterAbonnementController {

    @FXML
    private ListView<Utilisateur> utilisateursListView; // Liste des utilisateurs
    @FXML
    private TextField typeAbonnementField; // Champ pour saisir le type d'abonnement
    @FXML
    private DatePicker dateDebutPicker; // Date picker pour la date de début
    @FXML
    private DatePicker dateFinPicker; // Date picker pour la date de fin
    @FXML
    private TextField prixField; // Champ pour saisir le prix
    @FXML
    private ComboBox<String> statutComboBox; // Liste déroulante pour le statut de l'abonnement
    @FXML
    private Button affecterAbonnementButton; // Bouton pour affecter l'abonnement

    private UtilisateurService utilisateurService;
    private AbonnementService abonnementService;

    // Initialisation des services et chargement des utilisateurs
    public void initialize() {
        utilisateurService = new UtilisateurService();
        abonnementService = new AbonnementService();

        // Charger les utilisateurs dans la liste
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        utilisateursListView.getItems().setAll(utilisateurs);

        // Initialiser le statut de l'abonnement
        statutComboBox.getItems().setAll("Actif", "Inactif");
    }


    // Méthode appelée lors de la sélection d'un utilisateur et de l'affectation d'un abonnement
    @FXML
    public void affecterAbonnement(ActionEvent event) {
        Utilisateur selectedUser = utilisateursListView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Erreur", "Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur pour lui affecter un abonnement.");
            return;
        }

        // Récupérer les valeurs du formulaire
        String type = typeAbonnementField.getText();
        Date dateDebut = Date.valueOf(dateDebutPicker.getValue());
        Date dateFin = Date.valueOf(dateFinPicker.getValue());

        // Vérification des dates
        if (dateFin.before(dateDebut)) {
            showAlert("Erreur", "Dates invalides", "La date de fin ne peut pas être antérieure à la date de début.");
            return;
        }

        double prix;
        try {
            prix = Double.parseDouble(prixField.getText());
            if (prix <= 0) {
                showAlert("Erreur", "Prix invalide", "Le prix doit être un nombre positif.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Prix invalide", "Veuillez entrer un prix valide.");
            return;
        }

        String statut = statutComboBox.getValue();

        if (type.isEmpty() || dateDebut == null || dateFin == null || prix <= 0 || statut == null) {
            showAlert("Erreur", "Informations manquantes", "Veuillez remplir tous les champs avant de valider.");
            return;
        }

        // Créer l'abonnement
        Abonnement abonnement = new Abonnement(0, selectedUser.getId(), type, dateDebut, dateFin, prix, statut);

        // Ajouter l'abonnement dans la base de données
        abonnementService.ajouterAbonnement(abonnement);

        // Réactualiser la liste des utilisateurs (optionnel)
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        utilisateursListView.getItems().setAll(utilisateurs);

        // Afficher un message de succès
        showAlert("Succès", "Abonnement affecté", "L'abonnement a été affecté avec succès à " + selectedUser.getNom() + " " + selectedUser.getPrenom());

        // Réinitialiser les champs du formulaire
        resetForm();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Réinitialiser les champs du formulaire
    private void resetForm() {
        typeAbonnementField.clear();
        dateDebutPicker.setValue(null);
        dateFinPicker.setValue(null);
        prixField.clear();
        statutComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private Button retourButton; // Déclarez le bouton pour le lier à l'FXML

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
}
