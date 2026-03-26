package edu.co.poligran.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/CodigoSecreto";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {

            System.out.println("Error de conexión: " + e.getMessage());

        }

        return con;
    }
}