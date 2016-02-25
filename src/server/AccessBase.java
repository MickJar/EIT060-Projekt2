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

public class AccessBase {
	public static final String SU = "Sk�nes Universitetssjukhus";
	public static final String KI = "Karolinska Institutet";
	public static final String SB = "Sahlgrenska SexualSjukhus";
	private HashMap<BigInteger, User> userDatabase;
	private Integer idCounter = 1;
	private Division[] divs;
	

	public AccessBase() {
		userDatabase = new HashMap<BigInteger, User>();
		divs = new Division[3];
		divs[0] = new Division(AccessBase.SU);
		divs[1] = new Division(AccessBase.KI);
		divs[2] = new Division(AccessBase.SB);

	}
	
	public String getAUserId(){
		String uId = idCounter.toString();
		idCounter++;
		return uId;
	}
	
	public void setIdCounter(String newId){
		idCounter=Integer.parseInt(newId);
	}

	public User getUser(BigInteger Biggie) {
		if(!userDatabase.containsKey(Biggie)){
			return null;
		}
		return userDatabase.get(Biggie);
	}

	public User createUser(BigInteger Biggie, User staffMember) throws Exception {

		if (!userDatabase.containsKey(Biggie)) {
			userDatabase.put(Biggie, staffMember);
			return staffMember;
		} else {
			throw new Exception();
		}
	}
	
	public void readFile() {
		FileReader fileReader;
		try {
			fileReader = new FileReader("SavedFiles/accessBaseSave");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while((line = bufferedReader.readLine()) != null	){
				String[] idInfo = line.split(":");
				User u = null;
				Division d = null;
				if(idInfo[4] == "SU"){
					d=divs[0];
				} else if (idInfo[4] =="KI"){
					d=divs[1];
				} else if (idInfo[4] =="SB"){
					d=divs[2];
				}
				if(idInfo[1]=="Doctor"){
					u = new Doctor(idInfo[3],d, idInfo[2]);
					d.addMember(u);
					String[] patients = idInfo[5].split(";");
					for(String s : patients){
						((Doctor)u).newPatient(s);
					}
							
				} else if (idInfo[1]=="Nurse"){
					u = new Nurse(idInfo[3],d, idInfo[2]);
					d.addMember(u);
					String[] patients = idInfo[5].split(";");
					for(String s : patients){
						((Nurse)u).newPatient(s);
					}
					
				} else if (idInfo[1]=="Patient"){
					u = new Patient(idInfo[3],d, idInfo[2]);
					d.addMember(u);
					
				} else if (idInfo[1]=="Government"){
					
				}
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException io) {
			
		}

            
	}
	public void saveFile(){
		PrintWriter pw = null;
		File accessBaseSave = new File("SavedFiles/accessBaseSave");
		if(!accessBaseSave.exists()){
				try {
					accessBaseSave.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
		
		try {
			pw = new PrintWriter(accessBaseSave);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(Entry<BigInteger, User> e : userDatabase.entrySet()){
			pw.println(e.getKey() + ":" + e.getValue().toString());
		}
		pw.close();
		}
	
	}

