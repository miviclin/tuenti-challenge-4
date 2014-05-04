package com.miviclin.tuentichallenge4.challenge12;

import java.util.ArrayList;
import java.util.List;

public class AStarNode<T> {

	private T value;
	private AStarNode<T> parent;
	private List<AStarNode<T>> children;
	private float h;
	private float g;

	public AStarNode(T value, AStarNode<T> parent) {
		this.value = value;
		this.parent = parent;
		this.children = new ArrayList<>(3);
		this.h = 0;
		this.g = 0;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public boolean hasEqualValue(AStarNode<T> node) {
		return value.equals(node.value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public AStarNode<T> getParent() {
		return parent;
	}

	public void setParent(AStarNode<T> parent) {
		this.parent = parent;
	}

	public List<AStarNode<T>> getChildren() {
		return children;
	}

	public void setChildren(List<AStarNode<T>> children) {
		this.children = children;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

}
