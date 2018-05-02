package example.webservice.junit;

/*
Example 5–6 illustrates the use of the JMS API, SAAJ, and the DOM API to receive a SOAP message with attachments as the payload to a JMS message. The code shown for the ReceiveSOAPMessageWithJMS includes the following methods:

A constructor that calls the init method to initialize all the JMS objects needed to receive a message.

An onMessage method that delivers the message and which is called by the listener. The onMessage method also calls the message transformer utility to convert the JMS message into a SOAP message and then uses SAAJ to process the SOAP body and uses SAAJ and the DOM API to process the message attachments.

A main method that initializes the ReceiveSOAPMessageWithJMS class.

Example 5–6 Receiving a JMS Message with a SOAP Payload
*/

//Libraries that support SOAP processing
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
//import javax.xml.soap.AttachmentPart

//Library containing the JMS to SOAP transformer
//import com.sun.messaging.xml.MessageTransformer;

//Libraries for JMS messaging support
//import com.sun.messaging.TopicConnectionFactory

//Interfaces for JMS messaging
//import javax.jms.MessageListener;
//import javax.jms.TopicConnection;
//import javax.jms.TopicSession;
//import javax.jms.Message;
//import javax.jms.Session;
//import javax.jms.Topic;
//import javax.jms.JMSException;
//import javax.jms.TopicSubscriber

//Library to support parsing attachment part (from DOM API)
//import java.util.iterator;

public class ReceiveSoapMessageWithJMS { // implements MessageListener{
	/*
//public prototype2 implements MessageListener{
  TopicConnectionFactory tcf = null;
  TopicConnection tc = null;    
  TopicSession session = null;
  Topic topic = null;        
  TopicSubscriber subscriber = null;
  MessageFactory messageFactory = null;

//Default constructor
public ReceiveSOAPMessageWithJMS(String topicName) {
  init(topicName);
}
//Set up JMS connection and related objects
public void init(String topicName){
  try {
      //Construct default SOAP message factory
      messageFactory = MessageFactory.newInstance();

      //JMS set up
      tcf = new. com.sun.messaging.TopicConnectionFactory();
      tc = tcf.createTopicConnection();
      session = tc.createTopicSesstion(false, Session.AUTO_ACKNOWLEDGE);
      topic = session.createTopic(topicName);
      subscriber = session.createSubscriber(topic);
      subscriber.setMessageListener(this);
      tc.start();

      System.out.println("ready to receive SOAP m essages...");
  }catch (Exception jmse){
      jmse.printStackTrace();
      }
  }

//JMS messages are delivered to the onMessage method    
public void onMessage(Message message){
  try {
      //Convert JMS to SOAP message
      SOAPMessage soapMessage = MessageTransformer.SOAPMessageFromJMSMessage
                          (message, messageFactory);


      //Print attchment counts
      System.out.println("message received! Attachment counts:" + soapMessage.countAttachments());

      //Get attachment parts of the SOAP message
      Iterator iterator = soapMessage.getAttachments();
      while (iterator.hasNext()) {
          //Get next attachment
          AttachmentPart ap = (AttachmentPart) iterator.next();

          //Get content type
          String contentType = ap.getContentType();
          System.out.println("content type: " + conent TYpe);

          //Get content id
          String contentID = ap.getContentID();
          System.out.println("content Id:" + contentId);
  
          //Check to see if this is text    
          if(contentType.indexOf"text")>=0 {
              //Get and print string content if it is a text attachment
              String content = (String) ap.getContent();
              System.outprintln("*** attachment content: " + content);
          }
      }
  }catch (Exception e) {
      e.printStackTrace();
  }
}

//Main method to start sample receiver
public static void main (String[] args){
  try {
      String topicName = System.getProperty("TopicName");
      if( topicName == null) {
          topicName = "test";
      }
      ReceiveSOAPMessageWithJMS rsm = new ReceiveSOAPMessageWithJMS(topicName);
  }catch (Exception e) {
      e.printStackTrace();
      }
  }
  
  */
}