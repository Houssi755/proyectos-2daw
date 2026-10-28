package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/alta-producto")
public class AltaProductoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("producto.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String precioString = request.getParameter("precio");
        String categoria = request.getParameter("categoria");
        String disponible = request.getParameter("disponible");

        Set<String> categoriasExistentes = Set.of("informatica", "libros", "hogar");
        PrintWriter out = response.getWriter();

        if (nombre == null || nombre.isBlank() || precioString == null || precioString.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>Faltan datos</h1>");
            out.println("<a href='producto.html'>Volver</a>");
            return;
        }

        double precio;

        try {
            precio = Double.parseDouble(precioString);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>El precio debe ser un número</h1>");
            out.println("<a href='producto.html'>Volver</a>");
            return;
        }

        if (categoria == null || !categoriasExistentes.contains(categoria)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>Categoría no válida</h1>");
            out.println("<a href='producto.html'>Volver</a>");
            return;
        }

        if (disponible == null)
            disponible = "no";

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head><meta charset='UTF-8'><title>Producto dado de alta</title></head>");
        out.println("<body>");
        out.println("<h1>Producto dado de alta</h1>");
        out.println("<p>Nombre: " + nombre + "</p>");
        out.println("<p>Precio: " + precio + " €</p>");
        out.println("<p>Categoría: " + categoria + "</p>");
        out.println("<p>Disponible: " + disponible + "</p>");
        out.println("<a href='producto.html'>Dar de alta otro</a> | <a href='inicio'>Inicio</a>");
        out.println("</body></html>");
    }
}
