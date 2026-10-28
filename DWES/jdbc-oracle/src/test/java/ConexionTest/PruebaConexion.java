package ConexionTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;     

import model.Libro;
import test.Conexion;

public class PruebaConexion {

    public static void main(String[] args) {
        
        Conexion conexionBD = new Conexion();
        
        String sql = "select ID_LIBRO, TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE "+
                     "from libros where ID_AUTOR=? AND PRECIO < ?";
        
        int idAutor = 6;
        double precio = 14;
        
        List<Libro> listaLibros = new ArrayList<Libro>();
        
        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ){
            
            ps.setInt(1, idAutor);
            ps.setDouble(2, precio);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Libro libro = new Libro (rs.getInt("ID_LIBRO"), rs.getString("TITULO"), rs.getDouble("PRECIO"), rs.getInt("ANIO_PUBLICACION"), rs.getString("DISPONIBLE"));
                
                listaLibros.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Libros guardados: " + listaLibros.size());
    }
}