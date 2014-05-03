package com.miviclin.tuentichallenge4.challenge08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		try {
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));

			TableRestucturationSolver solver = new TableRestucturationSolver();

			int numTables = Integer.parseInt(stdInReader.readLine().trim());
			for (int i = 1; i <= numTables; i++) {
				String[][] initialStateData = readTable(stdInReader);
				String[][] goalStateData = readTable(stdInReader);

				int minimumNumberOfMoves = solver.findMinimumNumberOfMoves(initialStateData, goalStateData);

				System.out.println(minimumNumberOfMoves);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String[][] readTable(BufferedReader stdInReader) throws IOException {
		stdInReader.readLine();
		String[][] table = new String[State.NUM_ROWS][State.NUM_COLUMNS];
		for (int i = 0; i < State.NUM_ROWS; i++) {
			String[] row = stdInReader.readLine().trim().split(",");
			for (int j = 0; j < row.length; j++) {
				table[i][j] = row[j].trim();
			}
		}
		return table;
	}

}
