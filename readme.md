ETAPE 1  

client 1 :
keystore : client1.jks contient seulement sa clé privée et son certificat (alias client1)
truststore trustclient1.jks (contient le certificat de la clé privée du serveur) (alias serveur)

client 2 :
keystore : client2.jks contient seulement sa clé privée et son certificat (alias client2)
truststore trustclient2.jks (contient le certificat de la clé privée du serveur) (alias serveur)

serveur :
keystore : serveur.jks contient seulement sa clé privée  (alias serveur)
truststore trustserveur.jks (contient les deux certificats des clés privées des deux clients)

Test : Tout est ok :-)


ETAPE 2

Maintenant, ajout dans le keystore du serveur de cette fameuse clef privée p12
keystore : serveur.jks contient maintenant deux clés privées (alias **1** et _**serveur**_)

Test : Rien ne marche :-(


ETAPE 3

Ajout du certificat de cette clef p12 dans le truststore du client 2
trustclient2.jks contient maintenant deux certificats : celui de la clef privée du serveur et celui de la p12 (alias serveur et serveurdiap)


Test : 	client 1 ne marche pas :-(    
	client 2 marche :-)



TA MISSION

Le serveur doit être capable de répondre aux deux clients :
soit en ouvrant deux ports d'écoute
soit en utilisant la bonne clé en fonction du client 


  


