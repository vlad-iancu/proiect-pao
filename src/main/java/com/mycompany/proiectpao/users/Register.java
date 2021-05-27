/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.users;

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
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      PrintWriter out = resp.getWriter();
      String name = req.getParameter("name");
      String password = req.getParameter("password");
      UserRepo repo = new UserRepo();
      if(name.isEmpty() || name.isBlank()) {
          out.println("Enter a name");
          return;
      }
      if(password.isEmpty() || password.isBlank()) {
          out.println("Enter a password");
          return;
      }
      Integer id = repo.insertUser(name, password);
      Cookie idCookie = new Cookie("id", id.toString());
      resp.addCookie(idCookie);
      resp.sendRedirect("/ProiectPAO/home");
      
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("register/register.html");
        view.forward(req, resp);
    }

    

}
