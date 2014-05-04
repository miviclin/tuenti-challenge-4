package com.miviclin.tuentichallenge4.challenge12;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		try {
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));

			ShortestPathCalculator shortestPathCalculator = new ShortestPathCalculator();

			int numTestCases = Integer.parseInt(stdInReader.readLine().trim());
			for (int i = 1; i <= numTestCases; i++) {
				String[] dimensions = stdInReader.readLine().trim().split("\\s+");
				int cityMapWidth = Integer.parseInt(dimensions[0].trim());
				int cityMapHeight = Integer.parseInt(dimensions[1].trim());

				CityMap cityMap = new CityMap(cityMapHeight, cityMapWidth);

				for (int rowIndex = 0; rowIndex < cityMapHeight; rowIndex++) {
					String row = stdInReader.readLine().trim();
					for (int columnIndex = 0; columnIndex < cityMapWidth; columnIndex++) {
						cityMap.set(rowIndex, columnIndex, new Tile(rowIndex, columnIndex, row.charAt(columnIndex)));
					}
				}

				int shortestPathLength = shortestPathCalculator.calculateShortestPathLength(cityMap);

				System.out.println("Case #" + i + ": " + ((shortestPathLength < 0) ? "ERROR" : shortestPathLength));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
