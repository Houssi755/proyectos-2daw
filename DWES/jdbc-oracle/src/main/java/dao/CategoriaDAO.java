package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import test.Conexion;

public class CategoriaDAO {
	
	public int insert(Categoria categoria) {
		int filasAfectadas = 0;
		Conexion conexionBD = new Conexion();
		String sql = "INSERT INTO categorias (ID_CATEGORIA, NOMBRE, DESCRIPCION) VALUES(?,?,?)";

		try (Connection con = conexionBD.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, categoria.getIdCategoria());
			ps.setString(2, categoria.getNombre());
			ps.setString(3, categoria.getDescripcion());

			filasAfectadas = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error al insertar categoría: " + e.getMessage());
			e.printStackTrace();
		}
		return filasAfectadas;
	}

	public Categoria findById(int idCategoria) {
		Categoria categoria = null;
		Conexion conexionBD = new Conexion();
		String sql = "SELECT ID_CATEGORIA, NOMBRE, DESCRIPCION FROM categorias WHERE ID_CATEGORIA = ?";

		try (Connection con = conexionBD.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idCategoria);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				categoria = mapCategoria(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoria;
	}

	public List<Categoria> findAll() {
		List<Categoria> categorias = new ArrayList<>();
		Conexion conexionBD = new Conexion();
		String sql = "SELECT ID_CATEGORIA, NOMBRE, DESCRIPCION FROM categorias";

		try (Connection con = conexionBD.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				categorias.add(mapCategoria(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorias;
	}

	public int updateCategoria(Categoria categoria) {
		int filasAfectadas = 0;
		Conexion conexionBD = new Conexion();
		String sql = "UPDATE categorias SET NOMBRE = ?, DESCRIPCION = ? WHERE ID_CATEGORIA = ?";

		try (Connection con = conexionBD.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, categoria.getNombre());
			ps.setString(2, categoria.getDescripcion());
			ps.setInt(3, categoria.getIdCategoria()); 

			filasAfectadas = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}

	public int delete(int idCategoria) {
		int filasAfectadas = 0;
		Conexion conexionBD = new Conexion();
		String sql = "DELETE FROM categorias WHERE ID_CATEGORIA = ?";

		try (Connection con = conexionBD.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, idCategoria);
			filasAfectadas = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}
	
	private Categoria mapCategoria(ResultSet rs) throws SQLException {
		return new Categoria(
				rs.getInt("ID_CATEGORIA"), 
				rs.getString("NOMBRE"),
				rs.getString("DESCRIPCION")
		);
	}
}