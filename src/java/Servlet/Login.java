/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import static Servlet.Connections.con;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   if(con != null)
   {
       try {
           con.close();
           System.out.println("sa");
       } catch (SQLException ex) {
           Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       }} else {
   }
        UtilityInterface methods = new Utility_Methods();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String status = methods.getStatus(email, password, "registration");

        // cc.getOracleConnection();
        //cc.getPostgreConnection();
        try {
            if (status.equals("Approved")) {
                HttpSession session = request.getSession();

                session.setAttribute("email", email);
                session.setAttribute("password", password);
                ServerRequest.info(email,password);
                
                response.sendRedirect("UserHomePage.jsp");
            } else {
                response.sendRedirect("signin.jsp");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
