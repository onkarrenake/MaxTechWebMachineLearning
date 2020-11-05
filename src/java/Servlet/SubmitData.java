/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import static Servlet.ServerRequest.information;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ar
 */
@WebServlet(name = "SubmitData", urlPatterns = {"/SubmitData"})
public class SubmitData extends HttpServlet {

    DefaultTableModel dtm;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DefaultTableModel dtm = new DefaultTableModel();
        Utility_MethodsForServer methods = new Utility_MethodsForServer();
        Encryptdata en = new Encryptdata();
        dtm = Encryptdata.encryptdtm;
        String email = information.get(0).toString();
//        String password = information.get(1).toString();
        String tname = request.getParameter("radiobutton");
        
        if (tname != null) {
            try {
             int success=   methods.alterTable(dtm, tname,email);
               if(success>0)
               {
               methods.upadate_SubmitStatus(email, tname);
               
               }
               response.sendRedirect("Mappeddata.jsp");
                 
            } catch (SQLException ex) {
                Logger.getLogger(SubmitData.class.getName()).log(Level.SEVERE, null, ex);
            }
           

        }
      

    }

}
