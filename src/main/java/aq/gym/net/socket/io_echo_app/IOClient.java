package aq.gym.net.socket.io_echo_app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IOClient {

	private static final String HOST = "localhost";
	private static final int PORT = 5082;
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private Scanner scanner;
	
	public IOClient() throws UnknownHostException, IOException {
		socket = new Socket(HOST, PORT);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		scanner = new Scanner(System.in);
		process();
	}

	private void process() {
		Thread thread = new Thread(() -> {
			while(!socket.isClosed()) {
				try {
					goWhileConnection();
				} catch (IOException e) {
					System.out.println("Connection was broke! Close connection y/n?");
					except();
				}
			}
		});
		thread.start();
	}
	
	private void goWhileConnection() throws IOException {
		System.out.println("Please, write message:");
		String message = scanner.nextLine();
		output.writeUTF(message);
		message = input.readUTF();
		System.out.println(message);
	}
	
	private void except() {
		String message = scanner.nextLine();
		if(message.toLowerCase().contains("y")) {
			try {
				socket.close();
				scanner.close();
				System.out.println("Connection was close!");
			} catch (IOException e) {
				System.out.println("Connection was close!");
			}
		} 
	}
}
