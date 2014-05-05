package com.miviclin.tuentichallenge4.challenge14;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 14 - Train Empire
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class Wagon {

	private int value;
	private String destinationStationName;

	public Wagon(int value, String destinationStationName) {
		this.value = value;
		this.destinationStationName = destinationStationName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinationStationName == null) ? 0 : destinationStationName.hashCode());
		result = prime * result + value;
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
		Wagon other = (Wagon) obj;
		if (destinationStationName == null) {
			if (other.destinationStationName != null) {
				return false;
			}
		} else if (!destinationStationName.equals(other.destinationStationName)) {
			return false;
		}
		if (value != other.value) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return destinationStationName + ":" + value;
	}

	public int getValue() {
		return value;
	}

	public String getDestinationStationName() {
		return destinationStationName;
	}

}
