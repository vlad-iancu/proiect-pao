/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.catalogs;

import com.mycompany.proiectpao.data.Database;
import com.mycompany.proiectpao.users.DbUser;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iancu
 */
public class CatalogRepo {
    private static String GET_TEACHER_CATALOGS = "SELECT * FROM catalog WHERE teacherId = ?";
    private static String INSERT_CATALOG = "INSERT INTO catalog(name, teacherId) VALUES(?,?)";
    private static String GET_CATALOG_STUDENTS = 
            "SELECT * FROM enrollment e JOIN user u ON e.studentId = u.userId WHERE catalogId = ?";
    public List<Catalog> getTeacherCatalogs(int teacherId) {
        try(Database db = new Database()) {
               ArrayList<Catalog> result = new ArrayList<>();
               Connection connection = db.getConnection();
               PreparedStatement stmt = connection.prepareStatement(GET_TEACHER_CATALOGS);
               stmt.setInt(1, teacherId);
               ResultSet rs = stmt.executeQuery();
               while(rs.next()) {
                   result.add(
                           new Catalog(rs.getString("name"),rs.getInt("teacherId"), rs.getInt("catalogId"))
                   );
               }
               return result;
        }
        catch(SQLException e) {
            return null;
        }
    }
    public int insertCatalog(String name, int teacherId) {
        try(Database db = new Database()) {
            Connection connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT_CATALOG, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            return keys.getInt(1);
        }
        catch(SQLException e) {
            
        }
        return -1;
    }
    
    public List<Enrollment> getCatalogStudents(int catalogId) {
        try(Database db = new Database()) {
            Connection connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(GET_CATALOG_STUDENTS);
            stmt.setInt(1, catalogId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Enrollment> result = new ArrayList<>();
            while(rs.next()) {
                result.add(
                        new Enrollment(rs.getString("name"), rs.getInt("userId"), rs.getInt("grade"), catalogId)
                );
            }
            stmt.close();
            rs.close();
            return result;
        }catch(SQLException e) {
            return null;
        }
    }
    
    public boolean enrollStudent(int teacherId, int catalogId, int grade, String studentName) {
        try(Database db = new Database()) {
            Connection connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE userId = ? AND name = ?");
            stmt.setInt(1, teacherId);
            stmt.setString(2, studentName);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()) return false;
            Integer studentId = rs.getInt("userId");
            stmt.close();
            rs.close();
            stmt = connection.prepareStatement("INSERT INTO enrollment(catalogId, studentId, grade) VALUES(?,?,?)");
            stmt.setInt(1, catalogId);
            stmt.setInt(2, studentId);
            stmt.setInt(3, grade);
            stmt.executeUpdate();
            stmt.close();
            return true;
        }
        catch(SQLException e) {
            return false;
        }
    }
    
    public void deleteEnrollment(int studentId, int catalogId, int teacherId) {
        try(Database db = new Database()) {
            Connection connection = db.getConnection();
            PreparedStatement teacherExists = connection.prepareStatement("SELECT * FROM catalog WHERE catalogId = ? AND teacherId = ?");
            teacherExists.setInt(1, catalogId);
            teacherExists.setInt(2, teacherId);
            ResultSet rs = teacherExists.executeQuery();
            if(!rs.next()) return;
            rs.close();
            teacherExists.close();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM enrollment WHERE studentId = ? AND catalogId = ?");
            stmt.setInt(1, studentId);
            stmt.setInt(2, catalogId);
            stmt.executeUpdate();
            stmt.close();
        }catch(SQLException e) {
            
        }
    }
   
}
