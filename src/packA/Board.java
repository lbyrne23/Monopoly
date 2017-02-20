package packA;
// A class to display the board and paint the player tokens.

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

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
			System.out.println("Error: "+ie.getMessage());
		}

		output = newOutput; 																// Give area for outputting info.
		output.append("Welcome to Monopoly by Cessna Skyhawk!\nPlease enter a player name.\n");

		playerTurn = -1; 																	//Indicates players have not yet been instantiated. 
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
			if(numberOfPlayers == 6){ 														//Case of Maximum players
				playerTurn++;
			}


			if(numberOfPlayers>1 && command.equals("done")){								//Case of sufficient players to begin
				playerTurn++;
				output.append("Roll to see who goes first\n");

				/* All below is dice roll algorithm - will clean up into a method soon
				int Roll;
				int[] firstRolls = new int[numberOfPlayers];

				for(int i = 0; i < numberOfPlayers; i++){
					Roll = Dice.Roll();
					playerList.get(i).setFirstRoll(Roll);
					firstRolls[i] = Roll;
					output.append(playerList.get(i).getName() + " : " + playerList.get(i).getFirstRoll() + "\n");
				}

				Arrays.sort(firstRolls);

				for(int count = 0; count < numberOfPlayers; count++){
					if(playerList.get(count).getFirstRoll() == firstRolls[numberOfPlayers]){
						//Set this player to roll first
					}
				}

				Dice.rollAgain = false;
				End of dice roll shite*/
				output.append("(Player who rolled highest) goes first.\nEnter 'roll' \n");
				return;
				
			}


			if(numberOfPlayers < 6){														//Adding players if there's room
				playerList.add(new Player(numberOfPlayers, command));
				output.append("Player " + numberOfPlayers + " name : " + command + "\n");
				repaint();
				numberOfPlayers++;

				switch(numberOfPlayers){													//Different message displayed once sufficient players added.
				case 1:	output.append("Please enter a player name.\n");
				break;

				default : output.append("Please enter a player name, \nor type 'done' to begin.\n");
				}

				return;
			}
		}


		if(command.equals("done")){
			playerTurn = (playerTurn+1)%numberOfPlayers;
			output.append(playerList.get(playerTurn).getName() +"'s turn. Roll.\n");
		}

		if(command.equals("roll")){
			Player tmpPlayer = playerList.get(playerTurn);
			int thisRoll = Dice.Roll();
			if((tmpPlayer.getPosition()+ thisRoll)%40 < tmpPlayer.getPosition()){
				tmpPlayer.updateBalance(200);
			}

			tmpPlayer.setLocation((tmpPlayer.getPosition()+ thisRoll)%40);
			output.append(Dice.words() + "\n");
			repaint();

			output.append("Would you like to 'buy' or 'rent'?\nEnter 'done' to finish turn\n");

			/*if(	Dice.rollAgain == true){
				output.append("As you rolled doubles you can roll again \n"
						+ "Type 'again' to do so\n");

				output.append("Doubles: Roll again\n");
				tmpPlayer.setLocation((tmpPlayer.getPosition()+Dice.Roll())%40);
				output.append(Dice.words());


				Dice.rollAgain = false; 
			}*/
		}
		if(command.equals("help")){
			output.append("'roll' : Roll dice \n"
					+ "'pay rent' : Pay rent of square you landed on \n"
					+ "'buy' : Buy property of square you landed on \n"
					+ "'property': Query the properties you currently own \n"
					+ "'balance' : Query your current balance \n"
					+ "'done' : Finish your turn \n"
					+ "'quit' : Quit the game \n");
		}

		if(command.equals("balance")){
			output.append("Your balance: " + playerList.get(playerTurn).getBalance() + "\n");
		}

		/*else {
			output.append("Invalid command \n");
		} */
	}
}