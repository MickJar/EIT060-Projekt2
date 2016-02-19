package client;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class StoreHandler  {
	
	public static SSLSocket getSSLSocket(String pwd, String keystoreName, String truststoreName, String host, int port){
		char[] password = pwd.toCharArray();
		try {
			SSLSocketFactory factory = null;
			try {
				KeyStore ks = KeyStore.getInstance("JKS");
				
				KeyStore ts = KeyStore.getInstance("JKS");
				KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
				TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
				SSLContext ctx = SSLContext.getInstance("TLS");
				ks.load(new FileInputStream(keystoreName), password);  // keystore password (storepass)
				ts.load(new FileInputStream(truststoreName), password); // truststore password (storepass);
				kmf.init(ks, password); // user password (keypass)
				tmf.init(ts); // keystore can be used as truststore here
				ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
				factory = ctx.getSocketFactory();
				return (SSLSocket)factory.createSocket(host, port);
			} catch (Exception e){
				
			}
		} catch (Exception e){
			
		}
		return null;
	}
}