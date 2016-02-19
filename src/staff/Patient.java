package staff;

import java.util.ArrayList;

public class Patient extends StaffMember {
	private final String TITLE = "Patient";
	private Journal journal;

	public Patient(String name, Division div) {
		super(name, div);
		journal = new Journal();

	}
	
	public void appendJournal(String entry) {
		journal.addEntry(entry);
	}

	public void deleteJournal() {
		journal = new Journal();
	}
	
	public Journal getJournal() {
		return journal;
	}

}
