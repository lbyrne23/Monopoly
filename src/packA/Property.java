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
	private int rent, rentIndex = 0;
	private int colour;
	private int mortgage;
	private int[] rentArray;
	private int housePrice;
	
	public Property(String nameIN){
		name = nameIN;
	}

	public Property(String nameIN, int ownerIN, int priceIN, int rentIN, int colourIN, int mortgageIN, int houseIN){
		name = nameIN;
		owner = ownerIN;
		price = priceIN;
		rent = rentIN;
		colour = colourIN;
		mortgage = mortgageIN;
		housePrice = houseIN;
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
	
	public int returnMortgage(){
		return mortgage;
	}
	
	public int returnHousePrice(){
		return housePrice;
	}
	
	public int updateRent(){
		rentIndex++;
		rent = rentArray[rentIndex];
		return rent;
	}

	public ArrayList<Property> defaultProperties() {
		ArrayList<Property> propertyList = new ArrayList<Property>();		
		return propertyList;
	}
}