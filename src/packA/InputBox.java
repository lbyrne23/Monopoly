package packA;
// A Class to take input from a text-box in a java frame and store it for future use.
// Team : Cessna Skyhawk
// Michael Jordan
// Lucy Byrne
// Fiachra Dunn
//

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;


import java.awt.event.*;

public class InputBox extends JPanel{ 	
	
	public InputBox(){
		input = new JTextField(20); //Text field that will be used for input
		input.addActionListener(new TextProcess()); //add our custom ActionListener
		add(input);	//add 'input' to the Jpanel
		
	}
	
	private class TextProcess implements ActionListener{
		public void  actionPerformed(ActionEvent e){
			lastCommand = input.getText(); //set String 'lastCommand' to hold the text currently in JTextField
			input.setText(null);	//wipe the JTextField
			output.append(lastCommand + "\n");	//Append to the text currently in the JTextArea output
			
		}
	}
	
	
	//Function instantiates our output.
	public void setOutput(JTextArea newOutput){
		output = newOutput; 
	}
	
	
	public JTextField input;
	public JTextArea output;
	private String lastCommand;
	
}


