package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
		for(double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
				
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
		double[] lattitudes = new double[gpspoints.length];

		for(int i = 0;i<gpspoints.length;i++) {
			lattitudes[i] = gpspoints[i].getLatitude();
			
		}
		return lattitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] longitudes = new double[gpspoints.length];
		
		for(int i = 0; i<gpspoints.length;i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;
	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

	    double latitude1 = gpspoint1.getLatitude();
	    double longitude1 = gpspoint1.getLongitude();
	    double latitude2 = gpspoint2.getLatitude();
	    double longitude2 = gpspoint2.getLongitude();

	    double phi1 = Math.toRadians(latitude1);
	    double phi2 = Math.toRadians(latitude2);
	    double deltadelta = Math.toRadians(longitude2 - longitude1);
	    double deltaphi = Math.toRadians(latitude2 - latitude1);

	    double a = compute_a(phi1, phi2, deltaphi, deltadelta);
	    double c = compute_c(a);

	    double d = R * c;

	    return d;
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
		
		double result = Math.pow((Math.sin((deltaphi)/2)),2) + (Math.cos(phi1) * Math.cos(phi2)) * (Math.pow((Math.sin(deltadelta)/2),2));
		return result;
		

	}

	private static double compute_c(double a) {
		
		double result = 2*(Math.atan2(Math.sqrt(a),Math.sqrt(1-a)));
		return result;
		
	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		
		secs = gpspoint2.getTime()- gpspoint1.getTime();
		
		double d = distance(gpspoint1,gpspoint2);
		speed = d/secs;

		return speed;
	}

	public static String formatTime(int secs) {

        int hours = secs / 3600;
        int minutes = (secs % 3600) / 60;
        int seconds = secs % 60;

        return String.format("  %02d:%02d:%02d", hours, minutes, seconds);
	
		
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String formatted = String.format("%.2f", d);
        
        return String.format("%" + TEXTWIDTH + "s", formatted);
		
	}
}
