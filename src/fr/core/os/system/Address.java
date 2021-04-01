package fr.core.os.system;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Address {

	public static String getAddress() {
		String nomHote;
        String adresseIPLocale;
        String adresse = null;

        try{
           InetAddress inetadr = InetAddress.getLocalHost();
           //nom de machine
           nomHote = (String) inetadr.getHostName();
           //adresse ip sur le réseau
           adresseIPLocale = (String) inetadr.getHostAddress();
           adresse = "Nom de la machine: "+nomHote+"\nAdresse IP: "+adresseIPLocale;
           
        } catch (UnknownHostException e) {
               e.printStackTrace();
        }
		return adresse;
	}
	
}
