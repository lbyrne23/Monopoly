import java.util.ArrayList;

//Team : CessnaSkyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//
//



public class YourTeamName implements Bot {

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

	YourTeamName (BoardAPI board, PlayerAPI player, DiceAPI dice) {
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


		System.out.println(decision);
		switch (decision){
		case 0 : 
			return checkAllowedRoll();	//decision is 1 if in jail, 2 if not.
		case 1 :

			return checkInJail();


		case 2 : 
			return inJail();
		case 3 :
			return roll();
		case 4 :
			return doneFunction();


		default : 
			decision = 0;
			return "done";
		}
	}

	public String getDecision () {
		// Add your code here
		return "pay";
	}

	public String checkAllowedRoll(){
		if(dice.isDouble() && !player.isInJail() && !wasInJail){
			allowedRoll = true;
		}
		if(allowedRoll){
			decision = 1; //go to check jail
		}
		else{
			decision = 4; //go to done.
		}

		return ""; //Return null string, to move to next step.
	}

	public String checkInJail(){
		if(player.isInJail()){
			decision = 2;	//go to jail function
		}
		else{
			decision = 3;	//go to roll function
		}
		return "";
	}

	public String roll(){
		if(allowedRoll){
			allowedRoll = false;	//If you get to roll, next time you can't roll unless the previous if statement is passed.
			decision = 0;
			return "roll";
		}

		decision = 4;
		return ""; //Don't roll, send to 4, currently 'done'.

	}

	public String inJail(){
		if(!allowedRoll){
			decision = 4;
			return "";
		}
		wasInJail = true;
		System.out.println(player.isInJail());
		if(player.getNumProperties() < 10){
			if(player.getBalance() > 50){
				decision = 0;
				return "pay";
			}

		}


		allowedRoll = false;
		return "roll";

	}

	public String buyProperty(){
		decision = 2;	//Sets choice for next time getCommand() is called.

		Property property;

		if (player.getBalance() < 300){	//Critical stage: Only buy sites if affordable.
			stage = 0;
		}
		else if (player.getBalance() < 2000){	//Average stage: Only buy sites
			stage = 1;
		} 
		else {	//Rich stage: Buy everything.
			stage = 2;
		}
	
		
		if (board.isProperty(player.getPosition()) 
				&& !board.getProperty(player.getPosition()).isOwned() ){		//If board is property and not owned.
			
			property = board.getProperty(player.getPosition());

			switch (stage){
			case 0 :	//Critical Stage
				if (player.getBalance() >= property.getPrice()){
					if (board.isSite(property.getShortName())){
//						decision = 
						 return "buy";
					}
					else {
//						decision = 
						return "";
					}
				} 
				else {
					System.out.println("Balance < property price.");
//					decision = 
					return "";
				}
				
			case 1 :	//Average Stage
				if ( board.isSite(property.getShortName()) ){	
//					decision = 
					return "buy";
				}
				else {
//					decision = 
					return "";
				}
			
			case 2 :	//Rich Stage
//				decision = 
				return "buy";
				
			default :
				System.out.println("This should not have been printed.");
			}
		}

//		decision = 
		return "";	//Return null string when position is owned/not buyable.
	}

