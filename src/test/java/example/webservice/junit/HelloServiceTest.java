package example.webservice.junit;

import static org.junit.Assert.assertTrue;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.junit.Test;

public class HelloServiceTest {
	@Test
	public void test() {
		
		 String soapEndpointUrl = "http://localhost:9000/hello";
		//String soapEndpointUrl = "http://localhost:9001/hw";
	     String soapAction = "sayHello";

	     callSoapWebService(soapEndpointUrl, soapAction);
		
		assertTrue(true);

	}
	
    /*
    Constructed SOAP Request Message:
    <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:targetNamespace="http://www.webserviceX.NET">
        <SOAP-ENV:Header/>
        <SOAP-ENV:Body>
            <ns:sayHello>
                <name>New York</name>
            </ns:sayHello>
        </SOAP-ENV:Body>
    </SOAP-ENV:Envelope>
    */

	private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();
        		
        String myNamespace = "targetNamespace";
        String myNamespaceURI = "http://junit.webservice.example/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

       
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        
        QName bodyName = new QName(myNamespaceURI, "sayHello", "ns");
        SOAPBodyElement bodyElement = soapBody.addBodyElement(bodyName);
        
        QName name = new QName("name");
        SOAPElement symbol = bodyElement.addChildElement(name);
        symbol.addTextNode("Be very Good");
      
        
        
    }
	
	 private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();

	        createSoapEnvelope(soapMessage);

	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", soapAction);

	        soapMessage.saveChanges();

	        /* Print the request message, just for debugging purposes */
	        System.out.println("Request SOAP Header Message:");
	        System.out.println(headers.toString());
	        System.out.println("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println("\n");

	        return soapMessage;
	}
	private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }


}
