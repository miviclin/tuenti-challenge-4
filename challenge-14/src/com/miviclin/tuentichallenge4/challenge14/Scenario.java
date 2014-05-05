package com.miviclin.tuentichallenge4.challenge14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 14 - Train Empire
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class Scenario {

	private FuelLossCalculator fuelLossCalculator;
	private PathFinder<Station> pathFinder;
	private Set<Station> stations;
	private Map<Station, Wagon> wagonByStationAtStart;
	private ArrayList<Route> routes;
	private int fuelPerTrain;

	public Scenario(int fuelPerTrain) {
		this.fuelLossCalculator = new FuelLossCalculator();
		this.pathFinder = new PathFinder<>();
		this.stations = new HashSet<>();
		this.wagonByStationAtStart = new HashMap<>();
		this.routes = new ArrayList<>();
		this.fuelPerTrain = fuelPerTrain;
	}

	public int calculateMaxScore() {
		int maxScore = 0;
		for (Route route : routes) {
			maxScore += calculateMaxScore(route);
		}
		return maxScore;
	}

	private int calculateMaxScore(Route route) {
		Node<Station> sourceNode = createGraph(route);
		ArrayList<Path<Station>> paths = pathFinder.findAllPaths(sourceNode, fuelPerTrain, fuelLossCalculator);

		int maxScore = 0;
		for (Path<Station> path : paths) {
			int score = calculateMaxScore(path);
			maxScore = Math.max(maxScore, score);
		}
		return maxScore;
	}

	private static Node<Station> createGraph(Route route) {
		Node<Station> sourceNode = new Node<Station>(route.getStartingStation());

		List<Node<Station>> nodes = new ArrayList<>();
		nodes.add(sourceNode);

		for (Station station : route.getStations()) {
			Node<Station> sourcePointNode = findNode(nodes, station);
			if (sourcePointNode == null) {
				sourcePointNode = new Node<>(station);
				nodes.add(sourcePointNode);
			}

			Set<Station> adjacentStations = route.getConnections(station);
			for (Station adjacentStation : adjacentStations) {
				Node<Station> destinationPointNode = findNode(nodes, adjacentStation);
				if (destinationPointNode == null) {
					destinationPointNode = new Node<>(adjacentStation);
					nodes.add(destinationPointNode);
				}
				sourcePointNode.getAdjacencies().add(destinationPointNode);
			}
		}

		return sourceNode;
	}

	private static Node<Station> findNode(List<Node<Station>> nodes, Station station) {
		for (Node<Station> node : nodes) {
			if (node.getValue().equals(station)) {
				return node;
			}
		}
		return null;
	}

	private int calculateMaxScore(Path<Station> path) {
		ArrayList<Map<Station, ArrayList<Wagon>>> allWagonsByStation = new ArrayList<>();
		Map<Station, ArrayList<Wagon>> wagonsByStationAtStart = createWagonsByStationMap(wagonByStationAtStart);
		for (Station station : stations) {
			if (!path.getElements().contains(station)) {
				wagonsByStationAtStart.remove(station);
			}
		}
		findBestMoves(allWagonsByStation, wagonsByStationAtStart, path, 0, null);

		int maxScore = 0;
		for (Map<Station, ArrayList<Wagon>> wagonsByStation : allWagonsByStation) {
			int score = 0;
			for (Entry<Station, ArrayList<Wagon>> entry : wagonsByStation.entrySet()) {
				Station station = entry.getKey();
				ArrayList<Wagon> wagons = entry.getValue();
				for (Wagon wagon : wagons) {
					if (wagon.getDestinationStationName().equals(station.getName())) {
						score += wagon.getValue();
					}
				}
			}
			maxScore = Math.max(maxScore, score);
		}
		return maxScore;
	}

	private void findBestMoves(ArrayList<Map<Station, ArrayList<Wagon>>> allWagonsByStation,
			Map<Station, ArrayList<Wagon>> wagonsByStation, Path<Station> path, int pathIndex, Wagon incomingWagon) {

		Station previousStation = null;
		int indexInPreviousStation = -1;
		if ((pathIndex > 0) && (incomingWagon != null)) {
			previousStation = path.getElements().get(pathIndex - 1);
			ArrayList<Wagon> wagonsInPreviousStation = wagonsByStation.get(previousStation);
			indexInPreviousStation = wagonsInPreviousStation.indexOf(incomingWagon);
			wagonsInPreviousStation.remove(indexInPreviousStation);
		}

		Station currentStation = path.getElements().get(pathIndex);
		ArrayList<Wagon> wagonsAtCurrentStation = wagonsByStation.get(currentStation);
		if (incomingWagon != null) {
			wagonsAtCurrentStation.add(incomingWagon);
		}

		for (int i = 0; i <= wagonsAtCurrentStation.size(); i++) {
			int nextPathIndex = pathIndex + 1;
			if (nextPathIndex >= path.getElements().size()) {
				Map<Station, ArrayList<Wagon>> wagonsByStationAtEndOfPath = copyWagonsByStationMap(wagonsByStation);
				allWagonsByStation.add(wagonsByStationAtEndOfPath);

				restoreState(wagonsByStation, previousStation, currentStation, incomingWagon, indexInPreviousStation);

				return;
			}
			Wagon wagon = null;
			if (i < wagonsAtCurrentStation.size()) {
				wagon = wagonsAtCurrentStation.get(i);
				if (wagon.getDestinationStationName().equals(currentStation.getName())) {
					continue;
				}
			}
			findBestMoves(allWagonsByStation, wagonsByStation, path, nextPathIndex, wagon);
		}

		restoreState(wagonsByStation, previousStation, currentStation, incomingWagon, indexInPreviousStation);
	}

	private void restoreState(Map<Station, ArrayList<Wagon>> wagonsByStation, Station previousStation,
			Station currentStation, Wagon incomingWagon, int wagonIndexInPreviousStation) {

		if ((previousStation != null) && (incomingWagon != null)) {
			ArrayList<Wagon> wagonsInPreviousStation = wagonsByStation.get(previousStation);
			wagonsInPreviousStation.add(wagonIndexInPreviousStation, incomingWagon);
		}
		if (incomingWagon != null) {
			wagonsByStation.get(currentStation).remove(incomingWagon);
		}
	}

	private static Map<Station, ArrayList<Wagon>> createWagonsByStationMap(Map<Station, Wagon> wagonByStationAtStart) {
		Map<Station, ArrayList<Wagon>> wagonsByStation = new HashMap<>();
		for (Entry<Station, Wagon> entry : wagonByStationAtStart.entrySet()) {
			ArrayList<Wagon> wagons = new ArrayList<>();
			wagons.add(entry.getValue());
			wagonsByStation.put(entry.getKey(), wagons);
		}
		return wagonsByStation;
	}

	private static Map<Station, ArrayList<Wagon>> copyWagonsByStationMap(Map<Station, ArrayList<Wagon>> input) {
		Map<Station, ArrayList<Wagon>> wagonsByStation = new HashMap<>();
		for (Entry<Station, ArrayList<Wagon>> entry : input.entrySet()) {
			ArrayList<Wagon> wagons = new ArrayList<>(entry.getValue());
			wagonsByStation.put(entry.getKey(), wagons);
		}
		return wagonsByStation;
	}

	public void addStation(Station station) {
		stations.add(station);
	}

	public Station getStation(String name) {
		for (Station station : stations) {
			if (station.getName().equals(name)) {
				return station;
			}
		}
		return null;
	}

	public void addWagon(Station station, Wagon wagon) {
		wagonByStationAtStart.put(station, wagon);
	}

	public void addRoute(Route route) {
		this.routes.add(route);
	}

	private static class FuelLossCalculator implements EnergyLossCalculator<Station> {

		@Override
		public float calculateEnergyLoss(Node<Station> source, Node<Station> destination) {
			Station sourceStation = source.getValue();
			Station destinationStation = destination.getValue();
			int rowOffset = Math.abs(destinationStation.getRowIndex() - sourceStation.getRowIndex());
			int columnOffset = Math.abs(destinationStation.getColumnIndex() - sourceStation.getColumnIndex());
			if (rowOffset == 0) {
				return columnOffset;
			}
			if (columnOffset == 0) {
				return rowOffset;
			}
			return (float) Math.sqrt((rowOffset * rowOffset) + (columnOffset * columnOffset));
		}

	}

}
