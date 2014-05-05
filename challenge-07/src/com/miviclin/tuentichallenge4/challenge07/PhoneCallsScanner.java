package com.miviclin.tuentichallenge4.challenge07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Tuenti Challenge 2014<br>
 * Challenge 7 - Yes we scan
 * 
 * @author Miguel Vicente Linares
 * 
 */
public class PhoneCallsScanner {

	private static final String MSG_NOT_CONNECTED = "Not connected";
	private static final String MSG_CONNECTED = "Connected at ";

	private ArrayList<PhoneCall> phoneCallsLog;

	public PhoneCallsScanner() {
		this.phoneCallsLog = null;
	}

	public void readPhoneCallsLog(String logFilePath) {
		phoneCallsLog = new ArrayList<>();

		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(logFilePath));
			String phoneCallInfo = bufferedReader.readLine();
			while ((phoneCallInfo != null) && (phoneCallInfo != "")) {
				String[] tokens = phoneCallInfo.trim().split("\\s+");

				int person1 = Integer.parseInt(tokens[0]);
				int person2 = Integer.parseInt(tokens[1]);

				PhoneCall phoneCall = new PhoneCall(person1, person2);
				phoneCallsLog.add(phoneCall);

				phoneCallInfo = bufferedReader.readLine();
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

	public String findConnection(int person1, int person2) {
		int index = findConnectionIndex(person1, person2);
		if (index >= 0) {
			return MSG_CONNECTED + index;
		} else {
			return MSG_NOT_CONNECTED;
		}
	}

	public int findConnectionIndex(int person1, int person2) {
		if (phoneCallsLog == null) {
			throw new IllegalStateException("The log of phone calls must be read before calling this method.");
		}
		int firstPhoneCallIndex = findFirstCallIndexInLog(person1, person2);
		if (firstPhoneCallIndex < 0) {
			return -1;
		}

		if (isPhoneCallBetweenPersonsAtIndex(firstPhoneCallIndex, person1, person2)) {
			return firstPhoneCallIndex;
		}

		int secondPerson;
		if (isPhoneCallOfPersonAtIndex(firstPhoneCallIndex, person1)) {
			secondPerson = person2;
		} else {
			secondPerson = person1;
		}

		List<Integer> indices = new ArrayList<>();
		indices.add(firstPhoneCallIndex);
		indices.addAll(findIndicesOfPhoneCallOfPerson(firstPhoneCallIndex, secondPerson));

		if (indices.size() == 1) {
			return -1;
		}

		int[] indicesArray = new int[indices.size()];
		int count = 0;
		for (Integer index : indices) {
			indicesArray[count++] = index;
		}

		for (int i = 1; i < indicesArray.length; i++) {
			if (isPhoneCallBetweenPersonsAtIndex(i, person1, person2)) {
				return i;
			}
			Set<Integer> commonContacts = getCommonContactsBetweenIndices(person1, person2, firstPhoneCallIndex, i);
			if (commonContacts.size() > 2) {
				return i;
			}
		}
		return -1;
	}

	private int findFirstCallIndexInLog(int person1, int person2) {
		for (int i = 0; i < phoneCallsLog.size(); i++) {
			PhoneCall phoneCall = phoneCallsLog.get(i);

			if ((phoneCall.getPerson1() == person1) || (phoneCall.getPerson2() == person1) ||
					(phoneCall.getPerson1() == person2) || (phoneCall.getPerson2() == person2)) {

				return i;
			}
		}
		return -1;
	}

	private Set<Integer> getCommonContactsBetweenIndices(int person1, int person2, int startIndex, int endIndex) {
		Map<Integer, Set<Integer>> contactsByPerson = getContactsByPersonBetweenIndices(startIndex, endIndex);
		Set<Integer> contactsPerson1 = contactsByPerson.get(person1);
		Set<Integer> contactsPerson2 = contactsByPerson.get(person2);

		if ((contactsPerson1 == null) || (contactsPerson2 == null)) {
			return new HashSet<>();
		} else if (contactsPerson1 == contactsPerson2) {
			return contactsPerson1;
		}
		return new HashSet<>();
	}

	private Map<Integer, Set<Integer>> getContactsByPersonBetweenIndices(int startIndex, int endIndex) {
		Map<Integer, Set<Integer>> contactsByPerson = new HashMap<>();

		for (int i = startIndex; i <= endIndex; i++) {
			PhoneCall phoneCall = phoneCallsLog.get(i);
			int person1 = phoneCall.getPerson1();
			int person2 = phoneCall.getPerson2();

			Set<Integer> contactsOfPerson1And2 = null;

			Set<Integer> contactsOfPerson1 = contactsByPerson.get(person1);
			Set<Integer> contactsOfPerson2 = contactsByPerson.get(person2);

			if ((contactsOfPerson1 == null) && (contactsOfPerson2 == null)) {
				contactsOfPerson1And2 = new HashSet<>();
				contactsOfPerson1 = contactsOfPerson1And2;
				contactsOfPerson2 = contactsOfPerson1And2;
			} else if (contactsOfPerson1 == null) {
				contactsOfPerson1 = contactsOfPerson2;
				contactsOfPerson1And2 = contactsOfPerson2;
			} else if (contactsOfPerson2 == null) {
				contactsOfPerson2 = contactsOfPerson1;
				contactsOfPerson1And2 = contactsOfPerson1;
			} else {
				contactsOfPerson1And2 = contactsOfPerson1;
				contactsOfPerson1And2.addAll(contactsOfPerson2);
			}
			contactsByPerson.put(person1, contactsOfPerson1And2);
			contactsByPerson.put(person2, contactsOfPerson1And2);

			contactsOfPerson1And2.add(person1);
			contactsOfPerson1And2.add(person2);
		}
		return contactsByPerson;
	}

	private List<Integer> findIndicesOfPhoneCallOfPerson(int startIndex, int person) {
		List<Integer> indices = new ArrayList<>();
		for (int i = startIndex; i < phoneCallsLog.size(); i++) {
			if (isPhoneCallOfPersonAtIndex(i, person)) {
				indices.add(i);
			}
		}
		return indices;
	}

	private boolean isPhoneCallOfPersonAtIndex(int index, int person) {
		PhoneCall phoneCallAtIndex = phoneCallsLog.get(index);
		return (phoneCallAtIndex.getPerson1() == person) || (phoneCallAtIndex.getPerson2() == person);
	}

	private boolean isPhoneCallBetweenPersonsAtIndex(int index, int person1, int person2) {
		return isPhoneCallOfPersonAtIndex(index, person1) && isPhoneCallOfPersonAtIndex(index, person2);
	}
}
