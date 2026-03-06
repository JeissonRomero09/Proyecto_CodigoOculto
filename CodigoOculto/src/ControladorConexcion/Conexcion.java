package ControladorConexcion;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class Conexcion {

	    private static final String driver = "com.mysql.cj.jdbc.Driver";
	    private static final String url = "jdbc:mysql://localhost:3306/CodigoSecreto?useSSL=false&serverTimezone=UTC";
	    private static final String usuario = "root";
	    private static final String password = "";

	    private Connection con;

	    public Connection getConnection() {

	        try {

	            Class.forName(driver);
	            con = DriverManager.getConnection(url, usuario, password);

	            System.out.println("✅ Conexión exitosa a la base de datos");

	        } catch (ClassNotFoundException e) {

	            System.out.println("❌ Error: Driver no encontrado");
	            e.printStackTrace();

	        } catch (SQLException e) {

	            System.out.println("❌ Error de conexión con la base de datos");
	            e.printStackTrace();
	        }

	        return con;
	    }

	    public void close() {
	        try {
	            if (con != null) {
	                con.close();
	                System.out.println("🔒 Conexión cerrada");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	}

