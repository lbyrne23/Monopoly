package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;

import packA.Board;
import packA.InputBox;

public class Layout {
	static public void main(String[] args){

		JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
		frame.setLayout(new BorderLayout());


		JTextArea output = new JTextArea(5, 20);								//Text area for message display.
		output.setEditable(false);
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		Board monPanel = new Board(6, output);									//Panel for displaying board and players.
		InputBox inputPanel = new InputBox();									//Panel which takes in text.
		inputPanel.setOutput(monPanel);									 		//Set 'output' to be the designated output destination for text input.

		JScrollPane scrollPane = new JScrollPane(output); 						//Create JScrollPane for viewing text area.
		


		//Adding panels to frame.
		frame.add(inputPanel, BorderLayout.SOUTH);								 
		frame.add(scrollPane, BorderLayout.EAST);								
		frame.add(monPanel);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1250, 750);											
		frame.setVisible(true);	




	}
}