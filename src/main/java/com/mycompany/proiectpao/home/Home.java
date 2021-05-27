/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.home;

import com.mycompany.proiectpao.users.DbUser;
import com.mycompany.proiectpao.users.UserRepo;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Home extends HttpServlet {

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
        //response.getWriter().println(id);
        DbUser user = repo.getUser(id);
        RequestDispatcher view;
        if(user == null) view = request.getRequestDispatcher("/");
            else if(!user.isTeacher())
                    view = request.getRequestDispatcher("home/homeUser.html");
                   else view = request.getRequestDispatcher("home/homeTeacher.html");
        view.forward(request, response);
        
    }

}
