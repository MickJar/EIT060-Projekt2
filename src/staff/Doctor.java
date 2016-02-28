package staff;

import java.util.ArrayList;


public class Doctor extends User {

	public final boolean readAccess = true;

	private final static String TITLE = "Doctor";
	private ArrayList<String> patients;

	public Doctor(String name, Division div, String id) {
		super(name, div, id);
		patients = new ArrayList<String>();

	}
	public String getTitle() {
		return TITLE;
	}

	public ArrayList<String> getPatients() {
		return patients;
	}

	public boolean hasPatient(String id) {
		return patients.contains(id);
		
	}

	public void newPatient(String id) {
		if(!patients.contains(id)){
		patients.add(id);
		}
		
	}



	public void removePatient(Patient pat) {
		if (patients.contains(pat)) {
			patients.remove(pat);
		}
	}


	public String listOptions(){
		String output = null;
		output = (User.LIST_PATIENT_RECORDS+"\n"+User.LIST_DIVISION_RECORDS+"\n"+User.READ_PATIENT_RECORD+"\n"+User.WRITE_PATIENT_RECORD+"\n"+User.CREATE_PATIENT_RECORD+"\n");
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
