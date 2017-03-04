package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//

import java.util.ArrayList;

class Property {
	private String name;
	private Integer owner;
	private int price;
	private int rent;
	private int colour;

	public Property(String nameIN){
		name = nameIN;
	}

	public Property(String nameIN, int ownerIN, int priceIN, int rentIN, int colourIN){
		name = nameIN;
		owner = ownerIN;
		price = priceIN;
		rent = rentIN;
		colour = colourIN;
	}

	public String returnName(){
		return name;
	}

	public Integer returnOwner(){
		return owner;
	}

	public void setOwner(int playerNum){
		owner = playerNum;
	}

	public int returnPrice(){
		return price;
	}

	public int returnRent(){
		return rent;
	}

	public void boughtProperty(int playerNum){
		owner = playerNum;
	}
	
	public int returnColour(){
		return colour;
	}

	public ArrayList<Property> defaultProperties() {
		ArrayList<Property> propertyList = new ArrayList<Property>();		
		return propertyList;
	}
}