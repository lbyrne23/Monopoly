//Team : CessnaSkyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//
//Bot to play monopoly.

import java.util.ArrayList;

public class CessnaSkyhawk implements Bot {

	// The public API of YourTeamName must not change
	// You cannot change any other classes
	// YourTeamName may not alter the state of the board or the player objects
	// It may only inspect the state of the board and the player objects

	private int decision;
	private int stage = 0;
	private BoardAPI board;
	private PlayerAPI player;
	private DiceAPI dice;
	boolean allowedRoll;
	boolean wasInJail;
	ColourGroup brownProperty;
	ColourGroup lightBlueProperty;
	ColourGroup pinkProperty;
	ColourGroup orangeProperty;
	ColourGroup redProperty;
	ColourGroup yellowProperty;
	ColourGroup greenProperty;
	ColourGroup darkBlueProperty;

	CessnaSkyhawk (BoardAPI board, PlayerAPI player, DiceAPI dice) {
		allowedRoll = true;
		wasInJail = false;
		this.board = board;
		this.player = player;
		this.dice = dice;
		brownProperty = ((Site) board.getProperty(1)).getColourGroup();
		lightBlueProperty = ((Site) board.getProperty(6)).getColourGroup();
		pinkProperty = ((Site) board.getProperty(11)).getColourGroup();
		orangeProperty = ((Site) board.getProperty(16)).getColourGroup();
		redProperty = ((Site) board.getProperty(21)).getColourGroup();
		yellowProperty = ((Site) board.getProperty(26)).getColourGroup();
		greenProperty = ((Site) board.getProperty(31)).getColourGroup();
		darkBlueProperty = ((Site) board.getProperty(37)).getColourGroup();
		return;
	}


	public String getName () {
		return "YourTeamName";
	}


	public String getCommand () {

		switch (decision){
		case 0 : 
			return checkAllowedRoll();	// Decision is 1 if in jail, 2 if not.

		case 1 :
			return checkInJail();

		case 2 : 
			return inJail();

		case 3 :
			return roll();

		case 4 :
			return buyProperty();

		case 5 :
			return build();

		case 6 :
			return mortgage();

		case 7 :
			return demolish();

		case 8 :
			return bankrupt();

		case 9 : 
			return doneFunction();

		default : 
			decision = 0; // Send to checkAllowedRoll function.
			return "done";
		}
	}


	public String getDecision () {
		if ( player.getBalance() > 50){
			return "pay";
		} 
		else{
			return "chance";
		}
	}


	public String checkAllowedRoll(){
		if(dice.isDouble() && !player.isInJail() && !wasInJail){
			allowedRoll = true;
		}

		if (!allowedRoll){
			decision = 9; // Send to done function.
			return "";
		}

		decision = 1; 	// Send to checkInJail function.
		return ""; 		// Return null string, to move to next step.
	}


	public String checkInJail(){
		if(player.isInJail()){
			decision = 2; 	// Send to inJail function.
		}
		else{
			decision = 3; 	// Send to roll function.
		}

		return "";
	}


	public String roll(){
		if(allowedRoll){
			allowedRoll = false;	// If you get to roll, next time you can't roll unless the previous if statement is passed.
			decision = 4;			// Send to buy function.
			return "roll";
		}

		decision = 9;	// Send to done function.
		return "";
	}


	public String inJail(){
		if(!allowedRoll){
			decision = 9; // Send to inJail function.
			return "";
		}

		wasInJail = true;
		if(theirMonopoly() < 1){ 	// If we still have a chance to go around and get a monopoly.
			if(player.hasGetOutOfJailCard()){
				if(player.getBalance() > 600){
					decision = 0; 	// Send to checkAllowedRoll function.
					return "pay"; 	// Pay out to save card for low funds.
				}
				else { 
					decision = 0; 	// Send to checkAllowedRoll function.
					return "card";
				}
			}
			if(player.getBalance() > 50){
				decision = 0; 		// Send to checkAllowedRoll function.
				return "pay";
			}
		}

		allowedRoll = false;
		decision = 0; 				// Send to checkAllowedRoll function.
		return "roll";
	}


