package example.encode;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * This class provides methods to encrypt and decrypt data.
 */

public class CrytoJunit {

    private String md5(final String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        
        return  DatatypeConverter.printHexBinary(messageDigest); 
        //final BigInteger number = new BigInteger(1, messageDigest);
        //return String.format("%032x", number);
    }

    private String getMD5Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return  DatatypeConverter.printHexBinary(hash);  // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
	@Test
	public void TestEncode() {
	
		try {
			String orig = "Test Me Good";
			String encode = md5(orig); // getMD5Hash(orig);
			
			String enc2 = md5(orig); //getMD5Hash(orig);
			System.out.println(orig + " endode=" + encode);
			System.out.println(orig + " endode=" + enc2);
		} catch (Exception e) {
			
		}
		
	}

 
    private Cipher initCipher(final int mode, final String initialVectorString, final String secretKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec skeySpec = new SecretKeySpec(md5(secretKey).getBytes(), "AES");
        IvParameterSpec initialVector = new IvParameterSpec(initialVectorString.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(mode, skeySpec, initialVector);
        return cipher;
    }
 
    public String encrypt(final String dataToEncrypt, final String initialVector, final String secretKey) {
        String encryptedData = null;
        try {
            // Initialize the cipher
            Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, initialVector, secretKey);
            // Encrypt the data
            byte[] encryptedByteArray = cipher.doFinal(dataToEncrypt.getBytes());
            // Encode using Base64
            encryptedData = Base64.getEncoder().encodeToString(encryptedByteArray);
            
        } catch (Exception e) {
            System.err.println("Problem encrypting the data");
            e.printStackTrace();
        }
        return encryptedData;
    }

 
    public String decrypt(final String encryptedData, final String initialVector, final String secretKey) {
        String decryptedData = null;
        try {
            // Initialize the cipher
            Cipher cipher = initCipher(Cipher.DECRYPT_MODE, initialVector, secretKey);
            // Decode using Base64
            byte[] encryptedByteArray = Base64.getDecoder().decode(encryptedData); //(new BASE64Decoder()).decodeBuffer(encryptedData);
            // Decrypt the data
            byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
            decryptedData = new String(decryptedByteArray, "UTF8");
        } catch (Exception e) {
            System.err.println("Problem decrypting the data");
            e.printStackTrace();
        }
        return decryptedData;
    }

    @Test
    public void testMe() {
    	   final String iv = "0123456789123456"; // This has to be 16 characters
           final String secretKey = "Replace this by your secret key";
           
           String message = "brandon:passwords"; //This is a test message.
           final String encryptedData = encrypt(message, iv, secretKey);
           System.out.println(encryptedData);
           final String decryptedData = decrypt(encryptedData, iv, secretKey);
           System.out.println(decryptedData);
           assertTrue(true);
    	
    }
    

  

}
