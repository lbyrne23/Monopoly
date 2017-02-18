package packA;

import java.util.ArrayList;

public class Property {
	private String name;
	private int owner;
	private int price;
	private int rent;	


	public Property(String nameIN, int ownerIN, int priceIN, int rentIN){
		name = nameIN;
		owner = ownerIN;
		price = priceIN;
		rent = rentIN;
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

	public void boughtProperty(int playerNum){
		owner = playerNum;
	}

	public void propertyAdd() {
		ArrayList<Property> propertyList = new ArrayList<Property>();

		// Owner initially set to -1 when there is no owner. Rent set to the constant 10 for Sprint 2.
		propertyList.add(new Property("Old Kent Rd", -1, 60, 10));
		propertyList.add(new Property("Whitechapel Rd", -1, 60, 10));
		propertyList.add(new Property("The Angel Islington", -1, 100, 10));
		propertyList.add(new Property("Euston Rd", -1, 100, 10));
		propertyList.add(new Property("Pentonville Rd", -1, 120, 10));
		propertyList.add(new Property("Pall Mall", -1, 140, 10));
		propertyList.add(new Property("Whitehall", -1, 140, 10));
		propertyList.add(new Property("Northumberland Ave", -1, 160, 10));
		propertyList.add(new Property("Bow St", -1, 180, 10));
		propertyList.add(new Property("Marlborough St", -1, 180, 10));
		propertyList.add(new Property("Vine St", -1, 200, 10));
		propertyList.add(new Property("Strand", -1, 220, 10));
		propertyList.add(new Property("Fleet St", -1, 220, 10));
		propertyList.add(new Property("Trafalgar Sq", -1, 240, 10));
		propertyList.add(new Property("Leicester Sq", -1, 260, 10));
		propertyList.add(new Property("Coventry St", -1, 260, 10));
		propertyList.add(new Property("Picadilly", -1, 280, 10));
		propertyList.add(new Property("Regent St", -1, 300, 10));
		propertyList.add(new Property("Oxford St", -1, 300, 10));
		propertyList.add(new Property("Bond St", -1, 320, 10));
		propertyList.add(new Property("Park Lane", -1, 350, 10));
		propertyList.add(new Property("Mayfair", -1, 400, 10));
	}
}
