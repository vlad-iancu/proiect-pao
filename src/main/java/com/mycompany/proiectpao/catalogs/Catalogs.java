/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.catalogs;

import com.mycompany.proiectpao.users.DbUser;
import com.mycompany.proiectpao.users.UserRepo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iancu
 */
public class Catalogs extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        if(cookies.length == 0)
        {
            RequestDispatcher view = request.getRequestDispatcher("/");
            view.forward(request, response);
            return;
        }
        UserRepo repo = new UserRepo();
        Cookie authCookie = cookies[0];
        Integer id = Integer.parseInt(authCookie.getValue());
        DbUser user = repo.getUser(id);
        CatalogRepo catalogRepo = new CatalogRepo();
        List<Catalog> catalogs = catalogRepo.getTeacherCatalogs(id);
        String catalogsHtml = "";
        for(Catalog c : catalogs) {
            catalogsHtml += "<a href=\"/ProiectPAO/teacher_catalog?id="+c.getCatalogId()+"\">"+ c.getName()+"</a>";
        }
        out.println(catalogsHtml);
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
