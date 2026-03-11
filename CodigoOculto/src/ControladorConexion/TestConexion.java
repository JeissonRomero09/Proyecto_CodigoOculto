package ControladorConexion; // Verifica que el paquete se llame así exactamente

import java.sql.Connection;

public class TestConexion { // La T debe ser MAYÚSCULA
    public static void main(String[] args) {
        Conexion con = new Conexion();
        Connection cn = con.getConnection();

        if (cn != null) {
            System.out.println("¡CONEXIÓN EXITOSA!");
        } else {
            System.out.println("Error en la conexión.");
        }
    }
}