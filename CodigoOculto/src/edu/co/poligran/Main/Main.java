package edu.co.poligran.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Cargar el archivo FXML desde la ruta correcta
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/edu/co/poligran/Vista/PantallaInicio.fxml")
            );
            System.out.println(
            	    getClass().getResource("/edu/co/poligran/Vista/PantallaInicio.fxml")
            	);
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Configurar la ventana
            stage.setTitle("Secret Code - Código Oculto");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            System.out.println("❌ Error al cargar el FXML:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}