package edu.co.poligran.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class PantallaInicioControlador {

    public void irAPantallaJuego(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/co/poligran/vista/PantallaJuego.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}