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
	private static int i;
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
			return "pay"; //in jail
		case 9 : 
			return "card"; //in jail

		default : 
			i = 1;
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
	
	public int mortgage(){
		
	}
	
}