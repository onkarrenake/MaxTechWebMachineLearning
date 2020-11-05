/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ar
 */
@WebServlet(name = "Connections", urlPatterns = {"/Connections"})
public class Connections extends HttpServlet {

    public static Connection con = null;
    public static String dname = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connect c = new Connect();
        String port = request.getParameter("port");
        String databasename = request.getParameter("databasename");
        String databaseid = request.getParameter("databaseid");
        String databasepassword = request.getParameter("databasepassword");
        if (request.getParameter("mysqldatabase") != null) {
            dname = "mysql";
           
            con = c.getConnection(port, databasename, databaseid, databasepassword);
            if (con != null) {
                request.setAttribute("Success", "Y");
                RequestDispatcher rd = request.getRequestDispatcher("DatabaseSelection.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("Success", "N");
                RequestDispatcher rd = request.getRequestDispatcher("DatabaseSelection.jsp");
                rd.forward(request, response);
            }
        } else if (request.getParameter("oracle") != null) {
            dname = "oracle";
            con = c.getOracleConnection(port, databasename, databaseid, databasepassword);
            
            if (con != null) {
                request.setAttribute("Success", "Y");
                RequestDispatcher rd = request.getRequestDispatcher("DatabaseSelection.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("Success", "N");
                RequestDispatcher rd = request.getRequestDispatcher("DatabaseSelection.jsp");
                rd.forward(request, response);
            }

        } else if (request.getParameter("postgresdatabase") != null) {
            dname = "postgres";
            
            con = c.getPostgreConnection(port, databasename, databaseid, databasepassword);
            if (con != null) {
                request.setAttribute("Success", "Y");
                RequestDispatcher rd = request.getRequestDispatcher("DatabaseSelection.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("Success", "N");
                RequestDispatcher rd = request.getRequestDispatcher("DatabaseSelection.jsp");
                rd.forward(request, response);
            }
        }
    }

}
