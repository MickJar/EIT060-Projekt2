package server;


public class JournalEntry {
	private String entry;
	private String Date;
	
	public JournalEntry(String entry, String Date) {
		this.entry = entry;
		this.Date = Date;
	}
	
	public JournalEntry getEntry() {
		return this;
	}
	public String toString(){
		return  Date + ":" + entry;
	}
}
