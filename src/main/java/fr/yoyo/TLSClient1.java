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
public class TLSClient1
{
	private static final int PORT = 8000;

	private static final String SEND = "client1";

	public static void main(String[] args) throws Exception
	{
		KeyUtils.installCustomKeyStore("keys/client1.jks", "changeit", "keys/trustclient1.jks", "changeit");

		SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		Socket socket = sslSocketFactory.createSocket("localhost", PORT);
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out.println(SEND);
		System.out.println(bufferedReader.readLine());
	}

}
