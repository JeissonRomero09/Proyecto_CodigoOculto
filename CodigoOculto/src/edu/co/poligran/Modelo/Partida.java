package edu.co.poligran.Modelo;

public class Partida {
    private final int MAX_FILAS = 5;
    private final int MAX_COLUMNAS = 5;
    private String[][] tablero;
    private int filaActual;
    private int columnaActual;
    private boolean juegoTerminado;

    public Partida() {
        iniciarPartida();
    }

    // LÓGICA: Iniciar partida
    public void iniciarPartida() {
        this.tablero = new String[MAX_FILAS][MAX_COLUMNAS];
        this.filaActual = 0;
        this.columnaActual = 0;
        this.juegoTerminado = false;
    }

    // LÓGICA: Realizar intento (Gestión de estado interno)
    public boolean realizarIntento(String numero) {
        if (juegoTerminado || columnaActual >= MAX_COLUMNAS) return false;
        
        tablero[filaActual][columnaActual] = numero;
        avanzarCursorInterno();
        return true;
    }

    private void avanzarCursorInterno() {
        if (columnaActual < MAX_COLUMNAS - 1) {
            columnaActual++;
        }
    }

    // LÓGICA: Procesar cambio de fila o fin de juego
    public boolean intentarPasarDeFila() {
        if (esFilaCompleta()) {
            if (filaActual < MAX_FILAS - 1) {
                filaActual++;
                columnaActual = 0;
                return true;
            } else {
                finalizarPartida();
            }
        }
        return false;
    }

    public void finalizarPartida() {
        this.juegoTerminado = true;
    }

    public boolean esFilaCompleta() {
        for (int i = 0; i < MAX_COLUMNAS; i++) {
            if (tablero[filaActual][i] == null || tablero[filaActual][i].isEmpty()) return false;
        }
        return true;
    }

    public void moverCursorManual(int col) {
        if (!juegoTerminado) this.columnaActual = col;
    }

    // Getters mínimos para que el controlador sepa qué refrescar
    public int getFilaActual() { return filaActual; }
    public int getColumnaActual() { return columnaActual; }
    public boolean isJuegoTerminado() { return juegoTerminado; }
}