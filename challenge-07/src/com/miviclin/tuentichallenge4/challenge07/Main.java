package com.miviclin.tuentichallenge4.challenge07;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 7 - Yes we scan
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class Main {

	public static void main(String[] args) {
		String phoneCallsLogPath;
		if (args.length > 0) {
			phoneCallsLogPath = args[0];
		} else {
			throw new IllegalArgumentException("The path of the file that contains the log of phone calls should " +
					"be passed as an argument.");
		}

		try {
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));

			int firstTerroristId = Integer.parseInt(stdInReader.readLine().trim());
			int secondTerroristId = Integer.parseInt(stdInReader.readLine().trim());

			PhoneCallsScanner phoneCallsScanner = new PhoneCallsScanner();
			phoneCallsScanner.readPhoneCallsLog(phoneCallsLogPath);

			String connection = phoneCallsScanner.findConnection(firstTerroristId, secondTerroristId);

			System.out.println(connection);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
