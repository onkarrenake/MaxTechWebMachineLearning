/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classifiers.trees;
import core.*;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ganesh
 */
public class C45Node extends classifiers.trees.Classifier {

    
      //Attribute used for splitting.
        private core.Attribute m_Attribute;
        //children nodes
        private C45Node[] m_Successors;
        //Class value if node is leaf
        private double m_ClassValue;
        //Class distribution if node is leaf
        private double[] m_Distribution;
        //Class attribute of dataset.
        private core.Attribute m_ClassAttribute;
        //threshold value (split value) of numeric attribute

        private double m_Threshold = 0.0;
        //a copy of dataset
        private Instances m_Data;
        //return the number of nodes in the tree

        //
        // Returns a string describing classifier
        // @return a description suitable for
        // displaying in the explorer gui
        //
        @Override
        public  String GlobalInfo()
        {

            return "Class for constructing an unpruned decision tree based on the C45 " + "algorithm. Can deal with nominal and numeric attributes. Missing values " + "allowed. Empty leaves may result in unclassified instances. For more " + "information see: ";
        }

        //'
        // Generates the classifier.
        //
        // @param instances set of instances serving as training data 
        // @exception Exception if the classifier has not been generated successfully
        //'
    @Override
    public void BuildClassifier(Instances data) {
        try
            {
                if ((!data.ClassAttribute().IsNominal()))
                {
                    throw new Exception("C45Node: nominal class only, please.");
                }

                data = new Instances(data);
                data.DeleteWithMissingClass();
                m_Data = new Instances(data);

                //sort numeric attributes
                Hashtable sortedSplits = new Hashtable();
                Enumeration attEnum = m_Data.EnumerateAttributes();
                while ((attEnum.HasMoreElements()))
                {
                    core.Attribute att =(core.Attribute) attEnum.NextElement();
                    if ((att.IsNumeric()))
                    {
                        sortedSplits.put(att.Index(), SplitsListOfAttribute(att.Index()));
                    }
                }
                //build tree recursively
                MakeTree(data);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

        }
        public FastVector SplitsListOfAttribute(int attIndex) throws Exception
        {
            FastVector splits = new FastVector();
            Instances dataset ;
            dataset = new Instances(m_Data);
            dataset.Sort(attIndex);
            for (int i = 0; i <= dataset.NumInstances() - 2; i++)
            {
                Instance currentInst = dataset.GetInstance(i);
                Instance nextInst = dataset.GetInstance(i + 1);
                if ((currentInst.ClassIndex())==(nextInst.ClassIndex()))
                {
                    double split = (currentInst.Value(attIndex) + nextInst.Value(attIndex)) / 2;
                    splits.AddElement(split);
                }
            }
            return splits;
        }

        //
        // Classifies a given instance.
        //
        // @param instance the instance to be classified
        // @return index of the predicted class
        //
        @Override
        public double ClassifyInstance(Instance instance)
        {
            try
            {
                //return the most common class value when classifing 
                //an instance with missing values
                if ((instance.HasMissingValue()))
                {
                    double[] distribution = null;
                    distribution = new double[m_Data.NumClasses()];
                    Enumeration instEnum = m_Data.EnumerateInstances();
                    while ((instEnum.HasMoreElements()))
                    {
                        Instance inst = (Instance)instEnum.NextElement();
                        distribution[(int)(inst.ClassValue())] = distribution[(int)(inst.ClassValue())] + 1;
                    }

                    return (double)(utils.Utils1.MaxIndex(distribution));
                }
                //reach a leaf
                if ((m_Attribute == null))
                {
                    return m_ClassValue;
                }
                else
                {
                    //use binary split method for numeric attributes
                    if ((m_Attribute.IsNumeric()))
                    {
                        if ((instance.Value(m_Attribute) <= m_Threshold))
                        {
                            return m_Successors[0].ClassifyInstance(instance);
                        }
                        else
                        {
                            return m_Successors[1].ClassifyInstance(instance);
                        }
                    }
                    else
                    {
                        return m_Successors[(int)(instance.Value(m_Attribute))].ClassifyInstance(instance);
                    }
                }
             
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            return 0;
        }
        //'
        // Calculates the class membership probabilities for the given test instance.
        //
        // @param instance the instance to be classified
        // @return predicted class probability distribution
        // @exception Exception if class is numeric
        @Override
        public double[] DistributionForInstance(Instance instance)
        {
            try
            {
                if ((instance.HasMissingValue()))
                {
                    throw new Exception("C45Node: no missing values, please.");
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            if ((m_Attribute == null))
            {
                return m_Distribution;
            }
            else
            {
                return m_Successors[(int)(instance.Value(m_Attribute))].DistributionForInstance(instance);
            }
        }
        //'
        // Returns a description of the classifier.
        //
        // @return a description of the classifier as a string.
        //'

    /**
     *
     * @return
     */
        @Override
        public String toString()
        {
            System.out.println("sdfds");
            if (((m_Distribution == null) & (m_Successors == null)))
            {
                return "C45Node: No model built yet.";
            }
        try {
            return System.getProperty("line.separator") + toString(0);
        } catch (Exception ex) {
            Logger.getLogger(C45Node.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
        }
        private String toString(int level) throws Exception
        {
            //print the final tree (TEXT)
            StringBuilder text = new StringBuilder();
            if ((m_Attribute == null))
            {
                if ((boolean)(Boolean.parseBoolean(Instance.IsMissingValue(m_ClassValue).toString())))
                {
                    text.append(": Nothing");
                }
                else
                {
                    text.append(": " + m_ClassAttribute.Value((int)(m_ClassValue)));
                }
            }
            else
            {
                if ((m_Attribute.IsNumeric()))
                {
                    int i = 0;
                    int j = 0;
                    for (j = 0; j <= 1; j++)
                    {
                        text.append(System.getProperty("line.separator"));
                        for (i = 0; i <= level - 1; i++)
                        {
                            text.append("|  ");
                        }
                        if ((j == 0))
                        {
                            text.append(m_Attribute.Name() + " <= " + m_Threshold);
                            text.append(m_Successors[j].toString(level + 1));
                        }
                        else
                        {
                            text.append(m_Attribute.Name() + " > " + m_Threshold);
                            text.append(m_Successors[j].toString(level + 1));
                        }

                    }
                }
                else
                {
                    int i = 0;
                    int j = 0;
                    for (j = 0; j <= m_Attribute.NumValues() - 1; j++)
                    {
                        text.append(System.getProperty("line.separator"));
                        for (i = 0; i <= level - 1; i++)
                        {
                            text.append("|  ");
                        }
                        text.append(m_Attribute.Name() + " = " + m_Attribute.Value(j));
                        text.append(m_Successors[j].toString(level + 1));
                    }
                }

            }

            return text.toString();
        }


        private void MakeTree(Instances data)
        {
            try
            {
                //stop when there is no instance left
                if ((data.NumInstances() == 0))
                {
                    m_Attribute = null;
                    m_ClassValue = Instance.MissingValue();
                    m_Distribution = new double[data.NumClasses()];
                    return;
                }
                //stop when dataset's entropy value is 0
                if ((ComputeEntropy(data) == 0))
                {
                    m_Attribute = null;
                    m_ClassValue = data.FirstInstance().ClassValue();
                    m_Distribution = new double[data.NumClasses()];
                    m_ClassAttribute = data.ClassAttribute();
                    return;
                }
                //select the best attribute by using information gain and entropy calculation
                double[] infoGains = null;
                infoGains = new double[data.NumAttributes()];

                Enumeration enumAtt = data.EnumerateAttributes();
                while ((enumAtt.HasMoreElements()))
                {
                    core.Attribute att = (core.Attribute)enumAtt.NextElement();


                    if ((att.IsNumeric()))
                    {
                        infoGains[att.Index()] = ComputeInfoGain(data, att, GetThreshold(data, att));
                    }
                    else if ((att.IsNominal()))
                    {
                        infoGains[att.Index()] = ComputeInfoGain(data, att);
                    }


                }
                m_Attribute = data.Attribute(utils.Utils1.MaxIndex(infoGains));


                if ((m_Attribute.IsNumeric()))
                {
                    m_Attribute.SetBinarySplit(GetThreshold(data, m_Attribute));

                }


                //stop if the information gain is small enougth

                if (((utils.Utils1.Eq(infoGains[m_Attribute.Index()], 0))))
                {
                    m_Attribute = null;
                    m_Distribution = new double[data.NumClasses()];
                    Enumeration instEnum = data.EnumerateInstances();
                    while ((instEnum.HasMoreElements()))
                    {
                        Instance inst = (Instance)instEnum.NextElement();
                        m_Distribution[(int)(inst.ClassValue())] = m_Distribution[(int)(inst.ClassValue())] + 1;

                    }

                    //utils.Utils.Normalize(ref m_Distribution);
                    //******************** Normalizing m_Distribution here without using utils.Utils.Normalize(ref m_Distribution);
                    double sum = 0;
			int i = 0;
			for (i = 0; i <= m_Distribution.length - 1; i++) {
				sum += m_Distribution[i];
			}
                    if (Double.isNaN(sum)) {
				throw new Exception("Can't normalize array. Sum is NaN.");
			}
			if ((sum == 0)) {
				throw new Exception("Can't normalize array. Sum is zero.");
			}
			
			for (i = 0; i <= m_Distribution.length - 1; i++) {
				m_Distribution[i] /= sum;
			}
                    
                    //********************
                    m_ClassValue = utils.Utils1.MaxIndex(m_Distribution);

                    m_ClassAttribute = data.ClassAttribute();
                }
                else
                {
                    //split dataset for next level of the tree
                    Instances[] splitDatasets = null;

                    if ((m_Attribute.IsNumeric()))
                    {
                        splitDatasets = SplitData(data, m_Attribute, m_Attribute.GetBinarySplit());
                        m_Successors = new C45Node[2];
                        int j = 0;
                        for (j = 0; j <= 1; j++)
                        {
                            m_Successors[j] = new C45Node();
                            m_Successors[j].MakeTree(splitDatasets[j]);
                        }
                    }
                    else
                    {
                        splitDatasets = SplitData(data, m_Attribute);
                        m_Successors = new C45Node[m_Attribute.NumValues()];
                        int j = 0;
                        for (j = 0; j <= m_Attribute.NumValues() - 1; j++)
                        {
                            m_Successors[j] = new C45Node();
                            m_Successors[j].MakeTree(splitDatasets[j]);

                        }
                    }

                }

            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        private double ComputeInfoGain(Instances data, core.Attribute att) throws Exception
        {
            if ((att.IsNumeric()))
            {
                throw new Exception("Nominal attribute only");
            }

            double infoGain = ComputeEntropy(data);

            Instances[] splitDatasets = null;
            splitDatasets = SplitData(data, att);

            int j = 0;
            for (j = 0; j <= att.NumValues() - 1; j++)
            {
                if ((splitDatasets[j].NumInstances() > 0))
                {
                    infoGain -= ((double)(splitDatasets[j].NumInstances()) / (double)(data.NumInstances())) * ComputeEntropy(splitDatasets[j]);
                }
            }

            return infoGain;
        }
        private double ComputeInfoGain(Instances data, core.Attribute att, double threshold)
        {
            double infoGain = ComputeEntropy(data);
            try
            {
                Instances[] splitDatasets = null;
                splitDatasets = SplitData(data, att, threshold);
                if ((att.IsNumeric()))
                {
                    int j = 0;
                    for (j = 0; j <= 1; j++)
                    {
                        if ((splitDatasets[j].NumInstances() > 0))
                        {
                            infoGain -= ((double)(splitDatasets[j].NumInstances()) / (double)(data.NumInstances())) * ComputeEntropy(splitDatasets[j]);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return infoGain;
        }
        private double ComputeEntropy(Instances data)
        {
            double entropy = 0;
            try
            {
                double[] classCounts = null;
                classCounts = new double[data.NumClasses()];
                Enumeration instEnum = data.EnumerateInstances();
                while ((instEnum.HasMoreElements()))
                {
                    Instance inst = (Instance)instEnum.NextElement();
                    classCounts[(int)(inst.ClassValue())] = classCounts[(int)(inst.ClassValue())] + 1;
                }

                int j = 0;

                for (j = 0; j <= data.NumClasses() - 1; j++)
                {
                    if ((classCounts[j] > 0))
                    {
                        entropy -= classCounts[j] * utils.Utils1.Log2(classCounts[j]);
                    }
                }
                entropy /= (double)(data.NumInstances());
            }
            catch (Exception ex)
            {
            ex.printStackTrace();
            }
            return entropy + utils.Utils1.Log2(data.NumInstances());
        }
        // return the best threshold(binary split point) value of a numeric attribute
        private double GetThreshold(Instances data, core.Attribute att) throws Exception
        {

            double min = 0;
            double max = 0;
            min = Double.MAX_VALUE;
            max = Double.MIN_VALUE;

            Enumeration instEnum = data.EnumerateInstances();
            while ((instEnum.HasMoreElements()))
            {
                Instance inst = (Instance)instEnum.NextElement();
                if ((min > inst.Value(att)))
                {
                    min = inst.Value(att);
                }
                if ((max < inst.Value(att)))
                {
                    max = inst.Value(att);
                }
            }
            m_Threshold = (min + max) / 2;
            return m_Threshold;
        }
        private Instances[] SplitData(Instances data, core.Attribute att, double threshold) throws Exception
        {
            Instances[] splitDatasets = null;
            splitDatasets = new Instances[2];
            int j = 0;
            for (j = 0; j <= 1; j++)
            {
                splitDatasets[j] = new Instances(data, data.NumInstances());
            }

            Enumeration instEnum = data.EnumerateInstances();

            while ((instEnum.HasMoreElements()))
            {
                Instance inst = (Instance)instEnum.NextElement();
                if ((inst.Value(att) <= threshold))
                {
                    splitDatasets[0].Add(inst);
                }
                else
                {
                    splitDatasets[1].Add(inst);
                }
            }
            int i = 0;
            for (i = 0; i <= splitDatasets.length - 1; i++)
            {
                splitDatasets[i].Compactify();
            }

            return splitDatasets;
        }
        private Instances[] SplitData(Instances data, core.Attribute att) throws Exception
        {
            Instances[] splitDatasets = null;


            if ((att.IsNominal()))
            {
                splitDatasets = new Instances[att.NumValues()];
                int j = 0;
                for (j = 0; j <= att.NumValues() - 1; j++)
                {
                    splitDatasets[j] = new Instances(data, data.NumInstances());
                }
                Enumeration instEnum = data.EnumerateInstances();
                while ((instEnum.HasMoreElements()))
                {
                    Instance inst = (Instance)instEnum.NextElement();
                    if ((inst.Value(att) < att.NumValues()))
                    {
                        splitDatasets[(int)(inst.Value(att))].Add(inst);
                    }
                }
                int i = 0;
                for (i = 0; i <= splitDatasets.length - 1; i++)
                {
                    splitDatasets[i].Compactify();
                }

                return splitDatasets;
            }
            return null;
        }

        //public classifiers.trees.SLIQTREE.TreeVisualizer.Node TreeNodeGraph()
        //{

        //    if (((m_Distribution == null) & (m_Successors == null)))
        //    {
        //        return null;
        //    }

        //    return TreeNodeGraph(0);
        //}

        ////public classifiers.trees.SLIQTREE.TreeVisualizer.Node TreeNodeGraph(int level)
        ////{

        ////    vbWeka.classifiers.trees.B45.TreeVisualizer.Node treeGraph = new vbWeka.classifiers.trees.B45.TreeVisualizer.Node();
        ////    if ((m_Attribute == null))
        ////    {
        ////        if ((Instance.IsMissingValue(m_ClassValue)))
        ////        {
        ////            treeGraph.Children = null;
        ////            treeGraph.ClassValue = "Nothing";
        ////            treeGraph.Element = "Nothing";
        ////        }
        ////        else
        ////        {
        ////            treeGraph.Children = null;
        ////            treeGraph.ClassValue = m_ClassAttribute.Value(int.Parse(m_ClassValue));
        ////            treeGraph.Element = m_ClassAttribute.Value(int.Parse(m_ClassValue));
        ////        }
        ////    }
        ////    else
        ////    {
        ////        int j = 0;

        ////        if ((m_Attribute.IsNumeric))
        ////        {
        ////            treeGraph.ClassValue = m_ClassValue;
        ////            treeGraph.Element = m_Attribute.Name();
        ////            treeGraph.Children = new vbWeka.classifiers.trees.B45.TreeVisualizer.Node[2];
        ////            for (j = 0; j <= 1; j++)
        ////            {
        ////                treeGraph.Children(j) = m_Successors[j].TreeNodeGraph(level + 1);
        ////                treeGraph.Children(j).Parent = treeGraph;
        ////            }
        ////            treeGraph.Children(0).InLineValue = "<=" + m_Attribute.GetBinarySplit;
        ////            treeGraph.Children(1).InLineValue = ">" + m_Attribute.GetBinarySplit;
        ////        }
        ////        if ((m_Attribute.IsNominal))
        ////        {
        ////            treeGraph.ClassValue = m_ClassValue;
        ////            treeGraph.Element = m_Attribute.Name();
        ////            treeGraph.Children = new vbWeka.classifiers.trees.B45.TreeVisualizer.Node[m_Attribute.NumValues];

        ////            for (j = 0; j <= m_Attribute.NumValues - 1; j++)
        ////            {
        ////                treeGraph.Children(j) = m_Successors[j].TreeNodeGraph(level + 1);
        ////                treeGraph.Children(j).InLineValue = m_Attribute.Value(j);
        ////                treeGraph.Children(j).Parent = treeGraph;
        ////            }
        ////        }
        ////    }
        ////    return treeGraph;
        ////}
        //return number of nodes of the tree
        public int NumNodes()
        {
            int i = 0;
            int n = 1;
            if (((m_Successors != null)))
            {
                for (i = 0; i <= m_Successors.length - 1; i++)
                {
                    n = n + m_Successors[i].NumNodes();
                }
            }
            return n;
        }
        //return the number of leaves of the tree
        public int NumLeaves()
        {
            int i = 0;
            int n = 0;
            if ((m_Successors == null))
            {
                return 1;
            }
            else
            {
                for (i = 0; i <= m_Successors.length - 1; i++)
                {
                    n = n + m_Successors[i].NumLeaves();
                }
            }
            return n;
        }

    public String[] getOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

        private class UpdatableElementTable
        {
            protected int m_Index;
            protected int m_LastBestSplit;
            protected FastVector m_Splits;
            public int LastBestSplit=m_LastBestSplit;
            
            public UpdatableElementTable(int attIndex)
            {
                m_Index = attIndex;
                m_Splits = new FastVector();
            }
            public void AddSplit(double split) throws Exception
            {
                m_Splits.AddElement(split);
            }
            public void UpdateSplitsBy(double cutpoint, int leftOrRight) throws Exception
            {
                FastVector newSplits = new FastVector(m_Splits.Size());
                if ((m_Splits.Size() < 1))
                {
                    throw new Exception("Empty list cannot be updated.");
                }

                if ((leftOrRight == 0))
                {
                }
                if ((leftOrRight == 1))
                {
                    for (int i = m_Splits.Size() - 1; i <= 0; i++)
                    {
                        //if (m_Splits.ElementAt (i)
                    }
                }

            }
        }


    

}
