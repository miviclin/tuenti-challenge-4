package com.miviclin.tuentichallenge4.challenge01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentsInformation {

	private static final String MSG_NO_MATCH_FOUND = "NONE";

	private HashMap<String, ArrayList<String>> studentsByAnonymousInfo;

	public StudentsInformation() {
		this.studentsByAnonymousInfo = new HashMap<>();
	}

	public void loadStudentsInformationFromFile(String studentsFilePath) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(studentsFilePath));
			String studentInfo = bufferedReader.readLine();
			while ((studentInfo != null) && (studentInfo != "")) {
				storeStudentInformation(studentInfo);
				studentInfo = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void storeStudentInformation(String studentInfo) {
		int indexOfFirstComma = studentInfo.indexOf(',');
		String studentName = studentInfo.substring(0, indexOfFirstComma);
		String anonymousInfo = studentInfo.substring(indexOfFirstComma + 1).trim();

		ArrayList<String> studentsWithSameAnonymousInfo = studentsByAnonymousInfo.get(anonymousInfo);
		if (studentsWithSameAnonymousInfo == null) {
			studentsWithSameAnonymousInfo = new ArrayList<>();
			studentsByAnonymousInfo.put(anonymousInfo, studentsWithSameAnonymousInfo);
		}
		insertSortedStudentName(studentsWithSameAnonymousInfo, studentName);
	}

	private static void insertSortedStudentName(ArrayList<String> students, String studentName) {
		students.add(studentName);
		int j = students.size() - 1;
		for (int i = j - 1; i >= 0; i--) {
			if (students.get(i).compareTo(students.get(j)) > 0) {
				String student = students.set(i, students.get(j));
				students.set(j, student);
				j--;
			} else {
				break;
			}
		}
	}

	public String getStudentsFromAnonymousInfo(String anonymousInfo) {
		ArrayList<String> studentsNames = studentsByAnonymousInfo.get(anonymousInfo);

		if (studentsNames == null) {
			return MSG_NO_MATCH_FOUND;
		} else if (studentsNames.isEmpty()) {
			return MSG_NO_MATCH_FOUND;
		}

		String studentsWithSameAnonymousInfo = "";
		for (String studentName : studentsNames) {
			studentsWithSameAnonymousInfo += studentName + ",";
		}

		return studentsWithSameAnonymousInfo.substring(0, studentsWithSameAnonymousInfo.length() - 1);
	}

}
