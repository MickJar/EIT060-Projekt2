package server;

import java.util.HashMap;

import staff.Journal;

public class JournalDatabase {
	private HashMap<Integer, Journal> journals;

	public JournalDatabase() {
		journals = new HashMap<Integer, Journal>();
	}

	public Journal getJournal(int id) {
		return journals.get(id);
	}
	public void put(int id, Journal journal){
		journals.put(id,journal);
	}
	
}
