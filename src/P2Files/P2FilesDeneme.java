package P2Files;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class P2FilesDeneme extends JFrame {
	private int width,height,maxVal;
	private int [] pixels ;
	private DrawingPanel dp;
	P2FilesDeneme() {
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try {
			Scanner input=new Scanner(new File("coins.pgm"));
			System.out.println(input.next());
			
			
			width=Integer.parseInt(input.next());
			height=Integer.parseInt(input.next());
			maxVal=Integer.parseInt(input.next());
			pixels=new int [width*height];
			
			for(int i=0; i < height*width ;i++) {
				pixels [i] = Integer.parseInt(input.next());
			}
			dp=new DrawingPanel();
			add(dp);
			
		}
		catch(FileNotFoundException e){}
		setVisible(true);
	}
	
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			for(int row=0;row<height;row++) {
				for(int col=0;col<width;col++) {
					int intColor=pixels[row*width + col];
					g.setColor(new Color(intColor,intColor,intColor));
					g.fillRect(row, col, 1, 1);
				}
			}
				
		}
	}
	
	public static void main(String []args) {
		P2FilesDeneme o=new P2FilesDeneme();
		
	}
}
