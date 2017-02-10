import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;

import packA.InputBox;
import packA.showTable;

public class Layout{
	static public void main(String[] args){
		InputBox input = new InputBox();
		JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
		Panel panel = new showTable();
		frame.getContentPane().add(panel);
		frame.getContentPane().add(input.input, BorderLayout.NORTH);
		frame.getContentPane().add(input.output, BorderLayout.CENTER);
		frame.setSize(900, 900);
		frame.setVisible(true);	
	}
}