package fr.yoyo.keymanager;

import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509KeyManager;

public class AliasSelectorKeyManager implements X509KeyManager
{
    private X509KeyManager sourceKeyManager = null;

    private String alias;
 
    public AliasSelectorKeyManager(X509KeyManager keyManager, String alias)
    {
        this.sourceKeyManager=keyManager;        
        this.alias = alias;
    }

    @Override
    public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket)
    {   
        if (true)
        {
        	return sourceKeyManager.chooseClientAlias(keyType, issuers, socket);
        }
        
        // ====================================================================
        // ====================================================================
        
        
    	boolean aliasFound = false;
 
        //Get all aliases from the key manager. If any matches with the managed alias,
        //then return it.
        //If the alias has not been found, return null (and let the API to handle it, 
        //causing the handshake to fail).
 
        for (int i = 0; i<keyType.length && !aliasFound; i++) 
        {
            final String[] validAliases = sourceKeyManager.getClientAliases(keyType[i], issuers);
            if (validAliases != null) 
            {
                for (final String validAlias : validAliases) 
                {
                	if (alias.equals(validAlias))
                	{
                		aliasFound = true;
                    	break;
                	}
                }
            }
        }
 
        return (aliasFound) ? alias : null;
    }
 
    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket)
    {
        if (false)
        {
        	return sourceKeyManager.chooseServerAlias(keyType, issuers, socket);
        }
        
        // ====================================================================
        // ====================================================================
        
    	return alias;
    }
 
    @Override
    public X509Certificate[] getCertificateChain(String alias)
    {
        return sourceKeyManager.getCertificateChain(alias);
    }
 
    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers)
    {
        return sourceKeyManager.getClientAliases(keyType, issuers);
    }
 
    @Override
    public PrivateKey getPrivateKey(String alias)
    {
        return sourceKeyManager.getPrivateKey(alias);
    }
 
    @Override
    public String[] getServerAliases(String keyType, Principal[] issuers)
    {
        return sourceKeyManager.getServerAliases(keyType, issuers);
    }
}
