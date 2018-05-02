package example.rest.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class JSONServiceJunit {
	
	@Test
	public void TestPost() {
			String urlAddress = "http://localhost:9998/json/product/post";
			try {

				URL url = new URL(urlAddress);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");

				String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();

				if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {

					System.out.println(output);
				}

				conn.disconnect();
				assertTrue(true);
			} catch (MalformedURLException e) {

				e.printStackTrace();
				assertFalse(e.getMessage(), false);
			} catch (IOException e) {

				e.printStackTrace();
				assertFalse(e.getMessage(), false);

			}

		}
	
	@Test
	public void TestGet() {
			String urlAddress = "http://localhost:9998/json/product/get";
			try {

				URL url = new URL(urlAddress);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
				}
				
				conn.disconnect();

				assertTrue(true);
			} catch (MalformedURLException e) {

				e.printStackTrace();
				assertFalse(e.getMessage(), false);
			} catch (IOException e) {

				e.printStackTrace();
				assertFalse(e.getMessage(), false);
				
			}

		}



}
