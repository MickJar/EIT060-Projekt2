package staff;

import java.util.ArrayList;

public class Patient extends User {
	private final String TITLE = "Patient";
	private String id;

	public Patient(String name, Division div) {
		super(name, div);
		id = super.createPatientId();

	}
	
	public char[] listOptions(){
		return (User.LIST_PATIENT_RECORDS+"\n").toCharArray();
	}

}
