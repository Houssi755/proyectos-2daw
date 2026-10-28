package test;

import java.util.List;

import dao.CategoriaDAO;
import model.Categoria;

public class PruebaCategoriaDAO {

	public static void main(String[] args) {
		
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria nuevaCategoria = new Categoria(7, "Titulo test dao", "Prueba Categoria DAO");

//		int insertadas = categoriaDAO.insert(nuevaCategoria);
//		System.out.println("Categorías insertadas: " + insertadas);
//		
//		Categoria encontrada = categoriaDAO.findById(nuevaCategoria.getIdCategoria());
//		if(encontrada != null) {
//			System.out.println("Categoría encontrada: " + encontrada.toString());
//		}
//		nuevaCategoria.setDescripcion("Descripción modificada en prueba");
//		categoriaDAO.updateCategoria(nuevaCategoria);
//		System.out.println("Tras actualizar: " + categoriaDAO.findById(nuevaCategoria.getIdCategoria()).toString());
		
		List<Categoria> lista = categoriaDAO.findAll();
		for (Categoria categoriaActual : lista) {
			System.out.println(categoriaActual.toString());
		}
		
		

//		int borradas = categoriaDAO.delete(nuevaCategoria.getIdCategoria());
//		System.out.println("Categorías borradas: " + borradas);
	}
	
	
}