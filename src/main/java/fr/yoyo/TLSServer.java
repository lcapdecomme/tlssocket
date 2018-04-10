package fr.yoyo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocketFactory;

import fr.yoyo.utils.KeyUtils;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class TLSServer
{
	private static final int PORT = 8000;

	public static void main(String[] args) throws Exception
	{
		KeyUtils.installCustomKeyStore("keys/serveur.jks", "changeit", "keys/trustserveur.jks", "changeit");
		
		final SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();

		ServerSocket sslServerSocket = sslServerSocketFactory.createServerSocket(PORT);
		System.out.println("SSL ServerSocket started");
		System.out.println(sslServerSocket.toString());

		while (true)
		{
			Socket socket = sslServerSocket.accept();
			System.out.println("ServerSocket accepted");

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			System.out.println("before readLine()");
			String line = bufferedReader.readLine();
			System.out.println(line);
			out.println("Salut " + line);
		}
	}
}
