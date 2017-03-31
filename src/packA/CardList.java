package packA;

import java.util.ArrayList;

public class CardList extends ArrayList<Card>{
	public CardList(){
		// Types: 	1 Move to square, no condition for money.
		//			2 Move to square, condition for money.
		//			3 Pay fine/Collect money.
		//			4 Collect money from every player.
		//			5 Get out of jail free.
		//			6 Take steps.
		//			7 Repairs to houses/hotels.
		// communityChance, id, steps, message, square, money
		add (new Card("Community", 1, 0, "\nAdvance to Go.\n", 0, 0));
		add (new Card("Community", 2, 0, "\nGo back to Old Kent Road.\n", 1, -200));													//No need to be in condition for money.
		add (new Card("Community", 2, 0, "\nGo to jail. Move directly to jail. Do not pass Go. Do not collect £200.\n", 10, -200));		//Only sometimes take away money.
		add (new Card("Community", 3, 0, "\nPay hospital £100.\n", -1, -200));
		add (new Card("Community", 3, 0, "\nDoctor's fee. Pay £50.\n", -1, -50));
		add (new Card("Community", 3, 0, "\nPay your insurance premium £50.\n", -1, -50));
		add (new Card("Community", 3, 0, "\nBank error in your favour. Collect £200.\n", -1, 200));
		add (new Card("Community", 3, 0, "\nAnnuity matures. Collect £100.\n", -1, 100));
		add (new Card("Community", 3, 0, "\nYou inherit £100.\n", -1, 100));
		add (new Card("Community", 3, 0, "\nFrom sale of stock, you get £50.\n", -1, 50));
		add (new Card("Community", 3, 0, "\nReceive interest on 7% preference shares: £25.\n", -1, 25));
		add (new Card("Community", 3, 0, "\nIncome tax refund. Collect £20.", -1, 20));
		add (new Card("Community", 3, 0, "\nYou have won second prize in a beauty contest. Collect £10.\n", -1, 10));
		add (new Card("Community", 4, 0, "\nIt is your birthday. Collect £10 from each player.\n", -1, 10));
		add (new Card("Community", 5, 0, "\nGet out of jail free. This card may be kept until needed or sold.\n", -1, 0));
		add (new Card("Community", 6, 0, "\nPay a £10 fine or take a Chance.\n", -1, 10));

//		add (new Card("Chance", 1, 0, "\nAdvance to Go.\n", 0, 0));		
//		add (new Card("Chance", 2, 0, "\nGo to jail. Move directly to jail. Do not pass Go. Do not collect £200.\n", 10, -200));		//Only sometimes take away money.
//		add (new Card("Chance", 1, 0, "\nAdvance to Pall Mall. Collect £200 if you pass go.\n", 11, 0));
//		add (new Card("Chance", 1, 0, "\nTake a trip to Marylebone Station and if you pass Go collect £200.\n", 15, 0));
//		add (new Card("Chance", 1, 0, "\nAdvance to Trafalgar Square. If you pass Go collect £200.\n", 24, 0));
//		add (new Card("Chance", 1, 0, "\nAdvance to Mayfair.\n", 39, 0));																//Check edge case for when already on Mayfair.
//		add (new Card("Chance", 6, -3, "\nGo back three spaces.\n", -1, 0));
//		add (new Card("Chance", 7, 0, "\nMake general repairs on all of your houses. For each house pay £25. For each hotel pay £100.\n, -1, 0));	//House/hotel prices vary.
//		add (new Card("Chance", 7, 0, "\nYou are assessed for street repairs: £40 per house, £115 per hotel.\n", -1, 0));
//		add (new Card("Chance", 3, 0, "\nPay school fees of £150.\n", -1, 0));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));


	}

}
