package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//
// A class to calculate a dice roll.

import java.util.Random;

abstract class Dice {

	static Random rand = new Random();
	static int allowedRoll = 0;
	static int dice1, dice2;

	public static int Roll(){
		dice1 = rand.nextInt(6) + 1;		//Random number between 0 and 5, plus one.
		dice2 = rand.nextInt(6) + 1;

		allowedRoll++;

		if(dice1 == dice2){					//Re-roll.
			allowedRoll++;
		}

		return dice1+dice2;
	}


	public static String words() {
		return dice1 + " + " + dice2 + " = " + (dice1+dice2) + "\n"; 
	}	

}