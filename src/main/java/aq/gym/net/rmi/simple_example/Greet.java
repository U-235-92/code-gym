package aq.gym.net.rmi.simple_example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Greet extends Remote {

	public static final String REMOTE_NAME = "rmi://localhost:5082/greet";
	
	public int greet(String name) throws RemoteException;
}
