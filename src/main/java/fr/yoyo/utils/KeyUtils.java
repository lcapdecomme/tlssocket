package fr.yoyo.utils;

public class KeyUtils 
{
	public static final void installCustomKeyStore(String keyStorePath, String passwordKeyStore, String trustStorePath, String passwordTrustStore)
	{
		System.setProperty("javax.net.ssl.keyStore", keyStorePath);
		System.setProperty("javax.net.ssl.keyStorePassword", passwordKeyStore);

		System.setProperty("javax.net.ssl.trustStore", trustStorePath);
		System.setProperty("javax.net.ssl.trustStorePassword", passwordTrustStore);
	}
}
