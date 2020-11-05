/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import core.Enumeration;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.SimpleCart;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

/**
 *
 * @author ar
 */
public class C45_Classification {

    double time;
    double memory;
    double accuracy = 0;
    double error = 0;
    double count = 0;
    String[] str;
    StringBuffer sb = new StringBuffer();
    core.Instances m_CurrentDataset = null;
    protected String[] m_ClassifierOptions = null;
    protected classifiers.trees.Classifier m_Classifier = null;
    ArrayList<String> rules;
//    J48 classifier;
    SimpleCart classifier;

    public void classification() {
        StringBuffer sb = new StringBuffer();
        double accuracy = 0;
        double error = 0;
        double count = 0;

        try {

            CSVLoader csvData1 = new CSVLoader();
            csvData1.setFile(new File("traindata.csv"));

            Instances train = csvData1.getDataSet();
            train.setClassIndex(train.numAttributes() - 1);
  
//            classifier = new J48();
              classifier = new SimpleCart();
            classifier.buildClassifier(train);
            System.out.println(classifier);
            
            
            CSVLoader csvData2 = new CSVLoader();
            csvData2.setFile(new File("testdata.csv"));

            Instances test = csvData2.getDataSet();
            test.setClassIndex(test.numAttributes() - 1);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(classifier, test);

            count = eval.numInstances();
            accuracy = eval.correct();
            
           // error = 100 - accuracy;

            double acc = (accuracy / count) * 100.0;
            double err = 100 - acc;

            ArrayList<ArrayList<String>> AttT = CreateAttType();
            double t1 = System.currentTimeMillis();
            String[] AttNam = new String[AttT.size()];
            int i = 0;
            while (i < CsvFileWriter.trainmodel.getColumnCount()) {
                AttNam[i] = CsvFileWriter.trainmodel.getColumnName(i);
                i++;
            }
            m_CurrentDataset = new core.Instances(AttNam, AttT, CsvFileWriter.alldata);
            rules = doClassification();
           // extractRule(rules.toString());

//        double acc = (accuracy / count) * 100.0;
//        double err = 100 - acc;
            sb.append("acc " + accuracy + " error " + error);
            //jTextArea1.append(sb.toString());
            double t2 = System.currentTimeMillis();
            time = t2 - t1;
            memory = (Runtime.getRuntime().totalMemory() / 8 - Runtime.getRuntime().freeMemory() / 8) / 1024;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

 

    public ArrayList<ArrayList<String>> CreateAttType() {
        DefaultTableModel dt = CsvFileWriter.alldata;
        ArrayList<ArrayList<String>> G1 = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < dt.getColumnCount(); i++) {
            ArrayList<String> st = new ArrayList<String>();
            for (int j = 0; j < dt.getRowCount(); j++) {
                String val = (String) dt.getValueAt(j, i);
                if (!st.contains(val)) {
                    st.add(val);
                }

            }
            G1.add(st);
        }

        return G1;
    }

    private ArrayList<String> doClassification() {
        classifiers.trees.C45Node ccc = new classifiers.trees.C45Node();
        m_Classifier = ccc;

        try {
            if ((m_CurrentDataset == null)) {
                JOptionPane.showMessageDialog(null, "No dataset has been loaded", "NO DATASET !!", 1);
                return null;
            }

            if (m_Classifier == null) {
                JOptionPane.showMessageDialog(null, "No Classifier has been Selected", "NO Classifier !!", 1);
                return null;
            }

            int numFolds = Integer.parseInt("4");
            if ((numFolds < 2)) {
                //MessageBox.Show("Number of folds must be greater than 1");
                JOptionPane.showMessageDialog(null, "Number of folds must be greater than 1", "LESS FOLDS !!", 1);
                return null;
            }
            // a string buffer stores classification's output info
            StringBuilder buf = new StringBuilder();

            if (((m_ClassifierOptions != null))) {
                m_Classifier.SetOptions(m_ClassifierOptions);
            }

            m_CurrentDataset.SetClassIndex(m_CurrentDataset.NumAttributes() - 1);

            //buf.append("Relation Name: " + m_CurrentDataset.RelationName() + System.getProperty("line.separator"));
            buf.append("Number of Instances: " + m_CurrentDataset.NumInstances() + System.getProperty("line.separator"));

            buf.append(System.getProperty("line.separator"));
            buf.append("Attributes: " + System.getProperty("line.separator"));
            Enumeration attEnum = m_CurrentDataset.EnumerateAttributes();
            while ((boolean) attEnum.HasMoreElements()) {
                // System.out.println("H");
                core.Attribute att = (core.Attribute) attEnum.NextElement();
                buf.append(att.Name() + System.getProperty("line.separator"));
            }
            buf.append(System.getProperty("line.separator"));
            m_Classifier.BuildClassifier(m_CurrentDataset);
            rules = setRules(m_Classifier.toString());
            buf.append("Classifier: " + m_Classifier.toString() + System.getProperty("line.separator"));
            //jTextArea1.setText(buf.toString());
            String rle = "";
            for (int i = 0; i < rules.size(); i++) {
                rle = rle + rules.get(i) + "\n";
            }
            // jTextArea2.setText(rle);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return rules;
    }


    public ArrayList<String> setRules(String buf) {
        ArrayList<String> li = new ArrayList<String>();//Dim li As NEW List(Of String)
        ArrayList<String> Rul = new ArrayList<String>();//Dim Rul As List(Of String)
        //Rul = New List(Of String)
        String[] str1 = buf.split("\n");
        int i = 0;
        int c = 0;
        for (i = 0; i < str1.length; i++) {
            String p = str1[i];
            if (i < 0) {
            } else {
                str = new String[str1.length - i];
                for (int j = i; j <= str1.length - 1; j++) {
                    str[c] = str1[j];
                    c++;
                }
                break;
            }

        }
        i = 0;//Dim i As Integer = 0
        String s = "";
        int count = 0;
        int count1 = 0;
        int rule = 1;
        for (i = 0; i < str.length; i++) {
            count = 0;
            if (str[i].contains("=")) {
                if (str[i].contains("|")) {

                    if (str[i].contains(":")) {
                        if (!str[i].contains("Nothing")) {
                            for (int j = 0; j < str[i].length(); j++) {
                                if (str[i].charAt(j) == '|') {
                                    count++;
                                }
                            }
                            if (count == 1) {
                                if (count > count1) {
                                    s = s + "&" + str[i].substring(str[i].lastIndexOf('|') + 1);
                                    System.out.println("Rule" + rule + ":" + s);
                                } else if (count == count1) {
                                    s = s.substring(0, s.lastIndexOf("&"));
                                    s = s + "&" + str[i].substring(str[i].lastIndexOf('|') + 1);
                                    System.out.println("Rule" + rule + ":" + s);
                                } else {
                                    String s2[] = s.split("&");
                                    s = s2[0] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                                    System.out.println("Rule" + rule + ":" + s);
                                }

                            } else if (count == 2) {
                                if (count > count1) {
                                    s = s + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                                    System.out.println("Rule" + rule + ":" + s);

                                } else if (count == count1) {
                                    String s2[] = s.split("&");
                                    //s=s2[0]+"&"+str[i].substring(str[i].lastIndexOf("|")+1);
                                    s = s2[0] + "&" + s2[1] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);

                                    System.out.println("Rule" + rule + ":" + s);
                                } else {
                                    String s2[] = s.split("&");
                                    s = s2[0] + "&" + s2[1] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                                    System.out.println("Rule" + rule + ":" + s);
                                }

                            } else if (count == 3) {
                                if (count > count1) {
                                    s = s + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                                    System.out.println("Rule" + rule + ":" + s);
                                } else if (count == count1) {
                                    s = s.substring(0, s.lastIndexOf("&")) + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                                    System.out.println("Rule" + rule + ":" + s);
                                } else {
                                    String s2[] = s.split("&");
                                    s = s2[0] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                                    System.out.println("Rule" + rule + ":" + s);
                                }

                            } else if (count == 4) {
                                if (count > count1) {
                                    s = s + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                                    System.out.println("Rule" + rule + ":" + s);

                                } else if (count == count1) {
                                    String s2[] = s.split("&");
                                    for (int p = 0; p < s2.length; p++) {
                                        s = s + s2[p] + "&";
                                    }
                                    if (s.indexOf("&") == 1) {
                                        s = s.substring(0 + 1);
                                    }
                                    s = s + str[i].substring(str[i].lastIndexOf("|") + 1);
                                    System.out.println("Rule" + rule + ":" + s);
                                } else {
                                    String s2[] = s.split("&");
                                    s = s2[0] + "&" + s2[1] + str[i].substring(str[i].lastIndexOf("|") + 1);
                                    System.out.println("Rule" + rule + ":" + s);
                                }
                            }
                            count1 = count;
                            if (s.startsWith("&")) {
                                Rul.add(s.substring(0 + 1).trim());
                            } else {
                                Rul.add(s);
                            }
                            //jTextArea1.append(s+"\n");
                        }
                    } else {
                        for (int j = 0; j < str[i].length(); j++) {
                            if (str[i].charAt(j) == '|') {
                                count++;
                            }
                        }
                        if (count == 1) {
                            if (count > count1) {
                                s = s + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            } else if (count < count1) {
                                String s2[] = s.split("&");
                                s = s2[0] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            } else {
                                String s2[] = s.split("&");
                                s = s2[0] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            }
                        } else if (count == 2) {
                            if (count > count1) {
                                s = s + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            } else if (count < count1) {
                                String s2[] = s.split("&");
                                s = s2[0] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            } else {
                                String s2[] = s.split("&");
                                s = s2[0] + "&" + s2[1] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            }
                        } else if (count == 3) {
                            if (count > count1) {
                                s = s + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            } else if (count < count1) {
                                String s2[] = s.split("&");
                                s = s2[0] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            } else {
                                String s2[] = s.split("&");
                                s = s2[0] + "&" + s2[1] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            }
                        } else if (count == 4) {
                            if (count > count1) {
                                s = s + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            } else if (count < count1) {
                                String s2[] = s.split("&");
                                s = s2[0] + "&" + str[i].substring(str[i].lastIndexOf("|") + 1);
                            }
                        }
                        count1 = count;
                    }

                } else {
                    if (str[i].contains(":")) {
                        if (!str[i].contains("Nothing")) {
                            Rul.add(str[i] + "\n");
                        }
                    } else {
                        s = str[i];
                        count1 = 0;
                    }
                }
            }

            rule++;
        }
        return Rul;
    }
}
