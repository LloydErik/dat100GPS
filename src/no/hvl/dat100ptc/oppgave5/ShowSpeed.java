package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
		
		int x = MARGIN; 
	    double[] speeds = gpscomputer.speeds(); 
	    double avgSpeed = gpscomputer.averageSpeed(); 
	    double maxSpeed = GPSUtils.findMax(speeds); 

	    double yscale = BARHEIGHT / maxSpeed;
	    
	    for (int i = 0; i < speeds.length; i++) {
	        int barHeight = (int)(speeds[i] * yscale); 
	        
	        setColor(0, 0, 255); 
	        fillRectangle(x, ybase - barHeight, 2, barHeight); 
	        
	        x += 2; 
	    }
	    

	    setColor(0, 255, 0); 
	    int avgY = ybase - (int)(avgSpeed * yscale); 
	    drawLine(MARGIN, avgY, MARGIN + speeds.length * 2, avgY); 
		
	}
}
