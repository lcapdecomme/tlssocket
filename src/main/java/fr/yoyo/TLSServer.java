package fr.yoyo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Security;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509KeyManager;

import fr.yoyo.keymanager.AliasSelectorKeyManager;
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
		
		KeyManager[] keyManagers = KeyUtils.getInitialKeyManagers("keys/serveur.jks", "changeit");
        TrustManager[] trustManagers = KeyUtils.getInitialTrustManagers("keys/trustserveur.jks", "changeit");
     
     
        //For each key manager, check if it is a X509KeyManager (because we will override its       //functionality
        for (int i = 0; i < keyManagers.length; i++) 
        {
            if (keyManagers[i] instanceof X509KeyManager) 
            {
                keyManagers[i] = new AliasSelectorKeyManager((X509KeyManager)keyManagers[i], "serveur");
            }
        }
		
        SSLContext context = SSLContext.getInstance("SSL");
        context.init(keyManagers, trustManagers, null);
		
        final SSLServerSocket sslServerSocket = (SSLServerSocket)context.getServerSocketFactory().createServerSocket(PORT);
        sslServerSocket.setNeedClientAuth(true);
        
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
