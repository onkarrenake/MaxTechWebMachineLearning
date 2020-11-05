package Servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static Servlet.Connections.con;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ar
 */
@WebServlet(name = "ColumnSelect", urlPatterns = {"/ColumnSelect"})
public class DataInsert extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList data = new ArrayList();
        Connect c = new Connect();
        
        //Connection con =c.getConnection();
//        Utility_MethodsForServer server= new Utility_MethodsForServer();
//        server.getConnect();
//        Utility_Methods insert = new Utility_Methods();
//        data = insert.savedata();
//        for (int i = 0; i < data.size(); i++) {
//            String d = data.get(i).toString();
//            String data1[] = d.split(",");
//            String a1 = data1[0];
//            String a2 = data1[1];
//            String a3 = data1[2];
//            String a4 = data1[3];
//            String a5 = data1[4];
//          
//           
//             
//            
//            insert.savemysql(a1, a2, a3, a4,a5,con);
      //  }
        

    }

}
