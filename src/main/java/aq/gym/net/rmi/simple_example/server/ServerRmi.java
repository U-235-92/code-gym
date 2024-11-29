package aq.gym.net.rmi.simple_example.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import aq.gym.net.rmi.simple_example.Greet;

public class ServerRmi {

	public static void main(String[] args) {
		try {
			int port = 5082;
			LocateRegistry.createRegistry(port); // start rmiregistry
			Greet greet = new GreetImpl();
			Naming.rebind(Greet.REMOTE_NAME , greet);
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
