package aq.gym.net.tcp.file.client;

import java.io.IOException;

public class TCPFileClientMain {

	public static void main(String[] args) throws IOException {
		TCPFileClient client = new TCPFileClient();
		client.sendFile();
	}

}
