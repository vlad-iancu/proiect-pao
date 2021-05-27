/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.users;

/**
 *
 * @author iancu
 */
public class DbUser {
    private String name;
    private int userId;
    private String password;
    private boolean teacher;

    public boolean isTeacher() {
        return teacher;
    }

    public void setTeacher(boolean isTeacher) {
        this.teacher = isTeacher;
    }
    
    public DbUser(String name, int userId, boolean isTeacher) {
        this.name = name;
        this.userId = userId;
        this.teacher = isTeacher;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
