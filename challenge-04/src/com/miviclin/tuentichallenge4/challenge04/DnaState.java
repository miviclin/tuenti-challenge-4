package com.miviclin.tuentichallenge4.challenge04;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 4 - Shape shifters
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class DnaState {

	private String nucleotides;

	public DnaState(String nucleotides) {
		this.nucleotides = nucleotides;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nucleotides == null) ? 0 : nucleotides.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DnaState other = (DnaState) obj;
		if (nucleotides == null) {
			if (other.nucleotides != null) {
				return false;
			}
		} else if (!nucleotides.equals(other.nucleotides)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return nucleotides;
	}

	public String getNucleotides() {
		return nucleotides;
	}

	public int countNonMatchingNucleotides(DnaState dnaState) {
		if (nucleotides.length() != dnaState.nucleotides.length()) {
			return Integer.MAX_VALUE;
		}

		int count = 0;
		for (int i = 0; i < nucleotides.length(); i++) {
			if (nucleotides.charAt(i) != dnaState.nucleotides.charAt(i)) {
				count++;
			}
		}
		return count;
	}

}
