package server;

import staff.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map.Entry;

public class AccessBase {
	public static final String SU = "Sk�nes Universitetssjukhus";
	public static final String KI = "Karolinska Institutet";
	public static final String SB = "Sahlgrenska SexualSjukhus";
	private HashMap<BigInteger, User> userDatabase;
	private Integer idCounter = 1;

	public AccessBase() {
		userDatabase = new HashMap<BigInteger, User>();

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
			pw.println(e.getKey() + " " + e.getValue().toString());
		}
		pw.close();
		}
	
	}

