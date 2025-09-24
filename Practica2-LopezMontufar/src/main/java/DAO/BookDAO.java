/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author eleazar
 */
public class BookDAO {
    private final String jdbcURL = "jdbc:mysql:localhost:3306/bookdb";
    private final String jdbcUsername = "usr";
    private final String jdbcPassword = "pass";
    
    private static final String SELECT_ALL = "SELECT * FORM books";
    private static final String INSERT_BOOK = " INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
    
    // Conexión
    protected Connection getCOnnection() throws SQLException {
        try{
            Class.forName("com.mysql.ck.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch(ClassNotFoundExcepction e){
            throw new SQLException(e);
        }
    }
}
