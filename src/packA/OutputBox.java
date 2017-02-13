package packA;

import javax.swing.JTextArea;

public class OutputBox extends JTextArea {

	public OutputBox(){
		output = new JTextArea();
		output.setEditable(false);
		add(output);
	}

	public  JTextArea getJTextArea(){
		return output;
	}

	private JTextArea output;
}