	public String buyProperty(){
		Property property;

		if (player.getBalance() < 300){			// Critical stage: Only buy sites if affordable.
			stage = 0;
		}
		else if (player.getBalance() < 2000){	// Average stage: Buy everything.
			stage = 1;
		} 
		else {									// Rich stage: Buy everything, possibility implementing a redeem capability in the future when here.
			stage = 2;
		}


		if (board.isProperty(player.getPosition()) 
				&& !board.getProperty(player.getPosition()).isOwned() ){	// If board is a property and not owned.

			property = board.getProperty(player.getPosition());

			switch (stage){
			case 0 : // Critical Stage
				if (player.getBalance() >= property.getPrice()){
					if (board.isSite(property.getShortName())){
						decision = 5; // Send to build function.
						return "buy";
					}
					else {
						decision = 5; // Send to build function.
						return "";
					}
				} 
				else {
					decision = 5; // Send to build function.
					return "";
				}


			case 1 : // Average Stage
				decision = 5; // Send to build function.
				return "buy";


			case 2 : // Rich Stage
				decision = 5; // Send to build function.
				return "buy";

			}
		}

		decision = 5;	// Send to build function.
		return "";		// Return null string when position is owned/not buyable.
	}


	public String build(){
		ColourGroup[] colours = {brownProperty, lightBlueProperty, pinkProperty, orangeProperty,
				redProperty, yellowProperty, greenProperty, darkBlueProperty};

		for(int max = 1; max < 5; max++){				// First loop tries to build all site to 3.
			for(int i = 0; i < colours.length; i++){
				ArrayList<Site> members = colours[i].getMembers();

				for(int j = 0; j < members.size(); j++ ){
					Site site = members.get(j);

					// Building if we can afford it.
					if(player.isGroupOwner(site) 
							&& site.getNumBuildings() < max 
							&& player.getBalance() > site.getBuildingPrice() 
							&& !site.isMortgaged()){
						decision = 5;	// Send to self.
						return ("build " + site.getShortName() + " 1" );
					}
				}
			}
		}

		decision = 6; // Send to mortgage function.
		return ""; 
	}


	public String mortgage() {
		if(player.getBalance() < 0){

			/*---------------Mortgage Stations and Utilities First----------*/
			ArrayList<Property> properties = player.getProperties();
			int sizeProperties = player.getNumProperties();

			//Mortgage Utilities
			for( int i = 0; i < sizeProperties; i++){
				for (Property p : properties) {
					if (p instanceof Utility) { 
						if(!p.isMortgaged()){ 
							String name = p.getShortName();
							return "mortgage " + name;
						}
					}
				}
			}


			//Mortgage Stations
			for(int i = 0; i < sizeProperties; i++){
				for (Property p : properties) {
					if (p instanceof Station) {
						if(!p.isMortgaged()){
							String name = p.getShortName();
							return "mortgage " + name;
						}
					}
				}
			}


			ColourGroup[] desire = {brownProperty, lightBlueProperty, darkBlueProperty, greenProperty, pinkProperty,
					yellowProperty, redProperty, orangeProperty};

			int i;
			int j;
			Site site;



			/*--------- Check if other player owns a property on a colour group ---------*/
			int playerOwns;
			int otherPlayerOwns;

			for(i = 0; i < 8; i++){ 				// Go through each colourGroup.
				int groupSize = desire[i].size(); 	// Get the size. (i.e. brown = 2, pink = 3)
				playerOwns = 0;
				otherPlayerOwns = 0;

				// Find a colour group where both players own something.
				for(j = 0; j < groupSize; j++){ 		// Go through each member of the group.
					site = desire[i].getMembers().get(j);
					Player owner = site.getOwner(); 	// Get the player who owns the property.

					if(owner != null && owner.equals(player) && site.isMortgaged() == false){
						playerOwns++; 					// Record if player owns something in this colour.
					}
					if(owner != null && !owner.equals(player)){
						otherPlayerOwns++; 				// Record if another player owns something in this colour.
					}
				}

				// Find the first property we own in this colour group that isn't already mortgaged.
				if(playerOwns>0 && otherPlayerOwns > 0){ 	// We own a property of this colour and so does another player.
					for(j = 0; j < groupSize; j++){ 
						site = desire[i].getMembers().get(j);
						Player owner = site.getOwner(); 		// Get the player who owns the property.
						String shortName = site.getShortName(); // Get the short name.

						if(owner != null 
								&& owner.equals(player) 
								&& site.isMortgaged() == false 
								&& site.getNumBuildings() == 0){
							decision = 6;						// Send to self.
							return "mortgage "+ shortName; 		// Mortgage this property.
						}
					}
				}
			}

			/*----------- Check if we are far away from a Monopoly ---------*/
			for(i = 0; i < 8; i++){ 	// Go through each colourGroup.
				playerOwns = 0;
				int groupSize = desire[i].size(); 		// Get the size (i.e. brown = 2, pink = 3)

				//Find a colour group where we own something.
				for(j = 0; j < groupSize; j++){ 			// Go through each member of the group.
					site = desire[i].getMembers().get(j);
					Player owner = site.getOwner(); 		// Get the player who owns the property.
					if(owner != null 
							&& owner.equals(player) 
							&& site.isMortgaged() == false 
							&& site.getNumBuildings() == 0){
						playerOwns++; 						// Record if player owns something in this colour.
					}
				}

				// Mortgage if we are too far from a monopoly.
				if(playerOwns == 1){
					for(j = 0; j < groupSize; j++){ 
						site = desire[i].getMembers().get(j);
						Player owner = site.getOwner(); 		// Get the player who owns the property.
						String shortName = site.getShortName(); // Get the short name.
						if(owner != null 
								&& owner.equals(player) 
								&& site.isMortgaged() == false 
								&& site.getNumBuildings() == 0){
							decision = 6;						// Send to self.
							return "mortgage "+shortName; 		// Mortgage this property.
						}
					}
				}

				// Mortgaging properties where we only own 1 of the colour group has failed.
				// Try mortgaging a property where we only own 2 of the group.
				if(playerOwns == 2){
					for(j = 0; j < groupSize; j++){ 
						site = desire[i].getMembers().get(j);
						Player owner = site.getOwner(); 		// Get the player who owns the property.
						String shortName = site.getShortName(); // Get the short name.
						if(owner != null 
								&& owner.equals(player) 
								&& site.isMortgaged() == false 
								&& site.getNumBuildings() == 0){
							decision = 6;						// Send to self.
							return "mortgage " + shortName; 	// Mortgage this property.
						}
					}
				}

				if(playerOwns == 3){
					for(j = 0; j < groupSize; j++){ 
						site = desire[i].getMembers().get(j);
						Player owner = site.getOwner(); 		// Get the player who owns the property.
						String shortName = site.getShortName(); // Get the short name.
						if(owner.equals(player) && site.isMortgaged() == false && site.getNumBuildings() == 0){

							decision = 6;						// Send to self.
							return "mortgage "+shortName; 		// Mortgage this property.
						}
					}
				}
			}
		}

		decision = 7;	// Send to demolish function.
		return ""; 		// Return the Demolish Function if balance > 0.
	}


