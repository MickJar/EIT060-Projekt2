package server;

import java.util.HashMap;

import staff.Journal;

public class JournalDatabase {
	private HashMap<String, Journal> journals;

	public JournalDatabase() {
		journals = new HashMap<String, Journal>();
	}

	public Journal getJournal(int id) {
		return journals.get(id);
	}
	public void put(String id, Journal journal){
		journals.put(id,journal);
	}
	
}
