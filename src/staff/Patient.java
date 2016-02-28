package staff;



public class Patient extends User {
	private final static String TITLE = "Patient";

	public Patient(String name, Division div, String id) {
		super(name, div, id);
//		id = super.createPatientId();

	}
	public String getTitle() {
		return TITLE;
	}
	
	public String listOptions(){
		return (User.READ_PATIENT_RECORD_PATIENT+"\n");
	}
	public String toString(){
	String output = Patient.TITLE +":" + this.getId()+ ":" + this.getName() + ":" + this.getDivision().toString() + ":";
	
	return output;
	}
}
