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

	public Property(String nameIN, String shortNameIN, int ownerIN, int priceIN, int[] rentIN, int colourIN, int mortgageIN, int mortgagedPropertyIN, int houseIN){
		name = nameIN;
		shortName = shortNameIN;
		owner = ownerIN;
		price = priceIN;
		rentArray = rentIN;
		colour = colourIN;
		mortgage = mortgageIN;
		mortgagedProperty = mortgagedPropertyIN;		//If property is mortgaged, this value is changed from 0 to 1.
		housePrice = houseIN;
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
	
	public Integer setHouses(int newHouses){	//sets how many houses are on property.
		if(newHouses >= 0 && newHouses < rentArray.length){
			houses = newHouses;
			return rentArray[houses];
		}
		else return null;
	}

	
}