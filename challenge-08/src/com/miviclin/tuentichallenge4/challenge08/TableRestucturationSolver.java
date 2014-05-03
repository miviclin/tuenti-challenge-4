package com.miviclin.tuentichallenge4.challenge08;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class TableRestucturationSolver {

	public int findMinimumNumberOfMoves(String[][] initialStateData, String[][] goalStateData) {
		Map<String, Integer> idsByName = getIdsByNameMap(goalStateData);
		State initialState = createState(idsByName, initialStateData);
		State goalState = createState(idsByName, goalStateData);
		return findMinimumNumberOfMoves(initialState, goalState);
	}

	public int findMinimumNumberOfMoves(State initialState, State goalState) {

		LinkedList<AStarNode<State>> minimumMoves = findMinimumMoves(initialState, goalState);
		return minimumMoves.size() - 1;
	}

	public LinkedList<AStarNode<State>> findMinimumMoves(State initialState, State goalState) {
		PriorityQueue<AStarNode<State>> openList = new PriorityQueue<>(1000, new NodeComparator());
		ArrayList<AStarNode<State>> closedList = new ArrayList<>();

		AStarNode<State> startNode = new AStarNode<>(initialState, null);
		startNode.setF(initialState.calculateManhattanDistance(goalState));
		startNode.setG(0);

		openList.add(startNode);

		while (!openList.isEmpty()) {

			AStarNode<State> currentNode = openList.poll();
			closedList.add(currentNode);

			State currentState = currentNode.getValue();
			if (currentState.equals(goalState)) {
				return reconstructPath(currentNode);
			}

			generateChildren(currentNode);
			for (int i = 0; i < currentNode.getChildren().size(); i++) {
				AStarNode<State> childNode = currentNode.getChildren().get(i);

				float g = currentNode.getG() + 1;
				childNode.setG(g);
				childNode.setF(g + childNode.getValue().calculateManhattanDistance(goalState));

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

	private static void generateChildren(AStarNode<State> node) {
		for (int i = 0; i < State.NUM_ROWS; i++) {
			for (int j = 0; j < State.NUM_COLUMNS; j++) {
				if (!((i == 1) ^ (j == 1))) {
					continue;
				}
				generateChild(node, i, j, i - 1, j);
				generateChild(node, i, j, i + 1, j);
				generateChild(node, i, j, i, j - 1);
				generateChild(node, i, j, i, j + 1);
			}
		}
	}

	private static void generateChild(AStarNode<State> node,
			int rowIndex1, int columnIndex1, int rowIndex2, int columnIndex2) {

		State state = node.getValue();
		if (state.isValidCell(rowIndex2, columnIndex2)) {
			if (state.get(rowIndex1, columnIndex1) == 0 && state.get(columnIndex1, rowIndex2) == 0) {
				return;
			}
			State childState = new State(state);
			childState.swap(rowIndex1, columnIndex1, rowIndex2, columnIndex2);
			if (!childState.equals(state)) {
				AStarNode<State> child = new AStarNode<State>(childState, node);
				node.getChildren().add(child);
			}
		}
	}

	private static Map<String, Integer> getIdsByNameMap(String[][] initialStateData) {
		Map<String, Integer> idsByName = new HashMap<>();
		int id = 1;
		for (int i = 0; i < initialStateData.length; i++) {
			for (int j = 0; j < initialStateData[i].length; j++) {
				String value = initialStateData[i][j];
				if (value.equals("")) {
					idsByName.put(value, 0);
				} else {
					idsByName.put(value, id++);
				}
			}
		}
		return idsByName;
	}

	private static State createState(Map<String, Integer> idsByName, String[][] stateData) {
		State state = new State();
		for (int i = 0; i < stateData.length; i++) {
			for (int j = 0; j < stateData[i].length; j++) {
				int id = idsByName.get(stateData[i][j]);
				state.set(i, j, id);
			}
		}
		return state;
	}

	private static class NodeComparator implements Comparator<AStarNode<State>> {

		@Override
		public int compare(AStarNode<State> o1, AStarNode<State> o2) {
			if (o1.getF() < o2.getF()) {
				return -1;
			}
			if (o1.getF() > o2.getF()) {
				return 1;
			}
			if (o1.getF() == o2.getF()) {
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
