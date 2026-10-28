package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Autor {

    private int idAutor;
    private String nombre;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private List<Libro> libros;

    public Autor(int idAutor, String nombre, String nacionalidad, LocalDate fechaNacimiento) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.libros = new ArrayList<>(); 
    }

    public int getIdAutor() { return idAutor; }
    public void setIdAutor(int idAutor) { this.idAutor = idAutor; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public List<Libro> getLibros() { return libros; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }

    @Override
    public String toString() {
        return "Autor [idAutor=" + idAutor + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad
                + ", fechaNacimiento=" + fechaNacimiento + ", numeroDeLibros=" + libros.size() + "]";
    }
}