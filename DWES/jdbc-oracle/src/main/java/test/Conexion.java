package test;

import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;


public class Conexion {

	private String url= "jdbc:oracle:thin:@80.28.158.14:1521:oradai";
	private String usuario= "S2DAWBIBLIOTECA171";
	private String password= "CAMBIAR";
	
	public Connection getConnection() throws SQLException {
		OracleDataSource ods= new OracleDataSource();
		
		ods.setURL(url);
		ods.setUser(usuario);
		ods.setPassword(password);
		
		return ods.getConnection();
	}
}
