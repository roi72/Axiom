package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {

    private static Stage primaryStage;

    // Llamado una vez al inicio (desde MainApp)
    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    // Cambiar a una nueva escena por ruta FXML
    public static void cambiarEscena(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(SceneManager.class.getResource(fxmlPath)));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cambiar de escena a: " + fxmlPath);
            e.printStackTrace();
        }
    }
}

