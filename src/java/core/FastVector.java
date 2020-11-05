/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 *
 * @author ganesh
 */
public class FastVector implements core.Copyable{

    	// The capacity increment
		private int m_CapacityIncrement = 1;
		// The capacity multiplier
		private int m_CapacityMultiplier = 2;
		// The array of objects
		private Object[] m_Objects;
		// The current size

		private int m_Size = 0;
		//'
		//Constructs an empty vector with initial
		//capacity zero.
		//'
		public FastVector()
		{
			m_Objects = new Object[1];
		}
		//'
		//Constructs a vector with the given capacity.
		//
		// @param capacity the vector's initial capacity
		//
		// @requires capacity >= 0;
		public FastVector(int capacity)
		{
			m_Objects = new Object[capacity];
		}
		//Produces a shallow copy of this vector
		public Object Copy()
		{
			FastVector newCopy = new FastVector(m_Objects.length);
			newCopy.m_Size = m_Size;
			newCopy.m_CapacityIncrement = m_CapacityIncrement;
			newCopy.m_CapacityMultiplier = m_CapacityMultiplier;
			try {
				Copy123(m_Objects, 0, newCopy.m_Objects, 0, m_Size);

			} catch (Exception ex) {
			}
			return newCopy;

		}
		//
		// Adds an element to this vector. Increases its
		// capacity if its not large enough.
		// 
		// @param element the element to add
		// 

		public void AddElement(Object element) throws Exception
		{
			try {
				Object[] newObjects = null;

				if ((this.m_Size == m_Objects.length)) {
					newObjects = new Object[m_CapacityMultiplier * (m_Objects.length + m_CapacityIncrement) + 1];
					Copy123(m_Objects, 0, newObjects, 0, this.m_Size);
					m_Objects = newObjects;
				}

				m_Objects[m_Size] = element;
				m_Size = m_Size + 1;
			} catch (Exception ex) {
				throw new Exception(ex.toString());
			}

		}
		// 
		//Appends all elements of the supplied vector to this vector.
		//
		//@param toAppend the FastVector containing elements to append.
		//
		public void AppendElements(FastVector toAppend)
		{
			SetCapacity(Size() + toAppend.Size());
			Copy123(toAppend.m_Objects, 0, m_Objects, Size(), toAppend.Size());
			m_Size = m_Objects.length;
		}
		//
		// Returns the capacity of the vector.
		// 
		// @return the capacity of the vector
		// 
		// @ ensures \result == m_Objects.length;
		public int Capacity()
		{
			return m_Objects.length;
		}

		public boolean  Contains(Object o)
		{

			if ((o == null)) {
				return false;
			}
			int i = 0;
			for (i = 0; i <= m_Objects.length - 1; i++) {
				if ((o.equals(m_Objects[i]))) {
					return true;
				}
			}
			return false;
		}

		//
		// Clones the vector and shallow copies all its elements.
		// The elements have to implement the Copyable interface.
		// 
		// @return the new vector
		// 
		public Object CopyElements()
		{
			core.FastVector newCopy = new core.FastVector(m_Objects.length);
			newCopy.m_Size = m_Size;
			newCopy.m_CapacityIncrement = m_CapacityIncrement;
			newCopy.m_CapacityMultiplier = m_CapacityMultiplier;

			try {
				Copy123(m_Objects, 0, newCopy.m_Objects, 0, m_Size);

				int i = 0;
				for (i = 1; i <= m_Size; i++) {
					newCopy.m_Objects[i] = m_Objects[i];
				}

			} catch (Exception ex) {
			}


			return newCopy;
		}
		//
		// Returns the element at the given position.
		//
		// @param index the element's index
		// @return the element with the given index
		// 
		// @ requires 0 <= index;
		// @ requires index < m_Objects.length;
		public Object ElementAt(int index) 
		{
			try {
                            
			   //System.out.println("elementAt "+m_Objects[index]);	
                            return m_Objects[index];
                                
			} catch (Exception ex) {
				ex.printStackTrace();
                                return null;
			}
                      
		}

