package example.webservice.junit;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "example.webservice.junit.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
 
	@WebMethod(action="sayHello")
	public String sayHello(@WebParam(name="name") String name) {
		System.out.println("Got a request: " + name);
		return "Hello " + name + " !, Hope you are doing well !!";
	}
	
	 
 
}