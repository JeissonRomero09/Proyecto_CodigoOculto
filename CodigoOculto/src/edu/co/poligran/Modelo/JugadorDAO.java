package edu.co.poligran.Modelo;

import java.sql.*;

public class JugadorDAO {

    private Connection conexion;

    public JugadorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    //  BUSCAR JUGADOR POR NOMBRE (opcional, para validar duplicados)
    public Jugador buscarJugador(String nombre) {
        try {
            String sql = "SELECT * FROM jugador WHERE nombre = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Jugador(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //  BUSCAR JUGADOR POR ID (LOGIN)
    public Jugador buscarJugadorPorId(int id) {
        try {
			String sql = "SELECT * FROM jugador WHERE id = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Jugador(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //  GUARDAR JUGADOR Y OBTENER ID
    public boolean guardarJugador(Jugador jugador) {
        try {
            String nombre = jugador.getNombre();

            if (nombre == null || nombre.trim().isEmpty()) {
                System.out.println("ERROR: el nombre está vacío o null");
                return false;
            }

            String sql = "INSERT INTO jugador (nombre) VALUES (?)";

            PreparedStatement ps = conexion.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, nombre);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    jugador.setId(idGenerado); //  ID asignado aquí
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //  VALIDAR SI EL NOMBRE YA EXISTE
    public boolean existeNombre(String nombre) {
        try {
            String sql = "SELECT id FROM jugador WHERE nombre = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // true si existe

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}