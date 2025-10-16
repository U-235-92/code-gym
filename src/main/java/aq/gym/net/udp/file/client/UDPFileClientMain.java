package aq.gym.net.udp.file.client;

import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPFileClientMain {

	public static void main(String[] args) throws UnknownHostException, SocketException {
		UDPFileClient client = new UDPFileClient();
		client.sendFile();
	}
}
