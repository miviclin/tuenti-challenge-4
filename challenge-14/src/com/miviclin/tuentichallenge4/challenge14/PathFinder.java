package com.miviclin.tuentichallenge4.challenge14;

import java.util.ArrayList;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 14 - Train Empire
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class PathFinder<T> {

	public ArrayList<Path<T>> findAllPaths(Node<T> sourceNode, int maxEnergy,
			EnergyLossCalculator<T> energyLossCalculator) {

		ArrayList<Path<T>> allPaths = new ArrayList<>();
		Path<T> path = new Path<>();
		findAllPaths(allPaths, path, sourceNode, maxEnergy, energyLossCalculator);
		return allPaths;
	}

	private void findAllPaths(ArrayList<Path<T>> allPaths, Path<T> currentPath, Node<T> node, float currentEnergy,
			EnergyLossCalculator<T> energyLossCalculator) {

		currentPath.add(node.getValue());

		for (Node<T> adjacentNode : node.getAdjacencies()) {
			float energyLeft = currentEnergy - energyLossCalculator.calculateEnergyLoss(node, adjacentNode);
			if (energyLeft < 0) {
				Path<T> path = new Path<>(currentPath);
				allPaths.add(path);
				currentPath.removeLastElement();
				return;
			}
			findAllPaths(allPaths, currentPath, adjacentNode, energyLeft, energyLossCalculator);

		}
		currentPath.removeLastElement();
	}

}
