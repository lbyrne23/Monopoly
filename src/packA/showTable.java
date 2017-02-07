package packA;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

//MJ
//Still needs to display the image from a web address.

public class showTable extends Panel {
			BufferedImage  image;
			public showTable() {
				try {
					System.out.println("Enter image name\n");
					BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
					String imageName = buff.readLine();
					File input = new File(imageName);
					image = ImageIO.read(input);
				} catch (IOException ie) {
					System.out.println("Error:"+ie.getMessage());
				}
			}

			public void paint(Graphics g) {
				g.drawImage( image, 0, 0, 700, 704, null);
				
			}

			static public void main(String args[]) throws Exception {
				JFrame frame = new JFrame("Monopoly by CessnaSkyhawk");
				Panel panel = new showTable();
				frame.getContentPane().add(panel);
				frame.setSize(900, 900);
				frame.setVisible(true);
			}
		}
