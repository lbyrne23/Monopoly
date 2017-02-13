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
	private BufferedImage  image = null;	
	private ArrayList<Player> playerList = new ArrayList<Player>(6);
	
	//These Integers are just for Sprint 1 demosntration.
	private int timerLoop;
	private int playerLoop;
	
	public showTable() {
		try {
			image = ImageIO.read(new File("src/packA/Board2.gif"));
		} catch (IOException ie) {
			System.out.println("Error:"+ie.getMessage());
		}
		
		timerLoop = 0;
		playerLoop = 0;
		
		for (int i=0; i<6; i++){
			playerList.add(new Player(i));
		//	add(playerList.get(i));
		}
		
		

	}

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, 700, 704, null);
		for(Player p : playerList){
			p.paintComponent(g);
		}

	}
	
	public void sprint1Test(){
		Timer timer = new Timer(250, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (timerLoop == 0){
					add(playerList.get(playerLoop));
					timerLoop++;
				}
				Player currPlayer = playerList.get(playerLoop);
				currPlayer.setLocation((currPlayer.getPosition() + 1)%40);
				timerLoop++;
				
				if (timerLoop == 40){
					currPlayer.setLocation((currPlayer.getPosition() + 1)%40);
					remove(playerList.get(playerLoop));
					playerLoop = (playerLoop + 1)%6;
					timerLoop = 0;
				}
					
				repaint();
			}		
		});
		timer.start();
	}
}
