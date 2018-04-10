package fr.yoyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLServerSocketFactory;

/**
 * @web http://java-buddy.blogspot.com/
 */

public class TLSServer
{

	static final int port = 8000;

	public static void main(String[] args)
	{

		System.setProperty("javax.net.ssl.keyStore", "keys/serveur.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "changeit");

		System.setProperty("javax.net.ssl.trustStore", "keys/trustserveur.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");

		SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory
				.getDefault();

		try
		{
			ServerSocket sslServerSocket = sslServerSocketFactory.createServerSocket(port);
			System.out.println("SSL ServerSocket started");
			System.out.println(sslServerSocket.toString());

			while (true)
			{
				Socket socket = sslServerSocket.accept();
				System.out.println("ServerSocket accepted");

				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String line = bufferedReader.readLine();
				System.out.println(line);
				out.println("Salut " + line);
			}
		}
		catch (IOException ex)
		{
			Logger.getLogger(TLSServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
