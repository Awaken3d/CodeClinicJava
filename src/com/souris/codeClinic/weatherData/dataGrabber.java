package com.souris.codeClinic.weatherData;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class dataGrabber {
	String year;
	ArrayList<Double> wind, air, barometric;
	Double windMedian, windAverage, airMedian, airAverage, barometricMedian, barometricAverage;

	public dataGrabber(String year) {
		this.year = year;
	}

	public void pageConnector() throws IOException, NoSuchAlgorithmException, KeyManagementException {
		URL url = new URL("https://lpo.dt.navy.mil/data/DM/Environmental_Data_Deep_Moor_" + year + ".txt");
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		} };
		
		final SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		Scanner scan = new Scanner(conn.getInputStream());
		wind = new ArrayList<Double>();
		air = new ArrayList<Double>();
		barometric = new ArrayList<Double>();
		scan.nextLine();
		while (scan.hasNext()) {
			String[] input = scan.nextLine().split("\\s+");

			air.add(Double.parseDouble(input[2]));
			barometric.add(Double.parseDouble(input[3]));
			wind.add(Double.parseDouble(input[8]));
		}
		scan.close();
		airMedian = median(air);
		airAverage = average(air);
		windMedian = median(wind);
		windAverage = median(wind);
		barometricMedian = median(barometric);
		barometricAverage = average(barometric);
		return;

	}
	
	public double median(ArrayList<Double> in){
		Collections.sort(in);
		double median;
		if(in.size()%2 == 0){
			median = (in.get(((in.size()-1)/2))+ in.get((((in.size()-1)/2)+1)))/2;
			
		}else{
			median = in.get(in.size()/2);
		}
		return median;
	}
	
	public double average(ArrayList<Double> in){
		double sum = 0.0, avg = 0.0;
		for(double i:in){
			sum+=i;
		}
		avg = sum/in.size();
		return avg;
		
	}

}
