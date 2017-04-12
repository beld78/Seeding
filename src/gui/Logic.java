package gui;

import java.util.LinkedList;
import java.util.List;

public class Logic {

	List<String[]> groupsList = new LinkedList<>();
	List<String> participants = new LinkedList<>();

	String textToConvertArray[];
	int personsInGroup;
	int amountOfGroups;

	public void syso() {

		for (int i = 0; i < groupsList.size(); i++) {
			for (int z = 0; z < groupsList.get(i).length; z++) {

				System.out.println(groupsList.get(i)[z]);
			}
		}
	}

	public String format() {

		createGroups(amountOfGroups, personsInGroup);
		createParticipants(textToConvertArray);
		writeParticipantsInGroup(amountOfGroups, personsInGroup);
		return writeInResultList();
	}

	private String writeInResultList() {
		String result = "";
		// first for loop goes through the group numbers
		for (int i = 0; i < groupsList.size(); i++) {
			// second for loop goes through a single group
			for (int z = 0; z < groupsList.get(i).length; z++) {
				String currentLine = groupsList.get(i)[z];
				int removeNumber = currentLine.indexOf(".");
				if (removeNumber != -1) {
					currentLine = currentLine.substring(removeNumber + 2);
				}
				result += currentLine;
				result += "\n";
			}
		}
		return result;
	}

	private void writeParticipantsInGroup(int amountOfGroups, int personsInGroup) {
		int participantPlace = 0;
		for (int i = 0; i < personsInGroup; i++) {
			int startAtEndOfGroup = amountOfGroups - 1;
			for (int z = 0; z < amountOfGroups; z++) {
				// go backwards
				if (!isEven(i)) {
					groupsList.get(startAtEndOfGroup)[i] = participants.get(participantPlace);
					startAtEndOfGroup--;
				} else {
					groupsList.get(z)[i] = participants.get(participantPlace);
				}
				participantPlace++;
			}
		}
	}

	private boolean isEven(int counter) {
		while (counter > 1) {
			counter -= 2;
		}
		return counter == 0;
	}

	private void createParticipants(String[] textToConvertArray) {
		for (String participant : textToConvertArray) {
			participants.add(participant);
		}

	}

	private void createGroups(int amountOfGroups, int personsInGroup) {
		for (int i = 0; i < amountOfGroups; i++) {
			String[] temp = new String[personsInGroup];
			groupsList.add(temp);
		}
	}

	public List<String[]> getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(List<String[]> groupsList) {
		this.groupsList = groupsList;
	}

	public List<String> getParticipants() {
		return participants;
	}

	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}

	public String[] getTextToConvertArray() {
		return textToConvertArray;
	}

	public void setTextToConvertArray(String[] textToConvertArray) {
		this.textToConvertArray = textToConvertArray;
	}

	public int getPersonsInGroup() {
		return personsInGroup;
	}

	public void setPersonsInGroup(int personsInGroup) {
		this.personsInGroup = personsInGroup;
	}

	public int getAmountOfGroups() {
		return amountOfGroups;
	}

	public void setAmountOfGroups(int amountOfGroups) {
		this.amountOfGroups = amountOfGroups;
	}
}
