package dao;

import conexion.ConnectionFactory;
import model.Autor;
import model.Categoria;
import model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    private static final String SELECT_COMPLETO = """
            SELECT
                L.ID_LIBRO,
                L.TITULO,
                L.PRECIO,
                L.ANIO_PUBLICACION,
                L.DISPONIBLE,
                A.ID_AUTOR,
                A.NOMBRE AS AUTOR_NOMBRE,
                A.NACIONALIDAD,
                A.FECHA_NACIMIENTO,
                C.ID_CATEGORIA,
                C.NOMBRE AS CATEGORIA_NOMBRE,
                C.DESCRIPCION AS CATEGORIA_DESCRIPCION
            FROM LIBROS L
            JOIN AUTORES A ON A.ID_AUTOR = L.ID_AUTOR
            JOIN CATEGORIAS C ON C.ID_CATEGORIA = L.ID_CATEGORIA
            """;

    public List<Libro> findAll() throws SQLException {
        String sql = SELECT_COMPLETO + " ORDER BY L.TITULO";
        List<Libro> libros = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                libros.add(mapearLibro(rs));
            }
        }

        return libros;
    }

    public Libro findById(int idLibro) throws SQLException {
        String sql = SELECT_COMPLETO + " WHERE L.ID_LIBRO = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLibro);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapearLibro(rs) : null;
            }
        }
    }

    public int insert(Libro libro) throws SQLException {
        String sql = """
                INSERT INTO LIBROS
                    (TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE, ID_AUTOR, ID_CATEGORIA)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            asignarParametrosSinId(ps, libro);
            return ps.executeUpdate();
        }
    }
    
    public int insertWithId(Libro libro) throws SQLException {
        String sql = """
                INSERT INTO LIBROS
                    (ID_LIBRO, TITULO, PRECIO, ANIO_PUBLICACION, DISPONIBLE, ID_AUTOR, ID_CATEGORIA)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            asignarParametrosConId(ps, libro);
            return ps.executeUpdate();
        }
    }

    public int update(Libro libro) throws SQLException {
        String sql = """
                UPDATE LIBROS
                SET TITULO = ?,
                    PRECIO = ?,
                    ANIO_PUBLICACION = ?,
                    DISPONIBLE = ?,
                    ID_AUTOR = ?,
                    ID_CATEGORIA = ?
                WHERE ID_LIBRO = ?
                """;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            asignarParametrosSinId(ps, libro);
            ps.setInt(7, libro.getIdLibro());
            return ps.executeUpdate();
        }
    }

    public int delete(int idLibro) throws SQLException {
        String sql = "DELETE FROM LIBROS WHERE ID_LIBRO = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLibro);
            return ps.executeUpdate();
        }
    }

    private Libro mapearLibro(ResultSet rs) throws SQLException {
        Autor autor = new Autor(
                rs.getInt("ID_AUTOR"),
                rs.getString("AUTOR_NOMBRE"),
                rs.getString("NACIONALIDAD"),
                rs.getDate("FECHA_NACIMIENTO") == null
                        ? null
                        : rs.getDate("FECHA_NACIMIENTO").toLocalDate()
        );

        Categoria categoria = new Categoria(
                rs.getInt("ID_CATEGORIA"),
                rs.getString("CATEGORIA_NOMBRE"),
                rs.getString("CATEGORIA_DESCRIPCION")
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

    private void asignarParametrosSinId(PreparedStatement ps, Libro libro) throws SQLException {
        ps.setString(1, libro.getTitulo());
        ps.setDouble(2, libro.getPrecio());
        ps.setInt(3, libro.getAnioPublicacion());
        ps.setString(4, libro.getDisponible());
        ps.setInt(5, libro.getAutor().getIdAutor());
        ps.setInt(6, libro.getCategoria().getIdCategoria());
    }
    
    private void asignarParametrosConId(PreparedStatement ps, Libro libro) throws SQLException {
        ps.setInt(1, getNextId());
        ps.setString(2, libro.getTitulo());
        ps.setDouble(3, libro.getPrecio());
        ps.setInt(4, libro.getAnioPublicacion());
        ps.setString(5, libro.getDisponible());
        ps.setInt(6, libro.getAutor().getIdAutor());
        ps.setInt(7, libro.getCategoria().getIdCategoria());
    }
    
    public int getNextId() throws SQLException {
        String sql = "SELECT MAX(ID_LIBRO) AS MAX_ID FROM LIBROS";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

        	if (rs.next()) {
        		return rs.getInt("MAX_ID") + 1;
        	} else {
        		return 1;
        	}
        }
    }
}

