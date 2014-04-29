package com.miviclin.tuentichallenge4.challenge05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

	private static final int NUM_GRID_ROWS = 8;
	private static final int NUM_GRID_COLUMNS = 8;

	private static final int MAX_GENERATIONS = 100;

	public static void main(String[] args) {

		Set<Integer> rulesCellIsBorn = new HashSet<>();
		rulesCellIsBorn.add(3);

		Set<Integer> rulesCellStaysAlive = new HashSet<>();
		rulesCellStaysAlive.add(2);
		rulesCellStaysAlive.add(3);

		GameOfTribbleLife gameOfTribbleLife = new GameOfTribbleLife(rulesCellIsBorn, rulesCellStaysAlive);

		try {
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));

			TribblesGrid initialState = new TribblesGrid(NUM_GRID_ROWS, NUM_GRID_COLUMNS);
			for (int i = 0; i < NUM_GRID_ROWS; i++) {
				String rowData = stdInReader.readLine().trim();
				for (int j = 0; j < NUM_GRID_COLUMNS; j++) {
					if (rowData.charAt(j) == TribblesGrid.TRIBBLE_SYMBOL) {
						initialState.putTribble(i, j);
					}
				}
			}

			LoopInfo loopInfo = gameOfTribbleLife.findLoop(initialState, MAX_GENERATIONS);

			System.out.println(loopInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
