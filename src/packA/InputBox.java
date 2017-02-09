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
		
	}
	
	private class TextProcess implements ActionListener{
		public void  actionPerformed(ActionEvent e){
			input.setText(null);
		}
	}
	
	public JTextField getBox(){
		return input;
	}
	
	public void actionPerformed(KeyEvent e) {
		input.setText(null);
	}

	
	public JTextField input;
	
}


