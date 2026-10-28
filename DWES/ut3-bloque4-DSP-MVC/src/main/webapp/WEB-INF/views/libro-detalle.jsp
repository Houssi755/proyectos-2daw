<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Biblioteca Aurora · <c:out value="${libro.titulo}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/libros"><span class="brand-mark">BA</span><span>Biblioteca Aurora</span></a>
    <a class="button button-ghost" href="${pageContext.request.contextPath}/libros">Volver al catálogo</a>
</header>

<main class="detail-shell">
    <c:choose>
        <c:when test="${libro.categoria.idCategoria % 3 == 1}"><c:set var="cover" value="cover-blue.svg"/></c:when>
        <c:when test="${libro.categoria.idCategoria % 3 == 2}"><c:set var="cover" value="cover-green.svg"/></c:when>
        <c:otherwise><c:set var="cover" value="cover-orange.svg"/></c:otherwise>
    </c:choose>

    <section class="detail-card">
        <div class="detail-cover"><img src="${pageContext.request.contextPath}/assets/img/${cover}" alt="Portada genérica de ${libro.titulo}"></div>
        <div class="detail-copy">
            <span class="eyebrow"><c:out value="${libro.categoria.nombre}"/></span>
            <h1><c:out value="${libro.titulo}"/></h1>
            <p class="detail-author">de <strong><c:out value="${libro.autor.nombre}"/></strong></p>
            <div class="fact-grid">
                <div><span>Año</span><strong>${libro.anioPublicacion}</strong></div>
                <div><span>Precio</span><strong><fmt:formatNumber value="${libro.precio}" minFractionDigits="2" maxFractionDigits="2"/> €</strong></div>
                <div><span>Estado</span><strong>${libro.disponible == 'S' ? 'Disponible' : 'No disponible'}</strong></div>
                <div><span>Nacionalidad</span><strong><c:out value="${libro.autor.nacionalidad}"/></strong></div>
            </div>
            <div class="description-box">
                <h2>Sobre la categoría</h2>
                <p><c:out value="${libro.categoria.descripcion}"/></p>
            </div>
            <div class="detail-actions">
                <a class="button button-primary" href="${pageContext.request.contextPath}/libros?accion=editar&id=${libro.idLibro}">Editar libro</a>
                <form action="${pageContext.request.contextPath}/libros" method="post" onsubmit="return confirm('¿Eliminar este libro?');">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="id" value="${libro.idLibro}">
                    <button class="button button-danger" type="submit">Eliminar</button>
                </form>
            </div>
        </div>
    </section>
</main>
<footer>Los datos de esta ficha proceden de `LIBROS`, `AUTORES` y `CATEGORIAS`.</footer>
</body>
</html>

