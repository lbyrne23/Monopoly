package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//
// A class to display the board, paint the player tokens and interpret commands.

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import packA.Player;


public class Board extends JPanel {
	public static final int POUND = 163;												//Ascii code for pound symbol. Means no alterations are made when compiling from mac/windows.
	private BufferedImage  image = null;	
	protected static ArrayList<Player> playerList = new ArrayList<Player>(6);			//Array list to store players.
	protected static PropertyList properties = new PropertyList();
	protected static CardList jailCards = new CardList();
	protected static CardList communityCards = new CardList("community");
	protected static CardList chanceCards = new CardList("chance");
	protected static JLabel propertyCard = new JLabel();
	protected static int numberOfPlayers = 0;
	protected static int playersEntered = 0;
	protected static OutputBox outputBox;
	protected static JTextArea output;
	private static int winner = 0;

	//Following variables are for tracking details of players' turns. 
	protected static int playerTurn = -1;
	protected static boolean bankrupt = false;
	protected static boolean chooseFineOrChance = false;


	public Board(int players, OutputBox newOutput) {
		add(propertyCard);
		propertyCard.setSize(new Dimension(200, 229));
		propertyCard.setLocation(260, 80);
		outputBox = newOutput;
		output = newOutput.getJTextArea();

		//Try to load image from project files.
		URL url = getClass().getResource("Board3.gif");
		try {
			image = ImageIO.read(url);
		} catch (IOException e) {
			output.append("ERROR : COULDN'T LOAD IMAGE");
		}


		output.append("Welcome to Monopoly by Cessna Skyhawk!\nPlease enter a player name.\n");

		playerList = new ArrayList<Player>(players);							//List to hold players.
		String[] options = new String[] {"2", "3", "4", "5", "6"};
		int response = JOptionPane.showOptionDialog(null, 
				"How many are playing?", 
				"Monopoly by CessnaSkyhawk", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE,
				null, 
				options, 
				options[0]);

		if (response == -1){							//If cancelled.
			System.exit(0);
		} else {
			numberOfPlayers = response+2;
		}
	}


