<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bienvenida a JSP</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>

<main class="container page">
  <section class="hero">
    <div>
      <p class="eyebrow">Primera vista del bloque</p>
      <h1>Java también puede construir la web</h1>
      <p>Esta pantalla está escrita como JSP, procesada por Tomcat y entregada al navegador como HTML.</p>
      <!-- TODO 1: escribe una expresión EL que muestre el texto "JSP procesada correctamente". -->
      <span class="badge">${"JSP Procesada Correctamente"}</span>
    </div>
    <img class="hero-art" src="${pageContext.request.contextPath}/assets/img/welcome-code.svg" alt="De JSP a una página web">
  </section>
</main>
<footer class="footer">DWES · UT3 · JSP y MVC</footer>
</body>
</html>
