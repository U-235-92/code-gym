package aq.gym.net.rmi.simple_example.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeUnit;

import aq.gym.net.rmi.simple_example.Greet;

public class GreetImpl extends UnicastRemoteObject implements Greet {
	
	private static final long serialVersionUID = 1L;

	protected GreetImpl() throws RemoteException {
		super();
	}

	@Override
	public int greet(String name) {
		System.out.println("Invoke of RMI method...");
		sleep(2);
		System.out.println("Hello, " + name + " from RMI!");
		return 42;
	}

	private void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
