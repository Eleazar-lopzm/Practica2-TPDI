<%-- 
    Document   : libreria
    Created on : Sep 23, 2025, 7:57:53 PM
    Author     : eleazar
--%>
<%@page import="java.util.List"%>
<%@page import="Model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Libreria</title>
    </head>
    <body>
        <h1>Librería Conejo</h1>
        <h2>Bienvenido a la Libreria Conejo</h2>
        <p>Aquí encontrarás los libros que quieras y podrás añadir libros nuevos</p> 
       
        <form action="SearchServlet" method="get">
            <label>Busqueda por autor o titulo</label>
            <input type="text" name="query"/>
            <input type="submit" value="Buscar"/>
        </form>
        
        <h2>Añadir Libro</h2>
        <form action="BookServlet" method="post">
            <label>Título:</label>
            <input type="text" name="title" required/>
            <br/>
            <label>Autor:</label>
            <input type="text" name="author" required/>
            <br/>
            <label>Precio:</label>
            <input type="number" name="price" step="0.01" required/>
            <br/>
            <input type="submit" value="Añadir Libro" />
        </form>
        <%
            String message = (String) session.getAttribute("message");
            
            // Si el mensaje existe, mostrarlo y luego eliminarlo
            if (message != null) {
        %>
            <p style="color: green; font-weight: bold;"><%= message %></p>
        <%
                session.removeAttribute("message");
            }
        %>
        
        <h2>Libros Disponibles</h2>
        <form action="BookServlet" method="get">
            <input type="submit" value="Mostrar todos los libros"/>
        </form>
        <table border="1">
            <tr>
                <th>Título</th>
                <th>Autor</th>
                <th>Precio</th>
            </tr>
            <%
                // Obtener la lista de libros del request.
                // Esta lista puede ser de todos los libros o de los resultados de búsqueda.
                List<Book> books = (List<Book>) request.getAttribute("books");
                
                // Verificar si la lista no está vacía o es nula.
                if (books != null && !books.isEmpty()) {
                    for (Book book : books) {
            %>
            <tr>
                <td><%= book.getTitle()%></td>
                <td><%= book.getAuthor()%></td>
                <td><%= String.format("%.2f", book.getPrice())%></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="3">No hay libros para mostrar.</td>
            </tr>
            <%
                }
            %>
        </table>
        
    </body>
</html>
