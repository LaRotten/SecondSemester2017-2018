package P5Files;


import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class P5Deneme3 extends JFrame{
	private int height,width,maxVal;
	private int pixels[];
	private byte widtharray[],heightarray[],maxValarray[];
	private int count;
	private String strHeight,strWidth,strmaxVal;
	P5Deneme3(){
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			FileInputStream fis=new FileInputStream("baboon.pgm");
			
			byte magicNum [] =new byte[2];
			fis.read(magicNum);
			System.out.println((char)magicNum[0]+ ""+(char)magicNum[1] );	//MagicNumDone
			char chByte=(char)fis.read();
			while(Character.isWhitespace(chByte))	 chByte=(char)fis.read();
			widtharray=new byte [3];	widtharray[0]=(byte)chByte;
			chByte=(char)fis.read();	
			count=1;
			while(!Character.isWhitespace(chByte)) {
				widtharray[count++]=(byte)chByte;
				chByte=(char)fis.read();
			}
			System.out.print((char)widtharray[0]+""+(char)widtharray[1]+""+(char)widtharray[2]+" ");
			while(Character.isWhitespace(chByte))	chByte=(char)fis.read();
			heightarray=new byte[3];	heightarray[0]=(byte)chByte;
			chByte=(char)fis.read();
			count=1;
			while(!Character.isWhitespace(chByte)) {
				heightarray[count++]=(byte)chByte;
				chByte=(char)fis.read();
			}
			System.out.println((char)heightarray[0]+""+(char)heightarray[1]+""+(char)heightarray[2]);	
			while(Character.isWhitespace(chByte)) 	chByte=(char)fis.read();
			maxValarray=new byte[3];
			maxValarray[0]=(byte)chByte;
			chByte=(char)fis.read();
			count=1;
			while(!Character.isWhitespace(chByte)) {
				maxValarray[count++]=(byte)chByte;
				chByte=(char)fis.read();
			}
			System.out.println((char)maxValarray[0]+""+(char)maxValarray[1]+""+(char)maxValarray[2]);
			
			int intByte=fis.read();	
			while(Character.isWhitespace(intByte))	intByte=fis.read();	
			strHeight=new String(heightarray);		height=Integer.parseInt(strHeight);
			strWidth=new String(widtharray);		width=Integer.parseInt(strWidth);
			strmaxVal=new String(maxValarray);		maxVal=Integer.parseInt(strmaxVal);
		
			pixels=new int[height*width];
			pixels[0]=intByte;
			for(int i=1;i<height*width;i++) 		pixels[i]=fis.read();
			
			
			
			
			
		}
		catch(IOException e) {System.out.println("IO Exception");}
		
		
		
		setVisible(true);
	}
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int row=0;row<height;row++) {
				for(int col=0;col<width;col++) {
					try {
						Color c=new Color(pixels[row*width+col],pixels[row*width+col],pixels[row*width+col]);
					g.setColor(c);
					g.fillRect(col, row, 1, 1);
					}
					catch(IllegalArgumentException e) {System.out.println("IllegalArgument");}
			
				}
			}
		}
		
	}
	
	public static void main(String []args) {
		new P5Deneme3();
	}
}

