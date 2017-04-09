package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public final class OutputBox extends JPanel {

	public OutputBox(){
		output = new JTextArea(40, 40);
		output.setEditable(false);
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scrollPane = new JScrollPane(output);				//Create JScrollPane for viewing text area.
		add(scrollPane);
	}

	public  JTextArea getJTextArea(){
		return output;
	}
	
	public void resetCaret(){
		output.setCaretPosition(output.getDocument().getLength());
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
	}

	private JTextArea output;
}