package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map.Entry;

import staff.Journal;
import staff.User;

public class JournalDatabase {
	private HashMap<String, Journal> journals;

	public JournalDatabase() {
		journals = new HashMap<String, Journal>();
	}

	public Journal getJournal(String id) {
		if(!journals.containsKey(id)){
			journals.put(id, new Journal());
		}
		return journals.get(id);
	}
	public void put(String id, Journal journal){
		journals.put(id,journal);
	}
	public void saveFile(){
		PrintWriter pw = null;
		File journalBaseSave = new File("SavedFiles/journalBaseSave");
		if(!journalBaseSave.exists()){
				try {
					journalBaseSave.createNewFile();
					System.out.println("here");
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		
		try {
			pw = new PrintWriter(journalBaseSave);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(Entry<String, Journal> e : journals.entrySet()){
			pw.println(e.getKey() + " " + e.getValue().toString());
		}
		pw.close();
		}
	
}
