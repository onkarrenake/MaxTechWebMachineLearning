/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;


import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
//import myfinalproject.MyDataSet;
/**
 *
 * @author ganesh
 */
public class Instances {
		//The attribute information
		private FastVector m_Attributes;
		//The instances
		private FastVector m_Instances;
		//The class attribute's index
		private  int m_ClassIndex;
		

		public Instances(String[] AttName,ArrayList<ArrayList<String>> AttType,DefaultTableModel md) throws Exception
		{
			m_ClassIndex = -1;
			m_Instances = new FastVector(1000);

			//utils.io.ArffReader arff = new utils.io.ArffReader(file);
		         	
			//m_RelationName = arff.get1RelationName();
			//m_Attributes = arff.get1AttributesInfo();
			//FastVector dataset = arff.get1Instances();
                        StoreData std=new StoreData(md, AttName, AttType);
			m_Attributes = std.get1AttributesInfo();
			FastVector dataset = std.get1Instances();
                        //System.out.println(m_RelationName);
                        //System.out.println(m_Attributes);
                        //System.out.println(m_Instances);
			int i = 0;
			for (i = 0; i <= dataset.Size() - 1; i++) {
                            
				Instance inst = (Instance) dataset.ElementAt(i);
				
				Add(inst);

			}

		}


		public Instances(BufferedReader reader, int capacity)
		{
		}

		//
		//Constructor copying all instances and references to
		//the header information from the given set of instances.
		//*
		//@param instances the set to be copied
		//
		public Instances(Instances dataset) throws Exception 
		{
                     this(dataset, dataset.NumInstances());
			dataset.CopyInstances(0, this, dataset.NumInstances());
		}

		
		public Instances(Instances dataset, int capacity)
		{
			if ((capacity < 0)) {
				capacity = 0;
			}
			m_ClassIndex = dataset.m_ClassIndex;
			m_Attributes = dataset.m_Attributes;
			m_Instances = new FastVector(capacity);
		}
		
		public Instances(Instances source, int first, int toCopy)  throws Exception
		{
                        this(source, toCopy);
			if (((first < 0) | ((first + toCopy) > source.NumInstances()))) {
				throw new Exception("Parameters first and/or toCopy out " + "of range");
			}
			source.CopyInstances(first, this, toCopy);
		}
		
        public Instances(String name, FastVector attInfo, int capacity) throws Exception
		{
			m_ClassIndex = -1;
			m_Attributes = attInfo;
			int i = 0;
			for (i = 0; i <= NumAttributes() - 1; i++) {
				Attribute(i).SetIndex(i);
			}
			m_Instances = new FastVector(capacity);
		}

		public void Add(Instance inst) throws Exception
		{
			Instance newInst = new Instance(inst);
			newInst.SetDataset(this);
			m_Instances.AddElement(newInst);
		}
		
        public Attribute Attribute(int index) throws Exception
		{
			return (Attribute)m_Attributes.ElementAt(index);
		}
		///
		//   Returns an attribute given its name. If there is more than
		//   one attribute with the same name, it returns the first one.
		//   Returns null if the attribute can't be found.
		//  
		//   @param name the attribute's name
		//   @return the attribute with the given name, null if the
		//   attribute can't be found
		//  / 
		public Attribute Attribute(String name) throws Exception
		{
			int i = 0;
			for (i = 0; i <= NumAttributes() - 1; i++) {
				if ((Attribute(i).Name().equals(name))) {
					return Attribute(i);
				}
			}

			return null;
		}
		///
		//  Checks for string attributes in the dataset
		// 
		//  @return true if string attributes are present, false otherwise
		// /
		public boolean  CheckForStringAttributes() throws Exception
		{
			int i = 0;
			while ((i < m_Attributes.Size() - 1)) {
				i = i + 1;
				if((Attribute(i).IsStrings())) {
					return true;
				}
			}
			return false;
		}
		///
		//  Returns the class attribute.
		// 
		//  @return the class attribute
		//  @exception UnassignedClassException if the class is not set
		// /
		// @ requires classIndex() >= 0;
		public Attribute ClassAttribute() throws Exception
		{
			if ((m_ClassIndex < 0)) {
				throw new Exception("Class index is negative (not set)!");
			}
			return Attribute(m_ClassIndex);
		}
		///
		//   Returns the class attribute's index. Returns negative number
		//   if it's undefined.
		//  
		//   @return the class index as an integer
		//  /
		//   ensures \result == m_ClassIndex;
		public int ClassIndex()
		{
			return m_ClassIndex;
		}
		///
		//   Compactifies the set of instances. Decreases the capacity of
		//   the set so that it matches the number of instances in the set.
		//  /
		public void Compactify()
		{
			m_Instances.TrimToSize();
		}

