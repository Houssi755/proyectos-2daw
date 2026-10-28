package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet("/resumenalumno")
public class ProcesarAlumnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String nombre = request.getParameter("nombre");
		String edadString = request.getParameter("edad");
		String fechaString = request.getParameter("fecha");
		String turno = request.getParameter("turno");
		String matriculaActiva = request.getParameter("activo");
		String ciclo = request.getParameter("ciclo");

		out.println("<!DOCTYPE html>");
		out.println("<html lang='es'>");
		out.println("<head><meta charset='UTF-8'><title>Resumen Alumno</title></head>");
		out.println("<body>");

		if (edadString == null && fechaString == null) {
			out.println("<h1>Datos del alumno</h1>");
			out.println("<form action='resumenalumno' method='get'>");
			out.println("<label>Nombre: <input name='nombre' type='text'></label><br>");
			out.println("<label>Edad: <input name='edad' type='number' min='16' max='99' required></label><br>");
			out.println("<label>Fecha: <input name='fecha' type='date' required></label><br>");
			out.println("<p>Turno:</p>");
			out.println("<label><input name='turno' type='radio' value='manana' checked> Mañana</label>");
			out.println("<label><input name='turno' type='radio' value='tarde'> Tarde</label><br>");
			out.println("<label><input name='activo' type='checkbox' value='si'> Matrícula activa</label><br>");
			out.println("<select name='ciclo'>");
			out.println("<option value='daw'>DAW</option>");
			out.println("<option value='dam'>DAM</option>");
			out.println("<option value='asir'>ASIR</option>");
			out.println("</select><br><br>");
			out.println("<button type='submit'>Enviar</button>");
			out.println("</form>");
			out.println("</body></html>");
			return;
		}

		if (nombre == null || nombre.isBlank()) {
			nombre = "Anónimo";
		}

		int edad;
		try {
			edad = Integer.parseInt(edadString);
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.println("<h1>La edad debe ser un número entero</h1>");
			out.println("<a href='resumenalumno'>Volver al formulario</a>");
			out.println("</body></html>");
			return;
		}

		if (edad < 16 || edad > 99) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.println("<h1>La edad debe estar entre 16 y 99</h1>");
			out.println("<a href='resumenalumno'>Volver al formulario</a>");
			out.println("</body></html>");
			return;
		}

		LocalDate fecha;
		try {
			fecha = LocalDate.parse(fechaString);
		} catch (NullPointerException | DateTimeParseException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			out.println("<h1>La fecha no es válida</h1>");
			out.println("<a href='resumenalumno'>Volver al formulario</a>");
			out.println("</body></html>");
			return;
		}

		String turnoTexto;
		if ("manana".equals(turno))
			turnoTexto = "Mañana";
		else if ("tarde".equals(turno))
			turnoTexto = "Tarde";
		else
			turnoTexto = "No indicado";

		String activoTexto = "si".equals(matriculaActiva) ? "Sí" : "No";

		String cicloTexto;
		if ("daw".equals(ciclo) || "dam".equals(ciclo) || "asir".equals(ciclo))
			cicloTexto = ciclo.toUpperCase();
		else
			cicloTexto = "No indicado";

		out.println("<h1>Resumen del Alumno</h1>");
		out.println("<p><strong>Nombre:</strong> " + nombre + "</p>");
		out.println("<p><strong>Edad:</strong> " + edad + "</p>");
		out.println("<p><strong>Fecha:</strong> " + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</p>");
		out.println("<p><strong>Turno:</strong> " + turnoTexto + "</p>");
		out.println("<p><strong>Matrícula activa:</strong> " + activoTexto + "</p>");
		out.println("<p><strong>Ciclo:</strong> " + cicloTexto + "</p>");
		out.println("<br><a href='resumenalumno'>Volver al formulario</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
