/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ar
 */
@WebServlet(name = "AdminLogin", urlPatterns = {"/AdminLogin"})
public class AdminLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UtilityInterface methods = new Utility_Methods();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String status = methods.getStatus(email, password, "adminlogin");

        // cc.getOracleConnection();
        //cc.getPostgreConnection();
        try {
            if (status.equals("Approved")) {
                HttpSession session = request.getSession();

                session.setAttribute("email", email);
                session.setAttribute("name", password);

                response.sendRedirect("AdminHomePage.jsp");
            } else {
                response.sendRedirect("adminsignin.jsp");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
