package example.ddos;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
public class HelloImpl extends UnicastRemoteObject implements Hello 
{ 
    public HelloImpl() throws RemoteException {}

    public String sayHello() { return "Hello world!"; }

    public static void main(String args[]) 
    { 
    	String hostname ="";
    	try {
    		InetAddress addr = InetAddress.getLocalHost();
    		hostname = addr.getHostName();
    	} catch (UnknownHostException unknownHostException) {
    		System.err.println("Failure during host name resolution: " + unknownHostException);
    	}

        try 
        { 
            HelloImpl obj = new HelloImpl(); 
            // Bind this object instance to the name "HelloServer" 
            Naming.rebind("//" + hostname + "/HelloServer", obj); 
        } 
        catch (Exception e) 
        { 
            System.out.println("HelloImpl err: " + e.getMessage()); 
            e.printStackTrace(); 
        } 
    } 
}