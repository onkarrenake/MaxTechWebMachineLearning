/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "ShowDataServer", urlPatterns = {"/ShowDataServer"})
public class ShowDataServer extends HttpServlet {

    public static DefaultTableModel dtm = new DefaultTableModel();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uid = request.getParameter("id");
        String uname = request.getParameter("uname");
        String email = request.getParameter("email");
        String cname = request.getParameter("cname");
        String tname = request.getParameter("tname");
        String type = request.getParameter("type");
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        Utility_MethodsForServer met = new Utility_MethodsForServer();
        dtm = met.getclm(tname);
        data = met.getTableData(dtm, tname);
        for (int j = 0; j < data.get(0).size(); j++) {

            int t = 0;
            String[] row = new String[data.size()];
            int k = data.size();
            while (t < k) {
                row[t] = data.get(t).get(j);
                t++;

            }
            dtm.addRow(row);

        }
        CsvFileWriter.writeFile(dtm);
        C45_Classification cc = new C45_Classification();
        cc.classification();
        CART_Classification ca = new CART_Classification();
        ca.classification();
        response.sendRedirect("ShowDataServer.jsp");

    }

}
