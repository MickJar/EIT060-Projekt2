package server;

import java.util.ArrayList;

public class Journal {
	private ArrayList<JournalEntry> records;
	private String doctorId;
	private String nurseId;

	

	public Journal(String doctorId, String nurseId) {
		records = new ArrayList<JournalEntry>();
		this.doctorId=doctorId;
		this.nurseId=nurseId;

	}
	public String getDoctorId(){
		return doctorId;
	}
	
	public String getNurseId(){
		return nurseId;
	}
	
	public void addEntry(String entry, String date) {
		records.add(new JournalEntry(entry, date));
	}

	public ArrayList<JournalEntry> getRecords() {
		return records;
	}
	

	public String toString(){
		String output = "Doctor="+ doctorId + ",Nurse=" + nurseId;
		for(JournalEntry je : records){
			output+= "\n" +je.toString();
		}
		return output;
	}

}
