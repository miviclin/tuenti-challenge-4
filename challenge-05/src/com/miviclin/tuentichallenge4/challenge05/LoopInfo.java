package com.miviclin.tuentichallenge4.challenge05;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 5 - Tribblemaker
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class LoopInfo {

	private int firstGenerationIndex;
	private int period;

	public LoopInfo(int firstGenerationIndex, int period) {
		this.firstGenerationIndex = firstGenerationIndex;
		this.period = period;
	}

	@Override
	public String toString() {
		return firstGenerationIndex + " " + period;
	}

	public int getFirstGenerationIndex() {
		return firstGenerationIndex;
	}

	public void setFirstGenerationIndex(int firstGenerationIndex) {
		this.firstGenerationIndex = firstGenerationIndex;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

}
