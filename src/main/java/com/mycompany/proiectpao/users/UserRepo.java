/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.users;

import com.mycompany.proiectpao.data.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iancu
 */
public class UserRepo {
    private static final String GET_USER = "SELECT * FROM user WHERE userId = ?";
    private static final String INSERT_USER = "INSERT INTO user(name, password) VALUES(?,?)";
    private static final String GET_ALL_STUDENTS = "SELECT * FROM user where teacher = False";
    private static final String GET_USER_BY_NAME_AND_PASSWORD = "SELECT * FROM user WHERE name = ? AND password = ?";
    public DbUser getUser(int id) {
        try(Database db = new Database()) {
            Connection connection = db.getConnection();
            PreparedStatement getDbUser = connection.prepareStatement(GET_USER);
            getDbUser.setInt(1, id);
            ResultSet result = getDbUser.executeQuery();
            if(result.next())
            return new DbUser(result.getString("name"), result.getInt("userId"), result.getBoolean("teacher"));
        }
        catch(SQLException e) {
            return null;
        }
        return null;
    }
    public DbUser getUserByNameAndPassword(String name, String password) {
        try(Database db = new Database()) {
            Connection connection = db.getConnection();
            PreparedStatement getDbUser = connection.prepareStatement(GET_USER_BY_NAME_AND_PASSWORD);
            getDbUser.setString(1, name);
            getDbUser.setString(2, password);
            ResultSet result = getDbUser.executeQuery();
            if(result.next())
            return new DbUser(result.getString("name"), result.getInt("userId"), result.getBoolean("teacher"));
        }
        catch(SQLException e) {
            return null;
        }
        return null;
    }
    public int insertUser(String name, String password) {
          try(Database db = new Database()) {
            Connection connection = db.getConnection();
            PreparedStatement getDbUser = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            getDbUser.setString(1, name);
            getDbUser.setString(2, password);
            getDbUser.executeUpdate();
            ResultSet result = getDbUser.getGeneratedKeys();
            if(result.next()) {
                return result.getInt(1);
            }
            else {
                return 0;
            }
        }
        catch(SQLException e) {
            return 0;
        }
    }
    public List<DbUser> getAllStudents() {
        try(Database db = new Database()) {
            Connection connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(GET_ALL_STUDENTS);
            ResultSet results = stmt.executeQuery();
            ArrayList<DbUser> users = new ArrayList<DbUser>();
            while(results.next()) {
                users.add(
                        new DbUser(results.getString("name"), results.getInt("userId"), results.getBoolean("teacher"))
                );
            }
            return users;
        }
        catch(SQLException e) {
            return null;
        }
    }
}
