package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.security.KeyStore;

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
	private AccessBase userList = new AccessBase();
	private JournalDatabase jd = new JournalDatabase();
	private ServerSocket serverSocket = null;
	private static int numConnectedClients = 0;
	private KeyStore keystore;

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
			String subject = cert.getSubjectDN().getName();
			String[] name = subject.split(",");
			String clientName = name[0];
			clientName = clientName.substring(3);
			String clientClass = name[1];
			clientClass = clientClass.substring(4);
			String clientDivision = name[2];
			clientDivision = clientDivision.substring(3);
			numConnectedClients++;
			System.out.println("client connected");
			System.out.println("client name (cert subject DN field): " + name[0]);
			System.out.println(numConnectedClients + " concurrent connection(s)\n");

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			System.out.println(clientClass + " " + clientName + " " + clientDivision);

			// userList.
			String clientMsg = "";
			User user;
			if ((user = userList.getUser(cert.getSerialNumber())) == null) {
				User newUser;
				switch (clientClass) {
				case "D":
					newUser = new Doctor(clientName, new Division(clientDivision));
					break;
				case "N":

					newUser = new Nurse(clientName, new Division(clientDivision));
					break;
				case "P":
					newUser = new Patient(clientName, new Division(clientDivision));
					break;
				case "G":
					newUser = new GovAgency();
					break;
				default:
					newUser = null;
					break;
				}
				user = userList.createUser(cert.getSerialNumber(), newUser);
			}
			while ((clientMsg = in.readLine()) != null) {

				out.println(user.options());
				out.println("ENDOFMSG".toCharArray());
				out.println(user.getOption(in.readLine()));

			}
			close(socket, out, in);
		} catch (Exception e) {
			System.out.println("Client died: " + e.getMessage());
			e.printStackTrace();
			return;
		}
	}

	private void close(SSLSocket socket, PrintWriter out, BufferedReader in) throws IOException {
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
