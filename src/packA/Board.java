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
	private static ArrayList<Player> playerList = new ArrayList<Player>(6);// Array list to store players.
	private static PropertyList properties = new PropertyList();
	private static int numberOfPlayers;
	private static JTextArea output;
	static int winner = 0;

	//Following variables are for tracking details of players' turns. 
	private static int playerTurn;
	private static boolean rentPaid = true;


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
			info = tmpProperty.returnName() + " ; \n-This property is on the market for £"
				+ tmpProperty.returnPrice() + "\n-It has rent of £" + tmpProperty.returnRent() + ".\n";
		}

		else if(tmpProperty.returnOwner() == playerTurn){
			info = "This is your property!\n";
		}

		else{
			info = tmpProperty.returnName() + " ; \n - " + playerList.get(tmpProperty.returnOwner()).getName()
				+ " owns this property.\n You must pay rent of £" + tmpProperty.returnRent() + ".\n";
			rentPaid = false;
		}

		return info;
	}


	public void playerAction(String command){
		//This class will call other functions depending on command given
		if(playerTurn == -1){

			if(numberOfPlayers>1 && command.equalsIgnoreCase("done") || numberOfPlayers == 6){								//Case of sufficient players to begin
				playerTurn++;
				output.append("Roll to see who goes first\n");

				goFirst();

				output.append(playerList.get(playerTurn).getName() + " goes first.\nEnter 'roll' \n");
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

		else if(command.equalsIgnoreCase("pay rent")){
			payRentFunction();
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

		else if(command.equalsIgnoreCase("quit")){
			highestPlayer();
			playerList.clear();
			repaint();

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
				output.append("\nYou've passed GO!\nÂ£200 has been added to your balance.\n");
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
			output.append("\nYou cannot roll again.\n");
		}

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
		if (!rentPaid){
			output.append("\nYou cannot end your turn with outstanding rent.\n");
		}
		else if(Dice.allowedRoll != 0 && Dice.allowedRoll != 2){
			Dice.allowedRoll = 0;
			playerTurn = (playerTurn+1)%numberOfPlayers;
			output.append("\n" + playerList.get(playerTurn).getName() +"'s turn. Roll.\n");
		}
		else if (!(Dice.allowedRoll != 0 && Dice.allowedRoll != 2)){
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
				output.append("\n" + p.returnName() + " : \n-The current rent is £" + p.returnRent() +"\n");
			}
		}
	}

	public void payRentFunction(){
		if(rentPaid){
			output.append("\n There is no rent owed.");
		}

		else{
			Player currPlayer = playerList.get(playerTurn);
			Property currProperty = properties.get(currPlayer.getPosition());	//Get player, property and owner of property.
			Player debtor = playerList.get(currProperty.returnOwner());
			int rent = currProperty.returnRent();

			if(currPlayer.getBalance() >= rent){
				currPlayer.updateBalance(-(rent));
				debtor.updateBalance(rent);
				output.append("\nYou have paid " + debtor.getName() + " £" + rent + "\n");
				rentPaid = true;
			}
			else{
				debtor.updateBalance(currPlayer.getBalance());
				currPlayer.updateBalance(-((currPlayer.getBalance()) + 1));
				rentPaid = true;
			}
		}
	}


	//Function to determine who goes first
	public static void goFirst(){
		int Roll;
		int[] firstRolls = new int[numberOfPlayers]; //array to store the first roll

		for(int i = 0; i < numberOfPlayers; i++){
			Roll = Dice.Roll();
			playerList.get(i).setFirstRoll(Roll); //set value of first roll
			firstRolls[i] = Roll;
			output.append(playerList.get(i).getName() + " : " + playerList.get(i).getFirstRoll() + "\n"); //print first rolls to screen
		}

		//sort the first rolls from lowest to highest
		//this means the highest value is the last value in the array
		//meaning whoever rolled this highest value goes first
		Arrays.sort(firstRolls);
		
		
		if (firstRolls[numberOfPlayers-1] == firstRolls[numberOfPlayers-2])
		{
			output.append("\nRe-roll..\n");
			goFirst();
		} else
		{
			int g = 0;
			//go through list and determine which player's value matched the highest value
			while(g < numberOfPlayers){ 
				if(playerList.get(g).getFirstRoll() == firstRolls[numberOfPlayers-1]){
					playerTurn = g;
				}
				g++;
			}
		}

		Dice.allowedRoll = 0;
	}

	//adds assets and current balance
	public static void highestPlayer(){
		int[] houses = new int[numberOfPlayers]; //array to store the balances
		boolean draw = false;


		for(int i = 0; i < numberOfPlayers; i++){
			for (int j = 0; j < 40; j++){
				if(properties.get(j).returnOwner() != null && properties.get(j).returnOwner() == i){
					playerList.get(i).updateTotal(properties.get(j).returnPrice());
					houses[i] = playerList.get(i).getTotal();
				}
			}
		}

		for(int i = 0; i < numberOfPlayers; i++){
			houses[i] = houses[i] + playerList.get(i).getBalance();
		}

		output.append("\n"); //its for aesthetics plz trust me

		for(int i = 0; i < numberOfPlayers; i++){
			output.append(playerList.get(i).getName() + " : £" + playerList.get(i).getTotal() + " worth of properties "
					+ "and £"+ playerList.get(i).getBalance() +" current balance\n"
							+ "Total: £" + (playerList.get(i).getTotal()+playerList.get(i).getBalance()) + "\n"); //print balances to screen
		}


		//sort balances from lowest to highest
		Arrays.sort(houses);
		
		if (houses[numberOfPlayers-1] == houses[numberOfPlayers-2])
		{
			draw = true;
		}


		//go through list and determine which player's value matched the highest value
		for(int count = 0; count < numberOfPlayers; count++){ 
			if((playerList.get(count).getTotal()+playerList.get(count).getBalance()) == houses[numberOfPlayers-1]){
				winner = count;
			}
		}

		if(draw == false){
		output.append("\nGame Over. Winner is " + playerList.get(winner).getName()
				+ " with £" + (playerList.get(winner).getTotal()+playerList.get(winner).getBalance()) + " worth of assets \n");
		}
		if (draw == true){
			output.append("No Winner! There is a draw. ");
		}

	}
}
