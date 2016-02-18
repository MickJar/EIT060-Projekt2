package staff;

import java.util.ArrayList;

public class Patient {
	private Journal journal;
	private int id;
	private String name;
	private static int patientIdCounter = 1;

	public Patient(String name) {
		journal = new Journal();
		this.name = name;
		this.id = patientIdCounter;
		patientIdCounter++;
	}

	public void deleteJournal() {
		journal = new Journal();
	}
	
	public int getId(){
		return id;
	}

}
