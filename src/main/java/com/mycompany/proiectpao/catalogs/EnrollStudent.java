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
@WebServlet(name = "EnrollStudent", urlPatterns = {"/enroll_student"})
public class EnrollStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String catalogId = request.getParameter("catalogId");
        out.println("<html>");
        out.println("<body>");
        out.println("<form action=\"/ProiectPAO/enroll_student?catalogId=" + catalogId + "\" method=\"POST\">");
        out.println("<input type=\"text\" name=\"name\" placeholder=\"name\" />");
        out.println("<input type=\"number\" name=\"grade\" placeholder=\"grade\" />");
        out.println("<input type=\"submit\"  />");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

     //   out.print("</span>");
     // out.println("<p>");
     // out.println("Text");
     // out.println("</p>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer catalogId = Integer.parseInt(request.getParameter("catalogId"));
        Integer teacherId = Integer.parseInt(request.getCookies()[0].getValue());
        String studentName = request.getParameter("name");
        int grade = Integer.parseInt(request.getParameter("grade"));
        CatalogRepo repo = new CatalogRepo();
        repo.enrollStudent(teacherId, catalogId, grade, studentName);
        response.sendRedirect("/ProiectPAO/teacher_catalog?id="+catalogId);
    }

    

}
