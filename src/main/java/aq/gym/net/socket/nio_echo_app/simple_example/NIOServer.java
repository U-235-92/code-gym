package aq.gym.net.socket.nio_echo_app.simple_example;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static java.nio.channels.SelectionKey.*;

import java.io.IOException;

public class NIOServer {

	private static final int PORT = 5082; 
	private ServerSocketChannel serverChannel;
	private InetSocketAddress serverAddress;
	private Selector selector;
	
	public NIOServer() {
		try {
			selector = Selector.open();
			serverAddress = new InetSocketAddress(PORT);
			serverChannel = ServerSocketChannel.open();
			serverChannel.bind(serverAddress);
			serverChannel.configureBlocking(false);
			serverChannel.register(selector, OP_ACCEPT);
			System.out.println("Server started!");
		} catch (ClosedChannelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		while(true) {
			try {
				selector.select();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			iterator.forEachRemaining(key -> {
				try {
					if(key.isAcceptable()) {
						accept(key);
					} else if(key.isReadable()) {
						read(key);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iterator.remove();
			});
		}
	}

	private void accept(SelectionKey key) throws IOException {
		System.out.println("Client accepted!");
		SocketChannel channel = serverChannel.accept();
		channel.configureBlocking(false);
		channel.register(selector, OP_READ);
	}
	
	private void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buff = ByteBuffer.allocate(8);
		int read = channel.read(buff);
		if(read > 0) {
			buff.flip();
			String msg = new String(buff.array(), buff.position(), buff.remaining());
			System.out.println("[Recive message]: " + msg);
			String echo = new String("[Echo] " + msg);
			channel.write(ByteBuffer.wrap(echo.getBytes()));
		} else {
			channel.close();
		}
	}
}
