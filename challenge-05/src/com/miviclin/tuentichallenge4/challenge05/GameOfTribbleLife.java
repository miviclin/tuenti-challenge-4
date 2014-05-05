package com.miviclin.tuentichallenge4.challenge05;

import java.util.ArrayList;
import java.util.Set;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 5 - Tribblemaker
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class GameOfTribbleLife {

	private Set<Integer> rulesCellIsBorn;
	private Set<Integer> rulesCellStaysAlive;

	public GameOfTribbleLife(Set<Integer> rulesCellIsBorn, Set<Integer> rulesCellStaysAlive) {
		this.rulesCellIsBorn = rulesCellIsBorn;
		this.rulesCellStaysAlive = rulesCellStaysAlive;
	}

	public LoopInfo findLoop(TribblesGrid initialState, int maxGenerations) {
		ArrayList<TribblesGrid> states = new ArrayList<>();
		states.add(initialState);

		TribblesGrid currentState = initialState;
		for (int i = 1; i < maxGenerations; i++) {
			currentState = generateNextState(currentState);

			for (int previousStateIndex = 0; previousStateIndex < states.size(); previousStateIndex++) {
				if (states.get(previousStateIndex).equals(currentState)) {
					int firstGenerationIndex = previousStateIndex;
					int period = i - previousStateIndex;
					return new LoopInfo(firstGenerationIndex, period);
				}
			}
			states.add(currentState);
		}
		return null;
	}

	public TribblesGrid generateNextState(TribblesGrid state) {
		TribblesGrid nextState = new TribblesGrid(state.getNumRows(), state.getNumColumns());
		for (int i = 0; i < state.getNumRows(); i++) {
			for (int j = 0; j < state.getNumColumns(); j++) {
				int numNeighbours = state.countNeighbours(i, j);
				if (state.isTribbleAt(i, j)) {
					applyRulesToLivingTribble(nextState, i, j, numNeighbours);
				} else {
					applyRulesToVacantCell(nextState, i, j, numNeighbours);
				}
			}
		}
		return nextState;
	}

	private void applyRulesToLivingTribble(TribblesGrid state, int rowIndex, int columnIndex, int numNeighbours) {
		boolean matchesAnyRule = false;
		for (Integer rule : rulesCellStaysAlive) {
			if (numNeighbours == rule) {
				state.putTribble(rowIndex, columnIndex);
				matchesAnyRule = true;
			}
		}
		if (!matchesAnyRule) {
			state.removeTribble(rowIndex, columnIndex);
		}
	}

	private void applyRulesToVacantCell(TribblesGrid state, int rowIndex, int columnIndex, int numNeighbours) {
		for (Integer rule : rulesCellIsBorn) {
			if (numNeighbours == rule) {
				state.putTribble(rowIndex, columnIndex);
			}
		}
	}

}
