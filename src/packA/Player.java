package packA;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.JPanel;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.color.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class Player extends JComponent{

	ArrayList<Point> points = new ArrayList<Point>(); //Array of co-ordinates
	private int circleX;
	private int circleY;
	private double gX; // for casting
	private double gY;
	private int location; //location on board
	private int player; //to track which player it is
	private int place; //used to calculate loction and prevent collisions


	public Player(int playernum){
		location = 0; //start at GO
		player = playernum;
		place = player*10; //calculate place on square

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

		//Top
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

		//Right-Side
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
		super.paintComponent(g); //erase old squares
		gX = points.get(location).getX(); //get point on array list
		gY = points.get(location).getY();
		circleX = (int) gX; //cast
		circleY = (int) gY;


		//different colors depending on player number
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

	//Classes used when drawing on image of board
	public void setLocation(int newLocation){
		location = newLocation;
	}

	public int getPosition(){
		return location;
	}
}