	public String demolish(){
		ColourGroup[] colours = {brownProperty, lightBlueProperty, pinkProperty, orangeProperty,
				redProperty, yellowProperty, greenProperty, darkBlueProperty};
		if(player.getBalance() < 0){
			for(int min = 1; min < 6; min++){				// Demolish house with least property, this amount of property is recorded by 'min'.

				for(int i = 0; i < 8; i++){					// Loop through different colour groups.
					ArrayList<Site> members = colours[i].getMembers();

					for(int j = 0; j < members.size(); j++){// Loop through all members of colour group.
						Site site = members.get(j);

						//Demolish if we own the colour group, the amount of buildings is equal to min and we have a negative balance. 
						if(player.isGroupOwner(site) && site.getNumBuildings() == min && player.getBalance() < 0){
							decision = 6; // Send to mortgage function.
							return ("demolish " + site.getShortName() + " 1");
						}
					}
				}
			}
		}
		// If no property is demolished.
		decision = 8; // Send to bankrupt function.
		return ""; 
	}


	public String bankrupt(){
		if(player.getBalance() >= 0){
			decision = 9;	// Send to done function.
			return "";
		}

		for(int i = 0; i<40; i++){
			if (board.isProperty(i)){
				Property property = board.getProperty(i);
				if(property.getOwner() != null && property.getOwner() == player && !property.isMortgaged() ){
					decision = 6; // Send to mortgage function.
					return "" ;
				}
			}
		}

		decision = 9; // Send to done function.
		return "bankrupt";
	}


	public String doneFunction(){
		if(allowedRoll){
			decision = 0; 	// Send to checkAllowedRoll function.
			return "";
		}
		wasInJail = false;
		allowedRoll = true;
		decision = 0;		// Send to checkAllowedRoll function.
		return "done";
	}


	// Function to check how many monopolies they have.
	public int theirMonopoly(){

		ColourGroup[] groups = {brownProperty, lightBlueProperty, pinkProperty, orangeProperty,
				redProperty, yellowProperty, greenProperty, darkBlueProperty};

		int opponent = 0; 			// To record individual properties in a colour group.
		int theirMonopolies = 0;
		int i = 0;
		int j = 0;
		Site site = groups[i].getMembers().get(j); 

		for(i = 0; i < 8; i++){ 							// Go through each colour group.
			opponent = 0; 									// Reset.
			for(j = 0; j < groups[i].size(); j++){ 			// Go through each individual property.
				Player owner = site.getOwner();
				if(owner != null && !owner.equals(player)){ // Record if other player owns a property.
					opponent++;
				}
			}

			if(opponent == groups[i].size()){
				theirMonopolies++; 	//If amount of properties owned by other player matches size of group, record as monopoly.
			}
		}
		return theirMonopolies;
	}
}
