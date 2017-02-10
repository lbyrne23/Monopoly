package packA;
// A Class to take input from a text-box in a java frame and store it for future use.
// Team : Cessna Skyhawk
// Michael Jordan
// Lucy Byrne
// Fiachra Dunn
//

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.event.*;

public class InputBox{ 	
	
	public InputBox(){
		input = new JTextField();
		output = new JTextArea();
		output.setEditable(false);
		input.addActionListener(new TextProcess());
		
	}
	
	private class TextProcess implements ActionListener{
		public void  actionPerformed(ActionEvent e){
			lastCommand = input.getText();
			input.setText(null);
			output.append(lastCommand + "\n");
			
		}
	}
	
	public JTextField getBox(){
		return input;
	}
	
	
	public JTextField input;
	public JTextArea output;
	private String lastCommand;
	
}


