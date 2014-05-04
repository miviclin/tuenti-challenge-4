package com.miviclin.tuentichallenge4.challenge14;

public class Station {

	private String name;
	private int rowIndex;
	private int columnIndex;

	public Station(String name, int rowIndex, int columnIndex) {
		this.name = name;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}

	public Station(Station station) {
		this.name = station.name;
		this.rowIndex = station.rowIndex;
		this.columnIndex = station.columnIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columnIndex;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + rowIndex;
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
		Station other = (Station) obj;
		if (columnIndex != other.columnIndex) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (rowIndex != other.rowIndex) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

}
