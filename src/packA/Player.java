package packA;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.color.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class Player extends JPanel implements MouseMotionListener{

	ArrayList<Point> points = new ArrayList<Point>();
	int i = 0;
	int circleX = 40;
	int circleY = 45;
	boolean show = false;


	public Player(){
		addMouseMotionListener(this);
		points.add(new Point(40, 45));
		points.add(new Point(145, 45));
		points.add(new Point(225, 45));
		points.add(new Point(295, 45));
		points.add(new Point(370, 45));
		points.add(new Point(450, 45));
		points.add(new Point(550, 45));
		points.add(new Point(630, 45));
		points.add(new Point(705, 45));
		points.add(new Point(775, 45));
		points.add(new Point(855, 45));

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
		if(show){
			repaint();
			g.drawOval(circleX, circleY, 10, 10);
			g.setColor(Color.GREEN);
			g.fillOval(circleX, circleY, 10, 10);
		}

	}

	public void mouseClicked(MouseEvent e){}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void mouseDragged(MouseEvent e) {
		repaint();
		show = true;
		double g = points.get(i++).getX();
		double f = points.get(i++).getY();
		circleX = (int) g;
		circleY = (int) f;
	}

	public void mouseMoved(MouseEvent e) {}
}
