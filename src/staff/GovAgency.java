package staff;

public class GovAgency extends User{
	public GovAgency(){
		super("BigBrother", new Division("G"));

	}
	public char[] listOptions(){
		return (User.READ_PATIENT_RECORD+"\n"+User.DELETE_PATIENT_RECORD+"\n").toCharArray();
	}	
}
