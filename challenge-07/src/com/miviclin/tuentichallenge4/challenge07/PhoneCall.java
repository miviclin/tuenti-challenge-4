package com.miviclin.tuentichallenge4.challenge07;

public class PhoneCall {

	private int person1;
	private int person2;

	public PhoneCall(int person1, int person2) {
		this.person1 = person1;
		this.person2 = person2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + person1;
		result = prime * result + person2;
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
		PhoneCall other = (PhoneCall) obj;
		if (person1 != other.person1) {
			return false;
		}
		if (person2 != other.person2) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return person1 + " " + person2;
	}

	public int getPerson1() {
		return person1;
	}

	public int getPerson2() {
		return person2;
	}

}
