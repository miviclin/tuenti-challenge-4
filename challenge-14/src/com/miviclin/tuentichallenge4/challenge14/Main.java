package com.miviclin.tuentichallenge4.challenge14;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 14 - Train Empire
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class Main {

	public static void main(String[] args) {
		try {
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));

			int numScenarios = Integer.parseInt(stdInReader.readLine().trim());
			for (int scenarioIndex = 1; scenarioIndex <= numScenarios; scenarioIndex++) {
				String[] scenarioInfo = stdInReader.readLine().trim().split(",");
				int numStations = Integer.parseInt(scenarioInfo[0].trim());
				int numRoutes = Integer.parseInt(scenarioInfo[1].trim());
				int fuelPerTrain = Integer.parseInt(scenarioInfo[2].trim());

				Scenario scenario = new Scenario(fuelPerTrain);

				for (int stationIndex = 0; stationIndex < numStations; stationIndex++) {
					String[] stationInfo = stdInReader.readLine().trim().split("\\s+");

					String stationName = stationInfo[0];
					String[] stationCoords = stationInfo[1].split(",");
					int stationX = Integer.parseInt(stationCoords[0].trim());
					int stationY = Integer.parseInt(stationCoords[1].trim());
					Station station = new Station(stationName, stationX, stationY);
					scenario.addStation(station);

					String wagonDestinationStationName = stationInfo[2];
					int wagonValue = Integer.parseInt(stationInfo[3]);
					Wagon wagon = new Wagon(wagonValue, wagonDestinationStationName);
					scenario.addWagon(station, wagon);
				}

				for (int routeIndex = 0; routeIndex < numRoutes; routeIndex++) {
					String[] routeInfo = stdInReader.readLine().trim().split("\\s+");
					String startingStationName = routeInfo[0];
					Station startingStation = scenario.getStation(startingStationName);

					Route route = new Route(startingStation);

					for (int i = 1; i < routeInfo.length; i++) {
						String[] connection = routeInfo[i].split("-");
						String stationName1 = connection[0].trim();
						String stationName2 = connection[1].trim();

						Station station1 = scenario.getStation(stationName1);
						Station station2 = scenario.getStation(stationName2);

						route.addConnection(station1, station2);
					}
					scenario.addRoute(route);
				}

				int maxScore = scenario.calculateMaxScore();
				System.out.println(maxScore);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
