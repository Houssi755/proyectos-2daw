<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Biblioteca Aurora · ${modo == 'crear' ? 'Nuevo libro' : 'Editar libro'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/libros"><span class="brand-mark">BA</span><span>Biblioteca Aurora</span></a>
    <a class="button button-ghost" href="${pageContext.request.contextPath}/libros">Cancelar</a>
</header>

<main class="form-shell">
    <section class="form-intro">
        <span class="eyebrow">${modo == 'crear' ? 'Alta' : 'Edición'} mediante POST</span>
        <h1>${modo == 'crear' ? 'Añadir un libro' : 'Editar el libro'}</h1>
        <p>El formulario enviará sus parámetros al mismo servlet. `doPost()` elegirá la operación y llamará al DAO.</p>
    </section>

    <form class="book-form" action="${pageContext.request.contextPath}/libros" method="post">
        <input type="hidden" name="accion" value="${modo}">
        <c:if test="${modo == 'actualizar'}">
            <input type="hidden" name="id" value="${libro.idLibro}">
        </c:if>

        <div class="field field-wide">
            <label for="titulo">Título</label>
            <input id="titulo" name="titulo" type="text" maxlength="200" required value="<c:out value='${libro.titulo}'/>">
        </div>

        <div class="field">
            <label for="precio">Precio</label>
            <input id="precio" name="precio" type="number" min="0" step="0.01" required value="${libro.precio}">
        </div>

        <div class="field">
            <label for="anioPublicacion">Año de publicación</label>
            <input id="anioPublicacion" name="anioPublicacion" type="number" min="1000" max="2100" required value="${libro.anioPublicacion}">
        </div>

        <div class="field">
            <label for="disponible">Disponibilidad</label>
            <select id="disponible" name="disponible" required>
                <option value="S" ${libro.disponible == 'S' ? 'selected' : ''}>Disponible</option>
                <option value="N" ${libro.disponible == 'N' ? 'selected' : ''}>No disponible</option>
            </select>
        </div>

        <div class="field">
            <label for="idAutor">Autor</label>
            <select id="idAutor" name="idAutor" required>
                <option value="">Selecciona un autor</option>
                <c:forEach items="${autores}" var="autor">
                    <option value="${autor.idAutor}" ${libro.autor.idAutor == autor.idAutor ? 'selected' : ''}>
                        <c:out value="${autor.nombre}"/>
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="field field-wide">
            <label for="idCategoria">Categoría</label>
            <select id="idCategoria" name="idCategoria" required>
                <option value="">Selecciona una categoría</option>
                <c:forEach items="${categorias}" var="categoria">
                    <option value="${categoria.idCategoria}" ${libro.categoria.idCategoria == categoria.idCategoria ? 'selected' : ''}>
                        <c:out value="${categoria.nombre}"/>
                    </option>
                </c:forEach>
            </select>
        </div>

        <div class="form-actions field-wide">
            <a class="button button-ghost" href="${pageContext.request.contextPath}/libros">Cancelar</a>
            <button class="button button-primary" type="submit">${modo == 'crear' ? 'Crear libro' : 'Guardar cambios'}</button>
        </div>
    </form>
</main>
<footer>Los nombres de los campos coinciden con los parámetros leídos por `LibroServlet`.</footer>
</body>
</html>

