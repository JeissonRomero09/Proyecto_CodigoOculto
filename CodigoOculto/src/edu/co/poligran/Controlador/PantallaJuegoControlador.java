package edu.co.poligran.Controlador;

import edu.co.poligran.Modelo.Partida;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

public class PantallaJuegoControlador {

    @FXML private GridPane gridCasillas;
    private Partida partida = new Partida();

    @FXML
    private void presionarBoton(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String entrada = btn.getText();

        if (entrada.equalsIgnoreCase("Enter")) {
            // El controlador NO sabe si se puede pasar, solo pide intentarlo
            partida.intentarPasarDeFila();
        } 
        else {
            // El controlador NO sabe si el número cabe, solo pide realizar el intento
            int f = partida.getFilaActual();
            int c = partida.getColumnaActual();
            
            if (partida.realizarIntento(entrada)) {
                actualizarInterfaz(f, c, entrada);
            }
        }
    }

    @FXML
    private void seleccionarCasilla(MouseEvent event) {
        Button casilla = (Button) event.getSource();
        Integer r = GridPane.getRowIndex(casilla);
        Integer c = GridPane.getColumnIndex(casilla);

        // El controlador solo pasa las coordenadas, el modelo decide si es válido mover el cursor
        if (r != null && r == partida.getFilaActual()) {
            partida.moverCursorManual(c == null ? 0 : c);
        }
    }

    private void actualizarInterfaz(int f, int c, String t) {
        for (Node node : gridCasillas.getChildren()) {
            if (GridPane.getRowIndex(node) == f && GridPane.getColumnIndex(node) == c) {
                ((Button) node).setText(t);
                break;
            }
        }
    }
}