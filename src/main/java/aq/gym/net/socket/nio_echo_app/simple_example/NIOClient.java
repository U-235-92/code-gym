package aq.gym.net.socket.nio_echo_app.simple_example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOClient {

	private static final int PORT = 5082;
	private static final String HOST = "localhost";
	private SocketChannel channel;
	private InetSocketAddress address;
	private Scanner scanner;
	
	public NIOClient() {
		try {
			scanner = new Scanner(System.in);
			address = new InetSocketAddress(HOST, PORT);
			channel = SocketChannel.open(address);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		listenToConsole();
		while(true) {
			ByteBuffer buff = ByteBuffer.allocate(256);
			int read = -1;
			try {
				read = channel.read(buff);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(read > 0) {
				buff.flip();
				String msg = new String(buff.array(), buff.position(), buff.remaining());
				System.out.println(msg);
			}
		}
	}
	
	private void listenToConsole() {
		ExecutorService executor = Executors.newSingleThreadExecutor(run -> {
			Thread thread = new Thread(run);
			thread.setDaemon(true);
			return thread;
		});
		executor.submit(() -> {
			while(true) {			
				System.out.println("Write a message:");
				String msg = scanner.nextLine();
				ByteBuffer buff = ByteBuffer.wrap(msg.getBytes());
				channel.write(buff);
			}
		});
	}
}
