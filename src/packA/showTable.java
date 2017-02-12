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

	public showTable() {
		try {
			image = ImageIO.read(new File("src/packA/Board2.gif"));
		} catch (IOException ie) {
			System.out.println("Error:"+ie.getMessage());
		}

		for (int i = 0; i < 6; i++)
		{
			playerList.add(new Player(i));
		}

		for (Player A:playerList){
			int i = 0;
			A.setLocation(i);
			i++;
		}
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, 700, 704, null);

		for (Player A:playerList)
		{
			A.paintComponent(g);
		}
	}

	public void sprint1Test(){
		Timer timer = new Timer(500, new ActionListener(){
			public void actionPerformed(ActionEvent e){

				for(Player A:playerList)
				{
					A.setLocation( (A.getPosition() + 1) %39);
				}
				repaint();
			}		
		});
		timer.start();
	}
}
