//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//
//

package packA;

public class Station extends Property {

	private int[] rentTable;
	
	Station (String name, String shortName, int price, int mortgageValue, int[] rentTable) {
		super(name, price, shortName,mortgageValue);
		this.rentTable = rentTable;
		return;
	}
	
	public int getRent () {
		return rentTable[super.getOwner().getNumStationsOwned()-1];
	}
	
}
