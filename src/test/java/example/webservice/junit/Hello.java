package example.webservice.junit;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class Hello {
	
	
	@WebMethod(action="sayHello")
	public String sayHello(@WebParam(name="name") String name) {
		
		System.out.println("Got a request: " + name);
		return "Hello " + name;
	}
}
