package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Autor;
import model.Categoria;
import model.Libro;
import test.Conexion;

public class LibroDAO {

	public int insert(Libro libro) {
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
            ps.setInt(6, libro.getAutor().getIdAutor());
            ps.setInt(7, libro.getCategoria().getIdCategoria());

            filasAfectadas = ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAfectadas;
    }

    public int insert(Connection con, Libro libro) throws SQLException {
        String sql = "INSERT INTO LIBROS (ID_LIBRO, TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE, ID_AUTOR, ID_CATEGORIA) VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, libro.getIdLibro());
            ps.setString(2, libro.getTitulo());
            ps.setDouble(3, libro.getPrecio());
            ps.setInt(4, libro.getAnio_publicacion());
            ps.setString(5, libro.getDisponible());
            ps.setInt(6, libro.getAutor().getIdAutor());
            ps.setInt(7, libro.getCategoria().getIdCategoria());

            return ps.executeUpdate();
        }
    }

    public Libro findById(int idLibro) {
        Libro libro = null;
        Conexion conexionBD = new Conexion();
        
        String sql = "SELECT L.ID_LIBRO, L.TITULO, L.PRECIO, L.ANIO_PUBLICACION, L.DISPONIBLE, " +
                     "A.ID_AUTOR, A.NOMBRE AS AUTOR_NOMBRE, A.NACIONALIDAD, A.FECHA_NACIMIENTO, " +
                     "C.ID_CATEGORIA, C.NOMBRE AS CATEGORIA_NOMBRE, C.DESCRIPCION " +
                     "FROM LIBROS L JOIN AUTORES A ON L.ID_AUTOR = A.ID_AUTOR " +
                     "JOIN CATEGORIAS C ON L.ID_CATEGORIA = C.ID_CATEGORIA " +
                     "WHERE L.ID_LIBRO = ?";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                libro = mapLibro(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }

    public List<Libro> findAll() {
        List<Libro> libros = new ArrayList<>();
        Conexion conexionBD = new Conexion();
        
        String sql = "SELECT L.ID_LIBRO, L.TITULO, L.PRECIO, L.ANIO_PUBLICACION, L.DISPONIBLE, " + 
                     "A.ID_AUTOR, A.NOMBRE AS AUTOR_NOMBRE, A.NACIONALIDAD, A.FECHA_NACIMIENTO, " + 
                     "C.ID_CATEGORIA, C.NOMBRE AS CATEGORIA_NOMBRE, C.DESCRIPCION " +
                     "FROM LIBROS L JOIN AUTORES A ON L.ID_AUTOR=A.ID_AUTOR " +
                     "JOIN CATEGORIAS C ON L.ID_CATEGORIA=C.ID_CATEGORIA";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                libros.add(mapLibro(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    // READ: Buscar libros por ID de Autor
    public List<Libro> findByAutor(int idAutor) {
        List<Libro> librosDelAutor = new ArrayList<>();
        Conexion conexionBD = new Conexion();
        String sql = "SELECT ID_LIBRO, TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE, ID_AUTOR, ID_CATEGORIA FROM libros WHERE ID_AUTOR = ?";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAutor);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("ID_LIBRO"), rs.getString("TITULO"), rs.getDouble("PRECIO"),
                        rs.getInt("ANIO_PUBLICACION"), rs.getString("DISPONIBLE"),
                        rs.getInt("ID_AUTOR"), rs.getInt("ID_CATEGORIA")
                );
                librosDelAutor.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librosDelAutor;
    }

    public int updateLibro(Libro libro) {
        int filasAfectadas = 0;
        Conexion conexionBD = new Conexion();
        String sql = "UPDATE libros SET titulo = ?, precio = ?, anio_publicacion = ?, disponible = ?, id_autor = ?, id_categoria = ? WHERE id_libro = ?";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setDouble(2, libro.getPrecio());
            ps.setInt(3, libro.getAnio_publicacion());
            ps.setString(4, libro.getDisponible());
            ps.setInt(5, libro.getAutor().getIdAutor());
            ps.setInt(6, libro.getCategoria().getIdCategoria());
            ps.setInt(7, libro.getIdLibro()); 

            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAfectadas;
    }

    // DELETE: Borrar un libro por su ID
    public int delete(int idLibro) {
        int filasAfectadas = 0;
        Conexion conexionBD = new Conexion();
        String sql = "DELETE FROM libros WHERE id_libro = ?";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLibro);
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filasAfectadas;
    }
    
    public List<Libro> findByCategoria(int idCategoria) {
        List<Libro> librosCategoria = new ArrayList<>();
        Conexion conexionBD = new Conexion();
        String sql = "SELECT ID_LIBRO, TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE, ID_AUTOR, ID_CATEGORIA FROM libros WHERE ID_CATEGORIA = ?";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("ID_LIBRO"), rs.getString("TITULO"), rs.getDouble("PRECIO"),
                        rs.getInt("ANIO_PUBLICACION"), rs.getString("DISPONIBLE"),
                        rs.getInt("ID_AUTOR"), rs.getInt("ID_CATEGORIA")
                );
                librosCategoria.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librosCategoria;
    }
    
    private Libro mapLibro(ResultSet rs) throws SQLException {
        
        Autor autor = new Autor(
                rs.getInt("ID_AUTOR"), 
                rs.getString("AUTOR_NOMBRE"), 
                rs.getString("NACIONALIDAD"), 
                rs.getDate("FECHA_NACIMIENTO").toLocalDate()
        );
        
        Categoria categoria = new Categoria(
                rs.getInt("ID_CATEGORIA"), 
                rs.getString("CATEGORIA_NOMBRE"), 
                rs.getString("DESCRIPCION")
        );
        
        return new Libro(
                rs.getInt("ID_LIBRO"), 
                rs.getString("TITULO"), 
                rs.getDouble("PRECIO"), 
                rs.getInt("ANIO_PUBLICACION"), 
                rs.getString("DISPONIBLE"), 
                autor, 
                categoria
        );
    }
    public Libro insertWithAutor(Libro libro) {
        Conexion conexionBD = new Conexion();

        AutorDAO autorDAO = new AutorDAO();
        autorDAO.insertar(libro.getAutor());

        String sql = "INSERT INTO LIBROS (ID_LIBRO, TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE, ID_AUTOR, ID_CATEGORIA) VALUES(?,?,?,?,?,?,?)";

        try (Connection con = conexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, libro.getIdLibro());
            ps.setString(2, libro.getTitulo());
            ps.setDouble(3, libro.getPrecio());
            ps.setInt(4, libro.getAnio_publicacion());
            ps.setString(5, libro.getDisponible());
            ps.setInt(6, libro.getAutor().getIdAutor());
            ps.setInt(7, libro.getCategoria().getIdCategoria());

            int filasAfectadas = ps.executeUpdate();
            
            con.commit();

        } catch (SQLException e) {
            con.rollback();
        }
        
        catch (SQLException e) {
        	
        }
        return null;
    }
}