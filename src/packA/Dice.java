package packA;

import java.util.ArrayList;
import java.util.Random;

public class Dice {

	static Random rand = new Random();
	static boolean rollAgain = false;
	static int dice1, dice2;

	public static int Roll(){
		dice1 = rand.nextInt(6) + 1;
		dice2 = rand.nextInt(6) + 1;

		if(dice1 == dice2){
			rollAgain = true;
		}

		return dice1+dice2;
	}


	public static String words() {
		return dice1 + " + " + dice2 + " = " + (dice1+dice2) + "\n"; 
	}	
}