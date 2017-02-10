package packA;
// A Class to take input from a text-box in a java frame and store it for future use.
// Team : Cessna Skyhawk
// Michael Jordan
// Lucy Byrne
// Fiachra Dunn
//

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.*;

public class InputBox{ 	

	public InputBox(){
		input = new JTextField();
		input.addActionListener(new TextProcess());
		//		add(input);

	}

	private class TextProcess implements ActionListener{
		public void  actionPerformed(ActionEvent e){
			lastCommand = input.getText();
			input.setText(null);
			System.out.println(lastCommand);

		}
	}

	public JTextField getBox(){
		return input;
	}


	public JTextField input;
	private String lastCommand;

}


