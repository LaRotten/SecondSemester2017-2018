package PFiles;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PFiles extends JFrame{
	private int width,height,maxVal,pixels[],bByte,counter2;
	private String magicNum,filename;
	private DrawingPanel dp;
	private Color black=new Color(0,0,0);
	private Color white=new Color(255,255,255);
	private char a;
	private byte widtharray[],heightarray[],maxValarray[];
	private String strwidth,strheight,strmaxVal;
	private char chByte;
	
	PFiles(){
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.print("Please enter the name of the file: ");
		Scanner scan=new Scanner(System.in);
		filename=scan.nextLine();
		try {
			FileInputStream fis=new FileInputStream(filename);
			fis.read();		a=(char)fis.read();
			
			if(a=='1') {
				Scanner input=new Scanner(new File(filename));
				input.next();
				width=Integer.parseInt(input.next());
				height=Integer.parseInt(input.next());
				pixels=new int[width*height];
				for(int i=0;i<width*height;i++) {
					pixels[i]=Integer.parseInt(input.next());
				}
				
			}
			else if(a=='2') {
				Scanner input=new Scanner(new File(filename));
				input.next();
				width=Integer.parseInt(input.next());
				height=Integer.parseInt(input.next());
				maxVal=Integer.parseInt(input.next());
				pixels=new int[width*height];
				for(int i=0;i<width*height;i++) {
					pixels[i]=Integer.parseInt(input.next());
				}
			}
			else if(a=='3') {
				Scanner input=new Scanner(new File(filename));
				input.next();
				width=Integer.parseInt(input.next());
				height=Integer.parseInt(input.next());
				maxVal=Integer.parseInt(input.next());
				pixels=new int[width*height*3];
				for(int i=0;i<width*height*3;i++) {
					pixels[i]=Integer.parseInt(input.next());
				}
			}
			else if(a=='5') {
				chByte=(char)fis.read();
				while(Character.isWhitespace(chByte)) {
					chByte=(char)fis.read();
				}
				widtharray=new byte[3];
				widtharray[0]=(byte)chByte;
				chByte=(char)fis.read();
				counter2=1;
				while(!Character.isWhitespace(chByte)) {
					widtharray[counter2++]=(byte)chByte;
					chByte=(char)fis.read();
				}
				chByte=(char)fis.read();
				byte [] heightarray = new byte [3];
				heightarray[0]=(byte)chByte;
				counter2=1;
				chByte=(char)fis.read();
				while(!Character.isWhitespace(chByte)) {
					heightarray[counter2++]=(byte)chByte;
					chByte=(char)fis.read();
				}
				while(Character.isWhitespace(chByte)) {
					chByte =(char)fis.read();
				}
				maxValarray=new byte[3];
				maxValarray[0]=(byte)chByte;
				counter2=1;
				chByte=(char)fis.read();
				while(!Character.isWhitespace(chByte)) {
					maxValarray[counter2++]=(byte)chByte;
					chByte=(char)fis.read();
				}
				String strWidth=new String(widtharray);
				width=Integer.parseInt(strWidth);
				
				String strHeight=new String(heightarray);
				height=Integer.parseInt(strHeight);
				
				String strMaxVal=new String(maxValarray);
				maxVal=Integer.parseInt(strMaxVal);
				bByte=fis.read();
				
				while(Character.isWhitespace(bByte)) {
					bByte=fis.read();
				}	
				pixels=new int[width*height];
				pixels[0]=bByte;
				for(int i=1;i<height*width;i++) {
					pixels[i]=fis.read();
				}
				
			}
			else if(a=='6') {
				chByte=(char)fis.read();
				while(Character.isWhitespace(chByte)) {
					chByte=(char)fis.read();
				}
				widtharray=new byte[3];
				widtharray[0]=(byte)chByte;
				chByte=(char)fis.read();
				counter2=1;
				while(!Character.isWhitespace(chByte)) {
					widtharray[counter2++]=(byte)chByte;
					chByte=(char)fis.read();
				}
				chByte=(char)fis.read();
				byte [] heightarray = new byte [3];
				heightarray[0]=(byte)chByte;
				counter2=1;
				chByte=(char)fis.read();
				while(!Character.isWhitespace(chByte)) {
					heightarray[counter2++]=(byte)chByte;
					chByte=(char)fis.read();
				}
				while(Character.isWhitespace(chByte)) {
					chByte =(char)fis.read();
				}
				maxValarray=new byte[3];
				maxValarray[0]=(byte)chByte;
				counter2=1;
				chByte=(char)fis.read();
				while(!Character.isWhitespace(chByte)) {
					maxValarray[counter2++]=(byte)chByte;
					chByte=(char)fis.read();
				}
				String strWidth=new String(widtharray);
				width=Integer.parseInt(strWidth);
				
				String strHeight=new String(heightarray);
				height=Integer.parseInt(strHeight);
				
				String strMaxVal=new String(maxValarray);
				maxVal=Integer.parseInt(strMaxVal);
				bByte=fis.read();
				
				while(Character.isWhitespace(bByte)) {
					bByte=fis.read();
				}	
				pixels=new int[width*height*3];
				pixels[0]=bByte;
				for(int i=1;i<height*width*3;i++) {
					pixels[i]=fis.read();
				}
			}
			
		}
		catch(FileNotFoundException e) {}
		catch(NoSuchElementException e1) {}
		catch(IOException e2) {}
		dp=new DrawingPanel();
		add(dp);
				
		
		setVisible(true);	
	}
	
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(a=='1') {
				try {
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
				catch(IllegalArgumentException e) {System.out.println("Illegal Argument");}
			}
			else if(a=='2') {
				try {
					for(int row=0;row<height;row++) {
						for(int col=0;col<width;col++) {
								Color intColor=new Color(pixels[row*width+col],pixels[row*width+col],pixels[row*width+col]);
								g.setColor(intColor);
								g.fillRect(row, col, 1, 1);
						}
					}
				}
				catch(IllegalArgumentException e3) {System.out.println("Illegal Argument");}
			}
			else if(a=='3') {
				int counter=0;
				try {
					for(int row=0;row<height;row++) {
						for(int col=0;col<width;col++) {
							g.setColor(new Color(pixels[counter],pixels[counter+1],pixels[counter+2]));
							g.fillRect(col, row, 1, 1);
							counter=counter+3;
						}
					}	
				}
				catch(IllegalArgumentException e4) {System.out.println("Illegal Argument");}
			}
			else if(a=='5') {
				try {
					for(int row=0;row<height;row++) {
						for(int col=0;col<width;col++) {
							Color c=new Color(pixels[row*width+col],pixels[row*width+col],pixels[row*width+col]);
							g.setColor(c);
							g.fillRect(col, row,1, 1);
						}
					}
				}
				catch(IllegalArgumentException e5) {System.out.println("Illegal Argument");}
			}
			else if(a=='6') {
				try {
					int counter3=0;
					for(int row=0;row<height;row++) {
						for(int col=0;col<width;col++) {
							Color c=new Color(pixels[counter3],pixels[counter3+1],pixels[counter3+2]);
							g.setColor(c);
							g.fillRect(col, row,1, 1);
							counter3=counter3+3;
						}
					}
				}
				catch(IllegalArgumentException e5) {System.out.println("Illegal Argument");}
			}
		}
	}
	
	public static void main(String []args) {
		new PFiles();
	}

}
class PFiles2 extends PFiles{
	private byte[]magicNumm;
	
	PFiles2(String filename2){
		System.out.println("sa");
		
	}
}
