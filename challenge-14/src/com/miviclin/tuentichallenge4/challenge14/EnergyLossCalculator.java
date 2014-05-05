package com.miviclin.tuentichallenge4.challenge14;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 14 - Train Empire
 * 
 * @author Miguel Vicente Linares
 * 
 */
public interface EnergyLossCalculator<T> {

	float calculateEnergyLoss(Node<T> source, Node<T> destination);
}