		///
		//   Removes an instance at the given position from the set.
		//  
		//   @param index the instance's position
		//  /
		//  @ requires 0 <= index && index < numInstances();
		public void Delete(int index)
		{
			m_Instances.RemoveElementAt(index);
		}

		///
		//   Removes all instances with missing values for a particular
		//   attribute from the dataset.
		//  
		//   @param attIndex the attribute's index
		//  /
		//  @ requires 0 <= attIndex && attIndex < numAttributes();

		public void DeleteWithMissing(int attIndex) throws Exception
		{
			FastVector newInstances = new FastVector(NumInstances());
			int i = 0;
			for (i = 0; i <= NumInstances() - 1; i++) {
				if ((!GetInstance(i).IsMissing(attIndex))) {
					newInstances.AddElement(GetInstance(i));
				}
			}
			m_Instances = newInstances;
		}
		///
		//   Removes all instances with missing values for a particular
		//   attribute from the dataset.
		//  
		//   @param att the attribute
		//  /
		public void DeleteWithMissing(Attribute att) throws Exception
		{
			DeleteWithMissing(att.Index());
		}

		///
		//   Removes all instances with a missing class value
		//   from the dataset.
		//  
		//   @exception UnassignedClassException if class is not set
		//  /
		public void DeleteWithMissingClass() throws Exception
		{
			if ((m_ClassIndex < 0)) {
				throw new Exception("Class index is negative (not set)!");
			}
			DeleteWithMissing(m_ClassIndex);
		}

		///
		//  Returns an enumeration of all the attributes.
		// 
		//  @return enumeration of all the attributes.
		// /
		public core. Enumeration EnumerateAttributes() throws Exception
		{
			return m_Attributes.Elements(m_ClassIndex);
		}
		///
		//   Returns an enumeration of all instances in the dataset.
		//  
		//   @return enumeration of all instances in the dataset
		//  /
		public core.Enumeration EnumerateInstances() throws Exception
		{
			return m_Instances.Elements();
		}
		///
		//  Returns the first instance in the set.
		// 
		//  @return the first instance in the set
		// /
		// @ requires numInstances() > 0;
		public Instance FirstInstance()
		{
			return (Instance)m_Instances.FirstElement();
		}

		//  /
		// Returns the instance at the given position.
		//
		// @param index the instance's index
		// @return the instance at the given position
		///
		//@ requires 0 <= index;
		//@ requires index < numInstances();
		public Instance GetInstance(int index) throws Exception
		{
			return (Instance)m_Instances.ElementAt(index);
		}
		///
		//  Returns the last instance in the set.
		// 
		//  @return the last instance in the set
		// /
		// @ requires numInstances() > 0;
		public Instance LastInstance()
		{
			return (Instance)m_Instances.LastElement();
		}
		///
		//   Returns the number of attributes.
		//  
		//   @return the number of attributes as an integer
		//  /
		//  @ ensures \result == m_Attributes.size();
		public int NumAttributes()
		{
			return m_Attributes.Size();
		}

		///
		//   Returns the number of class labels.
		//  
		//   @return the number of class labels as an integer if the class 
		//   attribute is nominal, 1 otherwise.
		//   @exception UnassignedClassException if the class is not set
		//  /
		//  @ requires classIndex() >= 0;
		public int NumClasses() throws Exception
		{
			if ((m_ClassIndex < 0)) {
				throw new Exception("Class index is negative (not set)!");
			}
			if ((!ClassAttribute().IsNominal())) {
				return 1;
			} else {
				return ClassAttribute().NumValues();
			}
		}
		///
		//   Returns the number of instances in the dataset.
		//  
		//   @return the number of instances in the dataset as an integer
		//  /
		//  @ ensures \result == m_Instances.size();
		public int NumInstances()
		{
			return m_Instances.Size();
		}
		///
		//   Returns the relation's name.
		//  
		//   @return the relation's name as a string
		//  /
		//  @ ensures \result == m_RelationName;
		
