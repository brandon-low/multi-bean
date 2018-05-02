package example.webservice.junit;
import javax.xml.ws.Endpoint;

public class HelloServicePublisher {
	public static void main(String[] args) {
		String url = "http://localhost:9000/hello";
		Endpoint.publish(url, new Hello());
		System.out.println("Service started @ " + url);
		
		System.out.println(" to test open browwser type in " + url + "?wsdl");
	}
}