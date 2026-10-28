package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Autor;
import test.Conexion;

public class AutorDAO {
    
    private Conexion conexionBD;
    
    public AutorDAO() {
        conexionBD = new Conexion();
    }
    
    public List<Autor> findAll() {
        String sql = "SELECT * FROM AUTORES";
        List<Autor> autores = new ArrayList<>();
        
        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                autores.add(mapAutor(rs));
            }
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

    public Autor finAutoriaByID(int idAutor) {
        Autor autor = null;
        String sql = "SELECT * FROM AUTORES WHERE ID_AUTOR = ?";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAutor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    autor = mapAutor(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return autor;
    }
    
    public int insert(Autor autor) {
        int filasAfectadas = 0;
        Conexion conexionBD = new Conexion();
        String sql = "INSERT INTO AUTORES (ID_AUTOR, NOMBRE, NACIONALIDAD, FECHA_NACIMIENTO) VALUES (?, ?, ?, ?)";
        
        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, autor.getIdAutor());
            ps.setString(2, autor.getNombre());
            ps.setString(3, autor.getNacionalidad());
            ps.setDate(4, java.sql.Date.valueOf(autor.getFechaNacimiento()));
            
            filasAfectadas = ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAfectadas;
    }

    public int insert(Connection con, Autor autor) {
        int filasAfectadas = 0;
        String sql = "INSERT INTO autores (ID_AUTOR, NOMBRE, NACIONALIDAD, FECHA_NACIMIENTO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, autor.getIdAutor());
            ps.setString(2, autor.getNombre());
            ps.setString(3, autor.getNacionalidad());
            ps.setObject(4, autor.getFechaNacimiento());
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar autor: " + e.getMessage());
            e.printStackTrace();
        }
        return filasAfectadas;
    }
    
    public int update(Autor autor) {
        String sql = "UPDATE AUTORES SET NOMBRE = ?, NACIONALIDAD = ?, FECHA_NACIMIENTO = ? WHERE ID_AUTOR = ?";
        int filas = 0;

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, autor.getNombre());
            ps.setString(2, autor.getNacionalidad());
            ps.setDate(3, Date.valueOf(autor.getFechaNacimiento()));
            ps.setInt(4, autor.getIdAutor());
            
            filas = ps.executeUpdate();
            System.out.println("Filas afectadas: " + filas);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas;
    }
    
    public int deleteByID(int idAutor) {
        int filasAfectadas = 0;
        String sql = "DELETE FROM AUTORES WHERE ID_AUTOR = ?";
        
        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idAutor);
            filasAfectadas = ps.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filasAfectadas;
    }
    
    private Autor mapAutor(ResultSet rs) throws SQLException {
        LocalDate date = rs.getDate("FECHA_NACIMIENTO").toLocalDate();
        return new Autor(
                rs.getInt("ID_AUTOR"),
                rs.getString("NOMBRE"), 
                rs.getString("NACIONALIDAD"), 
                date
        );
    }
    
    
}