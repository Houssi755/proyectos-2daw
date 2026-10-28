<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Saludo</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>

<main class="container page">
  <section class="hero compact">
    <div>
      <p class="eyebrow">Controller → JSP</p>
      <!-- TODO 1: muestra el atributo "nombre" mediante EL. -->
      <h1>Hola, ${nombre}</h1>
      <p>El nombre no está escrito en la vista: viaja como atributo dentro de la petición.</p>
      <a class="btn btn-secondary" href="${pageContext.request.contextPath}/bienvenida">Volver al inicio</a>
    </div>
    <img class="hero-art" src="${pageContext.request.contextPath}/assets/img/welcome-code.svg" alt="Aplicación web dinámica">
  </section>
</main>
<footer class="footer">DWES · UT3 · JSP y MVC</footer>
</body>
</html>
