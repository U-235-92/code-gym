package aq.gym.net.socket.nio_echo_app.better_example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class NIOServer {

	private static final int BUFFER_SIZE = 256;
	private static final int PORT = 5082;
	private ServerSocketChannel serverChannel;
	private Selector selector;
	private InetSocketAddress serverAddress;
	private Map<SocketChannel, ByteBuffer> mapClientChannelToBuffer;
	
	public NIOServer() {
		serverAddress = new InetSocketAddress(PORT);
		try {
			selector = Selector.open();
			serverChannel = ServerSocketChannel.open();
			serverChannel.bind(serverAddress);
			serverChannel.configureBlocking(false);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			mapClientChannelToBuffer = new HashMap<>();
			System.out.println("Server started!");
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		while(true) {
			try {
				selector.select();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			iterator.forEachRemaining(key -> {
				try {
					if(key.isAcceptable()) {
						accept(key);
					} else if(key.isReadable()) {
						read(key);
					} else if(key.isWritable()) {
						write(key);
					}
				} catch (IOException e) {
					handleResetConnectionClient(key);
				}
				iterator.remove();
			});
		}
	}
	
	private void accept(SelectionKey key) throws IOException {
		System.out.println("New client connected!");
		SocketChannel channel = serverChannel.accept();
		channel.configureBlocking(false);
		channel.register(selector, SelectionKey.OP_READ);
		mapClientChannelToBuffer.put(channel, ByteBuffer.allocate(BUFFER_SIZE));
	}
	
	private void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buff = mapClientChannelToBuffer.get(channel);
		try {
			int bytesRead = channel.read(buff);
			if(bytesRead == -1) {
				mapClientChannelToBuffer.remove(channel);
				channel.close();
			} else {
				buff.flip();
				String msg = new String(buff.array(), buff.position(), buff.remaining()).trim();
				System.out.printf("[Bytes read = %d; Client msg]: %s%n", bytesRead, msg);
				if(bytesRead == msg.getBytes().length) {
//					buff.clear();
				} else {
					buff.compact();
				}
				channel.register(selector, SelectionKey.OP_WRITE);
			}
		} catch (IOException e) {
			handleResetConnectionClient(key);
		} 
	}
	
	private void write(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buff = mapClientChannelToBuffer.get(channel);
		String dataBuff = new String(buff.array(), buff.position(), buff.remaining()).trim();
		String prefix = "[Echo] ";
		String msg = prefix + dataBuff;
		buff.clear();
		buff.put(msg.getBytes());
		buff.flip();
		int bytesWritten = channel.write(buff);
		if(bytesWritten == msg.getBytes().length) {
			buff.clear();
		} else {
			buff.compact();
		}
		channel.register(selector, SelectionKey.OP_READ);
	}
	
	private void handleResetConnectionClient(SelectionKey key) {
		System.out.println("Client quit!");
		try {
			SocketChannel channel = (SocketChannel) key.channel();
			mapClientChannelToBuffer.remove(channel);
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
