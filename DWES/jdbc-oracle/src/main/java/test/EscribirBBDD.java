package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Libro;

public class EscribirBBDD {

    public static void main(String[] args) {
        
//        Libro libroNuevo = new Libro(105, "Libro de Prueba Insert", 19.99, 2026, "S", 1, 1);
//        int filasInsertadas = insertLibro(libroNuevo);
//        System.out.println("Resultado: Se han insertado " + filasInsertadas + " fila(s).");
//
//        Libro libroModificado = new Libro(12, "test1", 45.0, 1995, "S", 2, 4);
//        int filasActualizadas = updateLibro(libroModificado);
//        System.out.println("Resultado: Se han actualizado " + filasActualizadas + " fila(s).");
        
//        int filasBorradas = deleteById(105);
//        System.out.println("Resultado: Se han borrado " + filasBorradas + " fila(s).");
        
//        int idBuscar = 12;
//        Libro libroRecuperado = findLibroById(idBuscar);
//        
//        if (libroRecuperado != null) {
//            System.out.println("Resultado: Libro encontrado -> " + libroRecuperado.toString());
//        } else {
//            System.out.println("Resultado: No se encontró ningún libro con el ID " + idBuscar);
//        }
        
//        List<Libro> todosLosLibros = findAllLibros();
//        for (Libro l : todosLosLibros) {
//        	System.out.println(l.toString());
//        }
//        	System.out.println("Total de libros : " + todosLosLibros.size());
    	}
    	

    private static int insertLibro(Libro libro) {
        int filasAfectadas = 0;
        Conexion conexionBD = new Conexion();

        String sql = "INSERT INTO LIBROS (ID_LIBRO, TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE, ID_AUTOR, ID_CATEGORIA) VALUES(?,?,?,?,?,?,?)";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, libro.getIdLibro());
            ps.setString(2, libro.getTitulo());
            ps.setDouble(3, libro.getPrecio());
            ps.setInt(4, libro.getAnio_publicacion());
            ps.setString(5, libro.getDisponible());
            ps.setInt(6, libro.getIdAutor());
            ps.setInt(7, libro.getIdCategoria());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            e.printStackTrace();
        }

        return filasAfectadas;
    }
    
    private static int updateLibro(Libro libro) {
        int filasAfectadas = 0;
        Conexion conexionBD = new Conexion();

        String sql = "UPDATE libros SET titulo = ?, precio = ?, anio_publicacion = ?, disponible = ?, id_autor = ?, id_categoria = ? WHERE id_libro = ?";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, libro.getTitulo());
            ps.setDouble(2, libro.getPrecio());
            ps.setInt(3, libro.getAnio_publicacion());
            ps.setString(4, libro.getDisponible());
            ps.setInt(5, libro.getIdAutor());
            ps.setInt(6, libro.getIdCategoria());
            ps.setInt(7, libro.getIdLibro()); 

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            e.printStackTrace();
        }

        return filasAfectadas;
    }
    
    private static int deleteById(int id_libro) {
        int filasAfectadas = 0;
        Conexion conexionBD = new Conexion();
        String sql = "DELETE FROM libros WHERE id_libro=?";
        
        try (Connection con = conexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id_libro);
            filasAfectadas = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al borrar: " + e.getMessage());
            e.printStackTrace();
        }
        return filasAfectadas;
    }
    
    private static Libro findLibroById(int id_libro) {
        Libro libroEncontrado = null;
        Conexion conexionBD = new Conexion();

        String sql = "SELECT ID_LIBRO, TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE, ID_AUTOR, ID_CATEGORIA FROM libros WHERE ID_LIBRO = ?";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_libro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                libroEncontrado = new Libro(
                        rs.getInt("ID_LIBRO"),
                        rs.getString("TITULO"),
                        rs.getDouble("PRECIO"),
                        rs.getInt("ANIO_PUBLICACION"),
                        rs.getString("DISPONIBLE"),
                        rs.getInt("ID_AUTOR"),
                        rs.getInt("ID_CATEGORIA")
                       
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
            e.printStackTrace();
        }
        return libroEncontrado;
    }
    private static List<Libro> findAllLibros() {
    	List<Libro> libros = new ArrayList<Libro>();
    	Conexion conexionBD = new Conexion();
        
        String sql = "SELECT ID_LIBRO, TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE, ID_AUTOR, ID_CATEGORIA FROM libros";
    	
        try (Connection con = conexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
               ResultSet rs = ps.executeQuery();

               while (rs.next()) {
                   Libro libro = new Libro(
                           rs.getInt("ID_LIBRO"), 
                           rs.getString("TITULO"), 
                           rs.getDouble("PRECIO"),
                           rs.getInt("ANIO_PUBLICACION"), 
                           rs.getString("DISPONIBLE"), 
                           rs.getInt("ID_AUTOR"), 
                           rs.getInt("ID_CATEGORIA")
                   );
                   
                   libros.add(libro);
               }
        } catch (SQLException e) {
        	System.out.println("Error al buscar todos los libros: " + e.getMessage());
			e.printStackTrace();
		}
		return libros;
    	
    }
    private static int marcarNoDisponiblePorPrecio(double precioMaximo) {
        int filasAfectadas = 0;
        Conexion conexionBD = new Conexion();
        
        
        String sql = "" ;
        
        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, precioMaximo);
            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error ,no disponible: " + e.getMessage());
            e.printStackTrace();
        }

        return filasAfectadas;
    }
}