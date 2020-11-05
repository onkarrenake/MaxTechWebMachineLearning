/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import static Servlet.AESencrp.encrypt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "Encryptdata", urlPatterns = {"/Encryptdata"})
public class Encryptdata extends HttpServlet {

    private static  byte[] keyValue = new byte[]{'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
    private static DefaultTableModel dtm;
    public static DefaultTableModel encryptdtm;
    public static ArrayList colum;
    private static String classlable;

    public void getcolum(String[] c) {
        colum = new ArrayList();
        for (int i = 0; i < c.length; i++) {
            colum.add(c[i]);
        }
    }

    public void gettabledata(ArrayList<ArrayList<String>> data, String[] column) {
        dtm = new DefaultTableModel();

        for (int i = 0; i < column.length; i++) {
            dtm.addColumn(column[i]);
        }

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

    }

    public DefaultTableModel encryptdata(String lable,String tablename) {
        encryptdtm = new DefaultTableModel();
        classlable = new String();
        classlable=lable;
        for (int k = 0; k < dtm.getColumnCount(); k++) {
            encryptdtm.addColumn(dtm.getColumnName(k).toString());
        }
  
        for (int i = 0; i < dtm.getRowCount(); i++) {
            String[] encryptrow = new String[dtm.getColumnCount()];
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                String lable1 = dtm.getColumnName(j).toString();
                if (!lable.equals(lable1)) {

                    try {
                        String temp = dtm.getValueAt(i, j).toString();
                        String s = encrypt(keyValue, dtm.getValueAt(i, j).toString());
                        encryptrow[j] = s;
                    } catch (Exception ex) {
                        Logger.getLogger(Encryptdata.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    String classlable = dtm.getValueAt(i, j).toString();
                    encryptrow[j] = classlable;

                }
            }
            encryptdtm.addRow(encryptrow);
        }
        return encryptdtm;
    }

    public DefaultTableModel mappeddata(String lable ,String tablename) {

        for (int i = 0; i < encryptdtm.getColumnCount(); i++) {
            double cnt = 1;
            ArrayList<Double> lst = new ArrayList<Double>();
            HashMap<String, Double> map = new HashMap<String, Double>();
            String lablenmae = encryptdtm.getColumnName(i).toString();
            if (!classlable.equals(lablenmae)) {
                for (int j = 0; j < encryptdtm.getRowCount(); j++) {
                    String data = String.valueOf(encryptdtm.getValueAt(j, i));
                    try {
                        double d = Double.parseDouble(data);
                        lst.add(d);
                    } catch (NumberFormatException e) {
                        if (!map.containsKey(data)) {
                            encryptdtm.setValueAt(cnt, j, i);
                            map.put(data, cnt);
                            lst.add(cnt);
                            cnt++;
                        } else {
                            encryptdtm.setValueAt(map.get(data), j, i);
                            lst.add(map.get(data));
                        }

                    }
                }
            }
        }
        return encryptdtm;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        encryptdata(lable);
    }

}
