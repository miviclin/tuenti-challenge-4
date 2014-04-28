package com.miviclin.tuentichallenge4.challenge03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		try {
			PasswordGenerator passwordGenerator = new PasswordGenerator();
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));

			int numTestCases = Integer.parseInt(stdInReader.readLine().trim());
			for (int i = 1; i <= numTestCases; i++) {
				String line = stdInReader.readLine().trim();
				String[] tokens = line.split("\\s+");
				int x = Integer.parseInt(tokens[0]);
				int y = Integer.parseInt(tokens[1]);

				String password = passwordGenerator.generatePassword(x, y);

				System.out.println(password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
