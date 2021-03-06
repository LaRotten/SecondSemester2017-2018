package P6Files;



import javax.swing.JFrame;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class P6Deneme extends JFrame{
	private int [] pixs;
	private int width,height,maxVal;
	private DrawingPanel dp;
	P6Deneme(){
	  try{
		dp = new DrawingPanel();
		FileInputStream fis = 
			new FileInputStream("tree_2.ppm");
	   byte [] magicNum = new byte[2];
		fis.read(magicNum);
		//magicNum[0] = 112
		//magicNum[1] = 53

		char chByte = (char) fis.read();
		while(Character.isWhitespace(chByte))
			chByte = (char) fis.read();

		byte fb = (byte) chByte;
		//chByte has the first byte of width
		int count = 1;
		byte [] w = new byte[3];
		w[0] = fb; 

		byte aByte = (byte) fis.read();
		while(!Character.isWhitespace(aByte)){
			w[count++] = aByte;
			aByte = (byte) fis.read();
		}
		//if the file's head part has
		//P5
		//34 200 
		//...
		//w[0] = 51;
		//w[1] = 52;
		

		System.out.println("count: " + count);
		int i = 0;
		String strWidth = new String(w);

		//while(i < count)
		//	strWidth = strWidth + (w[i++]-'0');
		
		System.out.println(new String(magicNum)); 
		//System.out.println(w[0]+":" + w[1]+":"+w[2]);
		System.out.println(strWidth);
		
		width = Integer.parseInt(strWidth);
		
		aByte = (byte) fis.read();
		while(Character.isWhitespace(aByte))
			aByte = (byte) fis.read();
		
		
		//aByte has the first byte of height
		count = 1;
		byte [] h = new byte[3];
		h[0] = aByte; 

		//P5
		//34 200
		//255
		//binary-data
		aByte = (byte) fis.read();
		while(!Character.isWhitespace(aByte)){
			h[count++] = aByte;
			aByte = (byte) fis.read();
		}
		//h[0] = 50, h[1] = 48, h[2] = 48
		height = Integer.parseInt(new String(h));
		System.out.println("height: " + height);


		aByte = (byte) fis.read();
		while(Character.isWhitespace(aByte))
			aByte = (byte) fis.read();


		count = 1;
		byte [] mv = new byte[3];
		mv[0] = aByte;
		aByte = (byte) fis.read();
		while(!Character.isWhitespace(aByte)){
			mv[count++] = aByte;
			aByte = (byte) fis.read();
		}
		System.out.println("Max.Value: " + 
			new String(mv));

		aByte = (byte) fis.read();
		while(Character.isWhitespace(aByte))
			aByte = (byte) fis.read();

		pixs = new int[width*height*3];
		pixs[0] = aByte;
		for(int k = 1; k < width*height*3; k++)
			pixs[k] =  fis.read();
		System.out.println("Done reading the file");
		
		add(dp);
		
	  }
	  catch(FileNotFoundException e1){}
	  catch(IOException e2){}
	  //denemeee
	}   
	class DrawingPanel extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			int counter=0;
			for(int row = 0; row < height; row++)
				for(int col = 0; col < width; col++)
				{
					
				  try{
					Color c = new Color(
						pixs[counter],
						pixs[counter+1],
						pixs[counter+2]);
						g.setColor(c);
						g.fillRect(col ,row,1,1);
						counter=counter+3;
					}catch(IllegalArgumentException e){
					  System.out.println(pixs[counter]);
					}
				}
		}
	}
	public static void main(String [] args){
	   P6Deneme window = new P6Deneme();

		window.setSize(600,600);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}
	/*
		P5
		34	200
		255
		binary-data

	*/

}










