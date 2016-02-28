package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import staff.Division;
import staff.Doctor;
import staff.Nurse;
import staff.Patient;
import staff.User;

public class JournalDatabase {
	private HashMap<String, ArrayList<Journal>> journals;

	public JournalDatabase() {
		journals = new HashMap<String, ArrayList<Journal>>();
		readFile();
	}

	public Journal getJournal(String patientId, String doctorOrNurseId) {
		if(journals.containsKey(patientId)){
			for(Journal j: journals.get(patientId)){
				System.out.println(j.getDoctorId()+" "+j.getNurseId());
				if(j.getDoctorId().equals(doctorOrNurseId) || j.getNurseId().equals(doctorOrNurseId)){
					return j;
				}
			}
		}
		return null;
	}
	
	public void deleteRecord(String patientId){
		journals.remove(patientId);
	}
	
	public String readJournal(String patientId){
		String recs = "";
		if(journals.containsKey(patientId)){
			for(Journal j : journals.get(patientId)){
				recs+=j.toString()+"\n";
			}
		}
		return recs;
	}
	
	
	public void newJournal(String patientId, String doctorId, String nurseId) {
		if(!journals.containsKey(patientId)){
			ArrayList<Journal> patientsJournals = new ArrayList<Journal>();
			patientsJournals.add(new Journal(doctorId, nurseId));
			journals.put(patientId, patientsJournals);
			return;
		} else {
			for(Journal j : journals.get(patientId)){
				if(j.getDoctorId().equals(doctorId)){
					return;
				}
			}
			journals.get(patientId).add(new Journal (doctorId, nurseId));
			return;
			
		}
	}
//	public void put(String id, Journal journal){
//		journals.put(id,journal);
//	}
	public void saveFile(){
		PrintWriter pw = null;
		File journalBaseSave = new File("SavedFiles/journalBaseSave");
		if(!journalBaseSave.exists()){
				try {
					journalBaseSave.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		} 
		try {
			pw = new PrintWriter(journalBaseSave);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(Entry<String, ArrayList<Journal>> e : journals.entrySet()){
			pw.println(e.getKey());
			for(Journal j : e.getValue()){
				pw.println(j.toString());
			}
			pw.println("---");
		}
		pw.close();
		}
	public void readFile() {
		FileReader fileReader;
		try {
			fileReader = new FileReader("SavedFiles/journalBaseSave");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			
			while((line = bufferedReader.readLine()) != null	){
				ArrayList <Journal> temp = new ArrayList<Journal>();
				String patientId ="";
				String doctorId ="";
				String nurseId="";
				patientId = line;
				while(!((line=bufferedReader.readLine()).equals("---"))){
					if(line.substring(0, 6).equals("Doctor")){
						String[] lines = line.split(",");
						doctorId = lines[0].substring(7);
						nurseId = lines[1].substring(6);
						temp.add(new Journal(doctorId,nurseId));
					}else{
						String[] lines = line.split(":", 2);
						temp.get(temp.size()-1).addEntry(lines[1], lines[0]);
					}
					
					
				}
				journals.put(patientId, temp);
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

            
	}
	
}
