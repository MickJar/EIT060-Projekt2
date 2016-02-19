package staff;

import java.util.ArrayList;

public class Nurse extends StaffMember {
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

	public void AddRecord(Patient pat, String rec) {
		if (patients.contains(pat)) {
			pat.appendJournal(rec);
		}
	
	}

}
