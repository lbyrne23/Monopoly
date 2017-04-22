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
	private static int decision = 0;
	private static BoardAPI board;
	private static PlayerAPI player;
	private static DiceAPI dice;
	ColourGroup brownProperty = ((Site) board.getProperty(1)).getColourGroup();
	ColourGroup lightBlueProperty = ((Site) board.getProperty(6)).getColourGroup();
	ColourGroup pinkProperty = ((Site) board.getProperty(11)).getColourGroup();
	ColourGroup orangeProperty = ((Site) board.getProperty(16)).getColourGroup();
	ColourGroup redProperty = ((Site) board.getProperty(21)).getColourGroup();
	ColourGroup yellowProperty = ((Site) board.getProperty(26)).getColourGroup();
	ColourGroup greenProperty = ((Site) board.getProperty(31)).getColourGroup();
	ColourGroup darkBlueProperty = ((Site) board.getProperty(37)).getColourGroup();
	
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
			return "property";
		case 9 : 
			return "balance";
		case 10 :
			return "quit";
			
		default : 
			decision = 1;
			return "roll";
		}


	}
	
	public String getDecision () {
		// Add your code here
		return "pay";
	}
	
	public void considerBuilding(){
		int brown, lightBlue, pink, orange, red, yellow, green, darkBlue;
		

	}
	
}