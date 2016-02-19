package staff;

import java.util.ArrayList;

public class Doctor extends StaffMember {


	private final String TITLE = "Doctor";
	private ArrayList<Patient> patients;
	private static int doctorIdCounter = 1;


	public Doctor(String name, Division div) {
		super(name, div);
		patients = new ArrayList<Patient>();		
		
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
