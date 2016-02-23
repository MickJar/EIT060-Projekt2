package staff;

import java.util.ArrayList;

public class Journal {
	private ArrayList<JournalEntry> records;
	private int id;
	

	public Journal(int id) {
		records = new ArrayList<JournalEntry>();
		this.id = id;
	}

	public void addEntry(String entry) {
		records.add(new JournalEntry(entry));
	}

	public ArrayList<JournalEntry> getRecords() {
		return records;
	}
	
	public int getId(){
		return id;
	}
	public String toString(){
		return records.toString();
	}

}
