<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Biblioteca Aurora · Catálogo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/libros">
        <span class="brand-mark">BA</span>
        <span>Biblioteca Aurora</span>
    </a>
    <a class="button button-primary" href="${pageContext.request.contextPath}/libros?accion=nuevo">Añadir libro</a>
</header>

<main>
    <section class="hero">
        <div class="hero-content">
            <span class="eyebrow">Catálogo conectado a Oracle</span>
            <h1>Historias, conocimiento y lecturas para descubrir</h1>
            <p>El servlet ha consultado el DAO y ha enviado esta colección a una vista preparada.</p>
            <div class="flow-chip">Navegador → Servlet → DAO → Oracle → JSP</div>
        </div>
        <img src="${pageContext.request.contextPath}/assets/img/library-hero.svg" alt="Ilustración de una biblioteca moderna">
    </section>

    <section class="section-heading">
        <div>
            <span class="eyebrow">Selección disponible</span>
            <h2>Todos los libros</h2>
        </div>
        <span class="counter">${libros.size()} títulos</span>
    </section>

    <c:choose>
        <c:when test="${empty libros}">
            <section class="empty-state">
                <img src="${pageContext.request.contextPath}/assets/img/empty-library.svg" alt="Estantería vacía">
                <h2>Todavía no hay libros</h2>
                <p>Cuando Oracle devuelva filas, aparecerán aquí como tarjetas.</p>
            </section>
        </c:when>
        <c:otherwise>
            <section class="book-grid">
                <c:forEach items="${libros}" var="libro">
                    <article class="book-card">
                        <c:choose>
                            <c:when test="${libro.categoria.idCategoria % 3 == 1}"><c:set var="cover" value="cover-blue.svg"/></c:when>
                            <c:when test="${libro.categoria.idCategoria % 3 == 2}"><c:set var="cover" value="cover-green.svg"/></c:when>
                            <c:otherwise><c:set var="cover" value="cover-orange.svg"/></c:otherwise>
                        </c:choose>
                        <a class="cover" href="${pageContext.request.contextPath}/libros?accion=detalle&id=${libro.idLibro}">
                            <img src="${pageContext.request.contextPath}/assets/img/${cover}" alt="Portada genérica de ${libro.titulo}">
                        </a>
                        <div class="book-body">
                            <div class="card-topline">
                                <span class="category"><c:out value="${libro.categoria.nombre}"/></span>
                                <span class="availability ${libro.disponible == 'S' ? 'is-available' : 'is-unavailable'}">
                                    ${libro.disponible == 'S' ? 'Disponible' : 'No disponible'}
                                </span>
                            </div>
                            <h3><c:out value="${libro.titulo}"/></h3>
                            <p class="author"><c:out value="${libro.autor.nombre}"/></p>
                            <div class="book-meta">
                                <span>${libro.anioPublicacion}</span>
                                <strong><fmt:formatNumber value="${libro.precio}" minFractionDigits="2" maxFractionDigits="2"/> €</strong>
                            </div>
                            <div class="card-actions">
                                <a class="text-link" href="${pageContext.request.contextPath}/libros?accion=detalle&id=${libro.idLibro}">Ver detalle</a>
                                <a class="icon-link" href="${pageContext.request.contextPath}/libros?accion=editar&id=${libro.idLibro}" aria-label="Editar ${libro.titulo}">Editar</a>
                            </div>
                        </div>
                    </article>
                </c:forEach>
            </section>
        </c:otherwise>
    </c:choose>
</main>

<footer>UT3 · Integración de Servlets con los DAO de UT2</footer>
</body>
</html>

