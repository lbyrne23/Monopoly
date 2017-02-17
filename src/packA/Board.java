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
		output.append("Welcome to Monopoly by Cessna Skyhawk!\n Please enter a player name.\n");
		
		playerTurn = -1; //Indicates players have not yet been instantiated. 
		playerList = new ArrayList<Player>(players);
		numberOfPlayers = 0;
	}


	@Override																					
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, 700, 704, null); 											// Draw image of board.
		for(Player p : playerList){															// Loop to draw each player.
			p.paintComponent(g);
		}
	}
		
	public void playerAction(String command){
			//This class will call other functions depending on command given
		if(playerTurn == -1){
			if(numberOfPlayers == 6){ //Case of Maximum players
				playerTurn++;
			}
			
			if(numberOfPlayers>1 && command.equals("done")){	//Case of sufficient players to begin
				playerTurn++;
				return;
			}
			
			if(numberOfPlayers < 6){	//Adding players if there's room
				playerList.add(new Player(numberOfPlayers, command));
				output.append("Player " + numberOfPlayers + " name : " + command + "\n");
				repaint();
				numberOfPlayers++;
				
				switch(numberOfPlayers){	//Different message displayed once sufficient players added.
				case 1:	output.append("Please enter a player name.\n");
						break;
						
				default : output.append("Please enter a player name, or type 'done' to begin.\n");
				}
				
				return;
			}
			
			
		}
		
		if(command.equals("move")){
			Player tmpPlayer = playerList.get(playerTurn);
			tmpPlayer.setLocation(tmpPlayer.getPosition()+1);
			repaint();
		}
			
			if(command.equals("done")){
			playerTurn = (playerTurn+1)%numberOfPlayers;
		}
	}
	
}

