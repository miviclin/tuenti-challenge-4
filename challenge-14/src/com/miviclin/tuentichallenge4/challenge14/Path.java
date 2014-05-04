package com.miviclin.tuentichallenge4.challenge14;

import java.util.ArrayList;
import java.util.Collections;

public class Path<T> {

	private ArrayList<T> elements;

	public Path() {
		this.elements = new ArrayList<>();
	}

	public Path(Path<T> path) {
		this.elements = new ArrayList<>();

		for (T element : path.elements) {
			this.elements.add(element);
		}
	}

	@Override
	public String toString() {
		return elements.toString();
	}

	public void add(T element) {
		elements.add(element);
	}

	public boolean containsNode(Node<T> node) {
		return elements.contains(node);
	}

	public void reverse() {
		Collections.reverse(elements);
	}

	public ArrayList<T> getElements() {
		return elements;
	}

	public void removeLastElement() {
		if (elements.size() > 0) {
			elements.remove(elements.size() - 1);
		}
	}

}