		/// 
		//   Sets the class attribute.
		//  
		//   @param att attribute to be the class
		//  /
		public void SetClass(Attribute att)
		{
			m_ClassIndex = att.Index();
		}
		/// 
		//   Sets the class index of the set.
		//   If the class index is negative there is assumed to be no class.
		//   (ie. it is undefined)
		//  
		//   @param classIndex the new class index
		//   @exception IllegalArgumentException if the class index is too big or < 0
		//  /
		public void SetClassIndex(int classIndex) throws Exception
		{
			if ((classIndex >= NumAttributes())) {
				throw new Exception("Class index to large!");
			}
			m_ClassIndex = classIndex;
		}

		///
		//   Sets the relation's name.
		//  
		//   @param newName the new relation name.
		//  /
		
		///
		//   Stratifies a set of instances according to its class values 
		//   if the class attribute is nominal (so that afterwards a 
		//   stratified cross-validation can be performed).
		//  
		//   @param numFolds the number of folds in the cross-validation
		//   @exception UnassignedClassException if the class is not set
		//  /

		public void Stratify(int numFolds) throws Exception
		{
			Instance instance1 =new Instance();
			Instance instance2 = new Instance();
			int j = 0;
			int index = 0;
			if ((m_ClassIndex < 0)) {
				throw new Exception("Class index is negative (not set)!");
			}

			try {
				if ((ClassAttribute().IsNominal())) {
					index = 1;
					while ((index < NumInstances())) {
						instance1 = GetInstance(index - 1);
						for (j = index; j <= NumInstances() - 1; j++) {
							instance2 = GetInstance(j);
							if (((instance1.ClassValue() == instance2.ClassValue()) | (instance1.ClassIsMissing() & instance2.ClassIsMissing()))) {
								Swap(index, j);
								index = index + 1;
							}
						}
						index = index + 1;
					}
					StratStep(numFolds);
				}
			} catch (Exception ex) {
				throw new Exception(ex.toString());
			}


		}
		///
		//   Creates the test set for one fold of a cross-validation on 
		//   the dataset.
		//  
		//   @param numFolds the number of folds in the cross-validation. Must
		//   be greater than 1.
		//   @param numFold 0 for the first fold, 1 for the second, ...
		//   @return the test set as a set of weighted instances
		//   @exception IllegalArgumentException if the number of folds is less than 2
		//   or greater than the number of instances.
		//  /
		//  @ requires 2 <= numFolds && numFolds < numInstances();
		//  @ requires 0 <= numFold && numFold < numFolds;
		public Instances TestCV(int numFolds, int numFold) throws Exception
		{
			int numInstForFold = 0;
			int first = 0;
			int offset = 0;
			Instances test = null;
			if ((numFolds < 2)) {
				throw new Exception("Number of folds must be at least 2!");
			}
			if ((numFolds > NumInstances())) {
				throw new Exception("Can't have more folds than instances!");
			}
			numInstForFold = NumInstances() / numFolds;
			if ((numFold < NumInstances() % numFolds)) {
				numInstForFold = numInstForFold + 1;
				offset = numFold;
			} else {
				offset = NumInstances() % numFolds;
			}
			test = new Instances(this, numInstForFold);
			first = numFold * (NumInstances() / numFolds) + offset;
			CopyInstances(first, test, numInstForFold);
			return test;
		}
		///
		//   Creates the training set for one fold of a cross-validation 
		//   on the dataset. 
		//  
		//   @param numFolds the number of folds in the cross-validation. Must
		//   be greater than 1.
		//   @param numFold 0 for the first fold, 1 for the second, ...
		//   @return the training set 
		//   @exception IllegalArgumentException if the number of folds is less than 2
		//   or greater than the number of instances.
		//  /
		//  @ requires 2 <= numFolds && numFolds < numInstances();
		//  @ requires 0 <= numFold && numFold < numFolds;
		public Instances TrainCV(int numFolds, int numFold) throws Exception
		{
			int numInstForFold = 0;
			int first = 0;
			int offset = 0;
			Instances train = null;
			if ((numFolds < 2)) {
				throw new Exception("Number of folds must be at least 2!");
			}
			if ((numFolds > NumInstances())) {
				throw new Exception("Can't have more folds than instances!");
			}
			numInstForFold = NumInstances() / numFolds;
			if ((numFold < NumInstances() % numFolds)) {
				numInstForFold = numInstForFold + 1;
				offset = numFold;
			} else {
				offset = NumInstances() % numFolds;
			}
			train = new Instances(this, numInstForFold);
			first = numFold * (NumInstances() / numFolds) + offset;
			CopyInstances(0, train, first);
			CopyInstances(first + numInstForFold, train, NumInstances() - first - numInstForFold);
			return train;
		}
		///
		//   Creates the training set for one fold of a cross-validation 
		//   on the dataset. The data is subsequently randomized based
		//   on the given random number generator.
		//  
		//   @param numFolds the number of folds in the cross-validation. Must
		//   be greater than 1.
		//   @param numFold 0 for the first fold, 1 for the second, ...
		//   @param random the random number generator
		//   @return the training set 
		//   @exception IllegalArgumentException if the number of folds is less than 2
		//   or greater than the number of instances.
		//  /
		//  @ requires 2 <= numFolds && numFolds < numInstances();
		//  @ requires 0 <= numFold && numFold < numFolds;
		public Instances TrainCV(int numFolds, int numFold, Random random) throws Exception
		{
			Instances train = TrainCV(numFolds, numFold);
			train.Randomize(random);
			return train;
		}
		///
		//   Help function needed for stratification of set.
		//  
		//   @param numFolds the number of folds for the stratification
		//  /
		private void StratStep(int numFolds) throws Exception
		{
			FastVector newVec = new FastVector(m_Instances.Capacity());
			int start = 0;
			int j = 0;
			while ((newVec.Size() < NumInstances())) {
				j = start;
				while ((j < NumInstances())) {
					newVec.AddElement(GetInstance(j));
					j = j + numFolds;

				}
				start = start + 1;
			}
			m_Instances = newVec;
		}

