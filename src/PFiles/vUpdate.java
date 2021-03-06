package PFiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
public class vUpdate extends JFrame{
	JFileChooser jfchooser;
	File file;
    private int width, height, mval;
    private int[] pixels;
    private DrawingPanel dp;
    private static JMenuBar jmb;
    private char[] magicNum;
    vUpdate(){
    	JMenuItem jmiOpen, jmiExit;
    	JMenu jmFile;
    	JToolBar jtbar;
    	dp = new DrawingPanel();
        file = null;
        jmb = new JMenuBar();
        jmFile = new JMenu("File");
        jmb.add(jmFile);

        jmiOpen = new JMenuItem("Open");
        jmiExit = new JMenuItem("Exit");

        jmFile.add(jmiOpen);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        
        jtbar = new JToolBar();
        jtbar.setFloatable(false);
        
        setSize(650, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setJMenuBar(jmb);
		
        jmiOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
					JFileChooser jfchooser = new JFileChooser(".");
					int retValue = jfchooser.showOpenDialog(null);
					try {
						
						if(retValue == JFileChooser.APPROVE_OPTION) {
							file = jfchooser.getSelectedFile();
							FileInputStream fis = 
									new FileInputStream(file);
							BufferedInputStream bis =
									new BufferedInputStream(fis);
							Scanner sc = new Scanner(bis);
							
							magicNum = new char[2];
							magicNum[0] = (char) bis.read();
							magicNum[1] = (char) bis.read();
							System.out.println(magicNum);
							
							if(magicNum[1] == '1' || magicNum[1] == '2' || magicNum[1] == '3'){
								
								add(dp);
								width = Integer.parseInt(sc.nextLine());
								System.out.println(width);
								height = Integer.parseInt(sc.nextLine());
								System.out.println(height);
								
								if(magicNum[1] == '1'){
									mval = Integer.parseInt(sc.nextLine());
									System.out.println(mval);
								}
								if(magicNum[1] == '3'){
									pixels = new int[width*height*3];
									
								}
								else{
									pixels = new int[width*height];
								}
								
								for(int i = 0; i < pixels.length; i++){
									pixels[i] = Integer.parseInt(sc.nextLine());
								}
							}
							else if(magicNum[1] == '4' || magicNum[1] == '5' || magicNum[1] == '6'){
								
								add(dp);
								char charB = (char) bis.read();
								
								while(Character.isWhitespace(charB))
									charB = (char) bis.read();
								byte fb = (byte) charB;
								int count = 1;
								byte [] g = new byte[3];
								g[0] = fb;
								byte aByte = (byte) bis.read();
								
								while(!Character.isWhitespace(aByte)){
									g[count++] = aByte ;
									aByte = (byte) bis.read();
								}
								String strWidth = new String(g);
								width = Integer.parseInt(strWidth);
								System.out.println(width);
								aByte = (byte) bis.read();
								
								while(Character.isWhitespace(aByte))
									aByte = (byte) bis.read();
								count = 1;
								byte [] y = new byte[3];
								y[0] = aByte;
								aByte = (byte) bis.read();
								
								while(!Character.isWhitespace(aByte)){
									y[count++] = aByte;
									aByte = (byte) bis.read();
								}
								height = Integer.parseInt(new String(y));
								System.out.println(height);
								aByte = (byte) bis.read();
								
								while(Character.isWhitespace(aByte))
									aByte = (byte) bis.read();
								
								if(magicNum[1] == '5' || magicNum[1] == '6'){
									
									while(Character.isWhitespace(aByte))
										aByte = (byte) bis.read();
									count = 1;
									byte [] md = new byte[3];
									md[0] = aByte;
									aByte = (byte) bis.read();
									
									while(!Character.isWhitespace(aByte)){
										md[count++] = aByte;
										aByte = (byte) bis.read();
									}
									
									while(Character.isWhitespace(aByte))
										aByte = (byte) bis.read();
									if(magicNum[1] == '5'){
										pixels = new int[width*height];
									}
									else if(magicNum[1] == '6'){
										pixels = new int[width*height*3];
									}
									pixels[0] = aByte;
									for(int i = 1; i < pixels.length; i++){
										pixels[i] = bis.read();
									}
								}
								else{
									int [] px;
									if(width%8 == 0){
										px = new int[(height*(width/8))];
										pixels = new int[height*width];
									}
									else{
										int mod = width%8;
										px = new int[(height*(width/8))+height];
										pixels = new int[(height*width)];
									}
									for(int i = 0; i < px.length; i++){
										px[i] = bis.read();
									}
									int wd = width;
									int mod;
									int counter = 0;
									for(int i = 0; i < px.length; i++){
										mod = 8;
										wd -= 8;
										if(wd < 0){
											mod = width%8;
											wd = width;
										}
										for(int l = 7; l >= (8-mod); l--){
											pixels[counter++] = ((px[i]>>>l) & 1);
										}
									}
								}
							}
						}
					}catch(FileNotFoundException fnfe) {}
					catch(IOException ie){}
					catch(NullPointerException npe) {}
					catch(NumberFormatException nfe) {}
				}
		});  
        jmiExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
    }
    class DrawingPanel extends JPanel {
    	@Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            int counter = 0;
            if(magicNum[1] == '1' || magicNum[1] == '4'){
                	for(int col = 0; col < height; col++)
                		for(int row = 0; row < width; row++){
                			try{
                			if(pixels[counter++] == 0){
                				g.setColor(Color.white);
                			}
                			else{
                				g.setColor(Color.black);
                			}
                			g.fillRect(row, col, 1, 1);
            	}catch(IllegalArgumentException iae){g.fillRect(row, col, 1, 1);}
              }
            }
            else if(magicNum[1] == '2' || magicNum[1] == '5'){
            	
            		for(int col = 0; col < height; col++)
                		for(int row = 0; row < width; row++){
                			try{
                			g.setColor(new Color (
                					pixels[col*width+row],
                					pixels[col*width+row],
                					pixels[col*width+row]));
                			g.fillRect(row, col, 1, 1);
            	}catch(IllegalArgumentException iae){}
              }
            }
            else if(magicNum[1] == '3' || magicNum[1] == '6'){
            	
            		for(int col = 0; col < height; col++)
                		for(int row = 0; row < width; row++){
                			try{
                			g.setColor(new Color (
                					pixels[counter],
                					pixels[counter+1],
                					pixels[counter+2]));
                			counter+=3;
                			g.fillRect(col, row, 1, 1);
                		
            	}catch(IllegalArgumentException iae){}
         	  }
            }
    	}
    }
    public static void main(String args[]){
    	new vUpdate();
       
    }
}