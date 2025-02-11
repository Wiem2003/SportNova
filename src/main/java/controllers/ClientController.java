package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ClientController {

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleConsulterCours(ActionEvent event) {
        System.out.println("Ouverture de la page Consultation des cours...");
        // Ajoute ici l'ouverture de la page correspondante
    }

    @FXML
    private void handleInscrireCours(ActionEvent event) {
        System.out.println("Ouverture de la page Inscription aux cours...");
        // Ajoute ici l'ouverture de la page correspondante
    }

    @FXML
    private void handleReserverEquipement(ActionEvent event) {
        System.out.println("Ouverture de la page Réservation d'équipement...");
        // Ajoute ici l'ouverture de la page correspondante
    }
}
