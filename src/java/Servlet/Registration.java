/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ar
 */
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String company = request.getParameter("companyname");
        String password = request.getParameter("password");
        String conformpassword = request.getParameter("cp");

        UtilityInterface method = new Utility_Methods();
        int r = method.save_performance(name, email, contact, company, password, conformpassword);
        if (r == 1) {
            response.sendRedirect("signin.jsp");
        } else {
            response.sendRedirect("Signup.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
