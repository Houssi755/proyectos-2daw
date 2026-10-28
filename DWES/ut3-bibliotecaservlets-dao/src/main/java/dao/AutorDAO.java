package dao;

import conexion.ConnectionFactory;
import model.Autor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public List<Autor> findAll() throws SQLException {
        String sql = """
                SELECT ID_AUTOR, NOMBRE, NACIONALIDAD, FECHA_NACIMIENTO
                FROM AUTORES
                ORDER BY NOMBRE
                """;

        List<Autor> autores = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                autores.add(mapearAutor(rs));
            }
        }

        return autores;
    }

    public Autor findById(int idAutor) throws SQLException {
        String sql = """
                SELECT ID_AUTOR, NOMBRE, NACIONALIDAD, FECHA_NACIMIENTO
                FROM AUTORES
                WHERE ID_AUTOR = ?
                """;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAutor);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapearAutor(rs) : null;
            }
        }
    }

    private Autor mapearAutor(ResultSet rs) throws SQLException {
        Date fecha = rs.getDate("FECHA_NACIMIENTO");

        return new Autor(
                rs.getInt("ID_AUTOR"),
                rs.getString("NOMBRE"),
                rs.getString("NACIONALIDAD"),
                fecha == null ? null : fecha.toLocalDate()
        );
    }
}

