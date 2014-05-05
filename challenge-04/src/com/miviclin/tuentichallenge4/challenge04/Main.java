package com.miviclin.tuentichallenge4.challenge04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 4 - Shape shifters
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class Main {

	public static void main(String[] args) {
		try {
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));

			String sourceStateData = stdInReader.readLine().trim();
			DnaState sourceState = new DnaState(sourceStateData);

			String targetStateData = stdInReader.readLine().trim();
			DnaState targetState = new DnaState(targetStateData);

			Set<DnaState> permittedSafeStates = new HashSet<>();

			String line;
			while ((line = stdInReader.readLine()) != null) {
				String permittedSafeStateData = line.trim();
				if (permittedSafeStateData.equals("")) {
					break;
				}
				permittedSafeStates.add(new DnaState(permittedSafeStateData));
			}

			DnaMutator dnaMutator = new DnaMutator(sourceState, targetState, permittedSafeStates);
			DnaTransitionList minimumTransitions = dnaMutator.calculateMinimumTransitions();
			System.out.println(minimumTransitions.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
