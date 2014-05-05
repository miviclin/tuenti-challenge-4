package com.miviclin.tuentichallenge4.challenge09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 9 - Bendito Caos
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class CarsCalculator {

	private static final int METERS_PER_KILOMETER = 1000;

	private String sourceCityName;
	private String destinationCityName;
	private int maxSpeedNormalRoads;
	private int maxSpeedDirtRoads;
	private ArrayList<Road> roads;
	private int carLengthMeters;

	public CarsCalculator(String sourceCityName, String destinationCityName, int maxSpeedNormalRoads,
			int maxSpeedDirtRoads, ArrayList<Road> roads, int carLengthMeters) {

		this.sourceCityName = sourceCityName;
		this.destinationCityName = destinationCityName;
		this.maxSpeedNormalRoads = maxSpeedNormalRoads;
		this.maxSpeedDirtRoads = maxSpeedDirtRoads;
		this.roads = roads;
		this.carLengthMeters = carLengthMeters;
	}

	public int calculateMaxCarsFromSourceToDestination() {
		Node<String> sourceNode = new Node<>(sourceCityName);
		Node<String> destinationNode = new Node<>(destinationCityName);
		initializeGraph(sourceNode, destinationNode);

		PathFinder<String> pathFinder = new PathFinder<>();
		ArrayList<Path<String>> paths = pathFinder.findAllPaths(sourceNode, destinationNode);

		Map<Edge<String>, ArrayList<Path<String>>> pathsByEdgeReachingDestination = new HashMap<>();
		for (Path<String> path : paths) {
			Edge<String> edge = path.getEdges().get(path.getEdges().size() - 1);
			ArrayList<Path<String>> pathsWithSameFinalEdge = pathsByEdgeReachingDestination.get(edge);
			if (pathsWithSameFinalEdge == null) {
				pathsWithSameFinalEdge = new ArrayList<>();
				pathsByEdgeReachingDestination.put(edge, pathsWithSameFinalEdge);
			}
			pathsWithSameFinalEdge.add(path);
		}

		return calculateMaxCarsPerHour(pathsByEdgeReachingDestination);
	}

	private void initializeGraph(Node<String> sourceNode, Node<String> destinationNode) {
		List<Node<String>> nodes = new ArrayList<>();

		nodes.add(sourceNode);
		nodes.add(destinationNode);

		for (Road road : roads) {
			String sourcePointName = road.getSourcePointName();
			Node<String> sourcePointNode = findNode(nodes, sourcePointName);
			if (sourcePointNode == null) {
				sourcePointNode = new Node<>(sourcePointName);
				nodes.add(sourcePointNode);
			}

			String destinationPointName = road.getDestinationPointName();
			Node<String> destinationPointNode = findNode(nodes, destinationPointName);
			if (destinationPointNode == null) {
				destinationPointNode = new Node<>(destinationPointName);
				nodes.add(destinationPointNode);
			}

			sourcePointNode.getAdjacencies().add(new Edge<>(sourcePointNode, destinationPointNode));
		}
	}

	private Node<String> findNode(List<Node<String>> nodes, String name) {
		for (Node<String> node : nodes) {
			if (node.getValue().equals(name)) {
				return node;
			}
		}
		return null;
	}

	private int calculateMaxCarsPerHour(Map<Edge<String>, ArrayList<Path<String>>> pathsByEdgeReachingDestination) {
		int maxCarsPerHourGlobal = 0;
		for (Entry<Edge<String>, ArrayList<Path<String>>> entry : pathsByEdgeReachingDestination.entrySet()) {
			ArrayList<Path<String>> pathsWithSameFinalEdge = entry.getValue();
			int maxCarsPerHourPath = Integer.MAX_VALUE;
			for (Path<String> path : pathsWithSameFinalEdge) {
				maxCarsPerHourPath = Math.min(maxCarsPerHourPath, calculateMaxCarsPerHour(path));
			}
			maxCarsPerHourGlobal += maxCarsPerHourPath;
		}
		return maxCarsPerHourGlobal;
	}

	private int calculateMaxCarsPerHour(Path<String> path) {
		int maxCarsPerHour = Integer.MAX_VALUE;
		for (Edge<String> edge : path.getEdges()) {
			String sourcePointName = edge.getSource().getValue();
			String destinationPointName = edge.getDestination().getValue();
			int maxCarsPerHourRoad = calculateCarsHourRoad(sourcePointName, destinationPointName);
			maxCarsPerHour = Math.min(maxCarsPerHour, maxCarsPerHourRoad);
		}
		return maxCarsPerHour;
	}

	private int calculateCarsHourRoad(String sourcePointName, String destinationPointName) {
		for (Road road : roads) {
			if (road.getSourcePointName().equals(sourcePointName) &&
					road.getDestinationPointName().equals(destinationPointName)) {

				int maxSpeedKmh = (road.getType() == RoadType.NORMAL) ? maxSpeedNormalRoads : maxSpeedDirtRoads;
				int maxSpeedMh = maxSpeedKmh * METERS_PER_KILOMETER;

				return (maxSpeedMh / carLengthMeters) * road.getNumLanes();
			}
		}
		return 0;
	}

}
