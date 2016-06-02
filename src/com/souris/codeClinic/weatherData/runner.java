package com.souris.codeClinic.weatherData;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class runner {

	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		//takes input year from the user and passes it to the dataGrabber object
		Scanner scan = new Scanner(System.in);
		System.out.println(" Please input year (must be between 2012 - 2015)");
		String year = scan.nextLine();
		scan.close();
		dataGrabber data = new dataGrabber(year);
		data.pageConnector();
		
		System.out.println("Air Average: "+data.airAverage);
		System.out.println("Air Median: "+data.airMedian);
		System.out.println("Wind Average: "+data.windAverage);
		System.out.println("Wind Median: "+data.windMedian);
		System.out.println("Barometric Average: "+data.barometricAverage);
		System.out.println("Barometric Median: "+data.barometricMedian);
		

	}

}