	@Override																					
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage( image, 0, 0, 700, 704, null); 		//Draw image of board.
	}


	public void playerAction(String command){
		//Reset auto-scroll in case someone has clicked on the output box.
		outputBox.resetCaret();

		//Read in player names before beginning.
		if(playerTurn == -1){
			setupGame(command);
			return;
		}

		//Aesthetic purpose for output box.
		output.append("\n------------------------------------------------------------------------------------------\n");

		//TEST COMMAND USED TO PURCHASE ALL PROPERTY FOR DEBUGGING.
		if (command.equalsIgnoreCase("Squatters Rights")){
			int i;
			Player currPlayer = playerList.get(playerTurn);
			currPlayer.updateBalance(30000);

			for (i = 0; i < properties.size(); i++){
				currPlayer.setPosition(i);
				buyFunction();
			}
			output.append("\nYou own everything in sight, but do you feel any less empty?\n");
		}
		//TEST COMMAND USED TO ENTER BANKRUPTCY.
		else if(command.equalsIgnoreCase("purchase bit coin")){
			playerList.get(playerTurn).updateBalance(-1499);
		}
		//TEST COMMAND TO TAKE COMMUNITY CARD.
		else if(command.equalsIgnoreCase("gambino")){
			playerList.get(playerTurn).setPosition(3);
			squareInfo();
		}
		//TEST CASE TO GO TO JAIL.
		else if(command.equalsIgnoreCase("family reunion")){
			goToJail();
		}

		else if(command.equalsIgnoreCase("card")){
			gooJ();
		}

		else if(command.equalsIgnoreCase("buy")){
			buyFunction();
		}

		else if(command.equalsIgnoreCase("property")){
			propertyFunction();
		}

		else if(command.toLowerCase().startsWith("mortgage ")){
			String propInputName = command.substring(9);
			mortgageFunction(propInputName);
		}

		else if(command.toLowerCase().startsWith("redeem ")){
			String propInputName = command.substring(7);
			redeemFunction(propInputName);
		}

		else if(command.equalsIgnoreCase("done")){
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
			output.append("\nYour balance: " + (char)POUND + playerList.get(playerTurn).getBalance() + "\n");
		}

		else if(command.equalsIgnoreCase("bankrupt")){
			bankruptFunction();
		}

		else if(command.equalsIgnoreCase("quit")){
			highestPlayer();
			playerList.clear();
			repaint();
		}

		else if(command.equalsIgnoreCase("pay")){
			payBail();
		}

		else if(command.equalsIgnoreCase("jail")){
			goToJail();
		}

		else if(chooseFineOrChance && (command.equalsIgnoreCase("pay fine") || command.equalsIgnoreCase("chance")) ){
			fineOrChance(command);
		}

		else {
			output.append("\nInvalid command, enter 'help' for a list of commands.\n");
		}
	}

	public void setupGame(String command){
		Collections.shuffle(communityCards);							//Shuffle the decks.
		Collections.shuffle(chanceCards);

		String[] commands = {"done", "roll", "help", "buy", "pay", "property", "balance", "build", "mortgage"};
		for(String s : commands){
			s = s.toLowerCase();
			if(command.startsWith(s)){
				output.append("\nThis name is too similar to available commands. Try another!\n");
				return;
			}
		}

		if(command.equals("")){
			output.append("Can't have that name!\n");
			return;
		}

		if(!playerList.isEmpty()){
			for(Player p : playerList){
				if(command.equals(p.getName())){
					output.append("\nSomeone already has this name, please select another\n");
					return;
				}
			}
		}

		playerList.add(new Player(playersEntered, command));
		add(playerList.get(playersEntered));
		output.append("Player " + (playersEntered+1) + " name : " + command + "\n");
		revalidate();
		repaint();
		playersEntered++;

		if(playersEntered == numberOfPlayers){											//If all players added, begin.
			output.append("Roll to see who goes first.\n");
			goFirst();
			output.append("\n" + playerList.get(playerTurn).getName() + " goes first.\n\nEnter 'roll'.\n");
		}

		return;
	}

	public void squareInfo(){
		//Get property at location of current players turn.
		Player currPlayer = playerList.get(playerTurn);
		Property tmpProperty = properties.get(currPlayer.getPosition());
		String info;

		propertyCard.setIcon(tmpProperty.getImage());



		if(currPlayer.getPosition() == 30){
			goToJail();
		}

		if(tmpProperty.returnOwner() == null){									 				//i.e. Unbuyable property, just return name for now.
			info = "\n" + tmpProperty.returnName() + "\n";

			if(currPlayer.getPosition() == 10){	//If Visiting Jail.
				info = "\nJail (Just Visiting.)\n";
			}

			if(tmpProperty.returnRent() != 0){													//if rent exists (Rent & Unbuyable means it is a tax square.)
				payRentFunction();
				info = "\n" + tmpProperty.returnName() 
				+ "\nYou have paid a fine of " + (char)POUND + tmpProperty.returnRent() + ".\n";
			}
		}
		else if(tmpProperty.returnOwner() < 0){													//If owner<0 i.e. buyable property
			info = tmpProperty.returnName() 
					+ " ; \n-This property is on the market for " 
					+ (char)POUND + tmpProperty.returnPrice() 
					+ ".\n\nEnter 'buy' if you wish to purchase this property.\n"
					+ "Enter 'help' for all other commands\n";
		}

		else if(tmpProperty.returnOwner() == playerTurn){										//Owner is current player.
			info = tmpProperty.returnName() + " :\nThis is your property.";
			if(tmpProperty.returnColour() == 8){
				info = info + "\nIt has rent of " 
						+ (char)POUND + "25, " 
						+ (char)POUND + "50, " 
						+ (char)POUND + "100 or " 
						+ (char)POUND + "200 with 1, 2, 3 or 4 Stations owned respectively.\n";
			}
			if(tmpProperty.returnColour() == 8){
				info = info + "\nIt has rent of 4 or 10 times Dice Roll with 1 or 2 Utilities owned respectively.\n";
			}
		}

		else if(tmpProperty.isMortgage() == true){																//IF property is mortgaged.
			info = tmpProperty.returnName() + " is currently mortgaged. No rent paid.\n";
		}

		else{											
			info = tmpProperty.returnName() + "\n------------------------------------------------------------------------------------------\n"
					+ "\n" + tmpProperty.returnName() + "\n"
					+ "\n" + playerList.get(tmpProperty.returnOwner()).getName()								//If property owned.
					+ " owns this property, you have paid them "+ (char)POUND + payRentFunction() + ".\n"
					+ "\nYour balance is now " + (char)POUND + currPlayer.getBalance() +".\n";																				//
		}

		output.append(info);

		if (currPlayer.getPosition() == 2 || currPlayer.getPosition() == 17 || currPlayer.getPosition() == 33){	//Draw Community Chest.
			takeCard(communityCards);
		}
		if (currPlayer.getPosition() ==  7|| currPlayer.getPosition() == 22 || currPlayer.getPosition() == 36){	//Draw Chance.
			takeCard(chanceCards);
		}
	}


	public void checkJail(){
		Player tmpPlayer = playerList.get(playerTurn);
		if(Dice.allowedRoll == 0 && tmpPlayer.inJail() == true){
			output.append("\n" + playerList.get(playerTurn).getName() +"'s turn.\n");
			output.append("You are in jail\n\n"
					+ "To be released you must do one of these:\n"
					+ "1) Use a 'Get out of Jail Free' card\n"
					+ "2) Pay a fine of " + (char)POUND + "50\n"
					+ "3) Roll doubles\n"
					+ "If you do not roll doubles by your 3rd turn in jail you must pay the fine of " + (char)POUND + "50.\n\n"
					+ "Enter 'card', 'roll', or 'pay' to proceed.\n");

			if(tmpPlayer.getJailRoll() > 0 && tmpPlayer.getJailRoll() <= 2){
				output.append("You have " + tmpPlayer.getJailRoll() +" attempts left to roll doubles before you must pay the fine.\n");
			}
		}
	}

	public void takeCard(CardList deck){
		processCard(deck.get(0));

		if(deck.get(0).returnType() == 5){						//If GooJ card, add to jailCards and remove from deck.
			jailCards.add(deck.get(0));
			deck.remove(deck.get(0));
		} else {												//Move to end of deck.
			deck.add(deck.get(0));
			deck.remove(0);
		}
	}

	public void processCard(Card card){
		int type = card.returnType();
		Player currPlayer = playerList.get(playerTurn);

		output.append(card.returnMessage());								//Display card's message.

		switch(type){

		//Move to specific square.
		case 1 :																							
			if(currPlayer.getPosition() > card.returnToSquare()){
				currPlayer.updateBalance(200);
				output.append("\nYou've passed GO!\n" + (char)POUND + "200 has been added to your balance.\n");
			}
			currPlayer.setPosition(card.returnToSquare());					//Move player to square given by card.
			squareInfo();
			break;

		//Go To Jail
		case 2 :
			goToJail();
			break;

		//Receive Money or Pay a fine/expense.
		case 3 :
			currPlayer.updateBalance(card.returnMoney());
			break;

		//Collect money from every player.
		case 4 :
			for(Player p : playerList){
				if (p.getNumber() != playerTurn){							//If it isn't this player's turn
					p.updateBalance(- card.returnMoney());					//Update player's balance with negative value of returnMoney()
					currPlayer.updateBalance(card.returnMoney());			//Update currPlayer with positive value.
				}
			}
			break;

		//Give player a Get out of Jail free card, (recorded as int).
		case 5 :
			currPlayer.updateGooJ(1);
			break;

		//Move a set amount of steps.
		case 6 :
			currPlayer.setPosition(currPlayer.getPosition() + card.returnSteps());
			squareInfo();
			break;

		//Pay
		case 7 :
			int houseRepairs, hotelRepairs;
			if(card.returnMessage().startsWith("Make")){
				houseRepairs = 25;
				hotelRepairs = 100;
			} else {
				houseRepairs = 40;
				hotelRepairs = 115;
			}


			int numberHouses = 0, numberHotels = 0;
			for(Property p : properties){												//Increment through all properties.
				if(p.returnOwner() != null && p.returnOwner() == playerTurn && p.returnHouses() > 0){
					if (p.returnHouses() < 5){
						currPlayer.updateBalance(- (houseRepairs*p.returnHouses()));	//Charge 'houseRepairs' per house built
						numberHouses += p.returnHouses();					
					}
					if (p.returnHouses() == 5){
						currPlayer.updateBalance(-hotelRepairs);						//Charge 'hotelRepairs' per hotel
						numberHotels++;
					}		
				}
			}
			if(numberHotels != 0 || numberHouses != 0){
				output.append("\nHouses : " + numberHouses + "\nYou have paid " + (char)POUND + houseRepairs*numberHouses + ".\n");
				output.append("\nHotels : " + numberHotels + "\nYou have paid " + (char)POUND + hotelRepairs*numberHotels + ".\n");
			}
			else output.append("\nYou do not own any Houses or Hotels.\n");
			break;

		//Choice between Fine or Chance.
		case 8 : 
			chooseFineOrChance = true;
		}
	}

	//Function to roll dice and move player.
	public void rollFunction(){
		Player tmpPlayer = playerList.get(playerTurn);
		if(chooseFineOrChance){
			output.append("\nYou cannot roll without choosing to pay a fine or draw a card.\nType 'pay' or 'fine'.\n");
			return;
		}
		if (tmpPlayer.getBalance() < 0){
			output.append("\nYou cannot roll again while in debt.\n");
			return;
		}

		else if (playerList.get(playerTurn).inJail() == true){
			tmpPlayer = playerList.get(playerTurn);
			int thisRoll = Dice.Roll();

			if(Dice.allowedRoll == 8 || Dice.allowedRoll == 9){ 							//If player tries to roll after just been sent to jail.
				output.append("\nYou can't roll as you have just been sent to jail\n");
			}
			else if(Dice.allowedRoll == 2){ 												//If the player rolled doubles while in jail.
				output.append("\n"+ Dice.words() + "\n");
				output.append("Congratulations, you rolled doubles! You're free to go. \n");
				tmpPlayer.setJail(false);
				tmpPlayer.setPosition((tmpPlayer.getPosition()+ thisRoll)%40);
				squareInfo();
				Dice.allowedRoll = 1;
			}
			else {
				output.append("\n"+ Dice.words() + "\n");
				tmpPlayer.failedJailRoll(); 									//Reduce number of attempts left.
				output.append("\nYou failed to roll doubles.\n");
				if(tmpPlayer.getJailRoll() == 0){ 								//If all attempts are used up.
					output.append("You have no more attempts left.\n");
					payBail();
					Dice.allowedRoll = 0;
				}
				else {
					output.append("You have " + tmpPlayer.getJailRoll() +" attempts left before you must pay the " + (char)POUND + "50 fine.\n");
					//Move to next player.
					Dice.allowedRoll = 0;
					playerTurn = (playerTurn+1)%numberOfPlayers;
					if(playerList.get(playerTurn).inJail() == false){
						output.append("\n" + playerList.get(playerTurn).getName() +"'s turn. Roll.\n");
					}
					checkJail();
				}
			}
		}
		else if(Dice.allowedRoll == 0 || Dice.allowedRoll == 2 || Dice.allowedRoll == 4){

			int thisRoll = Dice.Roll();

			if((tmpPlayer.getPosition()+ thisRoll)%40 < tmpPlayer.getPosition()){
				tmpPlayer.updateBalance(200);
				output.append("\nYou've passed GO!\n" + (char)POUND + "200 has been added to your balance.\n");
			}

			tmpPlayer.setPosition((tmpPlayer.getPosition()+ thisRoll)%40);
			output.append("\n"+ Dice.words() + "\n");




			if(Dice.allowedRoll != 6){ 										//If not sent to jail, show the information about buying houses. 
				squareInfo();
			}
			if(Dice.allowedRoll == 2 || Dice.allowedRoll == 4){
				output.append("\nYou are able to roll again!\n");
			}
			else if (Dice.allowedRoll == 6){
				goToJail();
				Dice.allowedRoll = 0;
				playerTurn = (playerTurn+1)%numberOfPlayers;
				if(playerList.get(playerTurn).inJail() == false){
					output.append("\n" + playerList.get(playerTurn).getName() +"'s turn. Roll.\n");
				}
				checkJail();
			}

		}
		else{
			output.append("\nYou cannot roll again.\n");
		}
	}

	//Function to return legal commands.
	public void helpFunction(){
		output.append("\n'roll' : Roll dice.\n"
				+ "'buy' : Buy property of square you landed on.\n"
				+ "'property' : Query the properties you currently own.\n"
				+ "'balance' : Query your current balance.\n"
				+ "'build <property short name> <number of houses>': Build houses on a property you own\n"
				+ "'demolish <property short name> <number of houses>' : Demolish houses on a property you have built on\n"
				+ "'mortgage <property short name>' : Mortgage one of your properties.\n"
				+ "'redeem <property short name>' : Redeem a property you've mortgaged.\n"
				+ "'done' : Finish your turn.\n"
				+ "'quit' : Quit the game.\n");
	}


	//Function to handle bankruptcy.
	public void bankruptFunction(){
		Player currPlayer = playerList.get(playerTurn);
		if(currPlayer.getBalance() < 0 && ! playerOwnsAssets()){											//If player has a negative balance and owns no property.
			bankrupt = true;
			output.append("\nYou have declared bankruptcy, you will be removed from the game.\n");
			doneFunction();
		}
		else{
			bankrupt = false;
			output.append("\nCannot declare bankruptcy if balance is positive or if assets are owned.\n");
		}
	}

	public boolean playerOwnsAssets(){
		for(Property p : properties){
			if(p.returnOwner() != null && p.returnOwner() == playerTurn && ! p.isMortgage()){				//Property is ownable, owned by this palyer and not mortgaged.
				return true;
			}
		}
		return false;
	}

	//Function to end turn. Also takes bankruptcy into account.
	public void doneFunction(){
		//If player has drawn a card of type 8, they must choose to pay a fine or draw a chance card before ending turn or rolling.
		if(chooseFineOrChance){
			output.append("\nYou cannot end your turn without choosing to pay a fine or draw a card.\nType 'pay' or 'fine'.\n");
			return;
		}

		Player currPlayer = playerList.get(playerTurn);
		//If bankruptcy declared, remove player, check if game is finished otherwise carry on.
		if(bankrupt){																//If out of money.								
			releasePropertyFunction();													//Return properties to Market.
			playerList.remove(playerTurn);												//Remove player from game.
			numberOfPlayers--;															//Player Turn stays on same index, unless last player removed.
			output.append("\n" + currPlayer.getName()	+ " has been removed from the game\n");		
			repaint();
			playerTurn = (playerTurn)%numberOfPlayers;	


			if(numberOfPlayers == 1){													//If player is last remaining player.
				winner = playerTurn;
				output.append("\nGame Over. Winner is " + playerList.get(winner).getName() + "\n");
				playerList.clear();														//Remove final player.
				repaint();
				return;
			}

			else{
				bankrupt = false; 														//reset bankruptcy
				Dice.allowedRoll = 0;													//Allow player to roll again.
				if(playerList.get(playerTurn).inJail() == false){
					propertyCard.setIcon(null);
					output.append("\n" + playerList.get(playerTurn).getName() +"'s turn. Roll.\n");
				}
				checkJail();

			}
			return;

		}


		if (currPlayer.getBalance() < 0){																	//If rent not paid, not allowed end.
			output.append("\nYou are in arrears of " + (char)POUND + currPlayer.getBalance() + "\nYou must sell assets or declare bankruptcy.\n");
			return;
		}

		else if (Dice.allowedRoll == 0 || Dice.allowedRoll == 2 || Dice.allowedRoll == 4){						// If dice not rolled, not allowed end.
			output.append("\nYou cannot end your turn without rolling.\n");
		}
		else if(Dice.allowedRoll != 0 && Dice.allowedRoll != 2 && Dice.allowedRoll != 4){						//If dice rolled and rent-paid allowed end turn.
			Dice.allowedRoll = 0;
			playerTurn = (playerTurn+1)%numberOfPlayers;
			if(playerList.get(playerTurn).inJail() == false){
				propertyCard.setIcon(null);
				output.append("\n" + playerList.get(playerTurn).getName() +"'s turn. Roll.\n");
			}
			checkJail();
		}
	}

	//Function to send player to Jail
	public void goToJail(){
		Dice.allowedRoll = 7;
		Player currPlayer = playerList.get(playerTurn);
		output.append("\nYou have been sent to jail.\n\n"
				+ "To be released you must do one of these:\n"
				+ "1) Use a 'get out of jail free' card \n"
				+ "2) Pay a fine of " + (char)POUND + "50 \n"
				+ "3) Roll doubles \n"
				+ "If you do not roll doubles by your 3rd turn in jail you must pay the fine of " + (char)POUND + "50.\n\n"
				+ "Enter 'roll', 'card', or 'pay' to proceed\n");
		currPlayer.setPosition(10); 	//Move to jail square.
		currPlayer.setJail(true); 		//Record player as in jail.
		currPlayer.setJailRoll(); 		//Give 3 attempts to roll doubles.

	}

	//Function to get out of jail if you have a Card.
	public void gooJ(){
		Player currPlayer = playerList.get(playerTurn);
		if (currPlayer.inJail()){
			if(currPlayer.returnGooJ() > 0){		//If player has GooJ Card.
				currPlayer.setJail(false);			//Release from jail.
				currPlayer.updateGooJ(-1);			//Remove GooJ card.

				if (jailCards.get(0).returncommunityChance().equalsIgnoreCase("Chance")){	//If GooJ card is a chance card return to chance deck.
					chanceCards.add(jailCards.remove(0));
				}
				else{
					communityCards.add(jailCards.remove(0));								//Else return GooJ card to Community Deck.
				}

				output.append("\nYou have used a GooJ Card, you're free to go!\nYou now have " + currPlayer.returnGooJ() + " cards.\n");
			}
			else output.append("\nYou do not have a GooJ card.\n");
		}
		else{
			output.append("\nYou are not in Jail, you cannot use a GooJ card.\n");
		}

	}

	public void payBail(){
		Player currPlayer = playerList.get(playerTurn);
		if(currPlayer.inJail() == true){
			currPlayer.updateBalance(-50);
			currPlayer.setJail(false);
			output.append("You have paid a " + (char)POUND + "50 bail and are now free.\n");
		}
		else {
			output.append("You are not in jail.\n");
		}
	}

	//Function to allow player to pay a fine or else draw another card if a type 8 card is draw.
	public void fineOrChance(String command){
		Player currPlayer = playerList.get(playerTurn);
		if(command.equalsIgnoreCase("pay fine")){
			output.append("\nYou have paid the fine.\n");
			currPlayer.updateBalance(-10);
		}
		else if(command.equalsIgnoreCase("chance")){
			output.append("\nYou have drawn a Chance Card.\n");
			takeCard(chanceCards);
		}

		chooseFineOrChance = false;
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

		}
		else{
			output.append("\nYou cannot afford this property.\n");
		}
	}

	//Function to return owned properties for current Player.
	public void propertyFunction(){
		int count = 0;
				
		for(Property p : properties){
			if(p.returnOwner() != null && p.returnOwner() == playerTurn){
				if (p.isMortgage() == true){
					output.append("\n" + p.returnName() + " (Mortgaged):\n" 
							+ "-The rent is " + (char)POUND + p.returnRent() + ".\n" 
							+ "-The redeem cost is " + (char)POUND + p.returnRedeemValue() + ".\n");
				} else {
					output.append("\n" + p.returnName() + ":\n" 
							+ "-The rent is " + (char)POUND + p.returnRent() + ".\n" 
							+ "-The mortgage value is " + (char)POUND + p.returnMortgageValue() + ".\n");
				}
				
				if(p.returnHousePrice() != -1){																	//If it's a station.
					output.append("-The house price is " + (char)POUND + p.returnHousePrice() + ".\n");
				}
				
				count++;
			}
		}
		
		if (count == 0){
			output.append("\nYou do not own any property.\n");
		}
	}

	//Function to mortgage a property for the current player.
	public void mortgageFunction(String propInputName){
		boolean found = false;

		for(Property p: properties){													//Cycle through all properties.
			if(p.returnOwner() != null && propInputName.equalsIgnoreCase(p.returnShortName())){	//All properties owned that's short name equals the name inputed.
				if(p.returnOwner() == playerTurn){													//Condition to make sure it's owned by current player. (Separated for else)
					if(p.isMortgage() == false){														//Checking the property hasn't been mortgaged yet. (Separated for else)
						if (p.returnColour() > 7 || p.returnHouses() == 0){									//Making sure it's either a station or has no houses. (Separated for else)

							p.mortgage();
							playerList.get(playerTurn).updateBalance(p.returnMortgageValue());
							output.append("\nYou have mortgaged " + p.returnName() + " for " + (char)POUND + p.returnMortgageValue() + ".\n");

						} else {
							output.append("You must sell all houses first.\n");
						}
					} else {
						output.append("\nThis property has already been mortgaged.\n" + p.isMortgage());
					}
				} else {
					output.append("\nYou do not own this property.\n");
				}
				found = true;
			}
		}

		if(found == false){															//Outputting for when property wasn't found.
			output.append("\nProperty not found.\nEnter the short name for a property you own.\n");
		}
	}

	//Function to redeem a property. (Similar layout as mortgageFunction. If confused, look at corresponding lines there for comments.)
	public void redeemFunction(String propInputName){
		boolean found = false;
		Player currPlayer = playerList.get(playerTurn);

		for(Property p: properties){
			if(p.returnOwner() != null && propInputName.equalsIgnoreCase(p.returnShortName())){
				if (p.returnOwner() == playerTurn){
					if(p.isMortgage() == true){
						if(currPlayer.getBalance() >= p.returnRedeemValue()){					//Checking the player has enough money to redeem.

							playerList.get(playerTurn).updateBalance(p.returnRedeemValue());
							p.redeem();
							output.append("\nYou have redeemed " + p.returnName() + " for " + (char)POUND + p.returnRedeemValue() + "\n");

						} else {
							output.append("\nYou do not have sufficient funds.\n");
						}
					} else {
						output.append("\nThis property is not currently mortgaged.\n");
					}
					found = true;
				} else {
					output.append("\nYou do not own this property.\n");
				}
			}
		}

		if(found == false){
			output.append("\nProperty not found.\nEnter the short name for a property you've mortgaged.\n");
		}
	}

	//Build a house on the property.
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
				propertyNumber = -(propertyNumber);												//If demolishing, we must update houses with a negative number.

		}
		else{
			output.append("\nTo use build or demolish commands, use the form;\n'build/demolish <property short name> <number of houses as digit>'\n");
			return;
		}

		Player currPlayer = playerList.get(playerTurn);
		Property currProperty = null;
		for(Property p : properties){															//Iterate through properties until one matching entered name is found.
			if(p.returnShortName()!= null && p.returnShortName().equalsIgnoreCase(propertyName)){
				currProperty = p;
				break;
			}
		}
		if(currProperty == null){
			output.append("\nEntered Property Name is not valid.\n"
					+ "Hint : Enter first part of name except;\n"
					+ "For Pall Mall enter 'Mall'\n"
					+ "For Old Kent Road enter 'kent'\n"
					+ "For The Angel Islington type 'Islington'\n"
					+ "For King's Cross Station enter 'Kings'\n");
			return;
		}
		if(currProperty.returnOwner() == null || currProperty.returnOwner() != playerTurn ){ 		//Check ownership.
			output.append("\nYou don't own this property\n");
			return;
		}
		if(currProperty.returnColour() > 7 || currProperty.returnColour() < 0){													//House prices set to '-1' to indicate they can't be purchased.
			output.append("\nNeither stations nor utilities can be developed\n");
			return;
		}
		else if (currPlayer.getBalance() >= currProperty.returnHousePrice() && canBuild(currProperty.returnColour()) == true){ //Check if they can afford it and if they own all the colour group.


			if(currProperty.setHouses(currProperty.returnHouses() + propertyNumber) != null){		//Adjust amount of houses to new level if possible.
				int houses = currProperty.returnHouses();

				if(houses == 5){
					output.append("\nThere is now a hotel on " + currProperty.returnName() +"\n");
				}
				else if(houses != 1){																										//Case of subsequent houses built.
					output.append("\nThere are now " + currProperty.returnHouses() + " houses on " + currProperty.returnName() +"\n");
				}
				else{																														//Case of first house built.
					output.append("\nThere is now " + currProperty.returnHouses() + " house on " + currProperty.returnName() +"\n");
				}
				if(command.equalsIgnoreCase("build")){
					currPlayer.updateBalance(-(currProperty.returnHousePrice()*propertyNumber));											//Remove cost of house from player balance.
					output.append("\nYou have spent " + (char)POUND + ((currProperty.returnHousePrice())*propertyNumber) + ".\n");
				}
				else{
					currPlayer.updateBalance((currProperty.returnHousePrice()/2)*-1*propertyNumber);										//Add half cost of houses to balance.
					output.append("\nYou have earned " + (char)POUND + ((currProperty.returnHousePrice())*-1*propertyNumber)/2 + ".\n");
				}

			}
			else{
				output.append("\nYou cannot perform further developments on this property.\n"
						+ "Maximum Building Level : 5\n"
						+ "Current Building Level : " + currProperty.returnHouses() + "\n");
			}
			return;
		}
		else if(currPlayer.getBalance() < currProperty.returnHousePrice()){				//Case that they can't afford to build.
			output.append("\nYou cannot afford to build here\n");
		}
		else{																			//Case that they don't own all properties.
			output.append("\nYou must own all properties of this colour group.\n");
		}
	}

	//If player owns all of colour and it is not a station or utility, he can build.
	public boolean canBuild(int colour){
		if(colour > 7){ //If Utility or Station
			return false;
		}
		for(Property p : properties){
			if(p.returnOwner()!= null && p.returnOwner() != playerTurn && p.returnColour() == colour){ 		
				return false;		//If any property of given colour isn't owned by current player, he can't build.																	
			}	
		}
		return true;				//Otherwise he can.
	}

	// Function to return properties to market if player has lost.
	public void releasePropertyFunction(){
		for(Property p : properties){
			if(p.returnOwner()!= null && p.returnOwner() == playerTurn){
				p.setOwner(-1);
			}
		}
	}

	//Function to payRent on current square.
	public int payRentFunction(){

		Player currPlayer = playerList.get(playerTurn);							//Get player
		Property currProperty = properties.get(currPlayer.getPosition());		//Get property
		int rent = currProperty.returnRent();

		//If Property is utility, rent is multiplied by dice roll
		if(currProperty.returnColour() == 9){
			for(Property p : properties){
				if(p.returnColour() == currProperty.returnColour() 
						&& p.returnOwner() == currProperty.returnOwner() //Iterate through properties, and if another found.
						&& !p.isMortgage()
						&& !p.equals(currProperty)){
					rent = 10;						//Set rent to second level.
				}
				else{
					rent = 4;						//Set rent to lower level.
				}
			}
			rent = rent*Dice.total();
		}

		//If Property is Station, rent changes depending on how many owned.
		if(currProperty.returnColour() == 8){
			int stationsOwned = 0;
			rent = 25;
			for(Property p : properties){
				if(p.returnColour() == currProperty.returnColour() 	//Iterate through stations, and count how many others.
						&& p.returnOwner() == currProperty.returnOwner() 
						&& !p.isMortgage()
						&& !p.equals(currProperty)){
					stationsOwned++;								//Set rent based on how many stations owned.
					rent = 25*2*stationsOwned;
				}	
			}

		}
		//If all properties of colour owned, but no houses on this property, double rent.
		else if(colourMultiplier()){
			rent = rent*2;
		}
		currPlayer.updateBalance(-(rent));
		if(currProperty.returnOwner() != null){										//If owner exists
			Player debtor = playerList.get(currProperty.returnOwner());				//Get owner of property
			debtor.updateBalance(rent);												//Pay rent to owner
		}



		return rent;
	}


	//If player owns all of colour group, and a square has no houses, the rent of the square with no houses is doubled.
	public boolean colourMultiplier(){
		Player currPlayer = playerList.get(playerTurn);	
		Property currProperty = properties.get(currPlayer.getPosition());
		int colour = currProperty.returnColour();
		if(canBuild(colour) && currProperty.returnHouses() == 0 ){				//Return true if allowed build houses, but none on square.
			return true;
		}
		else return false;														//Else return false

	}


	//Highest roll goes first, if two players roll highest then they re-roll.
	//Cycle through players in 'clockwise' order from highest roller.
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
			output.append(playerList.get(i).getName() + " : " + (char)POUND + playerList.get(i).getTotal() + " worth of properties "
					+ "and " + (char)POUND+ playerList.get(i).getBalance() +" current balance.\n"
					+ "Total: " + (char)POUND 
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
					+ " with " + (char)POUND + (playerList.get(winner).getTotal()+playerList.get(winner).getBalance()) + " worth of assets.\n");
		}
		if (draw == true){
			output.append("No Winner! There is a draw. ");
		}

	}

}