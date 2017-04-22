import java.util.ArrayList;

import jdk.nashorn.internal.objects.annotations.Property;

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
		return "CessnaSkyhawk";
	}

	public String getCommand () {
		switch (decision){
		case 0 : 
			return "roll";
		case 1 :
			return "done";
		case 2 : 
			return "pay rent";
		case 3 :
			return "build";
		case 4 :
			return "demolish";
		case 5 :
			return "mortgage";
		case 6 :
			return "redeem";
		case 7 : 
			return "bankrupt";
		case 8 :
			return "pay"; //in jail
		case 9 : 
			return "card"; //in jail

		default : 
			decision = 1;
			return "roll";
		}
	}

	public String getDecision () {
		// Add your code here
		return "pay";
	}

	public int inJail(){
		if(player.getNumProperties() < 2){
			if(player.getBalance() < 50){
				return 0; //Roll because our balance is too low and we will lose the game if we pay out
			}
			else if(player.hasGetOutOfJailCard()){
				return 9; //use the card to get out for free quickly
			}
			else return 8; //pay out to get out ASAP
		}
		else if(player.getNumProperties() > 15){
			return 0; //roll beacuse we want to stay in jail and just collect rent
		}
		else if (player.getBalance() > 1000){
			return 8; //save our get out of jail card so other player cant have it/for us later when we're poor
		}
		else if(player.hasGetOutOfJailCard()){
			return 9; //use the card if we have low funds and want out
		}
		else return 0;
	}

	public void considerBuilding(){
		if(canBuild(orangeProperty)){

		}
	}


	public int mortgage(int goal){
		ArrayList<Site> sites = {brownProperty, lightBlueProperty, darkBlueProperty, greenProperty, pinkProperty, yellowProperty,  redProperty,  orangeProperty};
		ArrayList<Property> properties = player.getProperties();

		int earned = 0;
		int i;
		int j = 0;

		for(i = 0; i < properties.size(); i++){
			if(properties.get(i).getColourGroup() == sites.get(j)){
				if(countColoursOwned(sites.get(j)) < 2){
					//mortgage
				}	
			}
			j++;
		}
	}

	public int countColoursOwned (Site site) {
		boolean owns = 3;
		ColourGroup colourGroup = site.getColourGroup();
		for (Site s : colourGroup.getMembers()) {
			if (!s.isOwned() || (s.isOwned() && s.getOwner() != this))
				owns--;
		}
		return owns;
	}


	public boolean canBuild(ColourGroup colour){
		if(colour.getMembers().get(0).canBuild(1)){
			return true;
		}

		return false;
	}

}