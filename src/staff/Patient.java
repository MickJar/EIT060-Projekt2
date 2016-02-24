package staff;



public class Patient extends User {
	private final static String TITLE = "Patient";

	public Patient(String name, Division div, String id) {
		super(name, div, id);
//		id = super.createPatientId();

	}
	
	public char[] listOptions(){
		return (User.LIST_PATIENT_RECORDS+"\n").toCharArray();
	}
	public String toString(){
	String output = Patient.TITLE +":" + this.getId()+ ":" + this.getName() + ":" + this.getDivision().toString() + ":";
	
	return output;
	}
}
