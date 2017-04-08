package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import packA.Board;
import packA.InputBox;

public class Layout {
	static public void main(String[] args){

		JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
		frame.setLayout(new BorderLayout());


		OutputBox outputPanel = new OutputBox();
		Board monPanel = new Board(6, outputPanel.getJTextArea());			//Panel for displaying board and players.
		monPanel.setLayout(null);
		InputBox inputPanel = new InputBox();								//Panel which takes in text.
		inputPanel.setOutput(monPanel);								 		//Set 'output' to be the designated output destination for text input.


		
		//Adding panels to frame.
		frame.add(inputPanel, BorderLayout.SOUTH);								 
		frame.add(outputPanel, BorderLayout.EAST);								
		frame.add(monPanel);



		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1250, 750);											
		frame.setVisible(true);	

	}
}