	public String build(){
		ColourGroup[] colours = {brownProperty, lightBlueProperty, pinkProperty, orangeProperty,
				redProperty, yellowProperty, greenProperty, darkBlueProperty};

		for(int max = 3; max < 5; max++){	//First loop tries to build all site to 3,
			for(int i = 0; i < colours.length; i++){
				ArrayList<Site> members = colours[i].getMembers();

				for(int j = 0; j < members.size(); j++ ){
					Site site = members.get(j);
					//BUILD IF WE CAN AFFORD.
					if(player.isGroupOwner(site) && site.getNumBuildings() < max && player.getBalance() > site.getBuildingPrice()){
						return ("build " + site.getShortName() + " 1" );
						//decision = x SEND TO SELF
					}
				}

			}
		}
		//SEND ELSEWHERE
		return ""; 
}
	public String mortgage() {
		while( player.getBalance() < 0){
			ColourGroup[] desire = {brownProperty, lightBlueProperty, darkBlueProperty, greenProperty, pinkProperty,
					yellowProperty, redProperty, orangeProperty};

			int i = 0;
			int j = 0;
			Site site = desire[i].getMembers().get(j);
			
			

			/*--------- Check if other player owns a property on a colour group ---------*/
			int playerOwns;
			int otherPlayerOwns;

			for(i = 0; i < 8; i++){ //go through each colourGroup
				int groupSize = desire[i].size(); //get the size (ie. brown = 2, pink = 3)
				playerOwns = 0;
				otherPlayerOwns = 0;

				//Find a colour group where both players own something
				for(j = 0; j < groupSize; j++){ //go through each member of the group
					Player owner = site.getOwner(); //get the player who owns the property
					if(owner.equals(player) && site.isMortgaged() == false){
						playerOwns++; //record if player owns something in this colour
					}
					if(!(owner.equals(player)) && !(owner.equals(null))){
						otherPlayerOwns++; //record if another player owns something in this colour
					}
				}

				//Find the first property we own in this colour group that isnt already mortgaged
				if(playerOwns>0 && otherPlayerOwns > 0){ //We own a property of this colour and so does another player
					for(j = 0; j < groupSize; j++){ 
						Player owner = site.getOwner(); //get the player who owns the property
						String shortName = site.getShortName(); //get the short name
						
						if(owner.equals(player) && site.isMortgaged() == false){
							return "mortgage "+ shortName; //mortgage this property
						}
					}
				}
			}

			/*----------- Check if we are far away from a Monopoly ---------*/
			for(i = 0; i < 8; i++){ //go through each colourGroup
				playerOwns = 0;
				int groupSize = desire[i].size(); //get the size (ie. brown = 2, pink = 3)

				//Find a colour group where we own something
				for(j = 0; j < groupSize; j++){ //go through each member of the group
					Player owner = site.getOwner(); //get the player who owns the property
					if(owner.equals(player) && site.isMortgaged() == false){
						playerOwns++; //record if player owns something in this colour
					}
				}

				//mortgage if we are too far from monopoly
				if(playerOwns == 1){
					for(j = 0; j < groupSize; j++){ 
						Player owner = site.getOwner(); //get the player who owns the property
						String shortName = site.getShortName(); //get the short name
						if(owner.equals(player)){
							return "mortgage "+shortName; //mortgage this property
						}
					}
				}
				//Mortgaging properties where we only own 1 of the colour group has failed
				//try mortgaging a property where we only own 2 of the group
				if(playerOwns == 2){
					for(j = 0; j < groupSize; j++){ 
						Player owner = site.getOwner(); //get the player who owns the property
						String shortName = site.getShortName(); //get the short name
						if(owner.equals(player)){
							return "mortgage "+shortName; //mortgage this property
						}
					}
				}
			}
			return "";
		}
		//return the demolish function if this fails;
		return "";

	}

	public String demolish(){
		ColourGroup[] colours = {brownProperty, lightBlueProperty, pinkProperty, orangeProperty,
				redProperty, yellowProperty, greenProperty, darkBlueProperty};

		for(int min = 1; min < 6; min--){	//First loop tries to build all site to 3,
			for(int i = 0; i < colours.length; i++){
				ArrayList<Site> members = colours[i].getMembers();

				for(int j = 0; j < members.size(); j++ ){
					Site site = members.get(j);
					//BUILD IF WE CAN AFFORD.
					if(player.isGroupOwner(site) && site.getNumBuildings() == min && player.getBalance() < 0){
						return ("demolish " + site.getShortName() + " 1" );
						//decision = x SEND TO SELF
					}
				}

			}
		}
		//SEND ELSEWHERE
		return ""; 

	}
	
	public String bankrupt(){
		if(player.getBalance() >= 0){
			//Send to done.
			return "";
		}
		
		for(int i = 0; i<40; i++){
			Property property = board.getProperty(i);
				if(property.getOwner() != null && property.getOwner().equals(player)){
					//Send to mortgage
					return "";
				}
		}
		
		//send to done.
		return "bankrupt";
	}
	
	public String doneFunction(){
		wasInJail = false;
		allowedRoll = true;
		decision = 0;
		return "done";
	}
}
