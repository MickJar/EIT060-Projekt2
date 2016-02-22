package staff;

import java.util.ArrayList;

public class Nurse extends User {
	private final String TITLE = "Nurse";
	private ArrayList<Patient> patients;
		
	public Nurse(String name, Division div) {
		super(name, div);

	}
	
	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void addPatient(Patient pat) {
		if (!patients.contains(pat)) {
			patients.add(pat);
		}
	}

	public void removePatient(Patient pat) {
		if (patients.contains(pat)) {
			patients.remove(pat);
		}
	}
	public char[] options(){
		char[] output = "Press 1 to list patient records \n Press 2 to list division records".toCharArray();
		return output;
	}

}
