package aq.gym.net.echo_tcp_client_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {

	public static void main(String[] args) throws IOException {
		int port = 5082;
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Server started!");
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("Connected new client!");
			Thread thread = new Thread(() -> {
				while(true) {
					try {
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						String msg = dis.readUTF();
						System.out.println("Received: " + msg);
						msg = "[Echo]: " + msg;
						dos.writeUTF(msg);
					} catch (IOException e) {
						System.out.println("Client disconnected!");
						try {
							socket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						break;
					}
				}
			});
			thread.start();
		}
	}
}
