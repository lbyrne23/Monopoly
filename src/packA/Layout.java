package packA;
import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;

public class Layout{
	static public void main(String[] args){
		InputBox myTextInterface = new InputBox();
		myTextInterface.input.setBounds(50, 890, 700, 50);
		myTextInterface.output.setBounds(700, 0, 200, 50);
		JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
		Panel panel = new showTable();
		frame.getContentPane().add(panel);
		frame.getContentPane().add(myTextInterface.input);
		frame.getContentPane().add(myTextInterface.output);
		frame.setSize(1250, 750);
		frame.setVisible(true);	
	}
}