		///
		//   Swaps two instances in the set.
		//  
		//   @param i the first instance's index
		//   @param j the second instance's index
		//  /
		//  @ requires 0 <= i && i < numInstances();
		//  @ requires 0 <= j && j < numInstances();
		private void Swap(int i, int j)
		{
			m_Instances.Swap(i, j);
		}
		//  /
		// Copies instances from one set to the end of another 
		// one.
		//
		// @param source the source of the instances
		// @param from the position of the first instance to be copied
		// @param dest the destination for the instances
		// @param num the number of instances to be copied
		///
		//@ requires 0 <= from && from <= numInstances() - num;
		//@ requires 0 <= num;
		private void CopyInstances(int from, Instances dest, int num) throws Exception
		{
			int i = 0;
			for (i = 0; i <= num - 1; i++) {
				dest.Add(GetInstance(from + i));
			}
		}
		///
		//   Partitions the instances around a pivot. Used by quicksort and
		//   kthSmallestValue.
		//  
		//   @param attIndex the attribute's index
		//   @param left the first index of the subset 
		//   @param right the last index of the subset 
		//  
		//   @return the index of the middle element
		//  /
		//  @ requires 0 <= attIndex && attIndex < numAttributes();
		//  @ requires 0 <= left && left <= right && right < numInstances();
		protected int Partition(int attIndex, int l, int r) throws Exception
		{

			//in java ((int)(3+4)/2) is 3, but in vb (3+4)/2 is 4.
			//we need to use (l+r)\2 not (l+r)/2
			double pivot = GetInstance((l + r) / 2).Value(attIndex);

			while ((l < r)) {
				while (((GetInstance(l).Value(attIndex) < pivot) & (l < r))) {
					l = l + 1;
				}
				while (((GetInstance(r).Value(attIndex) > pivot) & (l < r))) {
					r = r - 1;
				}
				if ((l < r)) {
					Swap(l, r);
					l = l + 1;
					r = r - 1;
				}
			}
			if (((l == r) & (GetInstance(r).Value(attIndex) > pivot))) {
				r = r - 1;
			}

			return r;
		}
		///
		//   Implements quicksort according to Manber's "Introduction to
		//   Algorithms".
		//  
		//   @param attIndex the attribute's index
		//   @param left the first index of the subset to be sorted
		//   @param right the last index of the subset to be sorted
		//  /
		//  @ requires 0 <= attIndex && attIndex < numAttributes();
		//  @ requires 0 <= first && first <= right && right < numInstances();

