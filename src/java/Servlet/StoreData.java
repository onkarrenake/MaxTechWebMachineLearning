/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

/**
 *
 * @author ar
 */
import java.io.FileWriter;
import java.util.ArrayList;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class StoreData {

    public static void csvwriter(DefaultTableModel dtm, String tablename) throws IOException {
        //Instantiating the CSVWriter class
        System.out.println("ldc");
        CSVWriter writer = new CSVWriter(new FileWriter("output.csv"));
        //Writing data to a csv file
        List list = new ArrayList();
        String[] colum = new String[dtm.getColumnCount()];
        for (int k = 0; k < dtm.getColumnCount(); k++) {
            colum[k] = dtm.getColumnName(k).toString();
        }
        list.add(colum);
        for (int i = 0; i < dtm.getRowCount(); i++) {
            String[] row = new String[dtm.getColumnCount()];
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                row[j] = dtm.getValueAt(i, j).toString();
            }
            list.add(row);

        }
        //Instantiating the List Object

        //Writing data to the csv file
        writer.writeAll(list);
        writer.close();

        writer.flush();

        System.out.println(
                "Data entered");
    }

}
