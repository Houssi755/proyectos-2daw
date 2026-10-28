package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html lang='es'><head><meta charset='UTF-8'>");
        response.getWriter().println("<title>Inicio</title></head><body>");
        response.getWriter().println("<h1>Aplicación de prácticas</h1>");
        response.getWriter().println("<ul>");
        response.getWriter().println("<li><a href='saludo?nombre=Ana'>Saludo</a></li>");
        response.getWriter().println("<li><a href='datos?nombre=Ana&ciudad=León&edad=18'>Datos</a></li>");
        response.getWriter().println("<li><a href='registro.html'>Registro</a></li>");
        response.getWriter().println("<li><a href='calculadora.html'>Calculadora</a></li>");
        response.getWriter().println("<li><a href='alumno.html'>Alumno</a></li>");
        response.getWriter().println("<li><a href='producto.html'>Alta producto</a></li>");
        response.getWriter().println("</ul></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}