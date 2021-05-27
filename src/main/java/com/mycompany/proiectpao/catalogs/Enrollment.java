/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.catalogs;

/**
 *
 * @author iancu
 */
public class Enrollment {
    private String name;
    private int userId;
    private int grade;
    private int catalogId;
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Enrollment(String name, int userId, int grade, int catalogId) {
        this.name = name;
        this.userId = userId;
        this.grade = grade;
        this.catalogId = catalogId;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
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

}
