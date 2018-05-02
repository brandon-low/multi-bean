package example.webservice.junit;

import javax.xml.ws.Endpoint;

public class HelloWorldPublisher {
	
	// open browser and type in http://127.0.0.1:9876/hw?wsdl
 
	public static void main(String[] args) {
 
		String url = "http://localhost:9001/hw";
		
		System.out.println("Beginning to publish HelloWorld now");
		Endpoint.publish(url, new HelloWorldImpl());
		System.out.println("Service started @ " + url);
		
		System.out.println(" to test open browwser type in " + url + "?wsdl");
	}
 
}
