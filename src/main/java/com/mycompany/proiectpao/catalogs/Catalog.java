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
public class Catalog {
    private String name;
    private int teacherId;
    private int catalogId;

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

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Catalog(String name, int teacherId, int catalogId) {
        this.name = name;
        this.teacherId = teacherId;
        this.catalogId = catalogId;
    }
    
    
}
