package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double d = 0;

		for(int i = 0; i<gpspoints.length-1;i++) {
			int j = i+1;
		
			 d += GPSUtils.distance(gpspoints[i], gpspoints[j]);
			
		}
		return d;

	}

	public double totalElevation() {

	    double e = 0;

	    for (int i = 1; i < gpspoints.length; i++) {
	        double previousElevation = gpspoints[i - 1].getElevation();
	        double currentElevation = gpspoints[i].getElevation();
	        
	        if (currentElevation > previousElevation) {
	            e += currentElevation - previousElevation;
	        }
	    }

	    return e;
		
	}

	public int totalTime() {
		
	    if (gpspoints == null || gpspoints.length == 0) {
	        return 0;  
	    }

	    int startTime = gpspoints[0].getTime();
	    int endTime = gpspoints[gpspoints.length - 1].getTime();
	    
	    return endTime - startTime;
	}
		

	public double[] speeds() {
		
		int lenminone = gpspoints.length-1;
		double[] speeds = new double[lenminone];
		
		for( int i = 0; i<lenminone; i++){
			int j = i+1;
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[j]);
		}
			return speeds;	
	}
	
	public double maxSpeed() {
		
		return GPSUtils.findMax(speeds());
		 
	}

	public double averageSpeed() {
	    double totalDistance = totalDistance();  
	    int totalTime = totalTime();             

	    if (totalTime == 0) {
	        return 0; 
	    }

	    return totalDistance / totalTime;
	}


	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {
		
	    double speedmph = speed * MS; 
	    double met;
	    
	    if (speedmph < 10) {
	        met = 4.0;
	    } else if (speedmph < 12) {
	        met = 6.0;
	    } else if (speedmph < 14) {
	        met = 8.0;
	    } else if (speedmph < 16) {
	        met = 10.0;
	    } else if (speedmph < 20) {
	        met = 12.0;
	    } else {
	        met = 16.0;
	    }

	    double timeHours = secs / 3600.0;

	    double kcal = met * weight * timeHours;

	    return kcal;
		
	}

	public double totalKcal(double weight) {
		
	    double totalKcal = 0;

	    for (int i = 0; i < gpspoints.length - 1; i++) {
	        GPSPoint point1 = gpspoints[i];
	        GPSPoint point2 = gpspoints[i + 1];

	        int secs = point2.getTime() - point1.getTime();
	        double speed = GPSUtils.speed(point1, point2);  

	        totalKcal += kcal(weight, secs, speed);
	    }

	    return totalKcal;
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

	    int totalTime = totalTime();
	    double totalDistanceKm = totalDistance() / 1000;  
	    double totalElevation = totalElevation();  
	    double maxSpeedKmh = maxSpeed() * 3.6;  
	    double averageSpeedKmh = averageSpeed() * 3.6;  
	    double totalKcal = totalKcal(WEIGHT);  

	    System.out.println("==============================================");
	    System.out.println("Total Time     :   " + GPSUtils.formatTime(totalTime));
	    System.out.println(String.format("Total distance : %10.2f km", totalDistanceKm));
	    System.out.println(String.format("Total elevation: %10.2f m", totalElevation));
	    System.out.println(String.format("Max speed      : %10.2f km/t", maxSpeedKmh));
	    System.out.println(String.format("Average speed  : %10.2f km/t", averageSpeedKmh));
	    System.out.println(String.format("Energy         : %10.2f kcal", totalKcal));
	    System.out.println("==============================================");
	}

}
