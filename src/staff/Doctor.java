package staff;

import java.util.ArrayList;

public class Doctor extends User {

	public final boolean readAccess = true;

	private final static String TITLE = "Doctor";
	private ArrayList<String> patients;
	private static int doctorIdCounter = 1;

	public Doctor(String name, Division div) {
		super(name, div);
		patients = new ArrayList<String>();

	}

	public ArrayList<String> getPatients() {
		return patients;
	}

	public boolean hasPatient(String id) {
		return patients.contains(id);
		
	}

	public void newPatient(String id) {
		patients.add(id);
		
	}

	public void addPatient(Patient pat) {
		if (!patients.contains(pat)) {
			patients.add(pat.getId());
		}
	}

	public void removePatient(Patient pat) {
		if (patients.contains(pat)) {
			patients.remove(pat);
		}
	}

	public void appendJournal(int id, String text) {
		
		
	}
	
	public char[] handleInput(String readLine){
		char[] output = null;
		String[] readLines = readLine.split(" ");
		switch (readLines[0]) {
			default :
				output = "Press 1 to list patient records \nPress 2 to list division records \nPress 6 to enter new patient record".toCharArray();
				break;
			case "1":
				output = "Enter 3 followed by id to read a patient record (example: 3 001) \nEnter 4 followed by id to write a patient record \nPress B to go to main menu".toCharArray();
				break;
			case "2":
				output = "Enter 5 followed by id to read a division record (example: 5 001) \nPress B to go to main menu ".toCharArray();
				break;
			case "6":
				output = "Enter 7 followed by id to create a patient record (example: 7 001) \nPress B to go to main menu ".toCharArray();
				break;
			
				
		}
		return output;
	}
	public String toString(){
		String output = Doctor.TITLE + ":" + this.getName() + ":" + this.getDivision().toString() + ":";
		for(String e : patients){
			output += e + ";";
		}
		output += ":";
		return output;
	}

}
