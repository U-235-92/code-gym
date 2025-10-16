package aq.gym.net.udp.file.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPFileServer {

private final static String SERVER_MSG_HEADER = "[Server message]: ";
	
	private DatagramPacket datagramPacket;
	private DatagramSocket datagramSocket;
	private byte[] buff;
	private int buffSize;
	
	public UDPFileServer() throws UnknownHostException, SocketException {
		buffSize = 256;
		buff = new byte[256];
		datagramPacket = new DatagramPacket(buff, buffSize);
		datagramSocket = new DatagramSocket(5082);
		datagramSocket.setSoTimeout(10000);
	}
	
	public void receiveFile() throws FileNotFoundException, IOException {
		System.out.println(SERVER_MSG_HEADER + "start receive a file");
		try(FileOutputStream fos = new FileOutputStream(new File("src/main/java/aq/gym/net/udp/file/server/alaskaReceive.jpg"))) {
			while(true) {
				datagramSocket.receive(datagramPacket);
				if("END".equals(new String(buff, 0, 3))) {
					break;
				}
				fos.write(buff);
				fos.flush();
			}
		}
		System.out.println(SERVER_MSG_HEADER + "end receive a file");
	}
}
