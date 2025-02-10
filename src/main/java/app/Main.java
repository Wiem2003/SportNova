package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.UtilisateurService;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Création du compte admin par défaut (s'il n'existe pas déjà)
            UtilisateurService utilisateurService = new UtilisateurService();
            utilisateurService.createDefaultAdmin();

            // Chargement de la vue de connexion
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("SportNova Application");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
