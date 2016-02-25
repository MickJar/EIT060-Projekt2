package server;

import staff.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.util.Date;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import javax.security.cert.X509Certificate;

import staff.Division;
import staff.Doctor;
import staff.GovAgency;
import staff.Nurse;
import staff.Patient;
import staff.User;

public class Server implements Runnable {
	private KeyStore keystore;
	public static final String LIST_PATIENT_RECORDS = "1";
	public static final String LIST_DIVISION_RECORDS = "2";
	public static final String READ_PATIENT_RECORD = "3";
	public static final String WRITE_PATIENT_RECORD = "4";
	public static final String CREATE_PATIENT_RECORD = "5";
	public static final String DELETE_PATIENT_RECORD = "6";
	public static final String WRITE_PATIENT_RECORD_INFORMATION = "41";

	private AccessBase accessBase = new AccessBase();
	private JournalDatabase journalDatabase = new JournalDatabase();
	private ServerSocket serverSocket = null;
	private static int numConnectedClients = 0;
	private Logger theLog = new Logger("SavedFiles/loggerSave");

	public Server(ServerSocket ss) throws IOException {
		serverSocket = ss;
		newListener();

	}

	public void run() {
		try {
			SSLSocket socket = (SSLSocket) serverSocket.accept();
			newListener();
			SSLSession session = socket.getSession();
			X509Certificate cert = (X509Certificate) session.getPeerCertificateChain()[0];
			java.security.cert.Certificate aliasCert = session.getPeerCertificates()[0];

			numConnectedClients++;

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			User user = getUser(cert);
//			Logger.log(user.getId(), "N/A", "Log in");

			String clientMsg = "";
			char[] handleClientInput =null;
			input: while ((clientMsg = in.readLine()) != "" && clientMsg != null) {
				if (clientMsg != null && clientMsg.equals("quit")) {

					break input;
				}
				// user.handleInput(clientMsg)
				handleClientInput = handleClientInput(clientMsg, user);
				out.println(handleClientInput);
				out.println("ENDOFMSG".toCharArray());
				String a = "";
				for(char c: handleClientInput){
					a+=c;
				}
				if((a).equals("Write information")){
					System.out.println("was equal");
					String additionalClientMsg = in.readLine();
					char[] additionalHandleClientInput = handleClientInput("41 "+clientMsg+" "+additionalClientMsg, user);
					out.println(additionalHandleClientInput);
					out.println("ENDOFMSG".toCharArray());
				}
				
			}

			close(socket, out, in);
		} catch (Exception e) {
			System.out.println("Client died: " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	private char[] handleClientInput(String clientMsg, User user) {
		String[] inputs = clientMsg.split(" ");
		if (inputs.length > 1) {
			if (inputs[0].equals(READ_PATIENT_RECORD) && user.hasPatient(inputs[1])) {
				Logger.log(user.getId(), inputs[1], "Accessed Patient Records");
				return journalDatabase.getJournal((inputs[1])).toString().toCharArray();

			} else if (inputs[0].equals(WRITE_PATIENT_RECORD) && user.hasPatient(inputs[1]) && inputs.length > 2) {
//				Journal temp = journalDatabase.getJournal((inputs[1]));
//				temp.addEntry(inputs[2], new Date().toString());
//				journalDatabase.put(inputs[1], temp);
				Logger.log(user.getId(), inputs[1], "Wrote to Patient Record" + inputs[2]);
				return ("Write information").toCharArray();
//				return ("Record added:" + inputs[2]).toCharArray();
			} else if (inputs[0].equals(Server.CREATE_PATIENT_RECORD)) {
				((Doctor)user).newPatient(inputs[1]);
				journalDatabase.getJournal(inputs[1]);
				return (("Journal created\n\n")+(user.listOptions()).toString()).toCharArray();
			} else if (inputs[0].equals(Server.WRITE_PATIENT_RECORD_INFORMATION)) {
				
				Doctor d;
				Nurse n;
				String patientID = inputs [2];
				if(user instanceof Doctor){
					d=(Doctor)user;
					
					String nurseID = inputs [3];
					n = (Nurse)accessBase.getUserFromId(nurseID);
					n.addPatient(patientID);
				} else {
					String doctorID = inputs [3];
					d=(Doctor)accessBase.getUserFromId(doctorID);
					
					n = (Nurse)user;
					d.newPatient(patientID);
				}
				
				String information = "";
				if(inputs.length>4){
					for(int i=4; i<inputs.length; i++){
						information += inputs[i] + " ";
					}
				}
			
				
				journalDatabase.getJournal(patientID).addEntry(information, "today", d, n);
				
			} 
		} else if (inputs[0].equals(Server.LIST_PATIENT_RECORDS)){
			return user.printRecords().toCharArray();
		} else if (inputs[0].equals(Server.LIST_DIVISION_RECORDS)){
			String divMembers = "";
			for(User u: user.getDivision().getMembers()){
				if(u instanceof Patient){
					divMembers += u.getId() + "\n";
				}
				
			}
			return divMembers.toCharArray();
		}

		return user.listOptions();
	}

	private void close(SSLSocket socket, PrintWriter out, BufferedReader in) throws IOException {
		accessBase.saveFile();
		journalDatabase.saveFile();
		in.close();
		out.close();
		socket.close();
		numConnectedClients--;
		System.out.println("client disconnected");
		System.out.println(numConnectedClients + " concurrent connection(s)\n");
	}

	private void newListener() {
		(new Thread(this)).start();
	} // calls run()

	private User getUser(X509Certificate cert) {
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
		System.out.println(numConnectedClients + " concurrent connection(s)\n");
		System.out.println(clientClass + " " + clientName + " " + clientDivision);

		if ((user = accessBase.getUser(cert.getSerialNumber())) == null) {
			System.out.println("USERISNULL");
			System.out.println("USERISNULL");
			User newUser;
			switch (clientClass.toUpperCase()) {
			case "D":
				newUser = new Doctor(clientName, new Division(clientDivision), accessBase.getAUserId());
				break;
			case "N":
				newUser = new Nurse(clientName, new Division(clientDivision), accessBase.getAUserId());
				break;
			case "P":
				newUser = new Patient(clientName, new Division(clientDivision), accessBase.getAUserId());
				break;
			case "G":
				newUser = new GovAgency();
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

	public static void main(String args[]) {
		System.out.println("\nServer Started\n");
		int port = -1;
		if (args.length >= 1) {
			port = Integer.parseInt(args[0]);
		}
		String type = "TLS";
		try {
			ServerSocketFactory ssf = getServerSocketFactory(type);
			ServerSocket ss = ssf.createServerSocket(port);
			((SSLServerSocket) ss).setNeedClientAuth(true); // enables client
															// authentication
			new Server(ss);
		} catch (IOException e) {
			System.out.println("Unable to start Server: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// private KeyStore getKeyStore(String Id) {
	// try {
	// KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
	// KeyStore ks = KeyStore.getInstance("JKS");
	// char[] password = "password".toCharArray();
	// ks.load(new FileInputStream("serverstores/" + Id), password);
	// kmf.init(ks, password);
	//
	// return ks;
	// } catch (Exception e) {
	// System.err.println("Could not find keystore");
	// e.printStackTrace();
	// return null;
	// }
	//
	// }

	private static ServerSocketFactory getServerSocketFactory(String type) {
		if (type.equals("TLS")) {
			SSLServerSocketFactory ssf = null;
			try { // set up key manager to perform server authentication
				SSLContext ctx = SSLContext.getInstance("TLS");
				KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
				TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
				KeyStore ks = KeyStore.getInstance("JKS");
				KeyStore ts = KeyStore.getInstance("JKS");
				char[] password = "password".toCharArray();

				ks.load(new FileInputStream("Certs/ServerStore/serverkeystore"), password); // keystore
				// password
				// (storepass)
				ts.load(new FileInputStream("Certs/ServerStore/servertruststore"), password); // truststore
				// password
				// (storepass)
				kmf.init(ks, password); // certificate password (keypass)
				tmf.init(ts); // possible to use keystore as truststore here
				ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
				ssf = ctx.getServerSocketFactory();
				return ssf;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return ServerSocketFactory.getDefault();
		}
		return null;
	}
}
