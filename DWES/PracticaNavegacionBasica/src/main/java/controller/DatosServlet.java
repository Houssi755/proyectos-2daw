package controller;

import java.io.IOException;

import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/datos")
public class DatosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String ciudad = request.getParameter("ciudad");
        String edadString = request.getParameter("edad");
        
        if (nombre == null || nombre.isBlank())
            nombre = "visitante";

        if (ciudad == null || ciudad.isBlank())
            ciudad = "Ajofrín";
        
        if (edadString == null || edadString.isBlank())
            edadString = "18";
        
        PrintWriter out = response.getWriter();

        int edad;

        try {
            edad = Integer.parseInt(edadString);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>La edad debe ser un número entero</h1>");
            out.println("<a href='inicio'>Volver</a>");
            return;
        }

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head><meta charset='UTF-8'><title>Datos</title></head>");
        out.println("<body>");
        out.println("<h1>Hola, " + nombre + " de " + ciudad + " con " + edad + " años</h1>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}