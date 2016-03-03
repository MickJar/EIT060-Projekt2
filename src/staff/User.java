package staff;

import java.util.ArrayList;


public abstract class User  {
	public static final String LIST_PATIENT_RECORDS = "Enter 1 to list patient records";
	public static final String LIST_DIVISION_RECORDS = "Enter 2 to list division records";
	public static final String READ_PATIENT_RECORD = "Enter 3 followed by id to read a patient record (example: 3 1)";
	public static final String READ_PATIENT_RECORD_PATIENT = "Enter 3 to read your patient record";
	public static final String WRITE_PATIENT_RECORD = "Enter 4 followed by patient id to write a patient record (example 4 3)";
	public static final String CREATE_PATIENT_RECORD = "Enter 5 followed by patient id followed by nurse id to create a patient record (example 5 3 2)";
	public static final String DELETE_PATIENT_RECORD = "Enter 6 followed by id to delete a patient record (example 6 1)";

	private String id;
	private String name;
	private Division div;


	public User(String name, Division div, String id) {
		this.name = name;
		this.div = div;
		this.id=id;
		

	}
	
	public boolean isDoctorOrNurse(){
		return false;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return "";
	}
	public String getId(){
		return id;
	}


	public Division getDivision() {
		return div;
	}

	
	public ArrayList<String> getPatients(){
		return null;
	}
	


	public String listOptions() {
		return null;
		
	}

	public boolean hasPatient(String string) {
	
		return false;
	}


}
