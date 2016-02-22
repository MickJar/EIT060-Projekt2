package staff;

import server.JournalDatabase;

public abstract class User implements Comparable {
	private String id;
	private String title;
	private String name;
	private Division div;
	private static int idCounter = 1;
	private JournalDatabase jb;

	public User(String name, Division div) {
		this.name = name;
		this.div = div;
		id = Integer.toString(idCounter);
		idCounter++;

	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public Division getDivision() {
		return div;
	}
	
	@Override
	public int compareTo(Object o) {
		if (this.getClass() == o.getClass()) {
			return 1;
		}
		return 0;

	}
	
	public Journal getJournal(int id) {
		return jb.getJournal(id);
	}
	
	public void deleteJournal(int id) {
	}
	
	public void appendJournal(int id, String text) {
		
	}


}
