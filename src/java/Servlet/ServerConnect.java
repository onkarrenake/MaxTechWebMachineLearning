/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ar
 */
public class ServerConnect {

    public static String Tablename = null;

    public static Connection getConnection() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/privacypreservationdatabaseforserver?autoReconnect=true&useSSL=false", "root", "root");

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

  
}
