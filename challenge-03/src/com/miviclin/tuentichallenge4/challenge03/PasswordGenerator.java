package com.miviclin.tuentichallenge4.challenge03;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PasswordGenerator {

	private DecimalFormat decimalFormat;

	public PasswordGenerator() {
		this.decimalFormat = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
	}

	public String generatePassword(int x, int y) {
		float hypotenuse = (float) Math.sqrt((x * x) + (y * y));
		return decimalFormat.format(hypotenuse);
	}
}
