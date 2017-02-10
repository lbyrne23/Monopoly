package packA;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) {
		InputBox input = new InputBox();
		JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
		Panel panel = new showTable();
		frame.getContentPane().add(panel);
		frame.getContentPane().add(input.getBox(), BorderLayout.EAST);
		frame.setSize(1200, 800);
		frame.setVisible(true);
		
		Player dot = new Player();
		frame.add(dot);
		frame.setVisible(true);
	}

}
