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
	private int houses = 0;
	private int colour;
	private int mortgage;
	private int[] rentArray;
	private int housePrice;
	
	public Property(String nameIN){
		name = nameIN;
	}

	public Property(String nameIN, int ownerIN, int priceIN, int[] rentIN, int colourIN, int mortgageIN, int houseIN){
		name = nameIN;
		owner = ownerIN;
		price = priceIN;
		rentArray = rentIN;
		colour = colourIN;
		mortgage = mortgageIN;
		housePrice = houseIN;
	}
	
	public Property(String nameIN, int[] rentIN){
		name = nameIN;
		rentArray = rentIN;
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
		return rentArray[houses];
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
	
	public int returnHouses(){
		return houses;
	}
	
	public int returnHousePrice(){
		return housePrice;
	}
	
	public Integer updateRent(int update){	//increments or decrements how many houses are on property.
		if((houses + update) >= 0 && (houses + update) < rentArray.length){
		houses = houses + update;
		return rentArray[houses];
		}
		else return null;
	}

	/*public ArrayList<Property> defaultProperties() {
		ArrayList<Property> propertyList = new ArrayList<Property>();		
		return propertyList;
	}*/
}