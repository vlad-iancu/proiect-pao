/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.catalogs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iancu
 */
@WebServlet(name = "TeacherCatalog", urlPatterns = {"/teacher_catalog"})
public class TeacherCatalog extends HttpServlet {

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
        CatalogRepo repo = new CatalogRepo();
        Integer id = Integer.parseInt(request.getParameter("id"));
        List<Enrollment> enrollments = repo.getCatalogStudents(id);
        out.println("<h1>Studenti</h1>");
        out.println("<h3 style=\"display: inline-block\">Nume</h3><h3 style=\"display: inline-block;margin-left: 10px\">Nota</h3>");
        for(Enrollment e : enrollments) {
            out.print("<div><span style=\"display: inline-block\">"+e.getName() + "</span><span style=\"display: inline-block;margin-left: 10px\">" + e.getGrade() + "</span></div>");
            out.println("<a style=\"display: inline-block;\" href=\"/ProiectPAO/delete_enrollment?catalogId="+e.getCatalogId() + "&studentId="+e.getUserId()+"\">sterge</a>");
            
        }
        out.println("<a style=\"display:block;\" href=\"/ProiectPAO/enroll_student?catalogId=" + id + "\" >Adauga Student</a>");
    }
    
    

    

}
