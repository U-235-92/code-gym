package aq.gym.net.rmi.simple_example.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import aq.gym.net.rmi.simple_example.Greet;

public class ClientRmi {

	public static void main(String[] args) {
		try {
			Greet greet = (Greet) Naming.lookup(Greet.REMOTE_NAME);
			int result = greet.greet("Bob");
			System.out.println("Bob got result of RMI: " + result);
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
