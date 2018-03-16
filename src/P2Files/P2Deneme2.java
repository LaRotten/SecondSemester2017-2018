package P2Files;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class P2Deneme2 extends JFrame{
	private int height,width,maxVal,pixel[];
	private DrawingPanel dp;
	P2Deneme2(){
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			Scanner input=new Scanner(new File("coins.pgm"));
			System.out.println(input.next());
			width=Integer.parseInt(input.next());
			height=Integer.parseInt(input.next());
			System.out.println(width+" " +height);
			maxVal=Integer.parseInt(input.next());
			System.out.println(maxVal);
			pixel=new int[height*width];
			for(int i=0;i<height*width;i++) {
				pixel[i]=Integer.parseInt(input.next());
				
			}
		
			dp=new DrawingPanel();
			add(dp);
		}
		catch(FileNotFoundException e) {System.out.println("Dosya Bulunamadý");}
		
		
		setVisible(true);
	}
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			for(int row=0;row<height;row++) {
				for(int col=0;col<width;col++) {
					Color intColor=new Color(pixel[row*width+col],pixel[row*width+col],pixel[row*width+col]);
					g.setColor(intColor);
					g.fillRect(row, col, 1, 1);
				}
					
			}
		}
	}
	
	public static void main(String []args)
	{new P2Deneme2();}
}