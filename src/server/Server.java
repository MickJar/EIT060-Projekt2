package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.cert.X509Certificate;

public class Server {
	private int port;
	private String host;
	private ServerSocket serverSocket = null;

	public Server(int port, String host) {
		this.port = port;
		this.host = host;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server started on port: " + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				SSLSocket socket = (SSLSocket) serverSocket.accept();
				SSLSession session = socket.getSession();
				X509Certificate cert = (X509Certificate) session.getPeerCertificateChain()[0];
				String subject = cert.getSubjectDN().getName();
				System.out.println("client connected");
				System.out.println("client name (cert subject DN field): " + subject);

				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				;
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				close(socket, out, in);
			} catch (IOException e) {
				System.out.println("Client died: " + e.getMessage());
				e.printStackTrace();
				return;
			}
		}
	}

	private void close(SSLSocket socket, PrintWriter out, BufferedReader in) throws IOException {
		in.close();
		out.close();
		socket.close();
		System.out.println("client disconnected");
	}

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		Server server = new Server(port, "localhost");
		server.run();
	}
}