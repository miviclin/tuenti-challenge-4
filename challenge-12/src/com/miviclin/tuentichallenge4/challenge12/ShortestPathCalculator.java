package com.miviclin.tuentichallenge4.challenge12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class ShortestPathCalculator {

	public int calculateShortestPathLength(CityMap cityMap) {
		int shortestPathLength = Integer.MAX_VALUE;
		Direction[] directions = Direction.values();
		for (Direction initialDirection : directions) {
			int pathLength = calculateShortestPathLength(cityMap, initialDirection);
			if (pathLength < 0) {
				pathLength = Integer.MAX_VALUE;
			}
			shortestPathLength = Math.min(shortestPathLength, pathLength);
		}
		if (shortestPathLength == Integer.MAX_VALUE) {
			return -1;
		}
		return shortestPathLength;
	}

	private int calculateShortestPathLength(CityMap cityMap, Direction initialDirection) {
		LinkedList<AStarNode<State>> path = calculateShortestPath(cityMap, initialDirection);
		if (path != null) {
			return path.size() - 1;
		}
		return -1;
	}

	private LinkedList<AStarNode<State>> calculateShortestPath(CityMap cityMap, Direction initialDirection) {
		Tile startTile = cityMap.findStartTile();
		Tile destinationTile = cityMap.findDestinationTile();

		PriorityQueue<AStarNode<State>> openList = new PriorityQueue<>(1000, new NodeComparator());
		ArrayList<AStarNode<State>> closedList = new ArrayList<>();

		State startState = new State(startTile, initialDirection);
		AStarNode<State> startNode = new AStarNode<>(startState, null);
		startNode.setH(startState.getTile().calculateManhattanDistance(destinationTile));
		startNode.setG(0);

		openList.add(startNode);

		while (!openList.isEmpty()) {

			AStarNode<State> currentNode = openList.poll();
			closedList.add(currentNode);

			State currentState = currentNode.getValue();
			if (currentState.getTile().equals(destinationTile)) {
				return reconstructPath(currentNode);
			}

			generateChildren(cityMap, currentNode);
			for (int i = 0; i < currentNode.getChildren().size(); i++) {
				AStarNode<State> childNode = currentNode.getChildren().get(i);

				float g = currentNode.getG() + 1;
				childNode.setG(g);
				childNode.setH(g + childNode.getValue().getTile().calculateManhattanDistance(destinationTile));

				boolean continueLoop = false;
				for (int j = closedList.size() - 1; j >= 0; j--) {
					AStarNode<State> closedNode = closedList.get(j);
					if (closedNode.hasEqualValue(childNode)) {
						if (closedNode.getG() <= childNode.getG()) {
							continueLoop = true;
							break;
						}
						closedList.remove(closedNode);
						break;
					}
				}
				if (continueLoop) {
					continue;
				}

				continueLoop = false;
				boolean equalNodeFoundInOpenList = false;
				for (AStarNode<State> openNode : openList) {
					if (openNode.hasEqualValue(childNode)) {
						if (openNode.getG() <= childNode.getG()) {
							continueLoop = true;
							break;
						}
						equalNodeFoundInOpenList = true;
						break;
					}
				}
				if (continueLoop) {
					continue;
				} else if (!equalNodeFoundInOpenList) {
					openList.add(childNode);
				}
			}
		}
		return null;
	}

	private LinkedList<AStarNode<State>> reconstructPath(AStarNode<State> goalNode) {
		LinkedList<AStarNode<State>> path = new LinkedList<>();
		path.addFirst(goalNode);

		AStarNode<State> auxNode = goalNode;

		AStarNode<State> parentNode;
		while ((parentNode = auxNode.getParent()) != null) {
			path.addFirst(parentNode);
			auxNode = parentNode;
		}
		return path;
	}

	private static void generateChildren(CityMap cityMap, AStarNode<State> currentNode) {
		switch (currentNode.getValue().getDirection()) {
		case RIGHT:
			generateChild(cityMap, currentNode, 0, 1, Direction.RIGHT);
			generateChild(cityMap, currentNode, 1, 0, Direction.DOWN);
			break;
		case LEFT:
			generateChild(cityMap, currentNode, 0, -1, Direction.LEFT);
			generateChild(cityMap, currentNode, -1, 0, Direction.UP);
			break;
		case UP:
			generateChild(cityMap, currentNode, -1, 0, Direction.UP);
			generateChild(cityMap, currentNode, 0, 1, Direction.RIGHT);
			break;
		case DOWN:
			generateChild(cityMap, currentNode, 1, 0, Direction.DOWN);
			generateChild(cityMap, currentNode, 0, -1, Direction.LEFT);
			break;
		}
	}

	private static void generateChild(CityMap cityMap, AStarNode<State> currentNode, int rowOffset, int columnOffset,
			Direction direction) {

		State currentState = currentNode.getValue();
		int currentRowIndex = currentState.getTile().getRowIndex();
		int currentColumnIndex = currentState.getTile().getColumnIndex();

		if (cityMap.isValidMove(currentRowIndex + rowOffset, currentColumnIndex + columnOffset)) {
			Tile tileAtRight = cityMap.get(currentState.getTile(), rowOffset, columnOffset);
			State childState = new State(tileAtRight, direction);
			AStarNode<State> childNode = new AStarNode<State>(childState, currentNode);
			currentNode.getChildren().add(childNode);
		}
	}

	private static class NodeComparator implements Comparator<AStarNode<State>> {

		@Override
		public int compare(AStarNode<State> o1, AStarNode<State> o2) {
			if (o1.getH() < o2.getH()) {
				return -1;
			}
			if (o1.getH() > o2.getH()) {
				return 1;
			}
			if (o1.getH() == o2.getH()) {
				if (o1.getG() > o2.getG()) {
					return -1;
				}
				if (o1.getG() < o2.getG()) {
					return 1;
				}
			}
			return 0;
		}

	}

}
