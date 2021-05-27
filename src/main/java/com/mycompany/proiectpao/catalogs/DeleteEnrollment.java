/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proiectpao.catalogs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iancu
 */
@WebServlet(name = "DeleteEnrollment", urlPatterns = {"/delete_enrollment"})
public class DeleteEnrollment extends HttpServlet {
   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer catalogId = Integer.parseInt(request.getParameter("catalogId"));
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        Integer teacherId = Integer.parseInt(request.getCookies()[0].getValue());
        CatalogRepo repo = new CatalogRepo();
        repo.deleteEnrollment(studentId, catalogId, teacherId);
        response.sendRedirect("/ProiectPAO/teacher_catalog?id="+catalogId);
    }

}
