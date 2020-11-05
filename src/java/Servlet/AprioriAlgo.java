/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apriori;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author SHIVA
 */
public class AprioriAlgo {
     int FLAG = 0;
    List<String> candidates = new ArrayList<String>();
    LinkedHashMap<String,Integer>mapitemsupport=new LinkedHashMap<String,Integer>();
    ArrayList<Integer>countfrequency=new ArrayList();
    ArrayList<String>lstitems=new ArrayList<String>();
    ArrayList<String> dlm1 = new ArrayList<String>();
    public ArrayList<String> dlm2 = new ArrayList<String>();
   
     double minConfidence = 0;
     public double totalconfidance = 0;
    int numItems; //number of items per transaction
    int numTransactions; //number of transactions
    double minSup; //minimum support for a frequent itemset
    ArrayList<String> oneVal; //array of value per column that will be treated as a '1'
    String itemSep = " "; //the separator value for items in the database
    Random rm = new Random();
    ArrayList<ArrayList<String>> MyList;
    public static double Acc = 0;
    public static long Etime = 0;
    public static double Mem = 0;
    public static double errr = 0;
    int sum = 0;
    int Tctr = 0;
    int Fctr = 0;

    int tempsize = 0;

    public AprioriAlgo(int numItem, int numTrans, int minsup, ArrayList<String> itemset, ArrayList<ArrayList<String>> transactionset,ArrayList<String> candidate) {

        numItems = numItem;
        numTransactions = numTrans;
        minSup = minsup;
        MyList = transactionset;
        oneVal = itemset;
        candidates=candidate;
    }

    /**
     * **********************************************************************
     * Method Name : aprioriProcess Purpose : Generate the apriori itemsets
     * Parameters : None Return : None
     * ***********************************************************************
     */
    public ArrayList<String> aprioriProcess() {
        java.util.Date d; //date object for timing purposes
        long start, end; //start and end time
        int itemsetNumber = 0;
        //the current itemset being looked at

        //Show the default Config*******************
        dlm1.add("\nDefault Configuration: ");

        dlm1.add("\nInput configuration: " + numItems + " items " + MyList.get(0).size() + " transactions");
        dlm1.add("\nminsup = " + minSup + "%");
        dlm1.add("\n");

        //***************************
        dlm1.add("\n APROIRI algorithm has started.\n");

        //start timer
        d = new java.util.Date();
        start = d.getTime();
         dlm1.add("\nItems:");
            for (int i = 0; i < oneVal.size()-1; i++)
            {
                dlm1.add(oneVal.get(i)+"\n");
            }
        //while not complete
        do {
            //increase the itemset that is being looked at
            itemsetNumber++;

            //generate the candidates
            generateCandidates(itemsetNumber);

            //determine and display frequent itemsets
            
            //if there are <=1 frequent items, then its the end. This prevents reading through the database again. When there is only one frequent itemset.
        } while (candidates.size() > 1);

      
        return dlm1;
    }
   
