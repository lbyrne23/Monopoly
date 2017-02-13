package packA;

//import java.awt.BorderLayout;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
//import javax.swing.SwingUtilities;

import packA.showTable;
import packA.InputBox;

public class Layout {
	static public void main(String[] args){
		
		
		JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
		frame.setLayout(new BorderLayout());
		showTable monPanel = new showTable();	//panel for displaying board and players
		
		InputBox inputPanel = new InputBox();	// panel which takes in text
		
		JTextArea output = new JTextArea(5, 20);	//text area for message display
		output.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(output); //Create JScrollPane for viewing text area
		inputPanel.setOutput(output); //set 'output' to be the designated output destination for text input.
																			
		
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(inputPanel, BorderLayout.SOUTH);
		frame.add(scrollPane, BorderLayout.EAST);
		frame.add(monPanel);
		

		frame.setSize(1250, 750);
		frame.setVisible(true);	
		monPanel.sprint1Test();
	}
}