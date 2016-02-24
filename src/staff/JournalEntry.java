package staff;

import java.sql.Date;

public class JournalEntry {
	private String entry;
	private String Date;
	private Doctor doctor;
	private Nurse nurse;
	
	public JournalEntry(String entry, String Date) {
		this.entry = entry;
		this.Date = Date;
	}
	
	public JournalEntry getEntry() {
		return this;
	}
}
