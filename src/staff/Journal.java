package staff;

import java.util.ArrayList;

public class Journal {
	private ArrayList<JournalEntry> records;
	

	public Journal() {
		records = new ArrayList<JournalEntry>();
	}

	public void addEntry(String entry) {
		records.add(new JournalEntry(entry));
	}

	public ArrayList<JournalEntry> getRecords() {
		return records;
	}

}