    private void generateCandidates(int n) {
      //temporary candidate string vector
        ArrayList<String> tempCandidates = new ArrayList<String>();
        ArrayList<String>lstitem=new ArrayList<String>();
        countfrequency=new ArrayList<Integer>();
        String str1, str2; //strings that will be used for comparisons
         String it1, it2;
        StringTokenizer st1, st2; //string tokenizers for the two itemsets being compared
        StringTokenizer item1, item2;
        //if its the first set, candidates are just the numbers
          if(n==1)
            {
                for (int i = 0; i < numItems; i++)
                {
                    int count = 0;
                    String item = "";
                   
                    for (int k = 0; k < MyList.size(); k++)
                    {
                        if (MyList.get(k).get(Integer.parseInt(candidates.get(i))).equals(oneVal.get(i)))
                        {
                            count++;
                            item = oneVal.get(i);
                            
                        }
                   
                    }
                     if (count >= minSup)
                    {
                        tempCandidates.add(candidates.get(i));
                        countfrequency.add(count);
                        lstitem.add(item);
                        if(!mapitemsupport.containsKey(item))
                        {
                        mapitemsupport.put(item, count);
                        }
                    }
                    
                }
            }
        
        else if(n == 2) //second itemset is just all combinations of itemset 1
            {
                //add each itemset from the previous frequent itemsets together
                for (int i = 0; i < candidates.size(); i++)
                {
                    st1 = new StringTokenizer(candidates.get(i));
                    String itm1 = lstitems.get(i);
                    str1 = st1.nextToken();
                    for (int j = i + 1; j < candidates.size(); j++)
                    {
                        st2 = new StringTokenizer(candidates.get(j));
                        str2 = st2.nextToken();
                        String itm2 = lstitems.get(j);
                        int countoccurance = 0;
                      
                        for (int x = 0; x < MyList.size(); x++)
                        {
                            if (MyList.get(x).get(Integer.parseInt(str1.trim())).equals(itm1) && MyList.get(x).get(Integer.parseInt(str2.trim())).equals(itm2))
                            {
                                countoccurance++;
                            
                            }
                        }
                          if(countoccurance>=minSup)
                            {
                            tempCandidates.add(str1 + " " + str2);
                            lstitem.add(itm1 + " " + itm2);
                            countfrequency.add(countoccurance);
                            if (!mapitemsupport.containsKey(itm1 + " " + itm2))
                            {
                               mapitemsupport.put(itm1 + " " + itm2, countoccurance);
                              
                            }
                            }
                      
                    }
                   
                }
            } else {
            //for each itemset
             //for each itemset
                for (int i = 0; i < candidates.size(); i++)
                {
                    //compare to the next itemset
                    for (int j = i + 1; j < candidates.size(); j++)
                    {
                        //create the strings
                        str1 = "";
                        str2 = "";
                        it1 = "";
                        it2 = "";

                        //create the tokenizers
                        st1 = new StringTokenizer(candidates.get(i));
                        st2 = new StringTokenizer(candidates.get(j));
                        item1 = new StringTokenizer(lstitems.get(i));
                        item2 = new StringTokenizer(lstitems.get(j));

                        //make a string of the first n-2 tokens of the strings
                        for (int s = 0; s < n - 2; s++)
                        {
                            str1 = str1 + " " + st1.nextToken();
                            str2 = str2 + " " + st2.nextToken();
                            it1 = it1 + " " + item1.nextToken();
                            it2 = it2 + " " + item2.nextToken();
                        }

                        //if they have the same n-2 tokens, add them together
                        if (str2.compareTo(str1) == 0)
                        {
                            String candidatestr=(str1 + " " + st1.nextToken() + " " + st2.nextToken()).trim();
                            String itemstr = (it1 + " " + item1.nextToken() + " " + item2.nextToken()).trim();
                            String []resultitemstr=itemstr.split(" ");
                            String []resultindexstr=candidatestr.split(" ");
                            int  countoccurance = 0;
                             
                            for (int s = 0; s <MyList.size(); s++)
                            {
                                int tempcount = 0;
                                for (int x = 0; x <resultindexstr.length;x++)
                                {
                                    if (MyList.get(s).get(Integer.parseInt(resultindexstr[x].trim())).equals(resultitemstr[x].trim()))
                                    {
                                        tempcount++;
                                    }
                                }
                                if (tempcount == resultitemstr.length)
                                {
                                    countoccurance++;
                               
                                }
                            }
                            if(countoccurance>=minSup)
                            {
                                tempCandidates.add(candidatestr);
                                lstitem.add(itemstr);
                                countfrequency.add(countoccurance);
                                if(!mapitemsupport.containsKey(itemstr))
                                {
                                mapitemsupport.put(itemstr, countoccurance);
                               
                                }
                            }
                            
                        }

                    }
                }
        }
        //clear the old candidates
            candidates.clear();
            lstitems.clear();
            candidates = new ArrayList<String>(tempCandidates);
            lstitems = new ArrayList<String>(lstitem);
            tempCandidates.clear();
            lstitem.clear();
  }

     public static ArrayList<String> generateRules(LinkedHashMap<String, Integer> itemsetWithFrequency, int minconfidence) {
        ArrayList<String>rules=new ArrayList<>();
        List<String> itemset = new ArrayList<String>(itemsetWithFrequency.keySet());
        try {
            for (int i = 0; i < itemset.size(); i++) {
                String[] items = itemset.get(i).split(" ");
                if (items.length > 1) {
                    if (items.length == 2) {
                        String s = items[0] + " " + items[1];
                        double support = itemsetWithFrequency.get(s);

                        int support1 = itemsetWithFrequency.get(items[0]);
                        int support2 = itemsetWithFrequency.get(items[1]);

                        double finalsupport1 = (support / support1) * 100;
                        double finalsupport2 = (support / support2) * 100;
                        if (finalsupport1 > minconfidence) {
                           
                            rules.add(items[0] + "----->" + items[1]);
                        }
                        if (finalsupport2 > minconfidence) {
                          
                            rules.add(items[0] + "----->" + items[1]);
                        }
                    } else {
                        for (int j = 0; j < itemset.size(); j++) {
                            String s = itemset.get(i);

                            String[] item1 = itemset.get(j).split(" ");
                            if (!(item1.length >= items.length)) {
                                int flag = 0;
                                for (int k = 0; k < item1.length; k++) {

                                    if (s.contains(item1[k])) {
                                        int index = s.indexOf(item1[k]);
                                        if (index > 1) {
                                            int a = index + item1[k].length();
                                            if (a == s.length()) {
                                                s = s.substring(0, index - 1);
                                            } else {
                                                String s1 = s.substring(0, index - 1);
                                                String s2 = s.substring(index + item1[k].length() + 1);
                                                s = s1 + " " + s2;
                                            }
                                        } else {
                                            s = s.substring(item1[k].length() + 1);
                                        }
                                        flag = 1;
                                    } else {
                                        flag = 0;
                                    }
                                }
                                if (flag != 0) {
                                    double support = itemsetWithFrequency.get(itemset.get(i));
                                    int support1 = itemsetWithFrequency.get(itemset.get(j));
                                    int support2 = 1;
                                    if (itemsetWithFrequency.containsKey(s)) {
                                        support2 = itemsetWithFrequency.get(s);
                                    }
                                    double finalsupport1 = (support / support1) * 100;
                                    double finalsupport2 = (support / support2) * 100;
                                    if (finalsupport1 > minconfidence) {
                                      
                                        rules.add(itemset.get(j) + "----->" + s);
                                    }
                                    if (finalsupport2 > minconfidence) {
                                  
                                        rules.add(s + "----->" + itemset.get(j));
                                    }

                                }
                            }
                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //return new ArrayList<>(rules);
        return rules;
    }
}
