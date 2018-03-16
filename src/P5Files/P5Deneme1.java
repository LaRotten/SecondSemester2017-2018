package P5Files;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class P5Deneme1 extends JFrame{
	private int height,width,maxVal;
	private int [] pixs;
	private DrawingPanel dp;
		P5Deneme1() throws IOException {
			setSize(512,512);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				
				FileInputStream fis=new FileInputStream("baboon.pgm");
				byte [] magicNum=new byte [2];		
				fis.read(magicNum);
				System.out.println((char)magicNum[0] +""+ (char)magicNum[1]);
				
				
				char chByte=(char)fis.read();
				while(Character.isWhitespace(chByte)) {
					chByte=(char)fis.read();
				}
				//chByte = the first number of the width
				
				byte widtharray[]=new byte[3];
				widtharray[0]=(byte)chByte;
				
				int count=1;
				chByte=(char)fis.read();
				while(!Character.isWhitespace(chByte)) {
					widtharray[count++]=(byte)chByte;
					chByte=(char)fis.read();
				}
				System.out.print((char)widtharray[0]+""+(char)widtharray[1]+""+(char)widtharray[2] +" ");
				//white space which is between width and height
				//scanning width is over 
				chByte=(char)fis.read();
				byte [] heightarray = new byte [3];
				heightarray[0]=(byte)chByte;
				count=1;
				chByte=(char)fis.read();
				while(!Character.isWhitespace(chByte)) {
					heightarray[count++]=(byte)chByte;
					chByte=(char)fis.read();
				}
				System.out.println((char)heightarray[0]+ ""+(char)heightarray[1]+""+(char)heightarray[2]);
				//scanning height is over
				//chByte is a white space now
				while(Character.isWhitespace(chByte)) {
					chByte =(char)fis.read();
				}
				
				byte [] maxValarray=new byte[3];
				maxValarray[0]=(byte)chByte;
				
				count=1;
				chByte=(char)fis.read();
				while(!Character.isWhitespace(chByte)) {
					maxValarray[count++]=(byte)chByte;
					chByte=(char)fis.read();
				}
				System.out.println((char)maxValarray[0]+""+(char)maxValarray[1]+""+ (char)maxValarray[2]);
				//scanning maxVal is over
				
				String strWidth=new String(widtharray);
				width=Integer.parseInt(strWidth);
				
				String strHeight=new String(heightarray);
				height=Integer.parseInt(strHeight);
				
				String strMaxVal=new String(maxValarray);
				maxVal=Integer.parseInt(strMaxVal);
				
				System.out.printf("\n%s" + "%s"+ "\n" + "%d " + "%d" +"\n" + "%d\n"
				, (char)magicNum[0],(char)magicNum[1],width,height,maxVal);
				
				int  bByte=fis.read();
				
				while(Character.isWhitespace(bByte)) {
					bByte=fis.read();
				
				}
				System.out.println(bByte);
				pixs=new int[width*height];
				pixs[0]=bByte;
				
				for(int i=1;i<height*width;i++) {
					pixs[i]=fis.read();
				}
				System.out.println(pixs[0]+" "+pixs[1]);
			}
			catch(FileNotFoundException e){}
			
			dp=new DrawingPanel();
			add(dp);
			setVisible(true);
		}
		
	
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			for(int row=0;row<height;row++) {
				for(int col=0;col<width;col++) {
					Color c=new Color(pixs[row*width+col],pixs[row*width+col],pixs[row*width+col]);
					g.setColor(c);
					g.fillRect(col, row,1, 1);
				}
			}
		}
	}
	
	
	
	public static void main(String []args) throws IOException {
		P5Deneme1 o=new P5Deneme1();
	}
}
