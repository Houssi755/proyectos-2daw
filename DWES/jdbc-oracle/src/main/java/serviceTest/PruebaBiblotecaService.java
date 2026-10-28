package serviceTest;

import java.sql.SQLException;
import java.time.LocalDate;

import model.Autor;
import model.Categoria;
import model.Libro;
import service.BiblotecaService;

public class PruebaBiblotecaService {

	private static BiblotecaService biblotecaService = new BiblotecaService();
	
	public static void main(String[] args) throws SQLException {
		
		Autor autorNuevo = new Autor(66, "NuevoAutorFinal", "Rusia", LocalDate.now());
		Categoria categoria = new Categoria (1, "", "");
		Libro libroNuevo = new Libro(1, "Nuevo Libro con id repetido", 49, 2026, "S", autorNuevo, categoria);
		
		probandoRegistrarLibroConAutor(libroNuevo);
	}

	public static void probandoRegistrarLibroConAutor(Libro libro) throws SQLException {
		biblotecaService.registrarLibroConAutor(libro);
	}
}
