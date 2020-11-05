/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.File;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.SimpleCart;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

/**
 *
 * @author ar
 */
public class CART_Classification {

    public void classification() {
        StringBuffer sb = new StringBuffer();
        double accuracy = 0;
        double error = 0;
        double count = 0;

        try {
            double t1 = System.currentTimeMillis();
            CSVLoader csvData1 = new CSVLoader();
            csvData1.setFile(new File("traindata.csv"));

            Instances train = csvData1.getDataSet();
            train.setClassIndex(train.numAttributes() - 1);

            SimpleCart classifier = new SimpleCart();
            classifier.buildClassifier(train);
            System.out.println("Start" +classifier);

             
            
            CSVLoader csvData2 = new CSVLoader();
            csvData2.setFile(new File("testdata.csv"));

            Instances test = csvData2.getDataSet();
            test.setClassIndex(test.numAttributes() - 1);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(classifier, test);

            count = eval.numInstances();
            accuracy = eval.correct();
            error = 100 - accuracy;

            double acc = (accuracy / count) * 100.0;
            double err = 100 - acc;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
