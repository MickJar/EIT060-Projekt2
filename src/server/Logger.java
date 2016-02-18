package server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private PrintStream output;
	
	public Logger(String file) {
		try {
			FileOutputStream fileOutput = new FileOutputStream(file, true);
			output = new PrintStream(fileOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getTimeDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void log(String editor, String patient, String action) {
		output.println(getTimeDate() + " " + editor + " " + action + " for patient "  + patient);
		output.flush();	
	}
}