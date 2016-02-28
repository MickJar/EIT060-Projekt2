package server;

import javax.security.cert.X509Certificate;

import staff.Doctor;
import staff.GovAgency;
import staff.Nurse;
import staff.Patient;
import staff.User;

public class Hospital {
	public static final String LIST_PATIENT_RECORDS = "1";
	public static final String LIST_DIVISION_RECORDS = "2";
	public static final String READ_PATIENT_RECORD = "3";
	public static final String WRITE_PATIENT_RECORD = "4";
	public static final String CREATE_PATIENT_RECORD = "5";
	public static final String DELETE_PATIENT_RECORD = "6";
	public static final String WRITE_PATIENT_RECORD_INFORMATION = "41";

	private JournalDatabase journalDatabase;
	private AccessBase accessBase;

	@SuppressWarnings("unused")
	private Logger theLog = new Logger("SavedFiles/loggerSave");

	public Hospital() {
		accessBase = new AccessBase();
		journalDatabase = new JournalDatabase();

	}

	public void save() {
		accessBase.saveFile();
		journalDatabase.saveFile();
	}

	public String handleClientInput(String clientMsg, User user) {
		
		String[] inputs = clientMsg.split(" ");
		if (inputs.length > 1) {
			String patientId = inputs[1];
			if ((inputs[0].equals(READ_PATIENT_RECORD))) {
				Logger.log(user.getId(), patientId, "Accessed Patient Records");
				if (((user.getTitle().equals("Nurse") || user.getTitle().equals("Doctor"))
						&& user.getDivision().containsMember(patientId)) || user.getTitle().equals("Government")) {
					return journalDatabase.readJournal(patientId) + "\n" + user.listOptions();
				} else if (user.hasPatient(patientId)) {
					return journalDatabase.getJournal(patientId, user.getId()).toString() + "\n\n"+ user.listOptions();
				}

			} else if (inputs[0].equals(WRITE_PATIENT_RECORD) && user.hasPatient(patientId)) {
				Logger.log(user.getId(), inputs[1], "Wrote to Patient Record");
				return ("Write information");
			} else if (inputs[0].equals(CREATE_PATIENT_RECORD) && user instanceof Doctor) {
				Logger.log(user.getId(), patientId, "Created Patient Records");
				((Doctor) user).newPatient(patientId);
				journalDatabase.newJournal(patientId, user.getId(), inputs[2]);
				((Nurse) accessBase.getUserFromId(inputs[2])).addPatient(patientId);
				return (("Journal created\n\n") + (user.listOptions()).toString());
			} else if (inputs[0].equals(WRITE_PATIENT_RECORD_INFORMATION)) {
				patientId = inputs[2];
				String information = "";
				if (inputs.length > 3) {
					for (int i = 3; i < inputs.length; i++) {
						information += inputs[i] + " ";
					}
				}
				journalDatabase.getJournal(patientId, user.getId()).addEntry(information, Logger.getDate());
			} else if (inputs[0].equals(DELETE_PATIENT_RECORD) && user.getTitle().equals("Government")) {
				Logger.log(user.getId(), patientId, "Deleted Patient Records");
				journalDatabase.deleteRecord(inputs[1]);
			}
		} else if (inputs[0].equals(LIST_PATIENT_RECORDS)) {
			String listRecords = "Patient:IDnumber:Name:Hospital: \n";
			for (String patientId : user.getPatients()) {
				System.out.println("has a patient"+patientId);
				listRecords += accessBase.getUserFromId(patientId).toString() + "\n";
			}
			return listRecords;
		} else if (inputs[0].equals(LIST_DIVISION_RECORDS)) {
			String divMembers = "Patient:IDnumber:Name:Hospital: \n";
			for (User u : user.getDivision().getMembers()) {
				if (u instanceof Patient) {
					divMembers += u.toString() + "\n";
				}

			}
			return divMembers;
		} else if (inputs[0].equals(READ_PATIENT_RECORD)) {
			return journalDatabase.readJournal(user.getId());
		}

		return user.listOptions();
	}

	public User getUser(X509Certificate cert) {
		String subject = cert.getSubjectDN().getName();
		String[] name = subject.split(",");
		String clientName = name[0];
		clientName = clientName.substring(3);
		String clientClass = name[1];
		clientClass = clientClass.substring(4);
		String clientDivision = name[2];
		clientDivision = clientDivision.substring(3);
		User user;

		System.out.println("client connected");
		System.out.println("client name (cert subject DN field): " + name[0]);
		// System.out.println(numConnectedClients + " concurrent
		// connection(s)\n");
		System.out.println(clientClass + " " + clientName + " " + clientDivision);

		if ((user = accessBase.getUser(cert.getSerialNumber())) == null) {
			User newUser;
			switch (clientClass.toUpperCase()) {
			case "D":
				newUser = new Doctor(clientName, accessBase.getDivision(clientDivision), accessBase.getAUserId());
				break;
			case "N":
				newUser = new Nurse(clientName, accessBase.getDivision(clientDivision), accessBase.getAUserId());
				break;
			case "P":
				newUser = new Patient(clientName, accessBase.getDivision(clientDivision), accessBase.getAUserId());
				break;
			case "G":
				newUser = new GovAgency(clientName, accessBase.getDivision(clientDivision), accessBase.getAUserId());
				break;
			default:
				newUser = null;
				break;
			}
			try {
				user = accessBase.createUser(cert.getSerialNumber(), newUser);
			} catch (Exception e) {
				System.err.println("Illegal operation");
			}
		} else {
			System.out.println(cert.getSerialNumber());
			System.out.println(user.getName());
		}

		return user;
	}

}
