package com.miviclin.tuentichallenge4.challenge08;

import java.util.Arrays;

public final class State {

	public static final int NUM_ROWS = 3;
	public static final int NUM_COLUMNS = 3;

	private int[][] cells;

	public State() {
		this.cells = new int[NUM_ROWS][NUM_COLUMNS];
	}

	public State(State state) {
		this.cells = new int[NUM_ROWS][NUM_COLUMNS];
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				this.cells[i][j] = state.cells[i][j];
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cells);
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
		State other = (State) obj;
		if (!Arrays.deepEquals(cells, other.cells)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				output += "[" + cells[i][j] + "]";
			}
			output += "\n";
		}
		return output;
	}

	public int get(int rowIndex, int columnIndex) {
		return cells[rowIndex][columnIndex];
	}

	public void set(int rowIndex, int columnIndex, int value) {
		cells[rowIndex][columnIndex] = value;
	}

	public boolean isValidCell(int rowIndex, int columnIndex) {
		return (rowIndex >= 0) && (rowIndex < NUM_ROWS) && (columnIndex >= 0) && (columnIndex < NUM_COLUMNS);
	}

	public void swap(int rowIndex1, int columnIndex1, int rowIndex2, int columnIndex2) {
		int aux = cells[rowIndex1][columnIndex1];
		cells[rowIndex1][columnIndex1] = cells[rowIndex2][columnIndex2];
		cells[rowIndex2][columnIndex2] = aux;
	}

	public int calculateManhattanDistance(State state) {
		int distance = 0;
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				distance += calculateManhattanDistance(i, j, state);
			}
		}
		return distance;
	}

	private int calculateManhattanDistance(int x, int y, State state) {
		int value = get(x, y);
		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLUMNS; j++) {
				if (state.get(i, j) == value) {
					return calculateManhattanDistance(x, y, i, j);
				}
			}
		}
		return 0;
	}

	private static int calculateManhattanDistance(int startX, int startY, int endX, int endY) {
		return Math.abs(startX - endX) + Math.abs(startY - endY);
	}

}
