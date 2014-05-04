package com.miviclin.tuentichallenge4.challenge12;

public class CityMap {

	private int numRows;
	private int numColumns;
	private Tile[][] tiles;

	public CityMap(int numRows, int numColumns) {

		this.numRows = numRows;
		this.numColumns = numColumns;
		this.tiles = new Tile[numRows][numColumns];
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				output += tiles[i][j];
			}
			output += "\n";
		}
		return output;
	}

	public Tile get(Tile tile, int rowOffset, int columnOffset) {
		return tiles[tile.getRowIndex() + rowOffset][tile.getColumnIndex() + columnOffset];
	}

	public Tile get(int rowIndex, int columnIndex) {
		return tiles[rowIndex][columnIndex];
	}

	public void set(int rowIndex, int columnIndex, Tile value) {
		tiles[rowIndex][columnIndex] = value;
	}

	public boolean isValidMove(int rowIndex, int columnIndex) {

		boolean positionInsideBounds = (rowIndex >= 0) && (rowIndex < numRows) &&
				(columnIndex >= 0) && (columnIndex < numColumns);

		if (positionInsideBounds) {
			if (get(rowIndex, columnIndex).getSymbol() != Tile.WALL_SYMBOL) {
				return true;
			}
		}
		return false;
	}

	public Tile findStartTile() {
		return findFirstTileWithSymbol(Tile.START_SYMBOL);
	}

	public Tile findDestinationTile() {
		return findFirstTileWithSymbol(Tile.DESTINATION_SYMBOL);
	}

	private Tile findFirstTileWithSymbol(char symbol) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				Tile tile = get(i, j);
				if (tile.getSymbol() == symbol) {
					return tile;
				}
			}
		}
		return null;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

}
