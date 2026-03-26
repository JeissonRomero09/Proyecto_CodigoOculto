package edu.co.poligran.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.input.MouseEvent;

public class JuegoControlador {

    @FXML
    private GridPane gridCasillas;

    private int filaActual = 0;
    private int columnaActual = 0;
    private final int MAX_COLUMNAS = 5;
    private final int MAX_FILAS = 5;

    @FXML
    private void presionarBoton(ActionEvent event) {
        Button botonPresionado = (Button) event.getSource();
        String texto = botonPresionado.getText();

        // 1. Manejar la tecla Enter
        if (texto.equalsIgnoreCase("Enter")) {
            manejarEnter();
        } 
        // 2. Manejar números: solo avanza si el texto es un número del 0 al 9
        else if (texto.matches("[0-9]")) {
            manejarNumero(texto);
        } 
        // 3. Ignorar cualquier otra entrada que no sea número o Enter
        else {
            System.out.println("Entrada ignorada (no es número): " + texto);
        }
    }

    private void manejarNumero(String numero) {
        if (columnaActual < MAX_COLUMNAS) {
            escribirEnCasilla(numero, filaActual, columnaActual);
            
            // EL AVANCE: Solo ocurre aquí, tras confirmar que es un número
            if (columnaActual < MAX_COLUMNAS - 1) {
                columnaActual++;
            }
        }
    }

    @FXML
    private void seleccionarCasilla(MouseEvent event) {
        Button casillaClickeada = (Button) event.getSource();
        Integer r = GridPane.getRowIndex(casillaClickeada);
        int filaNodo = (r == null) ? 0 : r;

        // Solo permitimos mover el cursor dentro de la fila activa
        if (filaNodo == filaActual) {
            Integer c = GridPane.getColumnIndex(casillaClickeada);
            columnaActual = (c == null) ? 0 : c;
            System.out.println("Cursor movido a columna: " + columnaActual);
        }
    }

    private void escribirEnCasilla(String numero, int fila, int col) {
        Button casilla = obtenerCasilla(fila, col);
        if (casilla != null) {
            casilla.setText(numero);
            casilla.setFont(Font.font("System Bold", 18));
        }
    }

    private void manejarEnter() {
        if (filaCompleta()) {
            if (filaActual < MAX_FILAS - 1) {
                filaActual++;     
                columnaActual = 0; 
                System.out.println("Fila " + (filaActual + 1) + " iniciada.");
            } else {
                System.out.println("¡Juego terminado!");
            }
        } else {
            System.out.println("Fila incompleta.");
        }
    }

    private boolean filaCompleta() {
        for (int i = 0; i < MAX_COLUMNAS; i++) {
            Button b = obtenerCasilla(filaActual, i);
            if (b == null || b.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private Button obtenerCasilla(int fila, int columna) {
        for (Node node : gridCasillas.getChildren()) {
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);
            
            int f = (r == null) ? 0 : r;
            int col = (c == null) ? 0 : c;
            
            if (f == fila && col == columna && node instanceof Button) {
                return (Button) node;
            }
        }
        return null;
    }
}