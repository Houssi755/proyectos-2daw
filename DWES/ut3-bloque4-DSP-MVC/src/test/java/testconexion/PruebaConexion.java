package testconexion;

import java.sql.SQLException;

import conexion.ConnectionFactory;

public class PruebaConexion {
	public static void main(String[] args) {
		
		try {
			ConnectionFactory.getConnection();
			System.out.println("Conexión establecida");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}