		protected void Quicksort(int attIndex, int left, int right) throws Exception
		{
			if ((left < right)) {
				int middle = Partition(attIndex, left, right);
				Quicksort(attIndex, left, middle);
				Quicksort(attIndex, middle + 1, right);
			}
		}
		///
		//   Sorts the instances based on an attribute. For numeric attributes, 
		//   instances are sorted into ascending order. For nominal attributes, 
		//   instances are sorted based on the attribute label ordering 
		//   specified in the header. Instances with missing values for the 
		//   attribute are placed at the end of the dataset.
		//  
		//   @param att the attribute
		//  /
		public void Sort(core.Attribute att) throws Exception
		{
			Sort((int)att.Index());
		}
		///
		//   Sorts the instances based on an attribute. For numeric attributes, 
		//   instances are sorted in ascending order. For nominal attributes, 
		//   instances are sorted based on the attribute label ordering 
		//   specified in the header. Instances with missing values for the 
		//   attribute are placed at the end of the dataset.
		//  
		//   @param attIndex the attribute's index
		//  /
		public void Sort(int attIndex) throws Exception
		{
			int i = 0;
			int j = 0;
			//move all instances with missing values to end
			j = NumInstances() - 1;
			i = 0;

			while ((i <= j)) {
				if ((GetInstance(j).IsMissing(attIndex))) {
					j = j - 1;
				} else {
					if ((GetInstance(i).IsMissing(attIndex))) {
						Swap(i, j);
						j = j - 1;
					}
					i = i + 1;
				}
			}
			Quicksort(attIndex, 0, j);
		}
		// 
		//Shuffles the instances in the set so that they are ordered 
		//randomly.
		//
		//@param random a random number generator
		//
		public void Randomize(Random random)
		{
			int j = 0;
			for (j = NumInstances() - 1; j >= 0; j += -1) {
				Swap(j, random.nextInt(j + 1));
			}

		}
		//
		//   Returns the mean (mode) for a numeric (nominal) attribute as
		//   a floating-point value. Returns 0 if the attribute is neither nominal nor 
		//   numeric. If all values are missing it returns zero.
		//  
		//   @param attIndex the attribute's index
		//   @return the mean or the mode
		//  
		public double MeanOrMode(int attIndex) throws Exception
		{
			double result = 0;
			double found = 0;
			double[] counts = null;
			int j = 0;
			if ((Attribute(attIndex).IsNumeric())) {
                result = 0;
                    found = 0;
				for (j = 0; j <= NumInstances() - 1; j++) {
					found = found + GetInstance(j).Weight();
					result = result + GetInstance(j).Weight() * GetInstance(j).Value(attIndex);
				}
				if ((utils.Utils1.Eq(found, 0))) {
					return 0;
				} else {
					return result / found;
				}

			} else if ((Attribute(attIndex).IsNominal())) {
				counts = new double[Attribute(attIndex).NumValues()];
				for (j = 0; j <= NumInstances() - 1; j++) {
					if ((!GetInstance(j).IsMissing(attIndex))) {
						counts[(int)GetInstance(j).Value(attIndex)] += GetInstance(j).Weight();

					}
				}
				return (double)utils.Utils1.MaxIndex(counts);
			} else {
				return 0;
			}
		}
		
		public double MeanOrMode(core.Attribute att) throws Exception
		{
			return MeanOrMode((int)att.Index());
		}

}
