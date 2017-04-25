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
		decision = 2;													//Sets choice for next time getCommand() is called.

		Property property;

		if (player.getBalance() < 500){											//Critical stage: Only buy desirable properties, mortgage least wanted properties until above 300 pounds.
			stage = 0;
		}
		else if (player.getBalance() < 1000){									//Average stage: Buy most properties, avoid blacklisted ones.
			stage = 1;
		} 
		else {																	//Rich stage: Buy everything.
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

}
