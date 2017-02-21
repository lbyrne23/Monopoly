package packA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

abstract class Dice {

	static Random rand = new Random();
	static int allowedRoll = 0;
	static int dice1, dice2;

	public static int Roll(){
		dice1 = rand.nextInt(6) + 1;
		dice2 = rand.nextInt(6) + 1;
		
		allowedRoll++;
		
		if(dice1 == dice2){
			allowedRoll++;
		}

		return dice1+dice2;
	}


	public static String words() {
		return dice1 + " + " + dice2 + " = " + (dice1+dice2) + "\n"; 
	}	

}