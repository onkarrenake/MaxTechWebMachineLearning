/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import static Servlet.Utility_Methods.getConnect;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ar
 */
public class Utility_Methods implements UtilityInterface {

    public static String Tablename = null;
    public static HashMap info;

    public static Connection getConnect() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloudproject?autoReconnect=true&useSSL=false", "root", "root");

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    @Override
    public Integer save_performance(String name, String email, String contact, String company, String password, String conformpassword) {
        int i = 0;
        if (password.equals(conformpassword)) {

            try {
                Connection con = getConnect();
                PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO registration(name,email,contact,company,password,conformpassword,status) VALUES('" + name + "','" + email + "','" + contact + "','" + company + "','" + password + "','" + conformpassword + "','Approved')");

                ps.execute();
                i++;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (i == 1) {
            return 1;

        }
        return 0;
    }

    @Override
    public String getStatus(String email, String password, String tablename) {
        ResultSet rs = null;
        String status = "";
        int i = 0;
        try {
            Connection con = getConnect();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from " + tablename + " where Email='" + email + "' and Password='" + password + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                status = rs.getString("status");
            }
            i++;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public String adminLogin(String email, String password) {
        ResultSet rs = null;
        String status = "";
        int i = 0;
        try {
            Connection con = getConnect();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from registration where Email='" + email + "' and Password='" + password + "'");
            rs = ps.executeQuery();
            while (rs.next()) {
                status = rs.getString("status");
            }
            i++;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public ArrayList savedata() throws FileNotFoundException, IOException {
        String path = "/home/ar/NetBeansProjects/MachineLearning_project/datasets form machine learning/datasets_4247_6570_Iris.csv";
        File f = new File(path);
        FileReader fout = new FileReader(f);
        BufferedReader br = new BufferedReader(fout);
        String line = "";
        int i = 1;
        ArrayList ar = new ArrayList();
        while ((line = br.readLine()) != null) {
            if (i == 1) {
                //  String[] columnvalue = line.split(",");
                // for (int j = 0; j < columnvalue.length; j++) {
                //   dtm.addColumn(columnvalue[j]);

            } else {
                ar.add(line);

            }
            i++;

        }
        return ar;
    }

    public Integer save_performanceFormysql(String name, String email, String contact, String password, String conformpassword) {

        int i = 0;
        try {
            Connection con = getConnect();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO registration(name,email,contact,password,conformpassword,status) VALUES(?,?,?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, email);

            ps.setString(3, contact);

            ps.setString(4, password);
            ps.setString(5, conformpassword);
            ps.setString(6, "Approved");
            ps.execute();
            i++;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (i == 1) {
            return 1;

        }
        return 0;
    }

    public Integer savemysql(String SepalLengthCm, String SepalWidthCm, String PetalLengthCm, String PetalWidthCm, String Species, Connection con) {

        int i = 0;
        try {
            //Connection con = getConnect();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("INSERT INTO iris(SepalLengthCm,SepalWidthCm,PetalLengthCm,PetalWidthCm,Species) VALUES(?,?,?,?,?)");

            ps.setString(1, SepalLengthCm);
            ps.setString(2, SepalWidthCm);

            ps.setString(3, PetalLengthCm);

            ps.setString(4, PetalWidthCm);
            ps.setString(5, Species);

            ps.execute();
            i++;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (i == 1) {
            return 1;

        }
        return 0;
    }

    public ArrayList getcolumnname(String tablename, Connection con) {

        ResultSet rs = null;
        String status = "";
        int i = 0;
        info = new HashMap();
        info.put("tablename", tablename);
        Tablename = new String();
        Tablename = tablename;
        ArrayList columname = new ArrayList();
        try {
            // Connection con = getConnect();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from " + tablename + "");
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i1 = 1; i1 <= rsmd.getColumnCount(); i1++) {
                columname.add(rsmd.getColumnLabel(i1));

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return columname;

    }

    public ArrayList getTablename(Connection con, String name) {
        ArrayList tname = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        String status = "";
        int i = 0;

        try {
            if (name == "postgres") {
                tname = new ArrayList();
                DatabaseMetaData dbmd = con.getMetaData();
                String[] types = {"TABLE"};
                rs = dbmd.getTables(null, null, "%", types);
                while (rs.next()) {
                    System.out.println(rs.getString("TABLE_NAME"));
                    tname.add(rs.getString("TABLE_NAME"));
                }
            } else if (name == "mysql") {
                tname = new ArrayList();
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery("Show tables");
                System.out.println("Tables in the current database: ");
                while (rs.next()) {
                    tname.add(rs.getString(1));
                    System.out.print(rs.getString(1));
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return tname;

    }

    public ArrayList getdata(String[] columname, Connection con) {

        ResultSet rs = null;
        String status = "";
        int i = 0;

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            // Connection con = getConnect();
            for (int j = 0; j < columname.length; j++) {
                String colum = columname[j];

                PreparedStatement ps = (PreparedStatement) con.prepareStatement("select " + colum + " from " + Tablename + "");
                rs = ps.executeQuery();
                ArrayList columdata = new ArrayList();
                while (rs.next()) {
                    columdata.add(rs.getString(colum));

                }
                data.add(columdata);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return data;

    }
}
