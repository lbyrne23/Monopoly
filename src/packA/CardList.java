package packA;

import java.util.ArrayList;

public class CardList extends ArrayList<Card>{
	public static final int POUND = 163;	

	public CardList(String typeCard){
		// Types: 	1 Move to square, no condition for money.
		//			2 Move to square, condition for money.
		//			3 Pay fine/Collect money.
		//			4 Collect money from every player.
		//			5 Get out of jail free.
		//			6 Take steps.
		//			7 Repairs to houses/hotels.
		//			8 Fine or Chance
		// communityChance, id, steps, message, square, money
		
	if(typeCard.equalsIgnoreCase("community")){
		add (new Card("Community", 1, 0, "\nAdvance to Go.\n", 0, 0));
		add (new Card("Community", 1, 0, "\nGo back to Old Kent Road.\n", 1, 0));
		add (new Card("Community", 2, 0, "\nGo to jail. Move directly to jail. Do not pass Go. Do not collect " + (char)POUND + "200.\n", 10, 0));
		add (new Card("Community", 3, 0, "\nPay hospital " + (char)POUND + "100.\n", -1, -100));
		add (new Card("Community", 3, 0, "\nDoctor's fee. Pay " + (char)POUND + "50.\n", -1, -50));
		add (new Card("Community", 3, 0, "\nPay your insurance premium " + (char)POUND + "50.\n", -1, -50));
		add (new Card("Community", 3, 0, "\nBank error in your favour. Collect " + (char)POUND + "200.\n", -1, 200));
		add (new Card("Community", 3, 0, "\nAnnuity matures. Collect " + (char)POUND + "100.\n", -1, 100));
		add (new Card("Community", 3, 0, "\nYou inherit " + (char)POUND + "100.\n", -1, 100));
		add (new Card("Community", 3, 0, "\nFrom sale of stock, you get " + (char)POUND + "50.\n", -1, 50));
		add (new Card("Community", 3, 0, "\nReceive interest on 7% preference shares: " + (char)POUND + "25.\n", -1, 25));
		add (new Card("Community", 3, 0, "\nIncome tax refund. Collect " + (char)POUND + "20.", -1, 20));
		add (new Card("Community", 3, 0, "\nYou have won second prize in a beauty contest. Collect " + (char)POUND + "10.\n", -1, 10));
		add (new Card("Community", 4, 0, "\nIt is your birthday. Collect " + (char)POUND + "10 from each player.\n", -1, 10));
		add (new Card("Community", 5, 0, "\nGet out of jail free. This card may be kept until needed or sold.\n", -1, 0));

		add (new Card("Community", 8, 0, "\nPay a " + (char)POUND + "10 fine or take a Chance."
				+ "\n\nEnter 'chance' to take a chance or 'pay fine' to pay the fine.\n", -1, 10));
	}
	
	if(typeCard.equalsIgnoreCase("chance")){
		add (new Card("Chance", 1, 0, "\nAdvance to Go.\n", 0, 0));		
		add (new Card("Chance", 2, 0, "\nGo to jail. Move directly to jail. Do not pass Go. Do not collect " + (char)POUND + "200.\n", 10, 0));
		add (new Card("Chance", 1, 0, "\nAdvance to Pall Mall. Collect " + (char)POUND + "200 if you pass go.\n", 11, 0));
		add (new Card("Chance", 1, 0, "\nTake a trip to Marylebone Station and if you pass Go collect " + (char)POUND + "200.\n", 15, 0));
		add (new Card("Chance", 1, 0, "\nAdvance to Trafalgar Square. If you pass Go collect " + (char)POUND + "200.\n", 24, 0));
		add (new Card("Chance", 1, 0, "\nAdvance to Mayfair.\n", 39, 0));
		add (new Card("Chance", 6, -3, "\nGo back three spaces.\n", -1, 0));
		add (new Card("Chance", 7, 0, "\nMake general repairs on all of your houses.\n For each house pay " + (char)POUND + "25\n For each hotel pay " + (char)POUND + "100\n", -1, 0));	//House/hotel prices vary.
		add (new Card("Chance", 7, 0, "\nYou are assessed for street repairs: \n" + (char)POUND + "40 per house,\n" + (char)POUND + "115 per hotel.\n", -1, 0));
		add (new Card("Chance", 3, 0, "\nPay school fees of " + (char)POUND + "150.\n", -1, 0));
		add (new Card("Chance", 3, 0, "\nDrunk in charge fine " + (char)POUND + "20.\n", -1, -20));
		add (new Card("Chance", 3, 0, "\nSpeeding fine " + (char)POUND + "15.\n", -1, -15));
		add (new Card("Chance", 3, 0, "\nYour building loan matures. Receive " + (char)POUND + "150.\n", -1, 150));
		add (new Card("Chance", 3, 0, "\nYou have won a crossword competition. Collect " + (char)POUND + "100.\n", -1, 100));
		add (new Card("Chance", 3, 0, "\nBank pays you dividend of " + (char)POUND + "50.\n", -1, 50));
		add (new Card("Chance", 5, 0, "\nGet out of jail free. This card may be kept until needed or sold.\n", -1, 0));
		}	
	}

	public CardList(){
	}
	
}
