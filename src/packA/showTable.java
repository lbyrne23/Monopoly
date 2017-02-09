package packA;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextField;

//MJ
//Still needs to display the image from a web address.

public class showTable extends Panel {
			BufferedImage  image = null;
			public showTable() {
				try {
					image = ImageIO.read(new File("src/packA/Board2.gif"));
				} catch (IOException ie) {
					System.out.println("Error:"+ie.getMessage());
				}
			}

			public void paint(Graphics g) {
				g.drawImage( image, 0, 0, 700, 704, null);
				
			}

			static public void main(String args[]) throws Exception {
				InputBox input = new InputBox();
				JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
				Panel panel = new showTable();
				frame.getContentPane().add(panel);
				frame.getContentPane().add(input.getBox(), BorderLayout.EAST);
				frame.setSize(900, 900);
				frame.setVisible(true);
			}
		}
