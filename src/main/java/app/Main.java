package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;  // Utiliser Parent au lieu de BorderPane
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Vérifier si le fichier est trouvé
            System.out.println(getClass().getResource("/views/login.fxml"));

            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));

            Parent root = loader.load();  // Utiliser Parent ici, au lieu de BorderPane

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
