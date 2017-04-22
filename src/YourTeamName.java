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
	private static int i = 0;
	private static BoardAPI board;
	private static PlayerAPI player;
	private static DiceAPI dice;
	
	YourTeamName (BoardAPI board, PlayerAPI player, DiceAPI dice) {
		this.board = board;
		this.player = player;
		this.dice = dice;
		return;
	}
	
	public String getName () {
		return "CessnaSkyhawk";
	}
	
	public String getCommand () {
		switch (i){
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
			i = 1;
			return "roll";
		}


	}
	
	public String getDecision () {
		// Add your code here
		return "pay";
	}
	
}