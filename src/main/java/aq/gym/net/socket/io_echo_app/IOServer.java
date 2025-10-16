package aq.gym.net.socket.io_echo_app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {

	private static int countConnection;
	private static final int PORT = 5082;
	private ServerSocket serverSocket;
	
	public IOServer() throws IOException {
		serverSocket = new ServerSocket(PORT);
	}
	
	public void start() throws IOException {
		System.out.println("Start server!");
		while(true) {
			System.out.println("Wait for connection");
			Socket socket = serverSocket.accept();
			System.out.printf("Client %d connected!%n", ++countConnection);
			Handler handler = new Handler(socket);
			handler.handle();
		}
	}
	
	private class Handler {
		
		private Socket socket;
		private DataInputStream input;
		private DataOutputStream output;
		
		public Handler(Socket socket) throws IOException {
			this.socket = socket;
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		}
		
		private void handle() {
			Thread thread = new Thread(() -> {
				while(!socket.isClosed()) {
					try {
						processConection();
					} catch (IOException e) {
						System.out.println("Client connection was broke!");
						break;
					}
				}
			});
			thread.start();
		}
		
		private void processConection() throws IOException {
			String message = input.readUTF();
			if(message.equals("exit")) {
				System.out.println("Client disconnected!");
				socket.close();
			} else {
				System.out.println("[CLIENT MESSAGE]: " + message);
				output.writeUTF("[SERVER ECHO]: " + message);
			}
		}
	}
}
