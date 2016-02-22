package staff;

import java.util.ArrayList;

public class Doctor extends User {

	private final boolean readAccess = true;

	private final String TITLE = "Doctor";
	private ArrayList<String> patients;
	private static int doctorIdCounter = 1;

	public Doctor(String name, Division div) {
		super(name, div);
		patients = new ArrayList<String>();

	}

	public ArrayList<String> getPatients() {
		return patients;
	}

	public Boolean getPatient(String id) {
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
	public char[] options(){
		char[] output = "Press 1 to list patient records \n Press 2 to list division records \n Press 3 to enter new patient record".toCharArray();
		return output;
	}

}
