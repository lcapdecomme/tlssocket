package fr.yoyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocketFactory;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class TLSClient2 {

	static final int port = 8000;

	static final String send = "client2";

	public static void main(String[] args)
	{


		System.setProperty("javax.net.ssl.keyStore",	"keys/client2.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

		System.setProperty("javax.net.ssl.trustStore","keys/trustclient2.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

		
		SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		try
		{
			Socket socket = sslSocketFactory.createSocket("localhost", port);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out.println(send);
			System.out.println(bufferedReader.readLine());
		}
		catch (IOException ex)
		{
			Logger.getLogger(TLSClient2.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}