		// 
		//Returns an enumeration of this vector.
		//
		//@return an enumeration of this vector
		//this
		public core.Enumeration Elements() 
		{
			try {                            
                            //System.out.print(this.getClass());
                            return new FastVectorEnumeration(this);
                            
                            //Enumeration ent =(Enumeration) new FastVectorEnumeration(this);
                              
				//return ent;
			} catch (Exception ex) {
			   // System.out.println("FastVector:Elements()");	
                            ex.printStackTrace();
                                
                             return null;                      
			}
		}
		//
		// Returns an enumeration of this vector, skipping the
		// element with the given index.
		// 
		// @param index the element to skip
		// @return an enumeration of this vector
		// 
		// @ requires 0 <= index && index < size();
		public core.Enumeration Elements(int index) 
		{
			try {
                               return new FastVectorEnumeration(this,index);
                              //  Enumeration ent =(Enumeration) new FastVectorEnumeration(this,index);
                              
				//return ent;
			} catch (Exception ex) {
			    //System.out.println("FastVector:Elements()");	
                            ex.printStackTrace();
                                
                                return null;
			}

		}
		//
		// Returns the first element of the vector.
		//
		// @return the first element of the vector
		// 
		// @ requires m_Size > 0;
		public Object FirstElement()
		{
			return m_Objects[0];
		}
		//
		// Searches for the first occurence of the given argument, 
		// testing for equality using the equals method. 
		// 
		// @param element the element to be found
		// @return the index of the first occurrence of the argument 
		// in this vector; returns -1 if the object is not found
		// 
		public int IndexOf(Object element)
		{
			int i = 0;
			for (i = 0; i <= m_Objects.length - 1; i++) {
				if ((element.equals(m_Objects[i]))) {
					return i;
				}
			}
			return -1;
		}
		//
		// Inserts an element at the given position.
		//
		// @param element the element to be inserted
		// @param index the element's index
		// 
		public void InsertElementAt(int element, int index)
		{
			Object[] newObjects = null;
			if ((m_Size < m_Objects.length)) {
				Copy123(m_Objects, index, m_Objects, index + 1, m_Size - index);
				m_Objects[index] = element;
			} else {
				newObjects = new Object[m_CapacityMultiplier * (m_Objects.length + m_CapacityIncrement) + 1];
				Copy123(m_Objects, 0, newObjects, 0, index);
				newObjects[index] = element;
				Copy123(m_Objects, index, newObjects, index + 1, m_Size - index);
				m_Objects = newObjects;
			}
			m_Size = m_Size + 1;
		}
		//
		// Returns the last element of the vector.
		// 
		// @return the last element of the vector
		// 
		// @ requires m_Size > 0;
		public Object LastElement()
		{
			return m_Objects[m_Size - 1];
		}
		//
		// Removes all components from this vector and sets its 
		// size to zero. 
		// 
		public void RemoveAllElements()
		{

			m_Objects = new Object[m_Objects.length];
			m_Size = 0;

		}
		//
		// Deletes an element from this vector.
		// 
		// @param index the index of the element to be deleted
		// 
		//  @ requires 0 <= index && index < m_Size;
		public void RemoveElementAt(int index)
		{
			Copy123(m_Objects, index + 1, m_Objects, index, m_Size - index - 1);
			m_Size = m_Size - 1;
		}
		//
		// Sets the vector's capacity to the given value.
		// 
		// @param capacity the new capacity
		// 
		public void SetCapacity(int capacity)
		{
			Object[] newObjects = null;
			newObjects = new Object[capacity];
			Copy123(m_Objects, 0, newObjects, 0, Math.min(capacity, m_Size));
			m_Objects = newObjects;
			if ((m_Objects.length < m_Size)) {
				m_Size = m_Objects.length;
			}
		}
		//
		// Sets the element at the given index.
		// 
		// @param element the element to be put into the vector
		// @param index the index at which the element is to be placed
		// 
		// @ requires 0 <= index && index < size();
		public void setElementAt(Object element, int index)
		{
			m_Objects[index] = element;
		}
		//
		// Returns the vector's current size.
		// 
		// @return the vector's current size
		// 
		// @ ensures \result == m_Size;
		public int Size()
		{
			return m_Size;
		}
		//
		// Swaps two elements in the vector.
		// 
		// @param first index of the first element
		// @param second index of the second element
		// 
		// @ requires 0 <= first && first < size();
		// @ requires 0 <= second && second < size();
		public void Swap(int first, int sec)
		{
			Object help = m_Objects[first];

			m_Objects[first] = m_Objects[sec];
			m_Objects[sec] = help;
		}
		//
		// Returns all the elements of this vector as an array
		// 
		// @param an array containing all the elements of this vector
		// 
		public Object[] ToArray()
		{

			Object[] newObjects = new Object[Size() + 1];
			Copy123(m_Objects, 0, newObjects, 0, Size());
			return newObjects;
		}
		//
		// Sets the vector's capacity to its size.
		// 
		public void TrimToSize()
		{
			Object[] newObjects = null;
			newObjects = new Object[m_Size + 1];
			Copy123(m_Objects, 0, newObjects, 0, m_Size);
			m_Objects = newObjects;
		}


        public  int size()
        {
            return m_Size;
        }
public void Copy123(Object[] a,int indx1,Object[] b ,int indx2,int length1)
                { 
                    int j=indx2;
                    for(int i=indx1;i<length1;i++)
                    {
                        b[i]=a[j];
                        j++;
                    }
                }
    
}
