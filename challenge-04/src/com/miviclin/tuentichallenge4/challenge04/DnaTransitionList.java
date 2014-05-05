package com.miviclin.tuentichallenge4.challenge04;

import java.util.ArrayList;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 4 - Shape shifters
 * 
 * @author Miguel Vicente Linares
 * 
 */
@SuppressWarnings("serial")
public class DnaTransitionList extends ArrayList<DnaState> {

	@Override
	public String toString() {
		String output = "";
		String transitionSign = "->";
		for (DnaState dnaState : this) {
			output += dnaState.getNucleotides() + transitionSign;
		}
		return output.substring(0, output.length() - transitionSign.length());
	}
}
