package staff;

import java.util.ArrayList;

public class Journal {
	private ArrayList<JournalEntry> records;
	private Patient patient;
	
	public Journal(){
		records = new ArrayList<JournalEntry>();
	}
	
	public ArrayList<JournalEntry> getRecords() {
		return records;
	}
	

}
