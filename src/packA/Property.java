package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//

class Property {
	private String name, shortName;
	private Integer owner;
	private int price;
	private int houses = 0;
	private int colour;
	private int mortgage;
	private int mortgagedProperty;
	private int[] rentArray;
	private int housePrice;
	
	
	public Property(String nameIN){
		name = nameIN;
	}

	public Property(String name, String shortName, int owner, int price, int[] rent, int colour, int mortgage, int mortgagedProperty, int housePrice){
		this.name = name;
		this.shortName = shortName;
		this.owner = owner;
		this.price = price;
		this.rentArray = rent;
		this.colour = colour;
		this.mortgage = mortgage;
		this.mortgagedProperty = mortgagedProperty;			//If property is mortgaged, this value is changed from 0 to 1.
		this.housePrice = housePrice;
	}
	
	public Property(String nameIN, int[] rentIN){
		name = nameIN;
		rentArray = rentIN;
	}

	public String returnName(){
		return name;
	}
	
	public String returnShortName(){
		return shortName;
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
	
	public void doubleRent(){
		rentArray[houses] = (rentArray[houses])*2;
	}
	
	public void diceRent(int diceRoll){
		rentArray[houses] = (rentArray[houses])*diceRoll;
	}

	public void boughtProperty(int playerNum){
		owner = playerNum;
	}
	
	public int returnColour(){
		return colour;
	}
	
	public int mortgage(){
		mortgagedProperty = 1;
		rentArray[houses] = 0;
		return mortgage;
	}
	
	public int isMortgage(){
		return mortgagedProperty;
	}
	
	public int redeem(){
		double temp = mortgage * 1.1;
		int redeemValue = (int)temp * -1;
		mortgagedProperty = 0;
		return redeemValue;
	}
	public int returnHouses(){
		return houses;
	}
	
	public int returnHousePrice(){
		return housePrice;
	}
	
	public Integer setHouses(int newHouses){	//Sets how many houses are on property.
		if(newHouses >= 0 && newHouses < rentArray.length){
			houses = newHouses;
			return rentArray[houses];
		}
		else return null;
	}
}