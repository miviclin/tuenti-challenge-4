package com.miviclin.tuentichallenge4.challenge09;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 9 - Bendito Caos
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class Edge<T> {

	private Node<T> source;
	private Node<T> destination;

	public Edge(Node<T> source, Node<T> destination) {
		this.source = source;
		this.destination = destination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		Edge<?> other = (Edge<?>) obj;
		if (destination == null) {
			if (other.destination != null) {
				return false;
			}
		} else if (!destination.equals(other.destination)) {
			return false;
		}
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return source.toString() + " " + destination.toString();
	}

	public Node<T> getSource() {
		return source;
	}

	public Node<T> getDestination() {
		return destination;
	}

}
