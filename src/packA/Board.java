package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//
// A class to display the board and paint the player tokens.

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import packA.Player;


public class Board extends JPanel {
	private BufferedImage  image = null;	
	protected static ArrayList<Player> playerList = new ArrayList<Player>(6);			//Array list to store players.
	protected static PropertyList properties = new PropertyList();
	protected static int numberOfPlayers;
	protected static JTextArea output;
	private static int winner = 0;
	protected static boolean doubled = false;

	//Following variables are for tracking details of players' turns. 
	protected static int playerTurn;
	protected static boolean rentPaid = true;


	public Board(int players, JTextArea newOutput) {

		output = newOutput; 
		//Try to load image from project files.
		URL url = getClass().getResource("Board2.gif");
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			output.append("ERROR : COULDN'T LOAD IMAGE");
		}


		output.append("Welcome to Monopoly by Cessna Skyhawk!\nPlease enter a player name.\n");

		playerTurn = -1; 															//Indicates players have not yet been instantiated. 
		playerList = new ArrayList<Player>(players);								// List to hold players
		numberOfPlayers = 0;														//tracks number of players, begins at zero.
	}


	@Override																					
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, 700, 704, null); 									//Draw image of board.
		for(Player p : playerList){													//Loop to draw each player.
			p.paintComponent(g);
		}
	}


	public String squareInfo(){
		//Get property at location of current players turn.
		Player tmpPlayer = playerList.get(playerTurn);
		Property tmpProperty = properties.get(tmpPlayer.getPosition());
		String info;

		if(tmpProperty.returnOwner() == null){									 //ie unbuyable property, just return name for now
			info = tmpProperty.returnName() + "\n";
		}
		else if(tmpProperty.returnOwner() < 0){																	//If owner<0 i.e. buyable property
			info = tmpProperty.returnName() 
					+ " ; \n-This property is on the market for £" + tmpProperty.returnPrice() 
					+ "\n-It has rent of £" + tmpProperty.returnRent() + ".\n"
					+ "\nEnter 'buy' if you wish to purchase this property.\n"
					+ "Enter 'help' for all other commands\n";
		}

		else if(tmpProperty.returnOwner() == playerTurn){														//owner is current player
			info = tmpProperty.returnName() + " ; This is your property. "
					+ "\n-It has rent of £" + tmpProperty.returnRent() + ".\n";
		}

		else{											
			info = tmpProperty.returnName() + " ;\n- " + playerList.get(tmpProperty.returnOwner()).getName()	//owner > 0, i.e. owned property.
					+ " owns this property.\nYou must pay rent of £" + tmpProperty.returnRent() + ".\n";		// you must pay rent.
			rentPaid = false;																					//set rentPaid tracker to false.
		}

		return info;
	}


	public void playerAction(String command){
		//This class will call other functions depending on command given.
		if(playerTurn == -1){

			if(numberOfPlayers>1 && command.equalsIgnoreCase("done")){			//Case of sufficient players to begin.
				playerTurn++;
				output.append("Roll to see who goes first.\n");

				goFirst();														//function to arrange players based on dice rolls.

				output.append("\n" + playerList.get(playerTurn).getName() + " goes first.\nEnter 'roll' \n");
				return;

			}

			if(numberOfPlayers < 6){																	//Adding players if there's room.
				playerList.add(new Player(numberOfPlayers, command));
				output.append("Player " + numberOfPlayers + " name : " + command + "\n");
				repaint();
				numberOfPlayers++;

				switch(numberOfPlayers){																//Different message displayed once sufficient players added.
				case 1:	output.append("Please enter a player name.\n");
				break;

				case 6: output.append("Roll to see who goes first.\n");									//If maximum players added, begin.
				goFirst();
				output.append(playerList.get(playerTurn).getName() + " goes first.\nEnter 'roll' \n");
				break;

				default : output.append("Please enter a player name, \nor type 'done' to begin.\n");
				}

				return;
			}
		}

		//Aesthetic purpose for output box.
		output.append("\n------------------------------------------------------------------------------------------\n");

		//Assessing command.
		if(command.equalsIgnoreCase("buy")){
			buyFunction();
		}
		//TEST COMMAND USED TO PURCHASE ALL PROPERTY FOR DEBUGGING
		else if (command.equalsIgnoreCase("Squatters Rights")){
			int i;
			Player currPlayer = playerList.get(playerTurn);
			currPlayer.updateBalance(30000);

			for (i = 0; i < properties.size(); i++){
				currPlayer.setLocation(i);
				buyFunction();
			}
			output.append("\nYou own everything in sight, but do you feel any less empty?\n");
		}
		//TEST COMMAND USED TO ENTER BANKRUPTCY
		else if(command.equalsIgnoreCase("purchase bit coin")){
			playerList.get(playerTurn).updateBalance(-3000);
		}

		else if(command.equalsIgnoreCase("property")){
			propertyFunction();
		}
		
		else if(command.toLowerCase().startsWith("mortgage ")){
			String propInputName = command.substring(9);
			mortgageFunction(propInputName);
		}

		else if(command.equalsIgnoreCase("pay rent")){
			payRentFunction();
		}

		else if(command.equalsIgnoreCase("done")){
			colourMultiplier();
			doneFunction();
		}

		else if(command.toLowerCase().startsWith("build ") || command.toLowerCase().startsWith("demolish ") ){
			buildHouse(command);
		}

		else if(command.equalsIgnoreCase("roll")){
			rollFunction();

		}

		else if(command.equalsIgnoreCase("help")){
			helpFunction();
		}

		else if(command.equalsIgnoreCase("balance")){
			output.append("\nYour balance: £" + playerList.get(playerTurn).getBalance() + "\n");
		}

		else if(command.equalsIgnoreCase("quit")){
			highestPlayer();
			playerList.clear();
			repaint();
		}

		else {
			output.append("\nInvalid command, enter 'help' for a list of commands.\n");
		}
	}


	//Function to roll dice and move player.
	public void rollFunction(){
		if(!rentPaid){
			output.append("\nYou must pay rent before rolling again.\n");
			return;																//End function here if rent is due first.
		}

		if(Dice.allowedRoll == 0 || Dice.allowedRoll == 2){
			Player tmpPlayer = playerList.get(playerTurn);
			int thisRoll = Dice.Roll();

			if((tmpPlayer.getPosition()+ thisRoll)%40 < tmpPlayer.getPosition()){
				tmpPlayer.updateBalance(200);
				output.append("\nYou've passed GO!\n£200 has been added to your balance.\n");
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

	//Function to return legal commands.
	public void helpFunction(){
		output.append("\n'roll' : Roll dice.\n"
				+ "'pay rent' : Pay rent of square you landed on.\n"
				+ "'buy' : Buy property of square you landed on.\n"
				+ "'property' : Query the properties you currently own.\n"
				+ "'balance' : Query your current balance.\n"
				+ "'mortgage *short name of property*' : Mortgage one of your properties.	(Incomplete function)\n"
				+ "'done' : Finish your turn.\n"
				+ "'quit' : Quit the game.\n");
	}

	//Function to end turn. Also takes bankruptcy into account.
	public void doneFunction(){
		Player currPlayer = playerList.get(playerTurn);
		if(rentPaid && currPlayer.getBalance()<0){											//If out of money
			releasePropertyFunction();														//Return properties to Market
			playerList.remove(playerTurn);													//Remove player from game
			numberOfPlayers--;																//Player Turn stays on same index, unless last player removed.
			repaint();
			playerTurn = (playerTurn)%numberOfPlayers;		
			if(numberOfPlayers == 1){															//If player is last remaining player
				winner = playerTurn;
				output.append("\nGame Over. Winner is " + playerList.get(winner).getName() + "\n");
				playerList.clear();																//remove final player
				repaint();
			}
			else{										
				Dice.allowedRoll = 0;
				output.append("\n" + playerList.get(playerTurn).getName() +"'s turn. Roll.\n");
			}

		}

		if (!rentPaid){																		//If rent not paid, not allowed end.
			output.append("\nYou cannot end your turn with outstanding rent.\n");
		}

		else if (Dice.allowedRoll == 0 || Dice.allowedRoll == 2){						// If dice not rolled, not allowed end.
			output.append("\nYou cannot end your turn without rolling.\n");
		}
		else if(Dice.allowedRoll != 0 && Dice.allowedRoll != 2){						//If dice rolled and rent-paid allowed end turn
			Dice.allowedRoll = 0;
			playerTurn = (playerTurn+1)%numberOfPlayers;
			output.append("\n" + playerList.get(playerTurn).getName() +"'s turn. Roll.\n");
		}
	}

	// Function to buy property that current Player is on.
	public void buyFunction(){
		Player currPlayer = playerList.get(playerTurn);
		Property currProperty = properties.get(currPlayer.getPosition());
		if(currProperty.returnOwner() == null || currProperty.returnOwner() >= 0){ 		//If property is owned or can't be purchased.
			output.append("\nThis property cannot be purchased.\n");
		}

		else if (currPlayer.getBalance() >= currProperty.returnPrice()){				//If player can afford property, purchase.
			currProperty.setOwner(playerTurn);											//Set Owner using to player whose turn it is.
			currPlayer.updateBalance(-(currProperty.returnPrice()));					//Subtract cost from player Balance.
			output.append("\nYou have purchased '" + currProperty.returnName() + "'\n");

			if(currProperty.returnColour() == 8 || currProperty.returnColour() == 9){	//If property is a utility or station.
				int numberOwned = 0;
				ArrayList<Property> ownedProperties = new ArrayList<Property>();
				for(Property p : properties){
					if (p.returnColour() == currProperty.returnColour()){
						numberOwned++;													//Record number of Utilies or Stations owned
						ownedProperties.add(p);											//Add these properties to a list for update after
					}
				}
				for(Property p : ownedProperties){
					p.setHouses(numberOwned - 1);										//Set owned properties to rent level depending on number owned.
				}


			}
		}
		else{
			output.append("\nYou cannot afford this property.\n");
		}
	}

	//Function to return owned properties for current Player.
	public void propertyFunction(){
		output.append("\nThe properties you own are as follow;\n");
		for(Property p : properties){
			if(p.returnOwner() != null && p.returnOwner() == playerTurn){
				output.append("\n" + p.returnName() + " : \n-The current rent is £" + p.returnRent() +"\n");
			}
		}
	}

	//Function to mortgage a property for the current player.
	public void mortgageFunction(String propInputName){
		Player currPlayer = playerList.get(playerTurn);
		for(Property p: properties){														//Cycle through all properties.
			if(p.returnOwner() != null && p.returnOwner() == playerTurn){					//Condition to narrow the cycle to all properties the player owns.
				if(propInputName.equalsIgnoreCase(p.returnShortName())){					//If the name entered by the user is equals the short name of a property
//					Property propObject =  Property.returnMortgage();
					int mortgageValue = Property.returnMortgage();

					playerList.get(playerTurn).updateBalance(mortgageValue);
					output.append("You wishhh to mortgage " + p.returnShortName());
				}
			}
		}
	}
	
	public void demolishHouse(String propertyName){
		Player currPlayer = playerList.get(playerTurn);
		Property currProperty = null;
		for(Property p : properties){					//Iterate through properties until one matching entered name is found.
			if(p.returnName().equalsIgnoreCase(propertyName)){
				currProperty = p;
				break;
			}
		}
		if(currProperty == null){
			output.append("\nEntered Property Name is not valid.\nHint : \nPlease type 'st' for street and 'rd' for road.\n");
			return;
		}
		if(currProperty.returnOwner() == null || currProperty.returnOwner() != playerTurn ){ //check ownership
			output.append("\nYou don't own this property\n");
			return;
		}
		if(currProperty.returnHousePrice() < 0){	// house prices set to '-1' to indicate they can't be purchased.
			output.append("\nYou cannot build houses on Stations or Utilities\n");
			return;
		}
		else if (currPlayer.getBalance() >= currProperty.returnHousePrice() && canBuild(currProperty.returnColour()) == true){ // check if they can afford it and if they own all the colour group
			if(currProperty.updateRent(1) != null){						//Move the rent index to the next level.
				int houses = currProperty.returnHouses();
				if(houses != 1){										//Case of subsequent houses built
				output.append("\nThere are now " + currProperty.returnHouses() + " houses on " + currProperty.returnName() +"\n");
				}
				else if(houses == 6){
				output.append("\nThere is now a hotel on " + currProperty.returnName() +"\n");
				}
				else{													//Case of first house built
					output.append("\nThere is now " + currProperty.returnHouses() + " house on " + currProperty.returnName() +"\n");
				}
				currPlayer.updateBalance(-(currProperty.returnHousePrice()));	//Update Player balance
			}
			else{
				output.append("\nYou cannot build more houses on this property.\n");
			}
			return;
		}
		
	}
	
	//build a house on the property
	public void buildHouse(String buildInstructions){
		final Pattern propertyAndNumber = Pattern.compile("(\\w+).* (\\w+).* (\\d+).*");		//Pattern to be matched, all non-spaces before number,number.
		final Matcher m = propertyAndNumber.matcher(buildInstructions);
		String propertyName, command;
		int propertyNumber;
		if(m.find()){
			command = m.group(1);
			propertyName = m.group(2);
			propertyNumber = Integer.parseInt(m.group(3));
			if(propertyNumber < 1){
				output.append("\nNumber of properties to build must be greater than 1\n");
				return;
			}
			if(command.equals("demolish"))
				propertyNumber = -(propertyNumber);	//If demolishing, we must update houses with a negative number.

		}
		else{
			output.append("\nTo use build or demolish commands, use the form;\n 'build/demolish Property_Name Number_As_Digit'\n");
			return;
		}

		Player currPlayer = playerList.get(playerTurn);
		Property currProperty = null;
		for(Property p : properties){					//Iterate through properties until one matching entered name is found.
			if(p.returnShortName()!= null && p.returnShortName().equalsIgnoreCase(propertyName)){
				currProperty = p;
				break;
			}
		}
		if(currProperty == null){
			output.append("\nEntered Property Name is not valid.\nHint : Enter first part of name except; \nFor Pall Mall enter 'Mall'\nFor Old Kent Road enter 'kent'\n"
					+ "For The Angel Islington type 'Islington'\n For King's Cross Station enter 'Kings'\n");
			return;
		}
		if(currProperty.returnOwner() == null || currProperty.returnOwner() != playerTurn ){ //check ownership
			output.append("\nYou don't own this property\n");
			return;
		}
		if(currProperty.returnHousePrice() < 0){	// house prices set to '-1' to indicate they can't be purchased.
			output.append("\nNeither Stations nor Utilities\n");
			return;
		}
		else if (currPlayer.getBalance() >= currProperty.returnHousePrice() && canBuild(currProperty.returnColour()) == true){ // check if they can afford it and if they own all the colour group


			if(currProperty.setHouses(currProperty.returnHouses() + propertyNumber) != null){						//Adjust amount of houses to new level if possible.
				int houses = currProperty.returnHouses();

				if(houses == 5){
					output.append("\nThere is now a hotel on " + currProperty.returnName() +"\n");
				}
				else if(houses != 1){										//Case of subsequent houses built
					output.append("\nThere are now " + currProperty.returnHouses() + " houses on " + currProperty.returnName() +"\n");
				}
				else{													//Case of first house built
					output.append("\nThere is now " + currProperty.returnHouses() + " house on " + currProperty.returnName() +"\n");
				}
				if(command.equalsIgnoreCase("build")){
					currPlayer.updateBalance(-(currProperty.returnHousePrice()));	//remove cost of house from player balance
				}
				else{
					currPlayer.updateBalance(currProperty.returnHousePrice()/2);	//add half cost of house to balance.
				}

			}
			else{
				output.append("\nYou cannot perform further developments on this property.\nMaximum Building Level : 5\n"
						+ "Current Building Level : " + currProperty.returnHouses() + "\n");
			}
			return;
		}
		else if(currPlayer.getBalance() < currProperty.returnHousePrice()){	//Case that they can't afford to build
			output.append("\nYou cannot afford to build here\n");
		}
		else{																// Case that they don't own all properties.
			output.append("\nYou must own all properties of this colour group.\n");
		}
	}

	//go through all properties of current colour, if player doesn't own one they can't build a house
	public boolean canBuild(int colour){
		for(Property p : properties){
			if(p.returnOwner()!= null && p.returnOwner() != playerTurn && p.returnColour() == colour){ //If property can be owned, is owned by player and is of defined colour.
				return false;																			
			}	
		}
		return true;
	}

	// Function to return properties to market if player has lost.
	public void releasePropertyFunction(){
		for(Property p : properties){
			if(p.returnOwner()!= null && p.returnOwner() == playerTurn){
				p.setOwner(-1);
			}
		}
	}

	//function to payrent on current square
	public void payRentFunction(){
		if(rentPaid){
			output.append("\nThere is no rent owed.");
		}

		else{	// Pay Rent
			Player currPlayer = playerList.get(playerTurn);
			Property currProperty = properties.get(currPlayer.getPosition());		//Get player, property and owner of property.
			Player debtor = playerList.get(currProperty.returnOwner());
			int rent = currProperty.returnRent();

			//IF PLAYER CAN AFFORD RENT
			if(currPlayer.getBalance() >= rent){
				currPlayer.updateBalance(-(rent));
				debtor.updateBalance(rent);
				output.append("\nYou have paid " + debtor.getName() + " £" + rent + "\n");
				rentPaid = true;
			}
			//IF PLAYER CAN'T AFFORD RENT
			else{
				output.append("\nYou are bankrupt...\nUpon ending your turn you will exit the game and your properties will be released.");

				currPlayer.updateBalance(-((currPlayer.getBalance() + 1))); //indicate bankruptcy by -1 balance.
				rentPaid = true;
				Dice.allowedRoll = 1;										//Not allowed roll again. (Edge case of rolling doubles).

			}
		}
	}

	
	public void colourMultiplier(){
		if (doubled == false){ //this prevents the rent doubling after every turn
			for(Property p : properties){ //iterate through properties and double the rent of any where all colours have same owner and there are no houses
				if(p.returnOwner()!= null && p.returnOwner() == playerTurn){
					Property tmpProperty = p;
					if(canBuild(tmpProperty.returnColour()) == true && tmpProperty.returnHouses() == 0){
						tmpProperty.doubleRent();
						doubled = true;
					}												
				}	
			}

		}
	}

	public void diceMultiplier(){

	}

	//Function to determine who goes first.
	public static void goFirst(){
		int Roll;
		int[] firstRolls = new int[numberOfPlayers]; 							//Array to store the first roll.
		
		output.append("\nRoll results:\n");
		
		for(int i = 0; i < numberOfPlayers; i++){
			Roll = Dice.Roll();
			playerList.get(i).setFirstRoll(Roll); 								//Set value of first roll.
			firstRolls[i] = Roll;
			output.append(playerList.get(i).getName() + " : " 
					+ playerList.get(i).getFirstRoll() + "\n"); 				//Print first rolls to screen.
		}

		//Sort the first rolls from lowest to highest.
		//This means the highest value is the last value in the array.
		//Meaning whoever rolled this highest value goes first.
		Arrays.sort(firstRolls);


		if (firstRolls[numberOfPlayers-1] == firstRolls[numberOfPlayers-2])
		{
			output.append("\nRe-roll..\n");
			goFirst();															//Using recursion to ensure there is a unique highest roll.
		} else
		{
			int g = 0;
			//Go through list and determine which player's value matched the highest value.
			while(g < numberOfPlayers){ 
				if(playerList.get(g).getFirstRoll() == firstRolls[numberOfPlayers-1]){
					playerTurn = g;
				}
				g++;
			}
		}


		Dice.allowedRoll = 0;
	}

	//Adds assets and current balance.
	public void highestPlayer(){
		int[] houses = new int[numberOfPlayers];						 						//Array to store the balances.
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
					+ "and £"+ playerList.get(i).getBalance() +" current balance.\n"
					+ "Total: £" 
					+ (playerList.get(i).getTotal()+playerList.get(i).getBalance()) + "\n");  //Print balances to screen.
		}


		//Sort balances from lowest to highest.
		Arrays.sort(houses);

		if (houses[numberOfPlayers-1] == houses[numberOfPlayers-2])
		{
			draw = true;
		}


		//Go through list and determine which player's value matched the highest value.
		for(int count = 0; count < numberOfPlayers; count++){ 
			if((playerList.get(count).getTotal()+playerList.get(count).getBalance()) == houses[numberOfPlayers-1]){
				winner = count;
			}
		}

		if(draw == false){
			output.append("\nGame Over. Winner is " + playerList.get(winner).getName()
					+ " with £" + (playerList.get(winner).getTotal()+playerList.get(winner).getBalance()) + " worth of assets.\n");
		}
		if (draw == true){
			output.append("No Winner! There is a draw. ");
		}

	}
}