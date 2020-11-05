/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 *
 * @author ganesh
 */
public class ParameterOption {

      //What does this option do?
        private String m_Description;
        //The synopsis.
        private String m_Synopsis;
        //What's the option's name?
        private String m_Name;
        //How many arguments does it take? 
        private int m_NumArguments;
        //
        // Creates new option with the given parameters.
        //   
        // @param description the option's description
        // @param name the option's name
        // @param numArguments the number of arguments
        //

        public ParameterOption(String description, String name, int numArguments, String synopsis)
        {
            m_Description = description;
            m_Name = name;
            m_NumArguments = numArguments;
            m_Synopsis = synopsis;

        }
        //
        // Returns the option's name.
        //
        // @return the option's name
        //
        public String Name()
        {

            return m_Name;
        }

        //
        // Returns the option's number of arguments.
        // 
        // @return the option's number of arguments
        // 
        public int NumArguments()
        {

            return m_NumArguments;
        }

        //
        // Returns the option's synopsis.
        // 
        // @return the option's synopsis
        // 
        public String Synopsis()
        {
            return m_Synopsis;
        }
        //
        // Returns the option's description.
        //   
        // @return the option's description
        //
        public String Description()
        {
            return m_Description;
        }

    
}
