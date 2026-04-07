package edu.co.poligran.Controlador;

import edu.co.poligran.Modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MenuPrincipalControlador {

	public void irAMenuJuego(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/co/poligran/vista/MenuJuego.fxml"));

			Parent root = loader.load();

			//  Obtener controlador
			MenuJuegoControlador controller = loader.getController();

			//  Crear jugador invitado
			controller.setJugador(new Jugador("INVITADO"));

			// Cambiar escena
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}