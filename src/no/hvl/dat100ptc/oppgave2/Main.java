package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {


	
	public static void main(String[] args) {
		
		GPSPoint point1 = new GPSPoint(0,49.34879,3.928374,30.0);
		GPSPoint point2 = new GPSPoint(10,22.47548,6.473892,50.0);
		
		GPSData gpsData = new GPSData(2);
		
		gpsData.insertGPS(point1);
		gpsData.insertGPS(point2);
		
		gpsData.print();
		
		
	}
}
