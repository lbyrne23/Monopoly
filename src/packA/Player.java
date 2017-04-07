package packA;
//Team : Cessna Skyhawk
//Michael Jordan
//Lucy Byrne
//Fiachra Dunn
//
// A class to store coordinates for player tokens and paint them. It provides other classes with useful methods without seeing what's happening on the inside.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.Timer;


public class Player extends JLabel{

	ArrayList<Point> points = new ArrayList<Point>(); 					//Array of coordinates.
	private int circleX;
	private int circleY;
	private double gX; 													//For casting.
	private double gY;
	private int location; 												//Location on board.
	private int player; 												//To track which player it is.
	private int place; 													//Used to calculate location and prevent collisions.
	private String name;
	private int firstRoll;
	private int money;
	private int propertyTotal;
	private boolean inJail;
	private int jailRoll;
	private int gooJ;													//Tracks amount of gooJ cards (Max 2)

	public Player(int playernum, String playerName){
		this.setPreferredSize(new Dimension(15,15));
		
		location = 0; 													//Start at GO.
		player = playernum;
		name = playerName;
		place = player*10;												//Calculate place on square.
		money = 1500;
		inJail = false;
		gooJ = 0;
		//Bottom Squares 
		points.add(new Point(665, (635+place)));
		points.add(new Point(605, (635+place)));
		points.add(new Point(555, (635+place)));
		points.add(new Point(495, (635+place)));
		points.add(new Point(425, (635+place)));
		points.add(new Point(355, (635+place)));
		points.add(new Point(285, (635+place)));
		points.add(new Point(225, (635+place)));
		points.add(new Point(175, (635+place)));
		points.add(new Point(115, (635+place)));
		points.add(new Point(40, (635+place)));

		//Left Squares
		points.add(new Point((5+place), 605));
		points.add(new Point((5+place), 540));
		points.add(new Point((5+place), 480));
		points.add(new Point((5+place), 425));
		points.add(new Point((5+place), 350));
		points.add(new Point((5+place), 295));
		points.add(new Point((5+place), 245));
		points.add(new Point((5+place), 185));
		points.add(new Point((5+place), 115));
		points.add(new Point((5+place), 40));

		//Top Squares
		points.add(new Point(115, 5+place));
		points.add(new Point(175, 5+place));
		points.add(new Point(220, 5+place));
		points.add(new Point(285, 5+place));
		points.add(new Point(355, 5+place));
		points.add(new Point(425, 5+place));
		points.add(new Point(490, 5+place));
		points.add(new Point(550, 5+place));
		points.add(new Point(605, 5+place));
		points.add(new Point (655, 5+place));

		//Right Squares
		points.add(new Point(630+place, 115));
		points.add(new Point(630+place, 185));
		points.add(new Point(630+place, 245));
		points.add(new Point(630+place, 295));
		points.add(new Point(630+place, 355));
		points.add(new Point(630+place, 425));
		points.add(new Point(630+place, 485));
		points.add(new Point(630+place, 545));
		points.add(new Point(630+place, 605));
		
		this.setPosition(0);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g); 										//Erase old tokens.
		setPosition(location);
		
		//Different colors depending on player number.
		if(player == 0){
			g.setColor(Color.GREEN);
		}
		if(player == 1){
			g.setColor(Color.ORANGE);
		}
		if(player == 2){
			g.setColor(Color.RED);
		}
		if(player == 3){
			g.setColor(Color.YELLOW);
		}
		if(player == 4){
			g.setColor(Color.BLUE);
		}
		if(player == 5){
			g.setColor(Color.BLACK);
		}
		g.fillOval(0, 0, 10, 10); 
	}


	//Classes used when drawing on image of board.
	public void setPosition(int newLocation){
		  location = newLocation;
		  setLocation(points.get(location));
		
	}

	public int getPosition(){
		return location;
	}
	
	public int getNumber(){
		return player;
	}

	public String getName(){
		return name;
	}

	public int setFirstRoll(int number){
		return firstRoll = number;
	}

	public int getFirstRoll(){
		return firstRoll;
	}

	public int getBalance(){
		return money;
	}

	public int updateBalance(int amount){
		money = money + amount;
		return money;
	}

	public int getTotal(){
		return propertyTotal;
	}

	public void updateTotal(int value){
		propertyTotal = propertyTotal + value;
	}
	
	public void setJail(boolean condition){
		inJail = condition;
	}
	
	public boolean inJail(){
		return inJail;
	}
	
	public void setJailRoll(){
		jailRoll = 3;
	}
	
	public void failedJailRoll(){
		jailRoll--;
	}
	
	public int getJailRoll(){
		return jailRoll;
	}

}