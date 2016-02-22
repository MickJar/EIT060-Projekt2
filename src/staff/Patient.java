package staff;

import java.util.ArrayList;

public class Patient extends User {
	private final String TITLE = "Patient";

	public Patient(String name, Division div) {
		super(name, div);

	}
	
	public char[] options(){
		char[] output = "Press 1 to list records".toCharArray();
		return output;
	}

}
