package packA;
// A class to store co-ordinates for player tokens and paint them. It provides other classes with useful methods without seeing what's happening on the inside.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JComponent;


public class Player extends JComponent{

	ArrayList<Point> points = new ArrayList<Point>(); 					// Array of co-ordinates.
	private int circleX;
	private int circleY;
	private double gX; 													// For casting.
	private double gY;
	private int location; 												// Location on board.
	private int player; 												// To track which player it is.
	private int place; 													// Used to calculate location and prevent collisions.
	private String name;
	private int firstRoll;
	private int money;
	private int propertyTotal;


	public Player(int playernum, String playerName){
		location = 0; 													// Start at GO.
		player = playernum;
		name = playerName;
		place = player*10;												// Calculate place on square.
		money = 1500;

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

		//Left Side Squares
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

		//Right Side Squares
		points.add(new Point(630+place, 115));
		points.add(new Point(630+place, 185));
		points.add(new Point(630+place, 245));
		points.add(new Point(630+place, 295));
		points.add(new Point(630+place, 355));
		points.add(new Point(630+place, 425));
		points.add(new Point(630+place, 485));
		points.add(new Point(630+place, 545));
		points.add(new Point(630+place, 605));
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g); 										// Erase old squares.
		gX = points.get(location).getX(); 								// Get point on array list.
		gY = points.get(location).getY();
		circleX = (int) gX; 											// Casting.
		circleY = (int) gY;


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
		g.fillOval(circleX, circleY, 10, 10); 
	}


	//Classes used when drawing on image of board.
	public void setLocation(int newLocation){
		location = newLocation;
	}

	public int getPosition(){
		return location;
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

	public void updateBalance(int amount){
		money = money + amount;
	}
	
	public int getTotal(){
		return propertyTotal;
	}

	public void updateTotal(int value){
		propertyTotal = propertyTotal + value;
	}
	
}
