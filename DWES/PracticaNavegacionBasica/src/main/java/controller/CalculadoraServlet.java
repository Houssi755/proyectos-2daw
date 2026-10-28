package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calculadora")
public class CalculadoraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String num1String = request.getParameter("num1");
        String num2String = request.getParameter("num2");

        PrintWriter out = response.getWriter();

        if (num1String == null || num1String.isBlank() || num2String == null || num2String.isBlank()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>Faltan datos</h1>");
            out.println("<a href='calculadora.html'>Volver</a>");
            return;
        }

        double num1;
        double num2;

        try {
            num1 = Double.parseDouble(num1String.replace(',', '.'));
            num2 = Double.parseDouble(num2String.replace(',', '.'));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<h1>Los valores deben ser números</h1>");
            out.println("<a href='calculadora.html'>Volver</a>");
            return;
        }

        double resultado = num1 + num2;

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head><meta charset='UTF-8'><title>Calculadora</title></head>");
        out.println("<body>");
        out.println("<h1>Resultado</h1>");
        out.println("<p>" + num1 + " + " + num2 + " = " + resultado + "</p>");
        out.println("<a href='calculadora.html'>Nueva suma</a>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}