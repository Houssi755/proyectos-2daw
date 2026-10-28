package test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

public class PruebaConexion {

	public static void main(String[] args) {
		String url= "jdbc:oracle:thin:@80.28.158.14:1521:oradai";
		String usuario= "S2DAWBIBLIOTECA171";
		String password= "CAMBIAR";
		
		Connection con=null;
		
		try {
			OracleDataSource ods = new OracleDataSource();
			
			ods.setURL(url);
			ods.setUser(usuario);
			ods.setPassword(password);

			con = ods.getConnection();
			
			System.out.println("Conexion establecida correctamente");
			
			DatabaseMetaData meta = con.getMetaData();
			
			System.out.println("SGBD: "+ meta.getDatabaseProductName());
			
			System.out.println("Version SGBD "+meta.getDatabaseProductVersion());
			
			System.out.println("Driver: "+meta.getDriverName())
			;
			
			
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)
					con.close();

			} catch (SQLException e) {
				System.out.println("Excepcion al cerrar");				
				e.printStackTrace();
			}
	
		}
	}

}
