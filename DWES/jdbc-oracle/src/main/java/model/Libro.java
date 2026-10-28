package model;

public class Libro {

	private int idLibro;
	private String titulo;
	private double precio;
	private int anio_publicacion;
	private String disponible; 
	private Autor autor;
	private Categoria categoria;
	
	public Libro(int idLibro, String titulo, double precio, int anio_publicacion, String disponible, Autor autor,
			Categoria categoria) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.precio = precio;
		this.anio_publicacion = anio_publicacion;
		this.disponible = disponible;
		this.autor = autor;
		this.categoria = categoria;
	}
	
	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getAnio_publicacion() {
		return anio_publicacion;
	}
	public void setAnio_publicacion(int anio_publicacion) {
		this.anio_publicacion = anio_publicacion;
	}
	public String getDisponible() {
		return disponible;
	}
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Libro [idLibro=" + idLibro + ", titulo=" + titulo + ", precio=" + precio + ", anio_publicacion="
				+ anio_publicacion + ", disponible=" + disponible + ", autor=" + autor.toString() + ", categoria=" + categoria.toString()
				+ "]";
	}
}
