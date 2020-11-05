/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.table.DefaultTableModel;



public class CSVFile {
     public  static StringBuffer Cdata;
     private static final String NEW_LINE_SEPARATOR = "\n";
    public static DefaultTableModel trainmodel=new DefaultTableModel();
    public static DefaultTableModel testmodel=new DefaultTableModel();
    public static DefaultTableModel alldata=new DefaultTableModel();
     public static DefaultTableModel readCsv(String path) {
           Cdata = new StringBuffer();
        DefaultTableModel dtm = new DefaultTableModel();
        String row[];
        try {
            File f = new File(path);
            FileReader fout = new FileReader(f);
            BufferedReader br = new BufferedReader(fout);
            String line = "";
            int i = 1;
            while ((line = br.readLine()) != null) {
                if (i == 1) {
                    Cdata.append(line + "\n");
                    String[] columnvalue = line.split(",");
                    for (int j = 0; j < columnvalue.length; j++) {
                        dtm.addColumn(columnvalue[j]);
                    }
                } else {
                    row = line.split(",");
                    Cdata.append(line+"\n");
                    
                   dtm.addRow(row);
                }
                i++;
            }
        } catch (Exception e) {
        }
        return dtm;
    }
        public static void writeCSV(DefaultTableModel dtm) throws IOException {
        Random ran=new Random();
        
       ArrayList lst_test=new ArrayList<>();
        String attr = "";
        for (int i = 0; i < dtm.getColumnCount(); i++) {
            attr = attr +","+dtm.getColumnName(i);
            trainmodel.addColumn(dtm.getColumnName(i));
            testmodel.addColumn(dtm.getColumnName(i));
            alldata.addColumn(dtm.getColumnName(i));
        }
        FileWriter train_writer = null;
        FileWriter test_writer=null;
        int instances=dtm.getRowCount();
        int maxtrainindex=(int)(dtm.getRowCount()*70/100)+1;
        int maxtestindex=(int)(dtm.getRowCount()*30/100);
        int k=0;
        while(k<maxtestindex)
        { 
            int num=ran.nextInt(dtm.getRowCount());
           
            if(!lst_test.contains(num))
            {
                lst_test.add(num);
                k++;
            
            }
       
        }
        train_writer = new FileWriter("traindata.csv");
        test_writer=new FileWriter("testdata.csv");
        train_writer.append(attr.substring(1)); 
        train_writer.append(NEW_LINE_SEPARATOR);
        test_writer.append(attr.substring(1)); 
        test_writer.append(NEW_LINE_SEPARATOR);
        for (int i = 0; i < dtm.getRowCount(); i++) {
            StringBuilder row = new StringBuilder();
            String []dta=new String[dtm.getColumnCount()];
            int m=0;
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                String data =  dtm.getValueAt(i, j).toString();
                dta[m]=data.trim();
                row.append(data.trim()).append(",");
                m++;
            }
            String s =row.substring(0,row.lastIndexOf(","));
            String s1=s.substring(0,s.lastIndexOf(",")+1);
            String s2=s.substring(s.lastIndexOf(",")+1);      
          //  String nn = s2;
          //  nn.lastIndexOf(s2);
            String updatedclass=s2;
            
            //double val=Double.valueOf(updatedclass);
           // int val= b;
//              String val1= getvalue.get(i).toString();
                         double val= Double.parseDouble(updatedclass);
            if(val==1)
                updatedclass="Pass";
              else if(val==2)
                updatedclass="Withdrawn";
              else if(val==3)
                updatedclass="Fail";
              else
               updatedclass="Distinction";
               s=s1+updatedclass;
            dta[dta.length-1]=String.valueOf(val);
            if(i<maxtrainindex)
            {
               train_writer.append(s);
               train_writer.append(NEW_LINE_SEPARATOR);
            }
            if(lst_test.contains(i))
            {
               test_writer.append(s);
               test_writer.append(NEW_LINE_SEPARATOR);
            }
            alldata.addRow(dta);
        }
        train_writer.flush();
        train_writer.close();
        test_writer.flush();
        test_writer.close();
    }
}
