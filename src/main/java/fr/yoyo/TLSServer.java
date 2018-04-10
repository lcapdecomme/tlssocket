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
	private static final int PORT = 8000;

	public static void main(String[] args) throws Exception
	{
		// on définit le key store et trustu store pour le serveur
		defineCustomKeyStoreAndTrustStoreForServer();

		final SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory
				.getDefault();

		ServerSocket sslServerSocket = sslServerSocketFactory.createServerSocket(PORT);
		System.out.println("SSL ServerSocket started");
		System.out.println(sslServerSocket.toString());

		while (true)
		{
			Socket socket = sslServerSocket.accept();
			System.out.println("ServerSocket accepted");

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			System.out.println("before readLine()");
			String line = bufferedReader.readLine();
			System.out.println(line);
			out.println("Salut " + line);
		}

	}

	private static void defineCustomKeyStoreAndTrustStoreForServer()
	{
		System.setProperty("javax.net.ssl.keyStore", "keys/serveur.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
		System.setProperty("javax.net.ssl.trustStore", "keys/trustserveur.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	}
}
