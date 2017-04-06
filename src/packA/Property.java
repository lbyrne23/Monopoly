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
	private int mortgageValue;
	private boolean mortgagedProperty;
	private int[] rentArray;
	private int housePrice;
	private int squareNumber;
	private int storeRentDuringMortgage;
	
	public Property(String name, int squareNumber){
		this.name = name;
		this.squareNumber = squareNumber;
	}

	public Property(String name, String shortName, int owner, int price, int[] rent, int colour, int mortgage, boolean mortgagedProperty, int housePrice, int squareNumber){
		this.name = name;
		this.shortName = shortName;
		this.owner = owner;
		this.price = price;
		this.rentArray = rent;
		this.colour = colour;
		this.mortgageValue = mortgage;
		this.mortgagedProperty = mortgagedProperty;			//If property is mortgaged, this value is changed from 0 to 1.
		this.housePrice = housePrice;
		this.squareNumber = squareNumber;
	}
	
	public Property(String name, int[] rent, int squareNumber){
		this.name = name;
		this.rentArray = rent;
		this.squareNumber = squareNumber;
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
		if (rentArray == null){
			return 0;			//return 0 if rent doesn't exist.
		}
		return rentArray[houses];
	}
	

	public void boughtProperty(int playerNum){
		owner = playerNum;
	}
	
	public int returnColour(){
		return colour;
	}
	
	public int mortgage(){
		mortgagedProperty = true;
		return mortgageValue;
	}
	
	public boolean isMortgage(){
		return mortgagedProperty;
	}
	
	public int redeem(){
		double temp = mortgageValue * 1.1;
		int redeemValue = (int)temp * -1;
		mortgagedProperty = false;
		return redeemValue;
	}
	
	public int returnHouses(){
		return houses;
	}
	
	public int returnHousePrice(){
		return housePrice;
	}
	
	public int returnSquareNumber(){
		return squareNumber;
	}
	
	public Integer setHouses(int newHouses){	//Sets how many houses are on property.
		if(newHouses >= 0 && newHouses < rentArray.length){
			houses = newHouses;
			return rentArray[houses];
		}
		else return null;
	}
	
	
	
//	public void moveSquare(int toSquare){
//		this.squareNumber = toSquare;
//	}
}