package packA;

import packA.Property;

public class Card {
	private String name;						//Link between Card and Property.
	private String communityChance;
	private int type;
	private int steps;
	private String message;
	private int toSquare;
	private int money;
	
	public Card (String communityChance, int type, int steps, String message, int toSquare, int money){
		this.communityChance = communityChance;
		this.type = type;
		this.steps = steps;
		this.message = message;
		this.toSquare = toSquare;
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
	
	public int returnToSquare(){
		return toSquare;
	}
	
	public int returnMoney(){
		return money;
	}
}
