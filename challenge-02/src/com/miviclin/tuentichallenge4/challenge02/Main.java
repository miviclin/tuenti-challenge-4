package com.miviclin.tuentichallenge4.challenge02;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		try {
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));
			String inputTrack = stdInReader.readLine();
			TrackPrettifier trackPrettifier = new TrackPrettifier(inputTrack);
			System.out.println(trackPrettifier.prettify());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
