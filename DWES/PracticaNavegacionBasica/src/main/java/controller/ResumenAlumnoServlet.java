package controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ResumenAlumnoServlet")
public class ResumenAlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String nombre = request.getParameter("nombre");
        String edad = request.getParameter("edad");
        String fecha = request.getParameter("fecha");
        String turno = request.getParameter("turno");
        String activo = request.getParameter("activo");
        String ciclo = request.getParameter("ciclo");

        String [] lenguajes = request.getParameterValues("lenguajes");

        PrintWriter out = response.getWriter();

        if (nombre == null || nombre.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>Falta el nombre</h1>");
            out.println("<a href='alumno.html'>Volver</a>");
            return;
        }

        if (edad == null || edad.isBlank())
            edad = "No indicada";

        if (fecha == null || fecha.isBlank())
            fecha = "No indicada";

        if (turno == null)
            turno = "No indicado";

        if (activo == null)
            activo = "no";

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head><meta charset='UTF-8'><title>Resumen alumno</title></head>");
        out.println("<body>");
        out.println("<h1>Resumen del alumno</h1>");
        out.println("<p>Nombre: " + nombre + "</p>");
        out.println("<p>Edad: " + edad + "</p>");
        out.println("<p>Fecha: " + fecha + "</p>");
        out.println("<p>Turno: " + turno + "</p>");
        out.println("<p>Matrícula activa: " + activo + "</p>");
        out.println("<p>Ciclo: " + ciclo + "</p>");

        out.println("<p>Lenguajes:</p>");
        if (lenguajes == null)
        	out.println("<p>No hay ningun lenguaje seleccionado</p>");
        else {
            out.println("<ul>");
            for (String lenguaje : lenguajes)
                out.println("<li>" + lenguaje + "</li>");
            out.println("</ul>");
        }

        out.println("<a href='alumno.html'>Volver</a>");
        out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
