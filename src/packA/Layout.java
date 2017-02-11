package packA;

//import java.awt.BorderLayout;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.SwingUtilities;

import packA.showTable;
import packA.InputBox;

public class Layout {
	static public void main(String[] args){
		InputBox myTextInterface = new InputBox();
		JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
		JPanel monPanel = new showTable();	
		JPanel inputPanel = new JPanel();
		JPanel outputPanel = new JPanel();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		frame.setLocationRelativeTo(null);

		//		GridBagConstraints gbc = new GridBagConstraints();
		//		gbc.gridwidth = GridBagConstraints.REMAINDER;
		//		gbc.insets = new Insets(10, 10, 10, 10);
		//		gbc.fill = GridBagConstraints.HORIZONTAL;

		monPanel.setBounds(0, 0, 700, 704);
		inputPanel.setBounds(800, 0, 1250, 700);
		outputPanel.setBounds(0, 700, 1250, 750);

		frame.add(inputPanel.add(myTextInterface.input));
		frame.add(outputPanel.add(myTextInterface.output));
		frame.add(monPanel);
		//		frame.add(myTextInterface.input, gbc);
		//		frame.add(myTextInterface.output, gbc);		

		frame.setSize(1250, 750);
		frame.setVisible(true);	
	}
}