package com.miviclin.tuentichallenge4.challenge09;

public class Road {

	private String sourcePointName;
	private String destinationPointName;
	private RoadType type;
	private int numLanes;

	public Road(String sourcePointName, String destinationPointName, RoadType type, int numLanes) {
		this.sourcePointName = sourcePointName;
		this.destinationPointName = destinationPointName;
		this.type = type;
		this.numLanes = numLanes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinationPointName == null) ? 0 : destinationPointName.hashCode());
		result = prime * result + numLanes;
		result = prime * result + ((sourcePointName == null) ? 0 : sourcePointName.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Road other = (Road) obj;
		if (destinationPointName == null) {
			if (other.destinationPointName != null) {
				return false;
			}
		} else if (!destinationPointName.equals(other.destinationPointName)) {
			return false;
		}
		if (numLanes != other.numLanes) {
			return false;
		}
		if (sourcePointName == null) {
			if (other.sourcePointName != null) {
				return false;
			}
		} else if (!sourcePointName.equals(other.sourcePointName)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

	public String getSourcePointName() {
		return sourcePointName;
	}

	public String getDestinationPointName() {
		return destinationPointName;
	}

	public RoadType getType() {
		return type;
	}

	public int getNumLanes() {
		return numLanes;
	}

}
