package staff;

import server.JournalDatabase;

public abstract class User implements Comparable {
	public static final String LIST_PATIENT_RECORDS = "Enter 1 to list patient records";
	public static final String LIST_DIVISION_RECORDS = "Enter 2 to list division records";
	public static final String READ_PATIENT_RECORD = "Enter 3 followed by id to read a patient record (example: 3 001)";
	public static final String WRITE_PATIENT_RECORD = "Enter 4 followed by id to write a patient record (example 4 001)";
	public static final String CREATE_PATIENT_RECORD = "Enter 5 followed by id to create a patient record (example 5 001)";
	public static final String DELETE_PATIENT_RECORD = "Enter 6 followed by id to delete a patient record (example 6 001)";

	private String id;
	private String title;
	private String name;
	private Division div;
	private static int idCounter = 1;
	private JournalDatabase jb;

	public User(String name, Division div) {
		this.name = name;
		this.div = div;
		

	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}
	public String getId(){
		return id;
	}
	public String createPatientId() {
		idCounter++;
		id = Integer.toString(idCounter);
		return id;
	}

	public Division getDivision() {
		return div;
	}
	
	@Override
	public int compareTo(Object o) {
		if (this.getClass() == o.getClass()) {
			return 1;
		}
		return 0;

	}
	
	public Journal getJournal(int id) {
		return jb.getJournal(id);
	}
	
	public void deleteJournal(int id) {
	}
	
	public void appendJournal(int id, String text) {
		
	}



	public char[] handleInput(String readLine) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public boolean hasPatient(String string) {
		// TODO Auto-generated method stub
		return false;
	}


}
