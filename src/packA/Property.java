package packA;

import java.util.ArrayList;

class Property {
	private String name;
	private Integer owner;
	private int price;
	private int rent;	

	public Property(String nameIN){
		name = nameIN;
	}

	public Property(String nameIN, int ownerIN, int priceIN, int rentIN){
		name = nameIN;
		owner = ownerIN;
		price = priceIN;
		rent = rentIN;
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

	public ArrayList<Property> defaultProperties() {
		ArrayList<Property> propertyList = new ArrayList<Property>();		
		return propertyList;
	}
}
