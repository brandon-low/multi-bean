package example.webservice.junit;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
 
@WebService
@SOAPBinding(style = Style.RPC)
public interface HelloWorld {
 
	@WebMethod(action="sayHello")
	public String sayHello(@WebParam(name="name") String name);
}