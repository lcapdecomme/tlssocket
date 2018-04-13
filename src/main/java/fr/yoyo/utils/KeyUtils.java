package fr.yoyo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class KeyUtils 
{
	public static final void installCustomKeyStore(String keyStorePath, String passwordKeyStore, String trustStorePath, String passwordTrustStore)
	{
		System.setProperty("javax.net.ssl.keyStore", keyStorePath);
		System.setProperty("javax.net.ssl.keyStorePassword", passwordKeyStore);

		System.setProperty("javax.net.ssl.trustStore", trustStorePath);
		System.setProperty("javax.net.ssl.trustStorePassword", passwordTrustStore);
	}
	
	
	public static final KeyManager[] getInitialKeyManagers(String keyStorePath, String passwordKeyStore) throws IOException, GeneralSecurityException
	{
        //Init a key store with the given file.
        final KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        final KeyStore ks = KeyStore.getInstance("jks");
         
        try(final InputStream fis = new FileInputStream(new File(keyStorePath)))
        {
        	ks.load(fis, passwordKeyStore.toCharArray());
        }
 
        // Init the key manager factory with the loaded key store
        kmf.init(ks, passwordKeyStore.toCharArray());
 
        return kmf.getKeyManagers();
    }
	
	public static final TrustManager[] getInitialTrustManagers(String trustStorePath, String passwordTrustStore) throws IOException, GeneralSecurityException
    {
        final TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        final KeyStore ks = KeyStore.getInstance("jks");
        
        try(final InputStream fis = new FileInputStream(new File(trustStorePath)))
        {
        	ks.load(fis, passwordTrustStore.toCharArray());
        }
        
        tmf.init(ks);
        return tmf.getTrustManagers();
    }
}