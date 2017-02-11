package packA;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//import java.io.InputStreamReader;
//import java.io.BufferedReader;

import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.JFrame;

//MJ
//Still needs to display the image from a web address.

public class showTable extends JPanel {
	BufferedImage  image = null;
	public showTable() {
		try {
			image = ImageIO.read(new File("src/packA/Board2.gif"));
		} catch (IOException ie) {
			System.out.println("Error:"+ie.getMessage());
		}
	}

	public void paint(Graphics g) {
		g.drawImage( image, 0, 0, 700, 704, null);
	}
}
