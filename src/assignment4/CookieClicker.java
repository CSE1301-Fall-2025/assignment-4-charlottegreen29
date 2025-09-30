package assignment4;

import java.awt.Color;
import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;

import edu.princeton.cs.introcs.StdDraw;

public class CookieClicker {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		double chocolateChips[][] = new double[50][2]; //Randomly generates where our chocolate chips go
		for (int i=0;i<50;i++){
			double chocolateChipX = ((Math.random()*0.4)+0.3);
			double chocolateChipY = ((Math.random()*0.4)+0.3);
			double distanceFromCenter = Math.sqrt(Math.pow(0.5-chocolateChipX,2)+Math.pow(0.5-chocolateChipY,2));
			if (distanceFromCenter<0.19){
				chocolateChips[i][0]=chocolateChipX;
				chocolateChips[i][1]=chocolateChipY;
			}
		}

		int numCookies=0;
		int cookiesPerClick = 1;
		int clickPUCost = 10;
		int cookiesPerSec = 1;
		int secPUCost = 15;

		long lastUpdate = startTime;

		while(true){

			//clear screen
			StdDraw.clear();

			// generates the chocolate chip cookie
			Color cookieColor = new Color(160,130,90);
			StdDraw.setPenColor(cookieColor);
			StdDraw.filledCircle(.5, .5, .2);
			Color chocolateColor = new Color(51,36,43);
			StdDraw.setPenColor(chocolateColor);
			for (double chip[]: chocolateChips){
				if (chip[0]!=0){
					StdDraw.filledCircle(chip[0],chip[1],0.01);
				}
			}

			// increases point value when cookie is pressed
			if (StdDraw.isMousePressed() && (Math.sqrt(Math.pow(0.5-StdDraw.mouseX(),2)+Math.pow(0.5-StdDraw.mouseY(),2))<0.19)){
				numCookies+=cookiesPerClick;
			}
			StdDraw.text(0.5,0.9,"Number of Cookies: "+numCookies);

			//Click power-up
			StdDraw.setPenColor(25,120,25);
			StdDraw.filledRectangle(0.13,0.75,0.08,0.08);
			StdDraw.text(0.15,0.85,"Cookies per click: " + cookiesPerClick);
			StdDraw.text(0.13,0.65,"Cost: " + clickPUCost);
			boolean clickPUSpot = (StdDraw.mouseX()>(0.13-0.08) && StdDraw.mouseX()<(0.13+0.08))&&(StdDraw.mouseY()>(0.75-0.08) && StdDraw.mouseY()<(0.75+0.08));
			if (StdDraw.isMousePressed() && clickPUSpot){
				cookiesPerClick++;
				numCookies-=clickPUCost;
				clickPUCost+=10;
			}

			//Time power-up
			StdDraw.setPenColor(25,25,120);
			StdDraw.filledRectangle(0.13,0.35,0.08,0.08);
			StdDraw.text(0.15,0.45,"Cookies per sec: " + cookiesPerSec);
			StdDraw.text(0.13,0.25,"Cost: " + secPUCost);
			long now = System.currentTimeMillis();
			boolean secPUSpot = (StdDraw.mouseX()>(0.13-0.08) && StdDraw.mouseX()<(0.13+0.08))&&(StdDraw.mouseY()>(0.35-0.08) && StdDraw.mouseY()<(0.35+0.08));
			if (StdDraw.isMousePressed() && secPUSpot){
				cookiesPerSec++;
				numCookies-=secPUCost;
				secPUCost+=15;
			}
			if (now - lastUpdate >= 1000) {
    			numCookies+=cookiesPerSec;
    			lastUpdate = now;
			}

			// I just realized that the instructions said not to make buttons and to make the power-ups happen automatically, but I don't like that because then you don't get to make desicions about what you buy.

			// Another note: I know that there currently is nothing that keeps you from buying power-ups when you don't have enough cookies (it just says you have negative cookies). I could fix that if I wanted to, but I'm tired, so I'm going to bed.

			// pause
			StdDraw.show();
			StdDraw.pause(50);

		

		}
		
	}
}
