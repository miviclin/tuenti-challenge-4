package com.miviclin.tuentichallenge4.challenge12;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 12 - Taxi Driver
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class State {

	private Tile tile;
	private Direction direction;

	public State(Tile tile, Direction direction) {
		this.tile = tile;
		this.direction = direction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((tile == null) ? 0 : tile.hashCode());
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
		State other = (State) obj;
		if (direction != other.direction) {
			return false;
		}
		if (tile == null) {
			if (other.tile != null) {
				return false;
			}
		} else if (!tile.equals(other.tile)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "(" + tile.toString() + "," + direction + ")";
	}

	public Tile getTile() {
		return tile;
	}

	public Direction getDirection() {
		return direction;
	}

}
