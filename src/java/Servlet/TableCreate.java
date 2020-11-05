/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ar
 */
@WebServlet(name = "TableCreate", urlPatterns = {"/TableCreate"})
public class TableCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String uname = request.getParameter("uname");
        String email = request.getParameter("email");
        String cname = request.getParameter("cname");
        String tname = request.getParameter("tname");
        String type = request.getParameter("type");

        Utility_MethodsForServer met = new Utility_MethodsForServer();
        try {
            if (type.equals("New")) {

                int success = met.tableCreate(tname);
                if (success > 0) {
                    met.upadate_status(email, tname);
                    response.sendRedirect("AdminRequest.jsp");

                }
            }
            if (type.equals("Existing")) {
                System.out.println("jkfg");
                met.upadate_status(email, tname);
                response.sendRedirect("AdminRequest.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableCreate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
