package staff;

public class GovAgency extends User{
	public GovAgency(){
		super("BigBrother", new Division("G"));

	}
	public char[] options(){
		char[] output = "Press 1 to list patient records".toCharArray();
		return output;
	}	
}
