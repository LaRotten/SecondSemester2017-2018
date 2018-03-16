package P3Files;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class P3FilesDeneme extends JFrame{

	private int width,height,maxVal;
	private int []pixels;
	private DrawingPanel dp;
	P3FilesDeneme() {
		try{
			
			setSize(500,500);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
			Scanner input=new Scanner(new File("pbmlib.ascii.ppm"));
			System.out.println(input.next());
			
			
			width=Integer.parseInt(input.next());
			System.out.print(width + " ");
			height=Integer.parseInt(input.next());
			System.out.println(height);
			
			maxVal=Integer.parseInt(input.next());
			System.out.println(maxVal);
			System.out.println(width*height*3);
			pixels =new int [width*height*3];
			for(int i=0;i<(width*height*3);i++) {
				pixels[i]=Integer.parseInt(input.next());
				
			}
			
			dp=new DrawingPanel();
			add(dp);
			
			
		}
		catch(FileNotFoundException e) {System.out.println("Dosya bulunamadý");}
		catch(NumberFormatException e) {}
	}
	
	
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			int counter=0;
			for(int row =0;row<height;row++) {
				for(int col=0;col<width;col++) {
					try {
						g.setColor(new Color(pixels[counter],pixels[counter+1],pixels[counter+2]));
						g.fillRect(col, row, 1, 1);
						counter=counter+3;
					}
					catch(IllegalArgumentException e) {}
				}
				
			}
		}
		
	}
	public static void main(String []args) {
		P3FilesDeneme w=new P3FilesDeneme();
	}
}
