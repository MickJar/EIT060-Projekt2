package staff;

public class GovAgency extends User{
	public static final String TITLE = "Government";
	public GovAgency(String name, Division div, String id){
		super(name, div, id);

	}
	public String getTitle() {
		return TITLE;
	}
	

	
	public String listOptions(){
		return (User.READ_PATIENT_RECORD+"\n"+User.DELETE_PATIENT_RECORD+"\n");
	}	
	public String toString(){
		String output = GovAgency.TITLE +":" + this.getId()+ ":" + this.getName() + ":" + this.getDivision().toString() + ":";
		
		return output;
		}
}
