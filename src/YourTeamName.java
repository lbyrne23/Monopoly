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
	private static int decision;
	private static BoardAPI board;
	private static PlayerAPI player;
	private static DiceAPI dice;
	boolean allowedRoll = true;
	boolean wasInJail = false;
	ColourGroup brownProperty;
	ColourGroup lightBlueProperty;
	ColourGroup pinkProperty;
	ColourGroup orangeProperty;
	ColourGroup redProperty;
	ColourGroup yellowProperty;
	ColourGroup greenProperty;
	ColourGroup darkBlueProperty;

	YourTeamName (BoardAPI board, PlayerAPI player, DiceAPI dice) {
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
			return checkInJail();	//decision is 1 if in jail, 2 if not.
		case 1 :
			
			return inJail();
			
			
		case 2 : 
			return roll();
		case 3 :
			
			if(!allowedRoll){
				wasInJail = false;
				allowedRoll = true;
				return "done";
			}
			else
				decision = 0;
				return "";
		case 4 :
			return "demolish";
		case 5 :
			return "mortgage";
		case 6 :
			return "redeem";
		case 7 : 
			return "bankrupt";
		case 8 :
			return "pay"; 			//In jail.
		case 9 : 
			return "card"; 			//In jail.

		default : 
			decision = 0;
			return "roll";
		}
	}

	public String getDecision () {
		// Add your code here
		return "pay";
	}

	public String checkInJail(){
		if(player.isInJail()){
			decision = 1;
		}
		else{
			decision = 2;
		}
		return "";
	}
	
	public String roll(){
		//Check last roll
		if(dice.isDouble() && !wasInJail && !player.isInJail()){	//If player rolled doubles, wasn't just in jail and isn't in jail now.
			allowedRoll = true;
		}
		//Depending on last roll, either roll or go to next step. 
		if(allowedRoll){
			decision = 0;
			allowedRoll = false;
			return "roll";
		}
		
		decision = 3;
		return "";
	}
	
	public String inJail(){
		wasInJail = true;
		if(!allowedRoll){
			wasInJail = false;
			return "done";
		}
			
		if(player.getNumProperties() < 10){
				
				if(player.hasGetOutOfJailCard()){
					decision = 0;
					System.out.println("Used Card");
					return "card"; 						//Use the card to get out for free quickly.
					
				}
				else if(player.getBalance() > 100){
					decision = 0;
					System.out.println(player.isInJail());
					return "pay"; 						//Pay out to get out ASAP.
				}
				
		}
			
			decision = 0;
			allowedRoll = false;
			return "roll";
			
	}

	//Function tries to build three houses on all properties, from most desired property to least.
	public String considerBuild(){
		int colourIndex = 0;
		String command = "";
		ColourGroup[] colourGroups = {orangeProperty, redProperty, yellowProperty, pinkProperty, greenProperty,
										darkBlueProperty, lightBlueProperty};
		
		//Loop attempts to build on houses from most to least desirable, building one house at a time.
		
		//We attempt to build all houses to level 3, then all houses to level 4, then 5.
		for(int housesToBuild = 3; housesToBuild < 5; housesToBuild++){
			while(colourIndex < 7 ){	//	While Player can afford, and hasn't reached end of colour groups.
					if(ownsGroup(colourGroups[colourIndex])){									//If player owns group, loop through.
						ArrayList<Site> siteList = colourGroups[colourIndex].getMembers();		//List of properties of this colour group.
						for(int j=0; j < siteList.size(); j++){
							Site p = siteList.get(j);
							if(p.getNumHouses() < housesToBuild && player.getBalance() > 500){
								decision = 2;													//Loop back to same function if we succeed in building.
								
								command = "build " + p.getShortName() + " 1";
								
								return command;													//Build one house at a time.
							}
						}
						
					}
				
				colourIndex++;		
				}
		}	
			if(dice.isDouble() && !player.isInJail()){
				decision = 0;
				System.out.println("Rolled Doubles");
			}
			else{
				decision = 3;
			}
						
			
			command = "";
			return command;
			
	}
	
	public String buyProperty(){
		decision = 3 ;															//Sets choice for next time getCommand() is called.

		Property property;
		
		if (board.isProperty(player.getPosition()) ){
			property = board.getProperty(player.getPosition());
			
			if (!property.isOwned() && board.isSite(property.getShortName()) ){
				System.out.println("Bought");
				return "buy";
			}
		}
		
		return "";																//Return null string when property is owned/not buyable.
	}
	

//	public int mortgage(int goal){
//		ArrayList<Site> sites = {brownProperty, lightBlueProperty, darkBlueProperty, greenProperty, pinkProperty, yellowProperty,  redProperty,  orangeProperty};
//		ArrayList<Property> properties = player.getProperties();
//
//		int earned = 0;
//		int i;
//		int j = 0;
//
//		for(i = 0; i < properties.size(); i++){
//			if(properties.get(i).getColourGroup() == sites.get(j)){
//				if(countColoursOwned(sites.get(j)) < 2){
//					//mortgage
//				}	
//			}
//			j++;
//		}
//	}
//
//	public int countColoursOwned (Site site) {
//		int owns = 3;
//		ColourGroup colourGroup = site.getColourGroup();
//		for (Site s : colourGroup.getMembers()) {
//			if (!s.isOwned() || (s.isOwned() && s.getOwner() != player.getTokenId()))
//				owns--;
//		}
//		return owns;
//	}

	
	public boolean ownsGroup(ColourGroup colour){
		if( player.isGroupOwner(colour.getMembers().get(0)) ){
			
			return true;
		}

		return false;
	}
	
	public boolean threeHousesOrMore(ColourGroup colour){
		ArrayList<Site> propertyList = colour.getMembers();
		
		return false;
	}
}