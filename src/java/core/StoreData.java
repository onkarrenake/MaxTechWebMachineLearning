/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

//import myfinalproject.MyDataSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.StringTokenizer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ganesh
 */
public class StoreData {
    public    FastVector m_Attributes=new  FastVector();
		//a StreamReader to read the target .arff file
  int matchcnt=0;
  int cnt=0;		//protected BufferedReader m_BufferReader;
		//check whether the reader should start to read instances data

		//protected boolean  m_IsInDataArea = false;
		//stores the relation name

		//protected   String m_RelationName = null;
		//stores the instances that have been read

		public   FastVector m_Instances = new FastVector();
		// init a new ArffReader instance
		
                public StoreData(DefaultTableModel dt,String[] AttName,ArrayList<ArrayList<String>> AttType)
                {
                    setAtt(AttName,AttType);
                    BuildData(dt);
                }
                
                public void  BuildData(DefaultTableModel dt)
                {
                    String st="";
                    for(int i=0;i<dt.getRowCount();i++)
                    {
                        st="";
                    for(int j=0;j<dt.getColumnCount();j++)
                    {
                        String val=(String)dt.getValueAt(i,j);
                        st=st+val+",";
                    }
                    st=st.substring(0, st.length()-1);
            try {
                ReadInstance(st);
            } catch (Exception ex) {
                Logger.getLogger(StoreData.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
                    }
                    System.out.println("Total = "+cnt );
                    System.out.println("Match = "+matchcnt);
                    
                }
                
                
    public void setAtt(String[] attName,ArrayList<ArrayList<String>> attType)
    {
        int i=0,j=0;
        try {
             Attribute at=null;
             FastVector fv;
            for(i=0; i<attType.size();i++)
            {
              fv=new FastVector();
             for(j=0;j<attType.get(i).size();j++) 
             {
             fv.AddElement(attType.get(i).get(j).trim());
             }
              at=new Attribute(attName[i].trim(),fv,i);
              m_Attributes.AddElement(at);
             }
             } catch (Exception ex) {
            Logger.getLogger(StoreData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                
    
    
    
    
    public void ReadInstance(String line) throws Exception
		{
			try {
				//line=line.trim();
                            
					StringTokenizer instValuesToken = new StringTokenizer(line, ",");
					int index = 0;

					Instance inst;// = new Instance();
					double[] instArray = new double[m_Attributes.Size()];
					//parsing an instance data
					while ((instValuesToken.hasMoreTokens())) {
						String val = instValuesToken.nextToken();//.trim();
                                                
						core.Attribute att = null;
						att = (core.Attribute)(m_Attributes.ElementAt(index));
                                                
						if ((att.IsNominal())) {
							int i = 0;
							int tarIndex = 0;

							for (i = 0; i <= (att.NumValues() - 1); i++) {
                                                            cnt++;
                                                            //System.out.println("att vAl= "+att.Value(i).trim());
                                                            //System.out.println("VAL= "+val);
								if ((att.Value(i).equals(val))) {
                                                                     matchcnt++;
									tarIndex = i;
                                                                      }
							}

							if ((val.contains("?"))) {
								instArray[index] = Double.NaN;
							} else {
								instArray[index] = Double.parseDouble(String.valueOf(tarIndex));
							}
							index = index + 1;
						}

						if ((att.IsNumeric())) {
							if ((val.contains("?"))) {
								instArray[index] = Double.NaN;
							} else {
								instArray[index] = Double.parseDouble(val);
							}

							index = index + 1;
						}
					}

					inst = new Instance(1, instArray);
                                       //System.out.println(inst);
					m_Instances.AddElement(inst);

				
			} catch (Exception ex) {
				ex.printStackTrace();
                                System.out.print("ARFFREADER READ INSTANCE ");
			}
		}

    FastVector get1Instances() {
        return this.m_Instances;
    }

    FastVector get1AttributesInfo() {
        return this.m_Attributes;
    }

}
