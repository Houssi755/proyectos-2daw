package dao;

import java.time.LocalDate;
import java.util.List;

import dao.AutorDAO;
import model.Autor;

public class PruebaAutorDAO {

    public static void main(String[] args) {
        
        AutorDAO autorDAO = new AutorDAO();
        LocalDate fechaNac = LocalDate.of(1914, 8, 26);
        
        Autor nuevoAutor = new Autor(99, "Julio Cortázar", "Argentina", fechaNac);
        
        autorDAO.insertar(nuevoAutor);
        
        Autor encontrado = autorDAO.finAutoriaByID(nuevoAutor.getIdAutor());
        if(encontrado != null) {
            System.out.println("Autor encontrado: " + encontrado.toString());
        }
        
        
    }
}