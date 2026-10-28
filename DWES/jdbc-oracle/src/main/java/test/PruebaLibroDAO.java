package test;

import java.time.LocalDate;
import java.util.List;
import dao.AutorDAO;
import dao.CategoriaDAO;
import dao.LibroDAO;
import model.Autor;
import model.Categoria;
import model.Libro;

public class PruebaLibroDAO {

    public static void main(String[] args) {
        
        LibroDAO libroDAO = new LibroDAO();
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        AutorDAO autorDAO = new AutorDAO();

        Categoria categoria = categoriaDAO.findById(1);
        Autor autor = autorDAO.finAutoriaByID(1);
        Libro nuevoLibro = new Libro(78, "Titulo test dao con autor y categoria", 49, 2026, "SI", autor, categoria);
        nuevoLibro.setPrecio(1000);
        int id_libro = 999;
        
        Autor autorNuevo = new Autor(99, "NuevoAutor", "España", LocalDate.now().minusYears(30));
        Libro nuevoLibro2 = new Libro(999, "Titulo nuevo libro con nuevo autor", 37, 2026, "SI", autorNuevo, categoria);
        
        int filasAfectadas = libroDAO.insertWithAutor(nuevoLibro2);
        System.out.println("Filas afectadas: " + filasAfectadas);
        
        
        
        /*
        AutorDAO autorDAO = new AutorDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        Autor autor = autorDAO.finAutoriaByID(1); 
        Categoria categoria = categoriaDAO.findById(1);
        
        Libro libro = new Libro(99, "Don Quijote de la Mancha", 19.95, 1605, "SI", autor, categoria);
        
        libroDAO.insert(libro);
        libroDAO.findById(99);
        */

        Libro libroParaActualizar = libroDAO.findById(1);
        
        libroParaActualizar.setPrecio(45.50);
        libroParaActualizar.setDisponible("NO");

        libroDAO.updateLibro(libroParaActualizar);
        System.out.println(libroDAO.findById(1).toString());
        
        /*
        List<Libro> lista = libroDAO.findAll();
        for (Libro l : lista) {
            System.out.println(l.toString());
        }
        
        libroDAO.delete(99);
        */
        
    }
    
}