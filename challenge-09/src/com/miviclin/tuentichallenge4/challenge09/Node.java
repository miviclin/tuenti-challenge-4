package com.miviclin.tuentichallenge4.challenge09;

import java.util.ArrayList;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 9 - Bendito Caos
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class Node<T> {

	private T value;
	private ArrayList<Edge<T>> adjacencies;
	private Edge<T> parent;

	public Node(T value) {
		this.value = value;
		this.adjacencies = new ArrayList<>();
		this.parent = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Node<?> other = (Node<?>) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public Path<T> getPath() {
		Path<T> path = new Path<>();

		for (Edge<T> edge = parent; edge != null; edge = edge.getSource().getParent()) {
			path.addEdge(edge);
		}

		path.reverse();

		return path;

	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ArrayList<Edge<T>> getAdjacencies() {
		return adjacencies;
	}

	public Edge<T> getParent() {
		return parent;
	}

	public void setParent(Edge<T> parent) {
		this.parent = parent;
	}
}
