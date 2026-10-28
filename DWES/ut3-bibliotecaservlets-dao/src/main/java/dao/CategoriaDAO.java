package dao;

import conexion.ConnectionFactory;
import model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public List<Categoria> findAll() throws SQLException {
        String sql = """
                SELECT ID_CATEGORIA, NOMBRE, DESCRIPCION
                FROM CATEGORIAS
                ORDER BY NOMBRE
                """;

        List<Categoria> categorias = new ArrayList<>();

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categorias.add(mapearCategoria(rs));
            }
        }

        return categorias;
    }

    public Categoria findById(int idCategoria) throws SQLException {
        String sql = """
                SELECT ID_CATEGORIA, NOMBRE, DESCRIPCION
                FROM CATEGORIAS
                WHERE ID_CATEGORIA = ?
                """;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapearCategoria(rs) : null;
            }
        }
    }

    private Categoria mapearCategoria(ResultSet rs) throws SQLException {
        return new Categoria(
                rs.getInt("ID_CATEGORIA"),
                rs.getString("NOMBRE"),
                rs.getString("DESCRIPCION")
        );
    }
}

