package staff;

import server.JournalDatabase;

public abstract class User implements Comparable {
	public static final char[] R = "ReadAccessAllowed".toCharArray();
	public static final char[] W = "WriteAccessAllowed".toCharArray();
	public static final char[] D = "DeletesAccessAllowed".toCharArray();

	private String id;
	private String title;
	private String name;
	private Division div;
	private static int idCounter = 1;
	private JournalDatabase jb;

	public User(String name, Division div) {
		this.name = name;
		this.div = div;
		

	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}
	public String getId(){
		return id;
	}
	public String createPatientId() {
		idCounter++;
		id = Integer.toString(idCounter);
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



	public char[] handleInput(String readLine) {
		return null;
		// TODO Auto-generated method stub
		
	}


}
