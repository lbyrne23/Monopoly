package packA;
import java.awt.BorderLayout;
//import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Layout extends JPanel{
	static public void main(String[] args){
		InputBox myTextInterface = new InputBox();
		JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
		JPanel monPanel = new JPanel(new GridBagLayout());
		
		myTextInterface.input.setBounds(50, 890, 700, 50);
		myTextInterface.output.setBounds(700, 0, 200, 50);
		
		frame.setSize(1250, 750);
		frame.setVisible(true);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
			
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		frame.add(monPanel);
		frame.add(myTextInterface.input, gbc);
		frame.add(myTextInterface.output, gbc);		
		
	}
}