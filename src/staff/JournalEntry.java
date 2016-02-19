package staff;

public class JournalEntry {
	private String entry;
	
	public JournalEntry(String entry) {
		this.entry = entry;
	}
	
	public void AppendEntry(String text) {
		entry += text;
		
	}
	
	public String getEntry() {
		return entry;
	}
}
