package packA;
import javax.swing.JFrame;
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


public class Player extends JPanel{

	ArrayList<Point> points = new ArrayList<Point>();
	int circleX;
	int circleY;
	double gX;
	double gY;
	int location;
	int player;
	int place;


	public Player(int playernum){
		location = 0;
		player = playernum;
		place = player*10;

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

		points.add(new Point(855, 150));
		points.add(new Point(855, 235));
		points.add(new Point(855, 310));
		points.add(new Point(855, 360));
		points.add(new Point(855, 460));
		points.add(new Point(855, 545));
		points.add(new Point(855, 625));
		points.add(new Point(855, 705));
		points.add(new Point(855, 770));
		points.add(new Point(855, 860));

		points.add(new Point(775, 860));
		points.add(new Point(705, 860));
		points.add(new Point(630, 860));
		points.add(new Point(550, 860));
		points.add(new Point(450, 860));
		points.add(new Point(370, 860));
		points.add(new Point(295, 860));
		points.add(new Point(225, 860));
		points.add(new Point(145, 860));
		points.add(new Point(40, 860));

		points.add(new Point(40, 775));
		points.add(new Point(40, 705));
		points.add(new Point(40, 625));
		points.add(new Point(40, 545));
		points.add(new Point(40, 460));
		points.add(new Point(40, 360));
		points.add(new Point(40, 310));
		points.add(new Point(40, 235));
		points.add(new Point(40, 150));
	}

	public void paint(Graphics g){
		setSize(900,900);

		for(location = 0; location < points.size(); location++){
			super.paintComponent(g);
			gX = points.get(location).getX();
			gY = points.get(location).getY();
			circleX = (int) gX;
			circleY = (int) gY;
			g.drawOval(circleX, circleY, 10, 10);

			if(player == 0){
				g.setColor(Color.GREEN);
			}
			if(player == 1){
				g.setColor(Color.BLUE);
			}
			if(player == 2){
				g.setColor(Color.RED);
			}
			if(player == 3){
				g.setColor(Color.YELLOW);
			}
			if(player == 4){
				g.setColor(Color.ORANGE);
			}
			if(player == 5){
				g.setColor(Color.BLACK);
			}
			g.fillOval(circleX, circleY, 10, 10); 
		}
	}


	public static void main(String [] args){
		JPanel main = new JPanel();
		main.setSize(700, 704);
		Player a = new Player(0);
		main.setOpaque(true);
		main.add(a);
		main.setVisible(true);

	}
}
