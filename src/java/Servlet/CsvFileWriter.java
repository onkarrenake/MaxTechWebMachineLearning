/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

/**
 *
 * @author JAI GANESH
 */
import static Servlet.CSVFile.alldata;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
//import weka.core.converters.ArffSaver;
//import weka.core.converters.CSVLoader;

/**
 * @author ashraf
 *
 */
public class CsvFileWriter {

    private static final String NEW_LINE_SEPARATOR = "\n";
    public static DefaultTableModel trainmodel = new DefaultTableModel();
    public static DefaultTableModel testmodel = new DefaultTableModel();
    public static DefaultTableModel alldata = new DefaultTableModel();

    /**
     * method : writeCsv write data into given file
     *
     * @param fileName
     * @return
     *
     */
    public static DefaultTableModel readCSV(String path) {
        DefaultTableModel dtm = new DefaultTableModel();
        String row[] = null;
        try {
            File f = new File(path);
            FileReader fout = new FileReader(f);
            BufferedReader br = new BufferedReader(fout);
            String line = "";
            int i = 1;
            while ((line = br.readLine()) != null) {
                if (i == 1) {
                    String[] columnvalue = line.split(",");
                    for (int j = 0; j < columnvalue.length; j++) {
                        dtm.addColumn(columnvalue[j]);
                    }
                } else {
                    row = line.split(",");
                    int sss = row[0].length();
                    if (row[0].length() < 5) {

                        dtm.addRow(row);
                    }
                }
                i++;
            }
        } catch (Exception e) {
        }
        return dtm;
    }

    public static DefaultTableModel writeFile(DefaultTableModel dtm) throws IOException {
        trainmodel = new DefaultTableModel();
        testmodel = new DefaultTableModel();
        Random ran = new Random();
        ArrayList lst_test = new ArrayList<>();
        String attr = "";
        for (int i = 0; i < dtm.getColumnCount(); i++) {

//              if(dtm.getColumnName(i).equals("sentiment_gold")||dtm.getColumnName(i).equals("relevant_yn_gold")||dtm.getColumnName(i).equals("candidate_gold")||dtm.getColumnName(i).equals("subject_matter_gold"))
//              { continue;
//               }
//              else{
            attr = attr + "," + dtm.getColumnName(i);
            trainmodel.addColumn(dtm.getColumnName(i));
            testmodel.addColumn(dtm.getColumnName(i));
            alldata.addColumn(dtm.getColumnName(i));
            // }
        }
        //   attr=attr+","+dtm.getColumnName();
        //  trainmodel.addColumn(dtm.getColumnName(0));
        // testmodel.addColumn(dtm.getColumnName(0));
        FileWriter train_writer = null;
        FileWriter test_writer = null;
        int maxtrainindex = (int) (dtm.getRowCount() * 70 / 100);
        int maxtestindex = (int) (dtm.getRowCount() * 30 / 100);
        int k = 0;
        while (k < maxtestindex) {
            int num = ran.nextInt(dtm.getRowCount());
            if (num < 10500) {
                if (!lst_test.contains(num)) {
                    lst_test.add(num);
                    k++;
                }
            }
        }
        train_writer = new FileWriter("traindata.csv");
        test_writer = new FileWriter("testdata.csv");
        train_writer.append(attr.substring(1));
        train_writer.append(NEW_LINE_SEPARATOR);
        test_writer.append(attr.substring(1));
        test_writer.append(NEW_LINE_SEPARATOR);
        for (int i = 0; i < dtm.getRowCount(); i++) {
            StringBuffer row = new StringBuffer();
            String[] dta = new String[dtm.getColumnCount()];
            int m = 0;
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                String data = (String) dtm.getValueAt(i, j);
                StringBuffer sb = new StringBuffer(data);

                String data1 = sb.toString();

                dta[m] = data1;

                row.append("," + data1);
                m++;
            }
            //  String val=(String)dtm.getValueAt(i,0);
            // dta[dta.length-1]=val;
            //  row.append(","+val);
            String[] r = row.substring(1).split(",");
            String s = row.substring(1);

            if (i < maxtrainindex) {
                trainmodel.addRow(dta);
                train_writer.append(s);
                train_writer.append(NEW_LINE_SEPARATOR);
            }
            if (lst_test.contains(i)) {
                testmodel.addRow(dta);
                test_writer.append(s);
                test_writer.append(NEW_LINE_SEPARATOR);
            }
             alldata.addRow(dta);
        }
        train_writer.flush();
        train_writer.close();
        test_writer.flush();
        test_writer.close();
        return dtm;
    }

}
