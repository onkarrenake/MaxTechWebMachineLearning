/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classifiers.trees;

import core.OptionHandler;
import core.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ganesh
 */
public  abstract class Classifier implements OptionHandler,Cloneable {

    	
		//ISerializable
		private  boolean  m_Debug = false;
		public Object Clone() 
		{
			try {
				return this;
			} catch (Exception ex) {
				ex.printStackTrace();
                                return null;
			}
			//return null;
		}

    /**
     *
     * @param instances
     */
    public abstract void BuildClassifier(Instances instances);

        public  double  ClassifyInstance(Instance instance)
        {
            double d;
            d=Double.parseDouble( instance.toString());
            return d;
        }

		public  double[] DistributionForInstance(Instance instance) throws Exception
		{

			double[] dist = new double[instance.NumClasses()];

			switch ((instance.ClassAttribute().Type())) {
				case core.Attribute.NOMINAL:
					double classification = 0;

					classification = ClassifyInstance(instance);
					if ((classification == Double.NaN)) {
						return dist;
					} else {
						dist[(int)classification] = 1.0;
					}
					return dist;
				case core.Attribute.NUMERIC:
					dist[0] = ClassifyInstance(instance);
					return dist;
				default:
					return dist;
			}
			//return dist;
		}

        //private double ClassifyInstance(Instance instance)
        //{
        //    throw new NotImplementedException();
        //}

		public String[] GetOptions()
		{
			String[] options = null;
			if ((GetDebug())) {
				options = new String[1];
                                options[1]="-D";
			} else {
				options = new String[1];
			}
			return options;
		}

		public void SetDebug(boolean  debug)
		{
			m_Debug = debug;
		}

		public boolean  GetDebug()
		{
			return m_Debug;
		}

		public  core.Enumeration listOptions()
		{
			FastVector v = new FastVector(1);
        try {
            v.AddElement(new ParameterOption("If set, classifier is run in debuging mode and " + "may output additional info to the console", "D", 0, "-D"));
            return v.Elements();
        } catch (Exception ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        }
	   return null;		
		}

		public  void SetOptions(String[] options)
		{
			SetDebug(utils.Utils1.GetFlag("D", options));
		}
		public  String GlobalInfo()
		{
			return "Information about the classifier";
		}

        //public double ClassifyInstance(Instance inst)
        //{
        //    throw new NotImplementedException();
        //}

}
