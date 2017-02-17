package packA;
// A class to display the board and paint the player tokens.

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JTextArea;

import packA.Player;


public class Board extends JPanel {
	private BufferedImage  image = null;	
	private ArrayList<Player> playerList = new ArrayList<Player>(6);		 				// Array list to store players.
	private int playerTurn;
	private int numberOfPlayers;
	private JTextArea output;
	

	public Board(int players, JTextArea newOutput) {
		try {																				// Try to load image from project files.
			image = ImageIO.read(new File("src/packA/Board2.gif"));
		} catch (IOException ie) {
			System.out.println("Error:"+ie.getMessage());
		}
		
		output = newOutput; // Give area for outputting info.
		
		playerList = new ArrayList<Player>(players);


		//Add players to our playerList (currently loading maximum players every time).
		for (int i=0; i<players; i++){
			playerList.add(new Player(i, "Player" + i));
		}
	}


	@Override																					
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, 700, 704, null); 											// Draw image of board.
		for(Player p : playerList){															// Loop to draw each player.
			p.paintComponent(g);
		}
	}
		
	public boolean takeTurn(String command){
			//Return True unless the command 'done' is entered.
			//This class will call other functions depending on command given
			output.append(command + "\n");
			if(command.equals("done")){
				return false;
			}
			else return true;
		}
	}	

