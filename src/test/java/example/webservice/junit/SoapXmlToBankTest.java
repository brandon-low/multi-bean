package example.webservice.junit;

import static org.junit.Assert.assertTrue;

import java.util.Base64;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.junit.Test;

public class SoapXmlToBankTest {
	
	/**
	 * Junit test to send a soap message to a webservive to the following url
	 */
	@Test
	public void test() {
		
		 String soapEndpointUrl = "http://www.thomas-bayer.com/axis2/services/BLZService";
	     String soapAction = "getBank";

	        callSoapWebService(soapEndpointUrl, soapAction);
		
		assertTrue(true);
	}
	
	 // http://www.thomas-bayer.com/axis2/services/BLZService?wsdl
    /*
     * 
     * Sample of adding authen to soap header <?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
<soap:Header>
<Authentication xmlns="http://tempuri.org/">
<Password>string</Password>
<UserName>string</UserName>
</Authentication>
</soap:Header>
<soap:Body>
<HelloWorld xmlns="http://tempuri.org/" />
</soap:Body>
</soap:Envelope>


    Constructed SOAP Request Message:
    <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:targetNamespace="http://www.webserviceX.NET">
        <SOAP-ENV:Header/>
        <SOAP-ENV:Body>
            <targetNamespace:sayHello>
                <myNamespace:USCity>New York</myNamespace:USCity>
            </targetNamespace:sayHello>
        </SOAP-ENV:Body>
    </SOAP-ENV:Envelope>
    */

	 
		private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
	        SOAPPart soapPart = soapMessage.getSOAPPart();
        		
	        String myNamespace = "targetNamespace";
	        String myNamespaceURI = "http://thomas-bayer.com/blz/";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElem = soapBody.addChildElement("getBank", myNamespace);
	        
	        //SOAPElement soapBodyElem0 = soapBodyElem.addChildElement("getBankType", myNamespace);
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("blz", myNamespace);
	        soapBodyElem1.addTextNode("37050198");
	    }
		
		private static void printString(String str[]) {
			if (str != null) {
				for (int i = 0; i <str.length; i++) {
					System.out.print("["+ str[i] + "]");
				}
				System.out.print("\n");
			}
		}
		
		/**
		 * Soap header
		 * @param headers
		 */
		private static void printHeader(MimeHeaders headers){
			  System.out.println("Request SOAP Header Message:");
		      System.out.println(headers.toString());
		      
		      String [] keys = {	"Content-Type", // text/xml; charset=utf-8 
		    		  				"Accept", 		//  application/soap+xml, application/dime, multipart/related, text/* 
		    		  				"User-Agent",	// Axis/1.4 
						    		  "Host", //: {webservicehosturl}:{portnumber} 
						    		  "Cache-Control", //: no-cache 
						    		  "Pragma", //: no-cache 
						    		  "SOAPAction", //: {webserviceNamespace}/{operationname} 
						    		  "Content-Length", //: 408 
						    		  "Authorization" } ;//: Basic {encrypetedcredentials} };
		      for (int i = 0; i < keys.length; i++) {
		    		  System.out.println(keys[i]+ "=" +headers.getHeader(keys[i]));
		    		  printString(headers.getHeader(keys[i]));
		      }
		      
		     
		}
		
		 private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
		        MessageFactory messageFactory = MessageFactory.newInstance();
		        SOAPMessage soapMessage = messageFactory.createMessage();

		        // create the envelope
		        createSoapEnvelope(soapMessage);

		        // set http header
		        MimeHeaders headers = soapMessage.getMimeHeaders();
		        headers.addHeader("SOAPAction", soapAction);
		        
		        // set user name password to mime header, basic authentication
		        String username = "brandon";
		        String password = "someSecret";
		        String encodeString = username + ":" + password;
		        
		        //  Base64Coder.encode(username + ":" + password);
		        String authorization = "Basic " +  Base64.getEncoder().encodeToString(encodeString.getBytes());
			    headers.addHeader("Authorization", authorization);
		        

		        soapMessage.saveChanges();

		        /* Print the request message, just for debugging purposes */
		        printHeader(headers);
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
