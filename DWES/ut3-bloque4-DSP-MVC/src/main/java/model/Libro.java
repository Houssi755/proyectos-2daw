package model;

public class Libro {

    private int idLibro;
    private String titulo;
    private double precio;
    private int anioPublicacion;
    private String disponible;
    private Autor autor;
    private Categoria categoria;

    public Libro() {
    }

    public Libro(int idLibro, String titulo, double precio, int anioPublicacion,
                 String disponible, Autor autor, Categoria categoria) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.precio = precio;
        this.anioPublicacion = anioPublicacion;
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

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
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
}

