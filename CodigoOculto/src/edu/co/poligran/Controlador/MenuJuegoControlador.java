package edu.co.poligran.Controlador;

import edu.co.poligran.Modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuJuegoControlador {

    private Jugador jugador;

    @FXML
    private Text textoId;

    // Este método lo llamas cuando cargas la vista
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;

        // Aquí actualizas el texto en pantalla
        textoId.setText("ID : " + jugador.getId());
    }

    public void irAPantallaJuego(ActionEvent event) {
        System.out.println("Entrando al juego...");
    }
    public void cerrarSesion(ActionEvent event) {
        try {
            // Cargar la vista del menú principal
            Parent root = FXMLLoader.load(getClass().getResource("/ruta/MenuPrincipal.fxml"));

            // Obtener la ventana actual
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Cambiar escena
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}