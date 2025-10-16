package aq.gym.net.socket.nio_echo_app.better_example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOClient {

	private static final String HOST = "localhost";
	private static final int PORT = 5082;
	private static final int BUFFER_SIZE = 256;
	private SocketChannel socketChannel;
	private InetSocketAddress connectionAddress;
	private Selector selector;
	private ByteBuffer buff;
	private ExecutorService executor;
	private Scanner scanner;
	
	public NIOClient() {
		connectionAddress = new InetSocketAddress(HOST, PORT);
		try {
			socketChannel = SocketChannel.open(connectionAddress);
			socketChannel.configureBlocking(false);
			selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_WRITE);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		buff = ByteBuffer.allocate(BUFFER_SIZE);		
	}
	
	public void start() {
		listenToConsole();
		while(true) {
			try {
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			iterator.forEachRemaining(key -> {
				try {
					if(key.isReadable()) {
						read(key);
					}
				} catch (IOException e) {
					handleResetConnection();
				}
				iterator.remove();
			});
		}
	}

	private void listenToConsole() {
		executor = Executors.newSingleThreadExecutor(run -> {
			Thread thread = new Thread(run);
			thread.setDaemon(true);
			return thread;
		});
		executor.submit(() -> {
			while(true) {				
				scanner = new Scanner(System.in);
				System.out.println("Please, write a message:");
				String msg = scanner.nextLine();
				buff.put(msg.getBytes());
				buff.flip();
				int bytesWritten = 0;
				try {
					bytesWritten = socketChannel.write(buff);
					if(bytesWritten == msg.getBytes().length) {
						System.out.println("[Written a full message]: " + msg);
						buff.clear();
					} else {
						System.out.println("[Written a partial message]: " + msg);
						buff.compact();
					}
					socketChannel.register(selector, SelectionKey.OP_READ);
				} catch (IOException e) {
					handleResetConnection();
				}
			}
		});
	}
	
	private void read(SelectionKey key) throws IOException {
		int bytesRead = socketChannel.read(buff);
		buff.flip();
		String msg = new String(buff.array(), buff.position(), buff.remaining());
		System.out.println(msg);
		if(bytesRead == msg.getBytes().length) {
			buff.clear();
		} else {
			buff.compact();
		}
		socketChannel.register(selector, SelectionKey.OP_WRITE);
	}
	
	private void handleResetConnection() {
		try {
			socketChannel.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(true) {			
			System.out.println("Connection was reset");
			System.out.println("Do you want to continue: y/n?");
			String answer = scanner.nextLine();
			if(answer.toLowerCase().equals("n")) {
				scanner.close();
				executor.shutdownNow();
				System.exit(0);
			}
		}
	}
}
