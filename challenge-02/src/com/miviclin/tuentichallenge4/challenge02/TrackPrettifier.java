package com.miviclin.tuentichallenge4.challenge02;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 2 - F1 - Bird's-eye Circuit
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class TrackPrettifier {

	private static final char INPUT_START_FINISH_LINE = '#';
	private static final char INPUT_STRAIGHT_LINE = '-';
	private static final char INPUT_CURVE_1 = '/';
	private static final char INPUT_CURVE_2 = '\\';

	private String inputTrack;

	public TrackPrettifier(String inputTrack) {
		this.inputTrack = inputTrack;
	}

	public String prettify() {
		PrettyTrackBuilder prettyTrackBuilder = new PrettyTrackBuilder();

		String sortedInputTrack = getSortedInputTrack();

		int inputTrackIndex = 1;
		while (inputTrackIndex < sortedInputTrack.length()) {
			char currentChar = sortedInputTrack.charAt(inputTrackIndex);
			if (currentChar == INPUT_STRAIGHT_LINE) {
				int consecutiveStraightLines = countConsecutiveStraightLines(sortedInputTrack, inputTrackIndex);
				prettyTrackBuilder.insertStraightSegment(consecutiveStraightLines);
				inputTrackIndex += consecutiveStraightLines;
			} else if ((currentChar == INPUT_CURVE_1) || (currentChar == INPUT_CURVE_2)) {
				prettyTrackBuilder.insertCurve(currentChar);
				inputTrackIndex++;
			}
		}

		return prettyTrackBuilder.toString();
	}

	private String getSortedInputTrack() {
		int startFinishIndex = inputTrack.indexOf(INPUT_START_FINISH_LINE);
		if (startFinishIndex == 0) {
			return inputTrack;
		}
		String firstPart = inputTrack.substring(startFinishIndex);
		String secondPart = inputTrack.substring(0, startFinishIndex);
		return firstPart + secondPart;
	}

	private static int countConsecutiveStraightLines(String inputTrack, int startingIndex) {
		int count = 0;
		for (int i = startingIndex; i < inputTrack.length(); i++) {
			char currentChar = inputTrack.charAt(i);
			if (currentChar == INPUT_STRAIGHT_LINE) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

}
