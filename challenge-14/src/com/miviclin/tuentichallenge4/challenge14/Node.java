package com.miviclin.tuentichallenge4.challenge14;

import java.util.ArrayList;

public class Node<T> {

	private T value;
	private ArrayList<Node<T>> adjacencies;

	public Node(T value) {
		this.value = value;
		this.adjacencies = new ArrayList<>();
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

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ArrayList<Node<T>> getAdjacencies() {
		return adjacencies;
	}
}
