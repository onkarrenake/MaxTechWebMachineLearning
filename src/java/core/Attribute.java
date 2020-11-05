/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.xml.bind.annotation.XmlElement.DEFAULT;
import utils.Utils1;
/**
 *
 * @author ganesh
 */
public class Attribute implements Copyable {

    
    //Constant set for numeric attributes
		public static final  int NUMERIC = 0;
		//Constant set for nominal attributes
		public static  final  int NOMINAL = 1;
		//Constant set for attributes with string values
		public static final  int STRINGS = 2;
		//The attribute's name.
		private String m_Name;
		//The attribute's type
		private int m_Type;
		//The attribute's values
		private FastVector m_Values;
		//The attribute's index
		private int m_Index;
		//The attribute's split point (for numeric attribute) 

		private double m_BinarySplit;
		//
		// Produces a shallow copy of this attribute.
		// 
		// @return a copy of this attribute with the same index
		// 
		// @ also ensures \result instanceof Attribute;
		public Object Copy()
		{
			Attribute newCopy = new Attribute(m_Name);
			newCopy.m_Index = m_Index;
			if (((!IsNominal()) & (!IsStrings()))) {
				return newCopy;
			}
			newCopy.m_Type = m_Type;
			newCopy.m_Values = m_Values;
			return newCopy;
		}
		//
		// Constructor for a numeric attribute.
		//
		// @param attributeName the name for the attribute
		// 
		//  @ requires attributeName != null;
		//  @ ensures  m_Name == attributeName;
		public Attribute(String attributeName)
		{
			m_Name = attributeName;
			m_Index = -1;
			m_Values = null;
			m_Type = NUMERIC;
		}
		//
		// Constructor for nominal attributes and string attributes.
		// If a null vector of attribute values is passed to the method,
		// the attribute is assumed to be a string.
		// 
		// @param attributeName the name for the attribute
		// @param attributeValues a vector of strings denoting the 
		// attribute values. Null if the attribute is a string attribute.
		// 
		//  @ requires attributeName != null;
		//  @ ensures  m_Name == attributeName;
		public Attribute(String attributeName, FastVector attributeValues)
		{
			m_Name = attributeName;
			m_Index = -1;
			m_Values = attributeValues;

			if ((m_Values == null)) {
				m_Values = new FastVector();
				m_Type = STRINGS;
			} else {
				m_Type = NOMINAL;
			}

		}
		//
		// Constructor for a numeric attribute with a particular index.
		// 
		// @param attributeName the name for the attribute
		// @param index the attribute's index
		// 
		// @ requires attributeName != null;
		// @ requires index >= 0;
		// @ ensures  m_Name == attributeName;
		// @ ensures  m_Index == index;
		public Attribute(String attributeName, int index) 
		{
                     this(attributeName);
			m_Index = index;
		}
		//
		// Constructor for date attributes with a particular index.
		// 
		// @param attributeName the name for the attribute
		// @param dateFormat a string suitable for use with
		// SimpleDateFormatter for parsing dates.  Null for a default format
		// string.
		// @param index the attribute's index
		// 
		// requires attributeName != null;
		// requires index >= 0;
		// @ensures  m_Name == attributeName;
		// @ ensures  m_Index == index;
		public Attribute(String attributeName, FastVector attributeValues, int index) 
		{
                     this(attributeName, attributeValues);
			m_Index = index;
		}
		//
		// Returns an enumeration of all the attribute's values if
		// the attribute is nominal or a string, null otherwise. 
		// 
		// @return enumeration of all the attribute's values
		//  
		public core.Enumeration EnumerateValues() throws Exception
		{
			if ((IsNominal() | IsStrings())) {
				return  m_Values.Elements();
			}
			return null;
		}
		//
		// Tests if given attribute is equal to this attribute.
		// 
		// @param other the Object to be compared to this attribute
		// @return true if the given attribute is equal to this attribute
		// 
		public @Override
 boolean equals(Object other)
		{
			if (((other == null) | (other!=this))) {
				return false;
			}
			Attribute att = null;
			att = (Attribute)other;


			if ((!m_Name.equals(att.m_Name))) {
				return false;
			}
			if ((IsNumeric() & att.IsNumeric())) {
				return true;
			}
			if ((IsNumeric() | att.IsNumeric())) {
				return false;
			}
			if ((m_Values.Size() != att.m_Values.Size())) {
				return false;
			}

			int i = 0;
			for (i = 0; i <= m_Values.Size() - 1; i++) {
				//the following piece of code may cause bug ref:Attribute.java(L:177)
                            try{
				if ((!m_Values.ElementAt(i).toString().equals(att.m_Values.ElementAt(i).toString()))) {
					return false;
				}
                            }catch(Exception exx){exx.printStackTrace();}
			}
			return true;
		}
		//
		// Returns the index of this attribute.
		// 
		// @return the index of this attribute
		// 
		// @ ensures \result == m_Index;
		public int Index()
		{
			return m_Index;
		}
		//
		// Returns the index of a given attribute value. (The index of
		// the first occurence of this value.)
		// 
		// @param value the value for which the index is to be returned
		// @return the index of the given attribute value if attribute
		// is nominal or a string, -1 if it is numeric or the value 
		// can't be found
		// 

		public int IndexOfValue(String value)
		{
			if (((!IsNominal()) & (!IsStrings()))) {
				return -1;
			}
			return m_Values.IndexOf(value);
		}

