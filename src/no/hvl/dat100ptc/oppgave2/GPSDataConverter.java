package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		String hr , min, sec;
		
		int hrInt = Integer.parseInt(timestr.substring(TIME_STARTINDEX, TIME_STARTINDEX + 2));
		int minInt = Integer.parseInt(timestr.substring(TIME_STARTINDEX + 3, TIME_STARTINDEX + 5));
		int secInt = Integer.parseInt(timestr.substring(TIME_STARTINDEX + 6, TIME_STARTINDEX + 8));
		
		secs = (60*60)*hrInt + 60*minInt + secInt;
		
		return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		int timeInt = toSeconds(timeStr);
		double lattitudeDoub = Double.parseDouble(latitudeStr);
		double longitudeDoub = Double.parseDouble(longitudeStr);
		double elevationDoub = Double.parseDouble(elevationStr);
		
		gpspoint = new GPSPoint(timeInt,lattitudeDoub,longitudeDoub,elevationDoub);
		
		return gpspoint;
		
		 
		
	}
	
}
