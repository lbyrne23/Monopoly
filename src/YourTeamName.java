import java.util.ArrayList;

//Team : Cessna Skyhawk
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
			System.out.println(brownProperty.getMembers().get(1));
			return checkAllowedRoll();	//decision is 1 if in jail, 2 if not.
		case 1 :

			return checkInJail();


		case 2 : 
			return inJail();
		case 3 :
			return roll();
		case 4 :
			wasInJail = false;
			allowedRoll = true;
			decision = 0;
			return "done";


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

		if (player.getBalance() < 500){	//Critical stage: Only buy desirable properties, mortgage least wanted properties until above 300 pounds.
			stage = 0;
		}
		else if (player.getBalance() < 1000){//Average stage: Buy most properties, avoid blacklisted ones.
			stage = 1;
		} 
		else {	//Rich stage: Buy everything.
			stage = 2;
		}
		
		
		
		if (board.isProperty(player.getPosition()) ){
			property = board.getProperty(player.getPosition());
			
			switch (stage){
			case 0 :
				if (player.getBalance() < 300){
//MORTGAGE LEAST VISITED PROPERTY WHERE OPPONENT OWNS SAME COLOUR.
				}
//EVALUATE IF WE WANT THE PROPERTY. IF SO BUY.
				
			case 1 :
				if (!property.isOwned()){
//					if (board.isSite(property.getName())){
//					
//					}
				}
			
			case 2 :
				if (!property.isOwned() && board.isSite(property.getShortName()) ){
					return "buy";
				}
				
			default :

			}
		}
		
		return "";																//Return null string when property is owned/not buyable.

		
	}

	public String mortgage() {
		ColourGroup[] desire = {brownProperty, lightBlueProperty, darkBlueProperty, greenProperty, pinkProperty,
										yellowProperty, redProperty, orangeProperty};
		int playerOwns = 0;
		int otherPlayerOwns = 0;
		int j = 0;
		int i = 0;
		Site site = desire[i].getMembers().get(j);
		
		for(i = 0; i < 8; i++){ //go through each colourGroup
			
			int groupSize = desire[i].size(); //get the size (ie. brown = 2, pink = 3)
			
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
						return "mortgage "+"shortName"; //mortgage this property
					}
				}
			}
			playerOwns = 0;
			otherPlayerOwns = 0;
		}
		
		return "";
	}



}
