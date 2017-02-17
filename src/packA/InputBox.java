package packA;
// A Class to take input from a text-box in a java frame and store it for future use.

import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;


public class InputBox extends JPanel{ 	

	public InputBox(){
		input = new JTextField(20); 						// Text field that will be used for input.
		input.addActionListener(new TextProcess()); 		// Add our custom ActionListener.
		add(input);	// Add 'input' to the JPanel.
		input.setText("");
	}


	private class TextProcess implements ActionListener{
		public void  actionPerformed(ActionEvent e){
			lastCommand = input.getText(); 					// Set String 'lastCommand' to hold the text currently in JTextField.
			input.setText("");							// Wipe the JTextField.
		}
	}


	//Function instantiates our output.
	public void setOutput(JTextArea newOutput){
		output = newOutput; 
	}

	public String getCommand(){
		String tmpCommand = lastCommand;
		lastCommand = null;
		return tmpCommand;
	}

	public JTextField input;
	public JTextArea output;
	private String lastCommand;
}


