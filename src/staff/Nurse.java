package staff;

import java.util.ArrayList;

public class Nurse extends StaffMember {
	private final String TITLE = "Nurse";
	private ArrayList<Patient> patients;
		
	public Nurse(String name, Division div) {
		super(name, div);

	}

}
