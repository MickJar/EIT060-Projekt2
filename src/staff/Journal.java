package staff;

import java.util.ArrayList;

public class Journal {
	private ArrayList<JournalEntry> records;

	

	public Journal() {
		records = new ArrayList<JournalEntry>();

	}

	public void addEntry(String entry, String date, Doctor d, Nurse n) {
		records.add(new JournalEntry(entry, date, d, n));
	}

	public ArrayList<JournalEntry> getRecords() {
		return records;
	}
	

	public String toString(){
		String output = "";
		for(JournalEntry je : records){
			output+= je.toString() + "\n";
		}
		return output;
	}

}
