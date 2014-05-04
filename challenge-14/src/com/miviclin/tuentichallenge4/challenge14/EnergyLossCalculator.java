package com.miviclin.tuentichallenge4.challenge14;

public interface EnergyLossCalculator<T> {

	float calculateEnergyLoss(Node<T> source, Node<T> destination);
}
