package com.miviclin.tuentichallenge4.challenge09;

import java.util.ArrayList;
import java.util.Collections;

public class Path<T> {

	private ArrayList<Edge<T>> edges;

	public Path() {
		this.edges = new ArrayList<>();
	}

	@Override
	public String toString() {
		return edges.toString();
	}

	public void addEdge(Edge<T> edge) {
		edges.add(edge);
	}

	public boolean containsNode(Node<T> node) {
		for (int i = 0; i < edges.size(); i++) {
			Edge<T> edgeInPath = edges.get(i);
			if (edgeInPath.getSource().equals(node) || edgeInPath.getDestination().equals(node)) {
				return true;
			}
		}
		return false;
	}

	public void reverse() {
		Collections.reverse(edges);
	}

	public ArrayList<Edge<T>> getEdges() {
		return edges;
	}

}
