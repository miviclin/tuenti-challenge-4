package com.miviclin.tuentichallenge4.challenge05;

import java.util.Arrays;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 5 - Tribblemaker
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class TribblesGrid {

	public static final char VACANT_CELL_SYMBOL = '-';
	public static final char TRIBBLE_SYMBOL = 'X';

	private char[][] cells;
	private int numRows;
	private int numColumns;

	public TribblesGrid(int numRows, int numColumns) {
		this.cells = new char[numRows][numColumns];
		this.numRows = numRows;
		this.numColumns = numColumns;

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				this.cells[i][j] = VACANT_CELL_SYMBOL;
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cells);
		result = prime * result + numColumns;
		result = prime * result + numRows;
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
		TribblesGrid other = (TribblesGrid) obj;
		if (!Arrays.deepEquals(cells, other.cells)) {
			return false;
		}
		if (numColumns != other.numColumns) {
			return false;
		}
		if (numRows != other.numRows) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				output += cells[i][j];
			}
			output += "\n";
		}
		return output;
	}

	public int countNeighbours(int rowIndex, int columnIndex) {
		int startRowIndex = Math.max(0, rowIndex - 1);
		int endRowIndex = Math.min(rowIndex + 1, numRows - 1);

		int startColumnIndex = Math.max(0, columnIndex - 1);
		int endColumnIndex = Math.min(columnIndex + 1, numColumns - 1);

		int count = 0;

		for (int i = startRowIndex; i <= endRowIndex; i++) {
			for (int j = startColumnIndex; j <= endColumnIndex; j++) {
				if ((i == rowIndex) && (j == columnIndex)) {
					continue;
				}
				if (isTribbleAt(i, j)) {
					count++;
				}
			}
		}

		return count;
	}

	public boolean isTribbleAt(int rowIndex, int columnIndex) {
		return cells[rowIndex][columnIndex] == TRIBBLE_SYMBOL;
	}

	public void putTribble(int rowIndex, int columnIndex) {
		cells[rowIndex][columnIndex] = TRIBBLE_SYMBOL;
	}

	public void removeTribble(int rowIndex, int columnIndex) {
		cells[rowIndex][columnIndex] = VACANT_CELL_SYMBOL;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

}
