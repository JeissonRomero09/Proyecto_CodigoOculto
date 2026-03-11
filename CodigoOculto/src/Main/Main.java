package Main; // Corregido: sin la palabra 'main' al principio

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Usar una ruta relativa desde la raíz del classpath (/Vista/...)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/PantallaDeInicio.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            stage.setTitle(" Secret Code - Código Oculto");
            stage.setScene(scene);
            stage.setResizable(false); // Opcional: para que no se deforme tu diseño
            stage.show();
            
        } catch (IOException e) {
            System.err.println("Error: No se pudo cargar el archivo FXML. Verifica que esté en src/Vista/");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}