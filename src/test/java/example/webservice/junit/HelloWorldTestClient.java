package example.webservice.junit;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWorldTestClient {

	public static void main(String[] args) {

		try {

			URL url = new URL("http://localhost:9001/hw?wsdl");
			QName qname = new QName("http://junit.webservice.example/",
					"HelloWorldImplService");

			Service service = Service.create(url, qname);

			HelloWorld server = service.getPort(HelloWorld.class);
			String name = "prasad";
			System.out.println(server.sayHello(name));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}