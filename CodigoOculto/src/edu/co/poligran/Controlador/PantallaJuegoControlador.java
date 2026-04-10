package edu.co.poligran.Controlador;

import edu.co.poligran.Modelo.Partida;
import edu.co.poligran.Modelo.Jugador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

public class PantallaJuegoControlador {

    @FXML private GridPane gridCasillas;

    private Partida partida = new Partida();
    private Jugador jugador; //

    //  MÉTODO  set jugador
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
        System.out.println("Jugador en juego: " + jugador.getNombre());
    }

    @FXML
    private void presionarBoton(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String entrada = btn.getText();

        if (entrada.equalsIgnoreCase("Enter")) {
            partida.intentarPasarDeFila();
        } 
        else {
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