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
		input = new JTextField(20);
		input.addActionListener(new TextProcess());
		add(input);
		
	}
	
	private class TextProcess implements ActionListener{
		public void  actionPerformed(ActionEvent e){
			lastCommand = input.getText();
			input.setText(null);
			output.append(lastCommand + "\n");
			
		}
	}
	
	
	public void setOutput(JTextArea newOutput){
		output = newOutput;
	}
	
	
	public JTextField input;
	public JTextArea output;
	private String lastCommand;
	
}


