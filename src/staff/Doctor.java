package staff;

import java.util.ArrayList;

import server.Server;

public class Doctor extends User {

	public final boolean readAccess = true;

	private final static String TITLE = "Doctor";
	private ArrayList<String> patients;
//	private static int doctorIdCounter = 1;

	public Doctor(String name, Division div, String id) {
		super(name, div, id);
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
	
	public char[] listOptions(){
		char[] output = null;
		output = (User.LIST_PATIENT_RECORDS+"\n"+User.LIST_DIVISION_RECORDS+"\n"+User.READ_PATIENT_RECORD+"\n"+User.WRITE_PATIENT_RECORD+"\n"+User.CREATE_PATIENT_RECORD+"\n").toCharArray();
		return output;
	}
	public String toString(){
		String output = Doctor.TITLE + ":" + this.getId()+ ":" + this.getName() + ":" + this.getDivision().toString() + ":";
		for(String e : patients){
			output += e + ";";
		}
		output += ":";
		return output;
	}

}
