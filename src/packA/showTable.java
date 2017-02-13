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
	private ArrayList<Player> playerList = new ArrayList<Player>(6); //Array list to store players
	
	//These Integers are just for Sprint 1 demonstration.
	private int timerLoop;
	private int playerLoop;
	
	public showTable() {
		//try to load image from project files.
		try {
			image = ImageIO.read(new File("src/packA/Board2.gif"));
		} catch (IOException ie) {
			System.out.println("Error:"+ie.getMessage());
		}
		
		timerLoop = 0;
		playerLoop = 0;
		
		//add players to our playerlist, (currently loading maximum players every time)
		for (int i=0; i<6; i++){
			playerList.add(new Player(i));
		}
		
		

	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, 700, 704, null); // draw image of board
		for(Player p : playerList){					// loop to draw each player.
			p.paintComponent(g);
		}

	}
	
	//Timer to move players
	public void sprint1Test(){
		Timer timer = new Timer(250, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				timerLoop++; // Keeps track of how many moves have happened
				Player currPlayer = playerList.get(playerLoop);
				currPlayer.setLocation((currPlayer.getPosition() + 1)%40);
				
				
				if (timerLoop == 40){// If player has moved back to start, start moving next player
					playerLoop = (playerLoop + 1)%6; // playerLoop is used to reference player, move on to next player.
					timerLoop = 0;
				}
					
				repaint();
			}		
		});
		timer.start();
	}
}
