package packA;

public class Card {
	private String communityChance;
	private int type;
	private int steps;
	private String message;
	private int square;
	private int money;
	
	public Card (String communityChance, int type, int steps, String message, int square, int money){
		this.communityChance = communityChance;
		this.type = type;
		this.steps = steps;
		this.message = message;
		this.square = square;
		this.money = money;
	}
	
	public String returncommunityChance(){
		return communityChance;
	}
	
	public int returnType(){
		return type;
	}
	
	public int returnSteps(){
		return steps;
	}
	
	public String returnMessage(){
		return message;
	}
	
	public int returnSquare(){
		return square;
	}
	
	public int returnMoney(){
		return money;
	}
}
