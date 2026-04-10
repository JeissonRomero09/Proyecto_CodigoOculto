package edu.co.poligran.Controlador;

import edu.co.poligran.Modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MenuJuegoControlador {

    private Jugador jugador;

    @FXML
    private Text textoNombre;

    @FXML
    private Text textoId;

    //  Recibe el jugador desde el menú principal
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        actualizarTexto();
    }

    //  Se ejecuta al cargar la vista
    @FXML
    public void initialize() {
        actualizarTexto();
    }

    // Mostrar datos del jugador
    private void actualizarTexto() {
        if (jugador != null) {

            if (textoNombre != null) {
                textoNombre.setText("Name: " + jugador.getNombre());
            }

            if (textoId != null) {
                textoId.setText("ID: " + jugador.getId());
            }
        }
    }

    //  BOTÓN JUGAR  : IR A PantallaJuego.fxml
    @FXML
    public void irAPantallaJuego(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/edu/co/poligran/vista/PantallaJuego.fxml")
            );

            Parent root = loader.load();

            // 🔥 PASAR JUGADOR AL JUEGO
            PantallaJuegoControlador controller = loader.getController();
            controller.setJugador(jugador);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  CERRAR SESIÓN
    @FXML
    public void cerrarSesion(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/edu/co/poligran/vista/MenuPrincipal.fxml")
            );

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // obtener jugador
    public Jugador getJugador() {
        return jugador;
    }
}