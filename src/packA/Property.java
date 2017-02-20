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

		// Owner initially set to -1 when there is no owner. Rent set to the constant 10 for Sprint 2.
		
		
		return propertyList;
	}
}
