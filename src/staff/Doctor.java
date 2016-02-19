package staff;

import java.util.ArrayList;

public class Doctor extends StaffMember {

	private final boolean readAccess = true;

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

	public Patient getPatient(String id) {
		for (int i = 0; i < patients.size(); i++) {
			if (id.compareTo(patients.get(i).getId()) == 0) {
				return patients.get(i);
			}
		}
		return null;
	}

	public void newPatient(int id, String name, Division div) {
		patients.add(new Patient(id, name, div));
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

	public void appendJournal(int id, String text) {
		
		
	}

}
