package example.webservice.junit;

//Libraries needed to build SOAP message
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.Name;

//Libraries needed to work with attachments (Java Activation Framework API)
import java.net.URL;
import javax.activation.DataHandler;

//Libraries needed to convert the SOAP message to a JMS message and to send it
//import com.sun.messaging.xml.MessageTransformer;
//import com.sun.messaging.BasicConnectionFactory;

//Libraries needed to set up a JMS connection and to send a message
//import javax.jms.TopicConnectionFactory;
//import javax.jms.TopicConnection;
//import javax.jms.JMSException;
//import javax.jms.Session;
//import javax.jms.Message;
//import javax.jms.TopicSession;
//import javax.jms.Topic;
//import javax.jms.TopicPublisher;

//Define class that sends JMS message with SOAP payload
public class SendSoapMessageWithJMS{

//  TopicConnectionFactory tcf = null;
//  TopicConnection tc = null;
//  TopicSession session = null;
//  Topic topic = null;
//  TopicPublisher publisher = null;
  /*

//default constructor method
public SendSoapMessageWithJMS(String topicName){
  init(topicName);
  }

//Method to nitialize JMS Connection, Session, Topic, and Publisher
public void init(String topicName) {
  try {
      tcf = new com.sun.messaging.TopicConnectionFactory();
	  
      tc = tcf.createTopicConnection();
      session = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
      topic = session.createTopic(topicName);
      publisher = session.createPublisher(topic);
      }

//Method to create and send the SOAP/JMS message
public void send() throws Exception{
  MessageFactory mf = MessageFactory.newInstance(); //create default factory
  SOAPMessage soapMessage=mf.createMessage(); //create SOAP message object
  SOAPPart soapPart = soapMessage.getSOAPPart();//start to drill down to body
  SOAPEnvelope soapEnvelope = soapPart.getEnvelope(); //first the envelope
  SOAPBody soapBody = soapEnvelope.getBody();
  Name myName = soapEnvelope.createName("HelloWorld", "hw", " http://www.sun.com/imq"); 
                                                   //name for body element
  SOAPElement element = soapBody.addChildElement(myName); //add body element
  element.addTextNode("Welcome to SUnOne Web Services."); //add text value

  //Create an attachment with the Java Framework Activation API
  URL url = new URL("http://java.sun.com/webservices/");
  DataHandler dh = new DataHandler (url);
  AttachmentPart ap = soapMessage.createAttachmentPart(dh);

  //Set content type and ID
  ap.setContentType("text/html");
  ap.setContentId("cid-001");

  //Add attachment to the SOAP message
  soapMessage.addAttachmentPart(ap);
  soapMessage.saveChanges();

  //Convert SOAP to JMS message.
  Message m = MessageTransformer.SOAPMessageIntoJMSMessage
                                                   (soapMessage,session);

//Publish JMS message
  publisher.publish(m);

//Close JMS connection
  public void close() throws JMSException {
      tc.close();
  }

//Main program to send SOAP message with JMS
public static void main (String[] args) {
  try {
      String topicName = System.getProperty("TopicName");
      if(topicName == null) {
          topicName = "test";
      }

      SendSoapMessageWithJMS ssm = new SendSoapMessageWithJMS(topicName);
      ssm.send();
      ssm.close();
  }
      catch (Exception e) {
          e.printStackTrace();
      }    
  }
  */
}
