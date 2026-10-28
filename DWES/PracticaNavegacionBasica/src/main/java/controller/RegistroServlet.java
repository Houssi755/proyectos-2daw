package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("registro.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String edadString = request.getParameter("edad");

        PrintWriter out = response.getWriter();
        
        if (nombre == null || nombre.isBlank() || correo == null || correo.isBlank() || edadString == null || edadString.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>Faltan datos</h1>");
            out.println("<a href='registro.html'>Volver</a>");
            return;
        }
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head><meta charset='UTF-8'><title>Datos</title></head>");
        out.println("<body>");
        out.println("<h1>Datos recibidos</h1>");
        out.println("<p>Nombre: " + nombre + "</p>");
        out.println("<p>Correo: " + correo + "</p>");
        out.println("<p>Edad: " + edadString + "</p>");
        out.println("</body></html>");
    }
}