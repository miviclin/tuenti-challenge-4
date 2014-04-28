package com.miviclin.tuentichallenge4.challenge01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		String studentsFilePath;
		if (args.length > 0) {
			studentsFilePath = args[0];
		} else {
			throw new IllegalArgumentException("The students file path should be passed as an argument.");
		}

		StudentsInformation studentsInfo = new StudentsInformation();
		studentsInfo.loadStudentsInformationFromFile(studentsFilePath);

		try {
			BufferedReader stdInReader = new BufferedReader(new InputStreamReader(System.in));
			int numTestCases = Integer.parseInt(stdInReader.readLine().trim());
			for (int i = 1; i <= numTestCases; i++) {
				String studentAnonymousInfo = stdInReader.readLine().trim();
				String output = "Case #" + i + ": " + studentsInfo.getStudentsFromAnonymousInfo(studentAnonymousInfo);
				System.out.println(output);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
