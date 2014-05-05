package com.miviclin.tuentichallenge4.challenge04;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 4 - Shape shifters
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class Node<T> {

	private Node<T> parent;
	private T value;

	public Node(T value) {
		this(null, value);
	}

	public Node(Node<T> parent, T value) {
		this.parent = parent;
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
