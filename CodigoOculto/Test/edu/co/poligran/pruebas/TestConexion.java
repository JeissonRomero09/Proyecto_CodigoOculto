package edu.co.poligran.pruebas;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import edu.co.poligran.Modelo.Conexion;

public class TestConexion {

	@Test
	public void probarConexionBD() {

		Connection cn = Conexion.conectar();

		assertNotNull(cn, "La conexión a la base de datos falló");

		System.out.println("Conexión exitosa en test");

		try {
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}