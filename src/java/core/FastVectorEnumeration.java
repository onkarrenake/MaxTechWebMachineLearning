/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

//import com.sun.corba.se.spi.activation.Activator;
import com.sun.org.apache.bcel.internal.generic.Type;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author ganesh
 */
public class FastVectorEnumeration implements core.Enumeration {
    	//The counter
		private int m_Counter;
		//The vector
		private FastVector m_Vector;
		//Special element. Skipped during enumeration

		private int m_SpecialElement;
		// Constructs an enumeration.
		// 
		// @param vector the vector which is to be enumerated
		// 


public FastVectorEnumeration(FastVector vector)
		{
			m_Counter = 0;
                   m_Vector=vector;
                 m_SpecialElement = -1;
		}
		//
		// Constructs an enumeration with a special element.
		// The special element is skipped during the enumeration.
		// 
		// @param vector the vector which is to be enumerated
		// @param special the index of the special element
		// 
		// @ requires 0 <= special && special < vector.size();
		public FastVectorEnumeration(FastVector vector, int special)
		{
			m_Vector = vector;
                        //System.out.println("ASFASFASDF"+m_Vector);
			m_SpecialElement = special;
			if ((special == 0)) {
				m_Counter = 1;
			} else {
				m_Counter = 0;
			}
		}
		//
		// Tests if there are any more elements to enumerate.
		// 
		// @return true if there are some elements left
		// 
		public boolean  HasMoreElements()
		{
			if ((m_Counter < m_Vector.size())) {
				return true;
			}
			return false;
		}
		//
		// Returns the next element.
		// 
		// @return the next element to be enumerated
		// 
		// @ also requires hasMoreElements();
		public Object NextElement()
                {
		
        try {
            Object result;
            //System.out.println(m_Counter);
            result = m_Vector.ElementAt(m_Counter);
            m_Counter = m_Counter + 1;
			if ((m_Counter == m_SpecialElement)) {
				m_Counter = m_Counter + 1;
			}
            //System.out.println("Reault= "+result);
		     return  result;
            } catch (Exception ex) {
                ex.printStackTrace();
               //Logger.getLogger(FastVectorEnumeration.class.getName()).log(Level.SEVERE, null, ex);
               
            }
        
			return 0;
		}


}
