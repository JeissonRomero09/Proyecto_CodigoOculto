package edu.co.poligran.pruebas;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import edu.co.poligran.Modelo.Conexion;

public class TestConexion {

    @Test
    public void probarConexionBD() {

        Conexion conexion = new Conexion();
        Connection cn = conexion.getConnection();

        assertNotNull(cn, "La conexión a la base de datos falló");

    }
}