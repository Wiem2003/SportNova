package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Utilisateur;
import services.UtilisateurService;
import java.io.IOException;

public class UserListController {
    @FXML private TableView<Utilisateur> userTable;
    @FXML private TableColumn<Utilisateur, Integer> colId;
    @FXML private TableColumn<Utilisateur, String> colNom;
    @FXML private TableColumn<Utilisateur, String> colPrenom;
    @FXML private TableColumn<Utilisateur, String> colEmail;
    @FXML private TableColumn<Utilisateur, String> colRole;
    @FXML private TableColumn<Utilisateur, String> colPassword;
    @FXML private TableColumn<Utilisateur, String> colDateCreation;
    @FXML private Button editButton;
    @FXML private Button backButton;

    private UtilisateurService utilisateurService = new UtilisateurService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("motDePasse"));
        colDateCreation.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));

        ObservableList<Utilisateur> usersList = FXCollections.observableArrayList(utilisateurService.getAllUtilisateurs());
        userTable.setItems(usersList);
    }

    @FXML
    private void handleEditButtonAction(ActionEvent event) {
        Utilisateur selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun utilisateur sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un utilisateur à modifier ou supprimer.");
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditUser.fxml"));
            Parent root = loader.load();

            EditUserController editUserController = loader.getController();
            editUserController.setUtilisateur(selectedUser);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier/Supprimer l'utilisateur");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}
