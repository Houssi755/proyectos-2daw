package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static final String URL = "jdbc:oracle:thin:@80.28.158.14:1521:oradai";
    private static final String USER = "S2DAWBIBLIOTECA171";
    private static final String PASSWORD = "CAMBIAR";

    private ConnectionFactory() {
    }

    static {
    	try {
    		Class.forName("oracle.jdbc.OracleDriver");
    	} catch (ClassNotFoundException e) {
    		throw new ExceptionInInitializerError (
    					"No se ha encontrado el driver JDBC de Oracle"
    			);
    	}
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

