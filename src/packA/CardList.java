package packA;

import java.util.ArrayList;

public class CardList extends ArrayList<Card>{
	public CardList(){
		// Types: 	1 Move to square, no condition for money.
		//			2 Move to square, condition for money.
		//			3 Pay fine.
		//			4 Collect money from every player.
		//			5 Get out of jail free.
		// communityChance, id, steps, message, square, money
		add (new Card("Community", 1, 0, "\nAdvance to Go!\n", 0, 0));
		add (new Card("Community", 2, 0, "\nGo back to Old Kent Road.\n", 1, -200));			//No need to be in condition for money.
		add (new Card("Community", 2, 0, "\nGo to jail. Do not collect £200.\n", 10, -200));	//Only sometimes take away money.
		add (new Card("Community", 3, 0, "\nPay hospital £100.\n", -1, -200));
		add (new Card("Community", 3, 0, "\nDoctor's fee. Pay £50.\n", -1, -50));
		add (new Card("Community", 3, 0, "\nPay your insurance premium £50.\n", -1, -50));
		add (new Card("Community", 3, 0, "\nBank error in your favour. Collect £200.\n", -1, 200));
		add (new Card("Community", 3, 0, "\nAnnuity matures. Collect £100.\n", -1, 100));
		add (new Card("Community", 3, 0, "\nYou inherit £100.\n", -1, 100));
		add (new Card("Community", 3, 0, "\nFrom sale of stock, you get £50.\n", -1, 50));
		add (new Card("Community", 3, 0, "\nReceive interest on 7% preference shares: £25.\n", -1, 25));
		add (new Card("Community", 3, 0, "\nIncome tax refund. Collect £20.", -1, 20));
		add (new Card("Community", 3, 0, "\nYou won second prize in a beauty contest. Collect £10.\n", -1, 10));
		add (new Card("Community", 4, 0, "\nIt is your birthday. Collect £10 from each player.\n", -1, 10));
		add (new Card("Community", 5, 0, "\nGet out of jail free. This card may be kept until needed or sold.\n", -1, 0));
		add (new Card("Community", 6, 0, "\nPay £10 fine or take a Chance.\n", -1, 10));

//		add (new Card("Chance", ));		
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));
//		add (new Card("Chance", ));


	}

}
