package com.miviclin.tuentichallenge4.challenge12;

public class Tile {

	public static final char START_SYMBOL = 'S';
	public static final char DESTINATION_SYMBOL = 'X';
	public static final char WALL_SYMBOL = '#';
	public static final char EMPTY_TILE_SYMBOL = '.';

	private int rowIndex;
	private int columnIndex;
	private char symbol;

	public Tile(int rowIndex, int columnIndex, char symbol) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.symbol = symbol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columnIndex;
		result = prime * result + rowIndex;
		result = prime * result + symbol;
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
		Tile other = (Tile) obj;
		if (columnIndex != other.columnIndex) {
			return false;
		}
		if (rowIndex != other.rowIndex) {
			return false;
		}
		if (symbol != other.symbol) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "(" + rowIndex + "," + columnIndex + "," + symbol + ")";
	}

	public int calculateManhattanDistance(Tile tile) {
		int startX = getColumnIndex();
		int startY = getRowIndex();
		int endX = tile.getColumnIndex();
		int endY = tile.getRowIndex();
		return calculateManhattanDistance(startX, startY, endX, endY);
	}

	private static int calculateManhattanDistance(int startX, int startY, int endX, int endY) {
		return Math.abs(startX - endX) + Math.abs(startY - endY);
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public char getSymbol() {
		return symbol;
	}

}