		//
		// Test if the attribute is nominal.
		// 
		// @return true if the attribute is nominal
		// 
		// @ ensures \result <==> (m_Type == NOMINAL);
		public boolean  IsNominal()
		{

			if ((m_Type == NOMINAL)) {
				return true;
			}
			return false;
		}
		//
		// Tests if the attribute is numeric.
		// 
		// @return true if the attribute is numeric
		// 
		// @ ensures \result <==> ((m_Type == NUMERIC) || (m_Type == DATE));
		public boolean  IsNumeric()
		{

			if ((m_Type == NUMERIC)) {
				return true;
			}
			return false;
		}
		//
		// Tests if the attribute is a string.
		// 
		// @return true if the attribute is a string
		// 
		// @ ensures \result <==> (m_Type == STRING);
		public boolean  IsStrings()
		{

			if ((m_Type == STRINGS)) {
				return true;
			}
			return false;
		}
		//
		// Returns the attribute's name.
		//
		// @return the attribute's name as a string
		// 
		// @ ensures \result == m_Name;
		public String Name()
		{
			return m_Name;
		}
		//
		// Returns the number of attribute values. Returns 0 for numeric attributes.
		// 
		// @return the number of attribute values
		// 
		public int NumValues()
		{
			if (((!IsNominal()) & (!IsStrings()))) {
				return 0;
			} else {
				return m_Values.Size();
			}
		}
		//
		// Returns the attribute's type as an integer.
		// 
		// @return the attribute's type.
		// 
		// @ ensures \result == m_Type;
		public int Type()
		{
			return m_Type;
		}
		//
		// Returns a value of a nominal or string attribute. 
		// Returns an empty string if the attribute is neither
		// nominal nor a string attribute.
		//
		// @param valIndex the value's index
		// @return the attribute's value as a string
		// 
		public String Value(int valIndex) throws Exception
		{
			if (((!IsNominal()) & (!IsStrings()))) {
				return "";

			} else {
				return m_Values.ElementAt(valIndex).toString();
			}
		}
		//
		// Adds an attribute value. Creates a fresh list of attribute
		// values before adding it.
		// 
		// @param value the attribute value
		// 
		public void AddValue(String value) throws Exception
		{
			FreshAttributeValues();
			m_Values.AddElement(value);
		}
		//
		// Removes a value of a nominal or string attribute. Creates a 
		// fresh list of attribute values before removing it.
		// 
		// @param index the value's index
		// @exception IllegalArgumentException if the attribute is not nominal
		// 
		//  @ requires isNominal() || isString();
		//  @ requires 0 <= index && index < m_Values.size();

		public void Delete(int index) throws Exception
		{
			if (((!IsNominal()) & (!IsStrings()))) {
				Exception ex = new Exception("Can only remove value of nominal or strings attribute.");
				throw ex;
			} else {
				FreshAttributeValues();
				m_Values.RemoveElementAt(index);
			}
		}
		//
		// Adds an attribute value.
		// 
		// @param value the attribute value
		// 
		// @requires value != null;
		// @ensures  m_Values.size() == \old(m_Values.size()) + 1;
		public void ForceAddValue(String value) throws Exception
		{
			m_Values.AddElement(value);
		}
		//
		// Sets the index of this attribute.
		// 
		// @param the index of this attribute
		// 
		//  @ requires 0 <= index;
		//  @ assignable m_Index;
		//  @ ensures m_Index == index;
		public void SetIndex(int index)
		{
			m_Index = index;
		}
		//
		// Sets a value of a nominal attribute or string attribute.
		// Creates a fresh list of attribute values before it is set.
		// 
		// @param index the value's index
		// @param string the value
		// @exception IllegalArgumentException if the attribute is not nominal or 
		//   string.
		//   
		//  @ requires string != null;
		//  @ requires isNominal() || isString();
		//  @ requires 0 <= index && index < m_Values.size();
		public void SetValue(int index, String strings) throws Exception
		{
			if (((!IsNominal()) & (!IsStrings()))) {
				Exception ex = new Exception("Can only set value of nominal or strings attribute.");
				throw ex;
			} else {
				FreshAttributeValues();
				m_Values.setElementAt(strings, index);
			}
		}
		//
		// Adds an attribute value.
		//
		// @param value the attribute value
		// 
		//  @ requires value != null;
		//  @ ensures  m_Values.size() == \old(m_Values.size()) + 1;
		private void FreshAttributeValues()
		{
			m_Values = (FastVector)m_Values.Copy();
		}

		public void SetBinarySplit(double splitPoint) throws Exception
		{
			if ((this.IsNumeric() == true)) {
				m_BinarySplit = splitPoint;
			} else {
				throw new Exception("This function is for numeric attribute only");
			}
		}
		public double GetBinarySplit() throws Exception
		{
			if ((this.IsNumeric() == true)) {
				return m_BinarySplit;
			} else {
				throw new Exception("This function is for numeric attribute only");
			}
		}
		//
		// Returns a description of this attribute in ARFF format. Quotes
		// strings if they contain whitespace characters, or if they
		// are a question mark.
		// 
		// @return a description of this attribute as a string
		// 
		public @Override
 String toString()
		{
			StringBuilder text = new StringBuilder();

			text.append("@attribute " + Utils1.Quote(m_Name) + " ");

			if ((IsNominal())) {
				text.append("{");
				core.Enumeration en=new core.Enumeration() {

                public boolean hasMoreElements() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public Object nextElement() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public boolean HasMoreElements() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                public Object NextElement() {
                       
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
            try {
                en = (core.Enumeration) EnumerateValues();
            } catch (Exception ex) {
                Logger.getLogger(Attribute.class.getName()).log(Level.SEVERE, null, ex);
            }
				while ((en.HasMoreElements())) {
					text.append(Utils1.Quote(en.NextElement().toString()));
					if ((en.HasMoreElements())) {
						text.append(",");
					}
				}
				text.append("}");

			} else {
				if ((IsNumeric())) {
					text.append("numeric");
				} else {
					text.append("string");
				}
			}

			return text.toString();
		}


}
