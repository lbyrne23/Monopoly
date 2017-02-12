package packA;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;


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
		
		int i = 0;
		for (Player A:playerList){
			A.setLocation(i);
			i++;
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, 700, 704, null);
		playerList.get(0).paintComponent(g);
		repaint();
	}

	public void sprint1Test(){
		Timer timer = new Timer(100, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(Player A:playerList)
				{
					A.setLocation( (A.getPosition() + 1) %39);
					repaint();
				}
//				remove(0);
			}		
		});
		timer.start();
	}
}
