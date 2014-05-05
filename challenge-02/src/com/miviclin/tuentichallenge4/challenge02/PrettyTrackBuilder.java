package com.miviclin.tuentichallenge4.challenge02;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 2 - F1 - Bird's-eye Circuit
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class PrettyTrackBuilder {

	public static final char START_FINISH_LINE = '#';
	public static final char HORIZONTAL_TRACK = '-';
	public static final char VERTICAL_TRACK = '|';
	public static final char CURVE_1 = '/';
	public static final char CURVE_2 = '\\';
	public static final char EMPTY_SPACE = ' ';
	public static final char EOL = '\n';

	private char[][] data;
	private int numRows;
	private int numColumns;
	private char defaultValue;
	private int lastInsertionRowIndex;
	private int lastInsertionColumIndex;
	private Direction currentDirection;

	public PrettyTrackBuilder() {
		this.numRows = 1;
		this.numColumns = 1;
		this.defaultValue = EMPTY_SPACE;
		this.data = new char[numRows][numColumns];
		this.data[0][0] = START_FINISH_LINE;
		this.lastInsertionRowIndex = 0;
		this.lastInsertionColumIndex = 0;
		this.currentDirection = Direction.RIGHT;
	}

	public void insertStraightSegment(int amount) {
		switch (currentDirection) {
		case LEFT:
			insertLeft(HORIZONTAL_TRACK, amount);
			break;
		case RIGHT:
			insertRight(HORIZONTAL_TRACK, amount);
			break;
		case UP:
			insertUp(VERTICAL_TRACK, amount);
			break;
		case DOWN:
			insertDown(VERTICAL_TRACK, amount);
			break;
		default:
			break;
		}
	}

	public void insertCurve(char curveChar) {
		if (curveChar == CURVE_1) {
			insertCurve1();
		} else if (curveChar == CURVE_2) {
			insertCurve2();
		}
	}

	private void insertCurve1() {
		switch (currentDirection) {
		case LEFT:
			insertLeft(CURVE_1, 1);
			currentDirection = Direction.DOWN;
			break;
		case RIGHT:
			insertRight(CURVE_1, 1);
			currentDirection = Direction.UP;
			break;
		case UP:
			insertUp(CURVE_1, 1);
			currentDirection = Direction.RIGHT;
			break;
		case DOWN:
			insertDown(CURVE_1, 1);
			currentDirection = Direction.LEFT;
			break;
		default:
			break;
		}
	}

	private void insertCurve2() {
		switch (currentDirection) {
		case LEFT:
			insertLeft(CURVE_2, 1);
			currentDirection = Direction.UP;
			break;
		case RIGHT:
			insertRight(CURVE_2, 1);
			currentDirection = Direction.DOWN;
			break;
		case UP:
			insertUp(CURVE_2, 1);
			currentDirection = Direction.LEFT;
			break;
		case DOWN:
			insertDown(CURVE_2, 1);
			currentDirection = Direction.RIGHT;
			break;
		default:
			break;
		}
	}

	private void insertLeft(char character, int amount) {
		int startIndex = lastInsertionColumIndex - 1;
		int endIndex = lastInsertionColumIndex - amount;
		if (endIndex < 0) {
			int growAmount = Math.abs(endIndex);
			growLeft(growAmount);
			startIndex += growAmount;
			endIndex += growAmount;
		}
		for (int columnIndex = startIndex; columnIndex >= endIndex; columnIndex--) {
			data[lastInsertionRowIndex][columnIndex] = character;
		}
		lastInsertionColumIndex = endIndex;
	}

	private void insertRight(char character, int amount) {
		int startIndex = lastInsertionColumIndex + 1;
		int endIndex = lastInsertionColumIndex + amount;
		if (endIndex >= numColumns) {
			growRight(endIndex - numColumns + 1);
		}
		for (int columnIndex = startIndex; columnIndex <= endIndex; columnIndex++) {
			data[lastInsertionRowIndex][columnIndex] = character;
		}
		lastInsertionColumIndex = endIndex;
	}

	private void insertUp(char character, int amount) {
		int startIndex = lastInsertionRowIndex - 1;
		int endIndex = lastInsertionRowIndex - amount;
		if (endIndex < 0) {
			int growAmount = Math.abs(endIndex);
			growUp(growAmount);
			startIndex += growAmount;
			endIndex += growAmount;
		}
		for (int rowIndex = startIndex; rowIndex >= endIndex; rowIndex--) {
			data[rowIndex][lastInsertionColumIndex] = character;
		}
		lastInsertionRowIndex = endIndex;
	}

	private void insertDown(char character, int amount) {
		int startIndex = lastInsertionRowIndex + 1;
		int endIndex = lastInsertionRowIndex + amount;
		if (endIndex >= numRows) {
			growDown(endIndex - numRows + 1);
		}
		for (int rowIndex = startIndex; rowIndex <= endIndex; rowIndex++) {
			data[rowIndex][lastInsertionColumIndex] = character;
		}
		lastInsertionRowIndex = endIndex;
	}

	private void growLeft(int amount) {
		int newNumColumns = numColumns + amount;
		char[][] newData = new char[numRows][newNumColumns];

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < newNumColumns; j++) {
				if (j < amount) {
					newData[i][j] = defaultValue;
				} else {
					newData[i][j] = data[i][j - amount];
				}
			}
		}

		this.lastInsertionColumIndex += amount;
		this.data = newData;
		this.numColumns += amount;
	}

	private void growRight(int amount) {
		int newNumColumns = numColumns + amount;
		char[][] newData = new char[numRows][newNumColumns];

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < newNumColumns; j++) {
				if (j < numColumns) {
					newData[i][j] = data[i][j];
				} else {
					newData[i][j] = defaultValue;
				}
			}
		}

		this.data = newData;
		this.numColumns += amount;
	}

	private void growUp(int amount) {
		int newNumRows = numRows + amount;
		char[][] newData = new char[newNumRows][numColumns];

		for (int i = 0; i < newNumRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (i < amount) {
					newData[i][j] = defaultValue;
				} else {
					newData[i][j] = data[i - amount][j];
				}
			}
		}

		this.lastInsertionRowIndex += amount;
		this.data = newData;
		this.numRows += amount;
	}

	private void growDown(int amount) {
		int newNumRows = numRows + amount;
		char[][] newData = new char[newNumRows][numColumns];

		for (int i = 0; i < newNumRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (i < numRows) {
					newData[i][j] = data[i][j];
				} else {
					newData[i][j] = defaultValue;
				}
			}
		}

		this.data = newData;
		this.numRows += amount;
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				output += data[i][j];
			}
			output += EOL;
		}
		return output;
	}

}
