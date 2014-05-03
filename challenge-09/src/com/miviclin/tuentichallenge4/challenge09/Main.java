package com.miviclin.tuentichallenge4.challenge09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	private static final String AWESOMEVILLE_NAME = "AwesomeVille";
	private static final int CAR_LENGTH_METERS = 5;

	public static void main(String[] args) {
		try {
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));

			int numCities = Integer.parseInt(stdInReader.readLine().trim());
			for (int i = 0; i < numCities; i++) {
				String cityName = stdInReader.readLine().trim();

				String[] maxSpeeds = stdInReader.readLine().trim().split("\\s+");
				int maxSpeedNormalRoads = Integer.parseInt(maxSpeeds[0]);
				int maxSpeedDirtRoads = Integer.parseInt(maxSpeeds[1]);

				String[] intersectionsAndRoads = stdInReader.readLine().trim().split("\\s+");
				int numIntersections = Integer.parseInt(intersectionsAndRoads[0]); // Not needed
				int numRoads = Integer.parseInt(intersectionsAndRoads[1]);

				ArrayList<Road> roads = new ArrayList<>();
				for (int roadIndex = 0; roadIndex < numRoads; roadIndex++) {
					String[] tokens = stdInReader.readLine().trim().split("\\s+");

					String sourcePointName = tokens[0].trim();
					String destinationPointName = tokens[1].trim();
					RoadType roadType = tokens[2].trim().equals("normal") ? RoadType.NORMAL : RoadType.DIRT;
					int numLanes = Integer.parseInt(tokens[3]);

					roads.add(new Road(sourcePointName, destinationPointName, roadType, numLanes));
				}

				CarsCalculator carsCalculator = new CarsCalculator(cityName, AWESOMEVILLE_NAME,
						maxSpeedNormalRoads, maxSpeedDirtRoads, roads, CAR_LENGTH_METERS);

				int maxCarsFromSourceToDestination = carsCalculator.calculateMaxCarsFromSourceToDestination();

				System.out.println(cityName + " " + maxCarsFromSourceToDestination);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
