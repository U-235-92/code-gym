package aq.gym.net.udp.file.server;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPFileServerMain {

	public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
		UDPFileServer server = new UDPFileServer();
		server.receiveFile();
	}

}
