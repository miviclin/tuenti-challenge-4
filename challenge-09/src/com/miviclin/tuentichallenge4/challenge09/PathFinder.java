package com.miviclin.tuentichallenge4.challenge09;

import java.util.ArrayList;

public class PathFinder<T> {

	public ArrayList<Path<T>> findAllPaths(Node<T> sourceNode, Node<T> goalNode) {

		ArrayList<Path<T>> paths = new ArrayList<>();
		findAllPaths(paths, sourceNode, goalNode);
		return paths;
	}

	private void findAllPaths(ArrayList<Path<T>> paths, Node<T> node, Node<T> goalNode) {

		if (node.equals(goalNode)) {
			Path<T> path = goalNode.getPath();
			paths.add(path);
			return;
		}

		Path<T> path = node.getPath();

		for (Edge<T> edge : node.getAdjacencies()) {
			Node<T> destinationNode = edge.getDestination();

			if (path.containsNode(destinationNode)) {
				continue;
			}

			destinationNode.setParent(edge);
			findAllPaths(paths, destinationNode, goalNode);
		}
	}

}
