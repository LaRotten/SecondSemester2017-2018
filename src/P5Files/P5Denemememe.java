package P5Files;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

public class P5Denemememe extends JFrame{
	P5Denemememe(){
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Scanner input=new Scanner(new File("tree_2.ppm"));
			String s=input.next();
			System.out.print(s);
		}
		catch(IOException e) {}
		
		
		setVisible(true);
	}
	
	public static void main(String []args) {
		new P5Denemememe();
	}
}
