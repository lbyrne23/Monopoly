package packA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
//import java.io.InputStreamReader;
//import java.io.BufferedReader;

import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.JFrame;

import packA.Player;

//MJ

public class showTable extends JPanel {
	BufferedImage  image = null;	
	ArrayList<Player> playerList = new ArrayList<Player>(6);

	public showTable() {
		try {
			image = ImageIO.read(new File("src/packA/Board2.gif"));
		} catch (IOException ie) {
			System.out.println("Error:"+ie.getMessage());
		}
		
//		for (int i = 0; i < 6; i++)
//		{
//			playerList.set(i, new Player(i));
//		}
	}

	public void paint(Graphics g) {
		g.drawImage( image, 0, 0, 700, 704, null);
	}
	
	Player A = new Player(0);
	Player B = new Player(1);
	Player C = new Player(2);
	public void paintComponent(Graphics g){
		g.drawImage( image, 0, 0, 700, 704, null);
		A.draw(g);
		B.draw(g);
		C.draw(g);
	}
}
