/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import static Servlet.Connections.con;
import static Servlet.Utility_Methods.Tablename;
import static Servlet.Utility_Methods.getConnect;
import static Servlet.Utility_Methods.info;
import static Servlet.Utility_MethodsForServer.getConnect;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ar
 */
public class Utility_MethodsForServer {

    public static String Tablename;
    public static String companyname1;

    public static Connection getConnect() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/privacypreservationdatabaseforserver?autoReconnect=true&useSSL=false", "root", "root");

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return con;
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

    public Integer insertRequest(String email, String password, String tablename, String type) {

        int i = 0;
        ResultSet rs = null;
        try {

            Utility_Methods conn = new Utility_Methods();
            Connection con1 = conn.getConnect();
            PreparedStatement ps1 = (PreparedStatement) con1.prepareStatement("select * from registration where Email='" + email + "' and Password='" + password + "'");
            rs = ps1.executeQuery();
            String name = "";
            String companyname = "";
            String uid = "";

            while (rs.next()) {
                uid = rs.getString("id");
                name = rs.getString("name");
                companyname = rs.getString("company");

                companyname1 = companyname;
            }
            ServerConnect sc = new ServerConnect();
            Connection con2 = sc.getConnection();
            PreparedStatement ps = (PreparedStatement) con2.prepareStatement("INSERT INTO request(id,username,useremail,companyname,tablename,type,status) VALUES(?,?,?,?,?,?,?)");
            ps.setString(1, uid);
            ps.setString(2, name);
            ps.setString(3, email);

            ps.setString(4, companyname);

            ps.setString(5, tablename);
            ps.setString(6, type);
            ps.setString(7, "0");
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

    public ArrayList getTablename(Connection con) {
        ArrayList tname = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        String rname = "request";
        String status = "";
        int i = 0;

        try {

            tname = new ArrayList();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("Show tables");
            System.out.println("Tables in the current database: ");
            while (rs.next()) {
                if (rs.getString(1).equals(rname)) {
                } else {
                    tname.add(rs.getString(1));

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

    public static ArrayList getrequest(int status) {
        ArrayList<String> requst_list = new ArrayList();
        ServerConnect sc = new ServerConnect();
        Connection con2 = sc.getConnection();
        PreparedStatement ps;
        try {
            ps = con2.prepareStatement("select * from request where status='" + status + "'");
            ResultSet rs = ps.executeQuery();
            int i = 1;
            while (rs.next()) {
                String s = rs.getString(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4) + "," + rs.getString(5) + "," + rs.getString(6);
                requst_list.add(s);
                i++;
            }

        } catch (SQLException ex) {

        }
        return requst_list;

    }

    public static int tableCreate(String tablename) throws SQLException {
        int success = 0;
        System.out.println("hajds");
        Statement stmt = null;
        ServerConnect sc = new ServerConnect();
        Connection conn = sc.getConnection();
        stmt = conn.createStatement();
        try {
            String sql = "CREATE TABLE " + tablename + "(id INT NOT NULL AUTO_INCREMENT,PRIMARY KEY (id))";

            stmt.executeUpdate(sql);
            success++;
            System.err.println("ksdjfk");
        } catch (SQLException se) {
            System.out.println("error");
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return success;

    }

    public static void upadate_status(String email, String tname) {
        try {
            Connection con = getConnect();

            PreparedStatement ps = con.prepareStatement("update request set status='1' where useremail='" + email + "' and tablename='" + tname + "'");
            ps.executeUpdate();
            System.out.println("adj");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList getUserTables(String email, String password) {

        ResultSet rs = null;
        String status = "";
        int i = 0;
        String comname = "";

        ServerConnect sc = new ServerConnect();
        Connection con = sc.getConnection();

        Utility_Methods conn = new Utility_Methods();
        Connection con1 = conn.getConnect();
        ArrayList columdata = new ArrayList();
        ArrayList<String> data = new ArrayList<String>();
        try {

            // Connection con = getConnect();
            PreparedStatement ps1 = (PreparedStatement) con1.prepareStatement("select * from registration where Email='" + email + "' and Password='" + password + "'");
            rs = ps1.executeQuery();
            while (rs.next()) {

                comname = rs.getString("company");

            }

            PreparedStatement ps = (PreparedStatement) con.prepareStatement("select tablename from request where useremail='" + email + "' and companyname='" + comname + "'");
            rs = ps.executeQuery();

            while (rs.next()) {

                if (columdata.contains(rs.getString(1))) {
                } else {
                    columdata.add(rs.getString(1));
                }
            }
//            data.add(columdata);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return columdata;

    }

    public static int alterTable(DefaultTableModel dtm, String tbname, String email) throws SQLException {

        String tabletype = "";
        int success = 0;
        ServerConnect sc = new ServerConnect();
        Connection con = sc.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = null;

        for (int i = 0; i < dtm.getColumnCount(); i++) {
            String cn = dtm.getColumnName(i).toString();
            try {
                String query1 = "ALTER TABLE " + tbname + " ADD " + cn + " VARCHAR(100) DEFAULT 'Na'";
                stmt.executeUpdate(query1);

            } catch (Exception ex) {
                System.out.println("Column not found Exception");

            }
        }

        PreparedStatement ps = con.prepareStatement("select type from request where useremail='" + email + "' and tablename='" + tbname + "'");
        rs = ps.executeQuery();
        while (rs.next()) {

            tabletype = rs.getString(1);

        }

        char ch = '"';
        if (tabletype.equals("New")) {
            for (int j = 0; j < dtm.getRowCount(); j++) {
                String insertColumns1 = "";
                String vv = "";

                for (int k = 0; k < dtm.getColumnCount(); k++) {
                    insertColumns1 += "," + dtm.getColumnName(k).toString();
                    String value = dtm.getValueAt(j, k).toString();
                    vv += "," + ch + value + ch;

                }

                vv = vv.substring(1, vv.length());
                vv = vv.replaceAll("\"(.+)\"", "$1");

                insertColumns1 = insertColumns1.substring(1, insertColumns1.length());

                String insertSql = "INSERT INTO " + tbname + " (" + insertColumns1 + ") values(" + ch + vv + ch + ")";
                PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(insertSql);
                ps2.execute();

            }
        }

        try {
            char ch1 = '"';

            if (tabletype.equals("Existing")) {
                int idd = 1;
                for (int j = 0; j < dtm.getRowCount(); j++) {
                    String insertColumns = "";
                    String insertValues = "";
                    for (int k = 0; k < dtm.getColumnCount(); k++) {

                        insertColumns = dtm.getColumnName(k).toString();
                        String value = dtm.getValueAt(j, k).toString();

                        try {
                            PreparedStatement ps1 = con.prepareStatement("update " + tbname + " set " + insertColumns + "= '" + value + "' where " + insertColumns + "='Na' and id=" + idd + "");
                            ps1.executeUpdate();
                            success++;

                        } catch (Exception ex) {
                            System.out.println("error");
                        }
                    }
                    idd++;

                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            con.close();
        }
        return success;
    }

    public static void upadate_SubmitStatus(String email, String tname) throws SQLException {
        Connection con = getConnect();
        try {

            PreparedStatement ps = con.prepareStatement("update request set status='2' where useremail='" + email + "' and tablename='" + tname + "'");
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            con.close();
        }
    }

    public DefaultTableModel getclm(String tablename) {
        ResultSet rs = null;
        String status = "";
        int i = 0;
        DefaultTableModel dtm = new DefaultTableModel();
        Connection con = getConnect();
        ArrayList columname = new ArrayList();

        try {
            // Connection con = getConnect();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from " + tablename + "");
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i1 = 2; i1 <= rsmd.getColumnCount(); i1++) {
                columname.add(rsmd.getColumnLabel(i1));

            }
            for (int c = 0; c < columname.size(); c++) {
                dtm.addColumn(columname.get(c).toString());
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return dtm;
    }

    public ArrayList getTableData(DefaultTableModel dtm, String tablename) {
        ResultSet rs = null;
        String status = "";
        Connection con = getConnect();

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                String colum = dtm.getColumnName(j).toString();

                PreparedStatement ps = (PreparedStatement) con.prepareStatement("select " + colum + " from " + tablename + "");
                rs = ps.executeQuery();
                String row[] = new String[dtm.getColumnCount()];
                int r = 0;
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
