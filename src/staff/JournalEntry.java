package staff;

import java.sql.Date;

public class JournalEntry {
	private String entry;
	private String Date;
	private Doctor doctor;
	private Nurse nurse;
	
	public JournalEntry(String entry, String Date, Doctor d, Nurse n) {
		this.doctor=d;
		this.nurse=n;
		this.entry = entry;
		this.Date = Date;
	}
	
	public JournalEntry getEntry() {
		return this;
	}
	public String toString(){
		return doctor.getId() + ":" + nurse.getId() + ":" + Date + ":" + entry;
	}
}
