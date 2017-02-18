package packA;

import java.util.ArrayList;

public class Property {
	private String name;
	private int owner;
	private int price;
	private int rent;											// Using a fixed rent for Sprint 2.
	private int mortgage;

//	private String colour;
//	
//	private int houseCost;
//	private int oneHouse;
//	private int twoHouse;
//	private int threeHouse;
//	private int fourHouse;
//	
//	private int hotelCost;
//	private int hotel;
		
	
	public void property(String nameIN, int priceIN, int mortgageIN){
		name = nameIN;
		owner = -1;
		price = priceIN;
		rent = 50;
		mortgage = mortgageIN;
	}
	
	public String returnName(){
		return name;
	}
	
	public String returnOwner(){
		if (owner >= 0)
		{
			return "Owned by Player " + owner;
		} else {
			return "No owner yet.";
		}
	}
	
	public int returnPrice(){
		return price;
	}
	
	public int returnRent(){
		return rent;
	}
	
	public int returnMortgage(){
		return mortgage;
	}
	
	public void boughtProperty(int playerNum){
		owner = playerNum;
	}
	
	
	
	
	
	
	
	
	
	
//	public void propertyAdd() {
//		propertyList.add("Old Kent Road");
//		propertyList.add("Whitechapel Rd");
//		propertyList.add("The Angel Islington");
//		propertyList.add("Euston Rd");
//		propertyList.add("Pentonville Rd");
//		propertyList.add("Pall Mall");
//		propertyList.add("Whitehall");
//		propertyList.add("Northumberland Ave");
//		propertyList.add("Bow St");
//		propertyList.add("Marlborough St");
//		propertyList.add("Vine St");
//		propertyList.add("Strand");
//		propertyList.add("Fleet St");
//		propertyList.add("Trafalgar Sq");
//		propertyList.add("Leicester Sq");
//		propertyList.add("Coventry St");
//		propertyList.add("Picadilly");
//		propertyList.add("Regent St");
//		propertyList.add("Oxford St");
//		propertyList.add("Bond St");
//		propertyList.add("Park Lane");
//		propertyList.add("Mayfair");
//	}
}
