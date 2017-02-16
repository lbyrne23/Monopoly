package packA;

import java.util.Random;

public class Dice {
	
	static Random rand = new Random();
	
	public static void Roll(){
		int  dice1 = rand.nextInt(6) + 1;
		int  dice2 = rand.nextInt(6) + 1;
		
		display(dice1, dice2);
		
		if(dice1 == dice2){
			RollAgain();
		}
		
	}
	
	public static int display(int a, int b){
		System.out.println(a + " + " + b + " = " + (a+b));
		return a+b;
	}
	
	public static void RollAgain(){
		System.out.println("Second Roll");
		
		int  dice11 = rand.nextInt(6) + 1;
		int  dice22 = rand.nextInt(6) + 1;
		
		display(dice11, dice22);
		
		
	}

	
	public static void main(String args[]){
		Roll();
	}
}
