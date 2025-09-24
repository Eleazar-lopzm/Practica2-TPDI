/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.Book;

/**
 *
 * @author eleazar
 */
public class BookDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/bookdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "pass";
    
    private static final String SELECT_ALL = "SELECT * FROM books";
    private static final String INSERT_BOOK = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
    private static final String SEARCH_BOOK = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
    
    // Conexión
    protected Connection getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch(ClassNotFoundException e){
            throw new SQLException(e);
        }
    }
    
    
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                books.add(new Book(title, author, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void addBook(Book book) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_BOOK)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Método para buscar libros 
    public List<Book> searchBooks(String query) throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(SEARCH_BOOK)) {
            
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                
                books.add(new Book(title, author, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
}
