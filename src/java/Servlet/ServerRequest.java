/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
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
@WebServlet(name = "ServerRequest", urlPatterns = {"/ServerRequest"})
public class ServerRequest extends HttpServlet {

   public static ArrayList information;

    static void info(String e, String p) {
        information = new ArrayList();
        information.add(e);
        information.add(p);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connections cc = new Connections();
        ServerConnect c = new ServerConnect();

        Connection scon = c.getConnection();
        String email = information.get(0).toString();
        String password = information.get(1).toString();
        String tname = request.getParameter("Tablename");
        String tname1 = request.getParameter("radiobutton");
        Utility_MethodsForServer sm = new Utility_MethodsForServer();
        if (tname1!=null) {
            sm.insertRequest(email, password, tname1, "Existing");
            response.sendRedirect("Request.jsp");
        }
        if (tname!=null){

            sm.insertRequest(email, password, tname, "New");
            response.sendRedirect("Request.jsp");
        }
    }

}
