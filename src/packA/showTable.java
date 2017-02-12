package packA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	Player A = new Player(0);
	Player B = new Player(1);
	Player C = new Player(2);
	
	public showTable() {
		try {
			image = ImageIO.read(new File("src/packA/Board2.gif"));
		} catch (IOException ie) {
			System.out.println("Error:"+ie.getMessage());
		}
		
		add(A);
		add(B);
		add(C);
		A.setLocation(0);
		B.setLocation(1);
		C.setLocation(2);

	}

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, 700, 704, null);
		A.paintComponent(g);
		B.paintComponent(g);
		C.paintComponent(g);
	}
	
	public void sprint1Test(){
		Timer timer = new Timer(500, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				A.setLocation(	(A.getPosition() + 1)%39	);
				B.setLocation(	(B.getPosition() + 1)%39	);
				C.setLocation(	(C.getPosition() + 1)%39	);
				repaint();
			}		
		});
		timer.start();
	}
}
