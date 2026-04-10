package edu.co.poligran.Controlador;

import edu.co.poligran.Modelo.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class MenuPrincipalControlador {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    private JugadorDAO jugadorDAO;

    @FXML
    public void initialize() {
        jugadorDAO = new JugadorDAO(Conexion.conectar());
    }

    //  LOGIN POR ID
    @FXML
    public void iniciarSesion(ActionEvent event) {

        String textoId = txtId.getText();

        if (textoId == null || textoId.trim().isEmpty()) {
            mostrarAlerta("Error", "Ingrese un ID");
            return;
        }

        try {
            int id = Integer.parseInt(textoId);

            Jugador jugador = jugadorDAO.buscarJugadorPorId(id);

            if (jugador != null) {
                irMenuJuego(event, jugador);
            } else {
                mostrarAlerta("Error", "ID no encontrado");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser numérico");
        }
    }

    // REGISTRO CON NOMBRE
    @FXML
    public void registrarJugador(ActionEvent event) {

        String nombre = txtNombre.getText();

        // Validar vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            mostrarAlerta("Error", "Ingrese un nombre");
            return;
        }

        //  VALIDAR SI YA EXISTE
        if (jugadorDAO.existeNombre(nombre)) {
            mostrarAlerta("Error", "Ese nombre ya está registrado");
            return;
        }

        // Confirmación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar registro");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Registrar el nombre: " + nombre + "?");

        if (confirmacion.showAndWait().get() == ButtonType.OK) {

            Jugador nuevo = new Jugador(nombre);
            jugadorDAO.guardarJugador(nuevo);

            // Mostrar ID generado
            mostrarAlerta("Registro exitoso",
                    "Tu ID es: " + nuevo.getId() + "\nGuárdalo para iniciar sesión");

            irMenuJuego(event, nuevo);
        }
    }

    //  INVITADO
    @FXML
    public void irAMenuJuego(ActionEvent event) {
        Jugador invitado = Jugador.crearInvitado();
        irMenuJuego(event, invitado);
    }

    //  CAMBIO DE ESCENA
    private void irMenuJuego(ActionEvent event, Jugador jugador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/co/poligran/vista/MenuJuego.fxml"));
            Parent root = loader.load();

            MenuJuegoControlador controller = loader.getController();
            controller.setJugador(jugador);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  ALERTAS
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}