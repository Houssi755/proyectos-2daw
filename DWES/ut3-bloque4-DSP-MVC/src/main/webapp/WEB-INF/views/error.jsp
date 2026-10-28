<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Biblioteca Aurora · Error</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body class="error-page">
<main class="error-card">
    <span class="error-code">${requestScope['jakarta.servlet.error.status_code']}</span>
    <h1>No se ha podido mostrar la página</h1>
    <p>
        <c:choose>
            <c:when test="${not empty mensajeError}"><c:out value="${mensajeError}"/></c:when>
            <c:when test="${not empty requestScope['jakarta.servlet.error.message']}"><c:out value="${requestScope['jakarta.servlet.error.message']}"/></c:when>
            <c:otherwise>Revisa la URL, la acción solicitada y la conexión con Oracle.</c:otherwise>
        </c:choose>
    </p>
    <a class="button button-primary" href="${pageContext.request.contextPath}/libros">Volver al catálogo</a>
</main>
</body>
</html>

