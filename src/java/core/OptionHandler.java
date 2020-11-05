/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 *
 * @author ganesh
 */
public interface  OptionHandler {
//Returns an enumeration of all the available options..
		//@return an enumeration of all available options.
		Enumeration listOptions();


		//Sets the OptionHandler's options using the given list. All options
		// will be set (or reset) during this call (i.e. incremental setting
		// of options is not possible).

		//@param options the list of options as an array of strings
		//@exception Exception if an option is not supported

		//@ requires options is not nothing
		//@ requires \nonnullelements(options)
		void SetOptions(String[] options);

}
