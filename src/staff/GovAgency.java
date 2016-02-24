package staff;

public class GovAgency extends User{
	public static final String TITLE = "Government";
	public GovAgency(){
		super("BigBrother", new Division("G"), "999");

	}
	public char[] listOptions(){
		return (User.READ_PATIENT_RECORD+"\n"+User.DELETE_PATIENT_RECORD+"\n").toCharArray();
	}	
	public String toString(){
		String output = GovAgency.TITLE +":" + this.getId()+ ":" + this.getName() + ":" + this.getDivision().toString() + ":";
		
		return output;
		}
}
