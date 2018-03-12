package P1Files;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class P1FilesDeneme extends JFrame{
	private int width,height;
	private int pixels[];
	private DrawingPanel dp;
	P1FilesDeneme() {
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		try{
			Scanner input=new Scanner(new File("circle.ascii.pbm"));
			System.out.println(input.next());
			
			width=Integer.parseInt(input.next());
			height=Integer.parseInt(input.next());
			System.out.print(width+ " "+ height);
			pixels=new int[width*height];
			for(int i=0;i<width*height;i++) {
				pixels[i]=Integer.parseInt(input.next());
			}
			dp=new DrawingPanel();
			add(dp);
			
		}
		catch(FileNotFoundException e) {}
			
		setVisible(true);
	}
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Color black=new Color(0,0,0);
			Color white=new Color(255,255,255);
			
			for(int row=0;row<height;row++) {
				for(int col=0;col<width;col++) {
					if(pixels[row*width+col]%2==1) {
						g.setColor(black);
						g.fillRect(row, col, 1, 1);
					}
					else {
						g.setColor(white);
						g.fillRect(row, col, 1, 1);
					}
				}
			}
		}
	}
	
	
	
	
	
	public static void main(String []args) {
		new P1FilesDeneme();
	}
}
