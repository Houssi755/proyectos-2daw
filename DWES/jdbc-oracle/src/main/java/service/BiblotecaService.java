package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.AutorDAO;
import dao.LibroDAO;
import model.Libro;
import test.Conexion;

public class BiblotecaService {

	private Conexion conexionBD;
	private AutorDAO autorDAO;
	private LibroDAO libroDAO;
	
	public BiblotecaService() {
		conexionBD = new Conexion();
		autorDAO = new AutorDAO();
		libroDAO = new LibroDAO();
	}
	
	public void registrarLibroConAutor(Libro libro) throws SQLException {
		
		try(Connection con = conexionBD.getConnection()) {
			
			con.setAutoCommit(false);
			
			try {
				autorDAO.insert(con, libro.getAutor());
				libroDAO.insert(con, libro);

				con.commit();
				
			} catch (SQLException e) {
				con.rollback();
				throw e; 
			}
			
		}
	}
}