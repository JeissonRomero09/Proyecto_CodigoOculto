package co.edu.poli.CodigoOculto.Dao;

import co.edu.poli.CodigoOculto.Modelo.Jugador;
import java.sql.*;

public class JugadorDAO {

    private Connection conexion;

    public JugadorDAO(Connection conexion) {
        this.conexion = conexion;
    }

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

    public boolean guardarJugador(Jugador jugador) {
        try {
            String nombre = jugador.getNombre();

            if (nombre == null || nombre.trim().isEmpty()) {
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
                    jugador.setId(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeNombre(String nombre) {
        try {
            String sql = "SELECT id FROM jugador WHERE nombre = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}