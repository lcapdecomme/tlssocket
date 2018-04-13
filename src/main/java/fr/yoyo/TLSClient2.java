package fr.yoyo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

import fr.yoyo.utils.KeyUtils;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class TLSClient2 {

	private static final int PORT = 8000;

	private static final String SEND = "client2";

	public static void main(String[] args) throws Exception
	{
		KeyUtils.installCustomKeyStore("keys/client2.jks", "changeit", "keys/trustclient2.jks", "changeit");
		
		SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		Socket socket = sslSocketFactory.createSocket("localhost", PORT);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out.println(SEND);
		System.out.println(bufferedReader.readLine());
	}
}