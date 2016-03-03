package staff;

import java.util.ArrayList;

public class Nurse extends User {
	private final static String TITLE = "Nurse";
	private ArrayList<String> patients;
		
	public Nurse(String name, Division div, String id) {
		super(name, div, id);
		patients = new ArrayList<String>();

	}
	public String getTitle() {
		return TITLE;
	}
	
	public boolean isDoctorOrNurse(){
		return true;
	}
	
	public ArrayList<String> getPatients() {
		return patients;
	}

	public void addPatient(String pat) {
		if (!patients.contains(pat)) {
			patients.add(pat);
		}
	}
	
	public boolean hasPatient(String id) {
		return patients.contains(id);
		
	}
	
	public void newPatient(String id) {
		patients.add(id);
		
	}

	public void removePatient(Patient pat) {
		if (patients.contains(pat)) {
			patients.remove(pat);
		}
	}
	public String printRecords(){
		String records = "";
		for (String s: patients){
			records+=s+"\n";
		}
		return records;
	}
	
	
	public String listOptions(){
		return (User.LIST_PATIENT_RECORDS+"\n"+User.LIST_DIVISION_RECORDS+"\n"+User.READ_PATIENT_RECORD+"\n"+User.WRITE_PATIENT_RECORD+"\n");
	}
	public String toString(){
		String output = Nurse.TITLE +":" + this.getId()+ ":" + this.getName() + ":" + this.getDivision().toString() + ":";
		for(String e : patients){
			output += e + ";";
		}
		output += ":";
		return output;
	}
	

}
