/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ABC
 */
public class Connect {

   

    public static Connection getConnection(String port, String database, String userid, String password) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
     
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + database + "?autoReconnect=true&useSSL=false", "" + userid + "", "" + password + "");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public static Connection getOracleConnection(String port, String database, String userid, String password) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
       
        try {

//step1 load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object  
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "root");

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;

    }

    public static Connection getPostgreConnection(String port, String database, String userid, String password) {
        Connection c = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:" + port + "/" + database + "", "" + userid + "", "" + password + "");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

   
}
