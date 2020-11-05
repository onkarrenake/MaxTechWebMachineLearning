/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;
import utils.*;
import core.*;
		//
		//   Produces a shallow copy of this instance. The copy has
		//   access to the same dataset. (if you want to make a copy
		//   that doesn't have access to the dataset, use 
		//   <code>new Instance(instance)<code>
		//  
		//   @return the shallow copy
		//  
		//  @ also ensures \result != null;
		//  @ also ensures \result instanceof Instance;
		//  @ also ensures ((Instance)\result).m_Dataset == m_Dataset;
/**
 *
 * @author ganesh
 */
public class Instance implements core.Copyable{

    //Constant representing a missing value
		private static final double  MISSING_VALUE = Double.NaN;
		//
		//   The dataset the instance has access to.  Null if the instance
		//   doesn't have access to any dataset.  Only if an instance has
		//   access to a dataset, it knows about the actual attribute types.  
		//  
		private Instances m_Dataset;
		//The instance's attribute values
		private double[] m_AttValues;
		//The instance's weight
		private double m_Weight;
                public int OID;
		public  int m_OID;

    public Object Copy() {
        Instance result = new Instance(this);
			result.m_Dataset = m_Dataset;
			return result;
    }
		
		
		
		//
		//   Constructor that copies the attribute values and the weight from
		//   the given instance. Reference to the dataset is set to null.
		//   (ie. the instance doesn't have access to information about the
		//   attribute types)
		//  
		//   @param instance the instance from which the attribute
		//   values and the weight are to be copied 
		//  
		//  @ensures m_Dataset == null;
		public Instance(Instance newInstance)
		{
			m_AttValues = newInstance.m_AttValues;
			m_Weight = newInstance.m_Weight;
			m_Dataset = null;
		}
		//
		//   Constructor that inititalizes instance variable with given
		//   values. Reference to the dataset is set to null. (ie. the instance
		//   doesn't have access to information about the attribute types)
		//  
		//   @param weight the instance's weight
		//   @param attValues a vector of attribute values 
		//  
		//  @ ensures m_Dataset == null;
		public Instance(double weight, double[] attValues)
		{
			m_AttValues = attValues;
			m_Weight = weight;
			m_Dataset = null;
		}
                public Instance(){}
		//
		//   Constructor of an instance that sets weight to one, all values to
		//   be missing, and the reference to the dataset to null. (ie. the instance
		//   doesn't have access to information about the attribute types)
		//  
		//   @param numAttributes the size of the instance 
		//  
		//  @ requires numAttributes > 0;     Or maybe == 0 is okay too?
		//  @ ensures m_Dataset == null;
		public Instance(int numAttributes)
		{
			int i = 0;
			m_AttValues = new double[numAttributes];
			for (i = 0; i <= numAttributes - 1; i++) {
				m_AttValues[i] = MISSING_VALUE;
			}
			m_Weight = 1;
			m_Dataset = null;
		}
		//  
		// Returns the attribute with the given index.
		//
		// @param index the attribute's index
		// @return the attribute at the given position
		// @exception UnassignedDatasetException if instance doesn't have access to a
		// dataset
		// 
		//@ requires m_Dataset != null;
		public Attribute Attribute(int index) throws Exception
		{
			try {
				if ((m_Dataset == null)) {
					throw new Exception("Instance doesn't have access to a dataset!");
				}
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
			return m_Dataset.Attribute(index);
		}
		//
		//   Returns class attribute.
		//  
		//   @return the class attribute
		//   @exception UnassignedDatasetException if the class is not set or the
		//   instance doesn't have access to a dataset
		//  
		//  @ requires m_Dataset != null;
		public Attribute ClassAttribute() throws Exception
		{
			try {
				if ((m_Dataset == null)) {
					throw new Exception("Instance doesn't have access to a dataset!");
				}
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
			return m_Dataset.ClassAttribute();
		}
		//
		//   Returns an instance's class value in internal format. (ie. as a
		//   floating-point number)
		//  
		//   @return the corresponding value as a double (If the 
		//   corresponding attribute is nominal (or a string) then it returns the 
		//   value's index as a double).
		//   @exception UnassignedClassException if the class is not set or the instance doesn't
		//   have access to a dataset 
		//  
		//  @ requires classIndex() >= 0;
		public double ClassValue() throws Exception 
		{
			if ((ClassIndex() < 0)) {
				throw new Exception("Class is not set!");
			}
			return Value(ClassIndex());
		}
		//
		//   Returns the class attribute's index.
		//  
		//   @return the class index as an integer 
		//   @exception UnassignedDatasetException if instance doesn't have access to a dataset 
		//  
		//  @ requires m_Dataset != null;
		//  @ ensures  \result == m_Dataset.classIndex();
		public int ClassIndex() throws Exception
		{
			if ((m_Dataset == null)) {
				throw new Exception("Instance doesn't have access to a dataset!");
			}
			return m_Dataset.ClassIndex();
		}
		//
		//   Returns an instance's attribute value in internal format.
		//  
		//   @param attIndex the attribute's index
		//   @return the specified value as a double (If the corresponding
		//   attribute is nominal (or a string) then it returns the value's index as a 
		//   double).
		//  

		public double Value(int attIndex)
		{
			return m_AttValues[attIndex];
		}
		// 
		// Tests if an instance's class is missing.
		//
		// @return true if the instance's class is missing
		// @exception UnassignedClassException if the class is not set or the instance doesn't
		// have access to a dataset
		//
		//@ requires classIndex() >= 0;
		public boolean  ClassIsMissing() throws Exception
		{
			if ((ClassIndex() < 0)) {
				throw new Exception("Class is not set!");
			}
			return IsMissing(ClassIndex());
		}
		//
		//   Tests if a specific value is "missing".
		//  
		//   @param attIndex the attribute's index
		//  
		public boolean  IsMissing(int attIndex)
		{
			if ((Double.isNaN(m_AttValues[attIndex]))) {
				return true;
			}
			return false;
		}
		//
		//   Tests if a specific value is "missing".
		//   The given attribute has to belong to a dataset.
		//  
		//   @param att the attribute
		//  
		public boolean  IsMissing(Attribute att)
		{

			return IsMissing(att.Index());
		}
		//
		//   Returns the dataset this instance has access to. (ie. obtains
		//   information about attribute types from) Null if the instance
		//   doesn't have access to a dataset.
		//  
		//   @return the dataset the instance has accesss to
		//  
		//  @ ensures \result == m_Dataset;
		public Instances Dataset()
		{
			return m_Dataset;
		}

		//
		//   Returns an enumeration of all the attributes.
		//  
		//   @return enumeration of all the attributes
		//   @exception UnassignedDatasetException if the instance doesn't
		//   have access to a dataset 
		//  
		//  @ requires m_Dataset != null;
		public core.Enumeration EnumerateAttributes() throws Exception
		{
			if ((m_Dataset == null)) {
				throw new Exception("Instance doesn't have access to a dataset!");
			}
			return m_Dataset.EnumerateAttributes();
		}
		//
		//   Tests if the given value codes "missing".
		//  
		//   @param val the value to be tested
		//   @return true if val codes "missing"
		//  
		public static Object IsMissingValue(double val)
		{
			return Double.isNaN(val);
		}
		//
		//   Tests whether an instance has a missing value. Skips the class attribute if set.
		//   @return true if instance has a missing value.
		//   @exception UnassignedDatasetException if instance doesn't have access to any
		//   dataset
		//  
		//  @ requires m_Dataset != null;
		public boolean  HasMissingValue() throws Exception
		{
			if ((m_Dataset == null)) {
				throw new Exception("Instance does not access to a dataset");
			}
			int i = 0;
			for (i = 0; i <= NumAttributes() - 1; i++) {
				if ((i != ClassIndex())) {
					if ((IsMissing(i))) {
						return true;
					}
				}
			}
			return false;
		}
		//
		//   Returns the double that codes "missing".
		//  
		//   @return the double that codes "missing"
		//  
		public static double MissingValue()
		{
			return MISSING_VALUE;
		}
		//
		//   Returns the number of attributes.
		//  
		//   @return the number of attributes as an integer
		//  
		//  @ ensures \result == m_AttValues.length;
		public int NumAttributes()
		{
			return m_AttValues.length;
		}
		//
		//   Returns the number of class labels.
		//  
		//   @return the number of class labels as an integer if the 
		//   class attribute is nominal, 1 otherwise.
		//   @exception UnassignedDatasetException if instance doesn't have access to any
		//   dataset
		//  
		//  @ requires m_Dataset != null;

		public int NumClasses() throws Exception
		{
			if ((m_Dataset == null)) {
				throw new Exception("Instance doesn't have access to a dataset!");
			}
			return m_Dataset.NumClasses();
		}

		// 
		//   Replaces all missing values in the instance with the
		//   values contained in the given array. A deep copy of
		//   the vector of attribute values is performed before the
		//   values are replaced.
		//  
		//   @param array containing the means and modes
		//   @exception IllegalArgumentException if numbers of attributes are unequal
		//  
		public void ReplaceMissingValues(double[] array) throws Exception
		{
			if (((array == null) | (array.length != m_AttValues.length))) {
				throw new Exception("Unequal number of attributes!");
			}
			FreshAttributeVector();
			int i = 0;
			for (i = 0; i <= m_AttValues.length - 1; i++) {
				if ((IsMissing(i))) {
					m_AttValues[i] = array[i];
				}
			}
		}
		//
		//  Sets the class value of an instance to be "missing". A deep copy of
		//  the vector of attribute values is performed before the
		//  value is set to be missing.
		// 
		//  @exception UnassignedClassException if the class is not set
		//  @exception UnassignedDatasetException if the instance doesn't
		//  have access to a dataset
		// 
		// @ requires classIndex() >= 0;
		public void SetClassMissing() throws Exception
		{
			if ((ClassIndex() < 0)) {
				throw new Exception("Class is not set!");
			}
			FreshAttributeVector();
			SetMissing(ClassIndex());
		}
		//
		//  Sets the class value of an instance to the given value (internal
		//  floating-point format).  A deep copy of the vector of attribute
		//  values is performed before the value is set.
		// 
		//  @param value the new attribute value (If the corresponding
		//  attribute is nominal (or a string) then this is the new value's
		//  index as a double).  
		//  @exception UnassignedClassException if the class is not set
		//  @exception UnaddignedDatasetException if the instance doesn't
		//  have access to a dataset 
		// 
		// @ requires classIndex() >= 0;
		private void SetClassValue(double value) throws Exception
		{
			if ((ClassIndex() < 0)) {
				throw new Exception("Class is not set!");
			}
			FreshAttributeVector();
			m_AttValues[ClassIndex()] = value;
		}

		//
		//   Sets the class value of an instance to the given value. A deep
		//   copy of the vector of attribute values is performed before the
		//   value is set.
		//  
		//   @param value the new class value (If the class
		//   is a string attribute and the value can't be found,
		//   the value is added to the attribute).
		//   @exception UnassignedClassException if the class is not set
		//   @exception UnassignedDatasetException if the dataset is not set
		//   @exception IllegalArgumentException if the attribute is not
		//   nominal or a string, or the value couldn't be found for a nominal
		//   attribute 
		//  
		//  @ requires classIndex() >= 0;
		private void SetClassValue(String value) throws Exception
		{
			if ((ClassIndex() < 0)) {
				throw new Exception("Class is not set!");
			}
			SetValue(ClassIndex(), value);
		}
		//
		//   Sets the reference to the dataset. Does not check if the instance
		//   is compatible with the dataset. Note: the dataset does not know
		//   about this instance. If the structure of the dataset's header
		//   gets changed, this instance will not be adjusted automatically.
		//  
		//   @param instances the reference to the dataset 
		//  
		public void SetDataset(Instances instances)
		{
			m_Dataset = instances;
		}
		//
		//   Sets a specific value to be "missing". Performs a deep copy
		//   of the vector of attribute values before the value is set to
		//   be missing.
		//  
		//   @param attIndex the attribute's index
		//  
		public void SetMissing(int attIndex)
		{
			FreshAttributeVector();
			m_AttValues[attIndex] = MISSING_VALUE;
		}
		//
		//   Sets a specific value to be "missing". Performs a deep copy
		//   of the vector of attribute values before the value is set to
		//   be missing. The given attribute has to belong to a dataset.
		//  
		//   @param att the attribute
		//  

		public void SetMissing(Attribute att)
		{
			SetMissing(att.Index());
		}
		//
		//   Sets a specific value in the instance to the given value 
		//   (internal floating-point format). Performs a deep copy
		//   of the vector of attribute values before the value is set.
		//  
		//   @param attIndex the attribute's index 
		//   @param value the new attribute value (If the corresponding
		//   attribute is nominal (or a string) then this is the new value's
		//   index as a double).  
		//  
		public void SetValue(int attIndex, double value)
		{
			FreshAttributeVector();
			m_AttValues[attIndex] = value;
		}
		//
		//   Sets a value of a nominal or string attribute to the given
		//   value. Performs a deep copy of the vector of attribute values
		//   before the value is set.
		//  
		//   @param attIndex the attribute's index
		//   @param value the new attribute value (If the attribute
		//   is a string attribute and the value can't be found,
		//   the value is added to the attribute).
		//   @exception UnassignedDatasetException if the dataset is not set
		//   @exception IllegalArgumentException if the selected
		//   attribute is not nominal or a string, or the supplied value couldn't 
		//   be found for a nominal attribute 
		//  
		//  @ requires m_Dataset != null;
		public void SetValue(int attIndex, String value) throws Exception
		{
			int valIndex = 0;
			if ((m_Dataset == null)) {
				throw new Exception("Instance doesn't have access to a dataset!");
			}
			if (((!Attribute(attIndex).IsNominal()) & (!Attribute(attIndex).IsStrings()))) {
				throw new Exception("Attribute neither nominal nor string!");
			}
			valIndex = Attribute(attIndex).IndexOfValue(value);
			if ((valIndex == -1)) {
				if ((Attribute(attIndex).IsNominal())) {
					throw new Exception("Value not defined for given nominal attribute!");
				}
			} else {
				Attribute(attIndex).ForceAddValue(value);
				valIndex = Attribute(attIndex).IndexOfValue(value);
			}

			FreshAttributeVector();
			m_AttValues[attIndex] = valIndex;

		}
		//
		//   Sets a specific value in the instance to the given value
		//   (internal floating-point format). Performs a deep copy of the
		//   vector of attribute values before the value is set, so if you are
		//   planning on calling setValue many times it may be faster to
		//   create a new instance using toDoubleArray.  The given attribute
		//   has to belong to a dataset.
		//  
		//   @param att the attribute 
		//   @param value the new attribute value (If the corresponding
		//   attribute is nominal (or a string) then this is the new value's
		//   index as a double).  
		//  
		public void SetValue(Attribute att, double value)
		{
			FreshAttributeVector();
			m_AttValues[att.Index()] = value;
		}

		//
		// Sets a value of an nominal or string attribute to the given
		// value. Performs a deep copy of the vector of attribute values
		// before the value is set, so if you are planning on calling setValue many
		// times it may be faster to create a new instance using toDoubleArray.
		// The given attribute has to belong to a dataset.
		//
		// @param att the attribute
		// @param value the new attribute value (If the attribute
		// is a string attribute and the value can't be found,
		// the value is added to the attribute).
		// @exception IllegalArgumentException if the the attribute is not
		// nominal or a string, or the value couldn't be found for a nominal
		// attribute 
		//
		public void SetValue(Attribute att, String value) throws Exception
		{
			if (((!att.IsNominal()) & (!att.IsStrings()))) {
				throw new Exception("Attribute neither nominal nor string!");
			}
			int valIndex = att.IndexOfValue(value);
			if ((valIndex == -1)) {
				if ((att.IsNominal())) {
					throw new Exception("Value not defined for given nominal attribute!");
				}
			} else {
				att.ForceAddValue(value);
				valIndex = att.IndexOfValue(value);
			}


			FreshAttributeVector();
			m_AttValues[att.Index()] = valIndex;

		}

		//
		// Sets the weight of an instance.
		//
		// @param weight the weight
		//
		public void SetWeight(double weight)
		{
			m_Weight = weight;

		}

		//  
		// Returns the string value of a nominal, string, or date attribute
		// for the instance.
		//
		// @param attIndex the attribute's index
		// @return the value as a string
		// @exception IllegalArgumentException if the attribute is not a nominal,
		// string, or date attribute.
		// @exception UnassignedDatasetException if the instance doesn't belong
		// to a dataset.
		//
		//@ requires m_Dataset != null;
		public String StringValue(int attIndex) throws Exception
		{
			if ((m_Dataset == null)) {
				throw new Exception("Instance doesn't have access to a dataset!");
			}
			if (((!Attribute(attIndex).IsNominal()) & (!Attribute(attIndex).IsStrings()))) {
				throw new Exception("Attribute neither nominal nor string!");

			}

			return m_Dataset.Attribute(attIndex).Value((int)m_AttValues[attIndex]);
		}
		// 
		//   Returns the string value of a nominal, string, or date attribute
		//   for the instance.
		//  
		//   @param att the attribute
		//   @return the value as a string
		//   @exception IllegalArgumentException if the attribute is not a nominal,
		//   string, or date attribute.
		//   @exception UnassignedDatasetException if the instance doesn't belong
		//   to a dataset.
		//  
		public String StringValue(Attribute att) throws Exception
		{
			return StringValue(att.Index());
		}

		//
		//   Returns an instance's attribute value in internal format.
		//   The given attribute has to belong to a dataset.
		//  
		//   @param att the attribute
		//   @return the specified value as a double (If the corresponding
		//   attribute is nominal (or a string) then it returns the value's index as a
		//   double).
		//  
		public double Value(Attribute att)
		{
			return m_AttValues[att.Index()];
		}
		//
		//   Returns the instance's weight.
		//  
		//   @return the instance's weight as a double
		//  
		public double Weight()
		{
			return m_Weight;
		}
		//
		//   Deletes an attribute at the given position (0 to 
		//   numAttributes() - 1).
		//  
		//   @param pos the attribute's position
		//  

		public void ForceDeleteAttributeAt(int position)
		{
			double[] newValues = new double[m_AttValues.length];
			Copy123Double(m_AttValues, 0, newValues, 0, position);
			if ((position < m_AttValues.length - 1)) {
				Copy123Double(m_AttValues, position + 1, newValues, position, m_AttValues.length - (position + 1));

			}
			m_AttValues = newValues;
		}

		//
		//  Inserts an attribute at the given position
		//  (0 to numAttributes()) and sets its value to be missing. 
		// 
		//  @param pos the attribute's position
		// 
		public void ForceInsertAttributeAt(int position)
		{
			double[] newValues = new double[m_AttValues.length];
			Copy123Double(m_AttValues, 0, newValues, 0, position);
			newValues[position] = MISSING_VALUE;

			Copy123Double(m_AttValues, position, newValues, position + 1, m_AttValues.length - position);
			m_AttValues = newValues;
		}
		//
		//   Clones the attribute vector of the instance and
		//   overwrites it with the clone.
		//  
		private void FreshAttributeVector()
		{
			double[] newValues = null;
			newValues = new double[m_AttValues.length];
			Copy123Double(m_AttValues, 0, newValues, 0, m_AttValues.length);
			m_AttValues = newValues;
		}
		//
		//   Returns the description of one instance. If the instance
		//   doesn't have access to a dataset, it returns the internal
		//   floating-point values. Quotes string
		//   values that contain whitespace characters.
		//  
		//   @return the instance's description as a string
		//  
		public  @Override
String toString()
		{

			StringBuilder text = new StringBuilder();
			int i = 0;
			for (i = 0; i <= m_AttValues.length - 1; i++) {
				if ((i > 0)) {
					text.append(",");
				}
				text.append(this.Value(i));

			}
			return text.toString();
		}
		//
		//  Returns the description of one value of the instance as a 
		//  string. If the instance doesn't have access to a dataset it 
		//  returns the internal floating-point value. Quotes string
		//  values that contain whitespace characters, or if they
		//  are a question mark.
		//  The given attribute has to belong to a dataset.
		// 
		//  @param att the attribute
		//  @return the value's description as a string
		// 
		public String ToStringBy(int attIndex)
		{

			return String.valueOf(this.Value(attIndex));
		}


   public void Copy123Object(Object a[],int indx1,Object b[],int indx2,int length1)
                { 
                    int j=indx2;
                    for(int i=indx1;i<length1;i++)
                    {
                        b[i]=a[j];
                        j++;
                    }
                }
    public void Copy123Double(double a[],int indx1,double  b[],int indx2,int length1)
                { 
                    int j=indx2;
                    for(int i=indx1;i<length1;i++)
                    {
                        b[i]=a[j];
                        j++;
                    }
                }
}
