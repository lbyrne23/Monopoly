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
	private ArrayList<Player> playerList = new ArrayList<Player>(6);// Array list to store players.
	private PropertyList properties = new PropertyList();
	private int numberOfPlayers;
	private JTextArea output;
	
	//Following integers are for tracking details of players' turns. 
	private int playerTurn;


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
	
	public String squareInfo(){
		//Get property at location of current players turn.
		Player tmpPlayer = playerList.get(playerTurn);
		Property tmpProperty = properties.get(tmpPlayer.getPosition());
		String info;
		
		if(tmpProperty.returnOwner() == null){
			info = tmpProperty.returnName() + "\n";
		}
		else if(tmpProperty.returnOwner() < 0){
		info = tmpProperty.returnName() + " ; \n-This property is on the market for �"
		+ tmpProperty.returnPrice() + "\n-It has rent of �" + tmpProperty.returnRent() + ".\n";
		}
		
		else if(tmpProperty.returnOwner() == playerTurn){
			info = "This is your property!\n";
		}
		
		else{
			info = tmpProperty.returnName() + " ; \n -Player " + tmpProperty.returnOwner()
			+ " owns this property.\n You must pay rent of �" + tmpProperty.returnRent() + ".\n";
		}
		
		return info;
	}


	public void playerAction(String command){
		//This class will call other functions depending on command given
		if(playerTurn == -1){
			if(numberOfPlayers == 6){ 														//Case of Maximum players
				playerTurn++;
			}


			if(numberOfPlayers>1 && command.equalsIgnoreCase("done")){								//Case of sufficient players to begin
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

		
		if(command.equalsIgnoreCase("buy")){
			buyFunction();
		}
		
		else if(command.equalsIgnoreCase("property")){
			propertyFunction();
		}
		
		else if(command.equalsIgnoreCase("done")){
			doneFunction();
		}

		else if(command.equalsIgnoreCase("roll")){
			rollFunction();
		}
		
		else if(command.equalsIgnoreCase("help")){
		helpFunction();
		}

		else if(command.equalsIgnoreCase("balance")){
			output.append("\nYour balance: " + playerList.get(playerTurn).getBalance() + "\n");
		}

		else {
			output.append("\nInvalid command, enter 'help' for a list of commands. \n");
		}
	}
	
	public void rollFunction(){
		if(Dice.allowedRoll == 0 || Dice.allowedRoll == 2){
			Player tmpPlayer = playerList.get(playerTurn);
			int thisRoll = Dice.Roll();
			
			if((tmpPlayer.getPosition()+ thisRoll)%40 < tmpPlayer.getPosition()){
				tmpPlayer.updateBalance(200);
			}

			tmpPlayer.setLocation((tmpPlayer.getPosition()+ thisRoll)%40);
			output.append("\n"+ Dice.words() + "\n");
			repaint();

			output.append(squareInfo());
		
			if(Dice.allowedRoll == 2){
				output.append("\nYou are able to roll again!\n");
			}
		}
		else{
			output.append("\nYou cannot roll again.");
		}
		

		/*if(	Dice.rollAgain == true){
			output.append("As you rolled doubles you can roll again \n"
					+ "Type 'again' to do so\n");

			output.append("Doubles: Roll again\n");
			tmpPlayer.setLocation((tmpPlayer.getPosition()+Dice.Roll())%40);
			output.append(Dice.words());


			Dice.rollAgain = false; 
		}*/
	}
	
	public void helpFunction(){
		output.append("\n'roll' : Roll dice \n"
				+ "'pay rent' : Pay rent of square you landed on \n"
				+ "'buy' : Buy property of square you landed on \n"
				+ "'property': Query the properties you currently own \n"
				+ "'balance' : Query your current balance \n"
				+ "'done' : Finish your turn \n"
				+ "'quit' : Quit the game \n");
	}
	
	public void doneFunction(){
		if(Dice.allowedRoll != 0 && Dice.allowedRoll != 2){
		Dice.allowedRoll = 0;
		playerTurn = (playerTurn+1)%numberOfPlayers;
		output.append("\n" + playerList.get(playerTurn).getName() +"'s turn. Roll.\n");
		}
		else{
			output.append("\nYou cannot end your turn without rolling");
		}
		
		
	}
	
	public void buyFunction(){
		Player currPlayer = playerList.get(playerTurn);
		Property currProperty = properties.get(currPlayer.getPosition());
		if(currProperty.returnOwner() == null || currProperty.returnOwner() >= 0){ 		// If property is owned or can't be purchased
			output.append("\n This property cannot be purchased. \n");
		}
		
		else if (currPlayer.getBalance() >= currProperty.returnPrice()){				//If player can afford property, purchase
			currProperty.setOwner(playerTurn);											//set Owner using whose turn it is.
			currPlayer.updateBalance(-(currProperty.returnPrice()));					//subtract cost from player Balance
			output.append("\n You have purchased '" + currProperty.returnName() + "'\n");
		}
		else{
			output.append("\nYou cannot afford this property.\n");
		}
	}
	
	public void propertyFunction(){
			output.append("\nThe properties you own are as follow;\n");
		for(Property p : properties){
			if(p.returnOwner() != null && p.returnOwner() == playerTurn){
				output.append("\n" + p.returnName() + " : \n-The current rent is �" + p.returnRent() +"\n");
			}
		}
	}
}