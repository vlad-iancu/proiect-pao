/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author iancu
 */
public class Database implements AutoCloseable {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/proiect_pao";
    
    private static final String USER = "proiect_pao_user";
    private static final String PASSWORD = "mysql";
    
    private Connection connection;
    
    public Database() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        
    }
    
    @Override
    public void close() throws SQLException {
        connection.close();
    }
    
    public Connection getConnection() { return connection; }
   
}
