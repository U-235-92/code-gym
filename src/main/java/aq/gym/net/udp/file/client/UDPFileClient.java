package aq.gym.net.udp.file.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UDPFileClient {

	private final static String CLIENT_MSG_HEADER = "[Client message]: ";

	private InetAddress inetAddress;
	private DatagramPacket datagramPacket;
	private DatagramSocket datagramSocket;
	private byte[] buff;
	private int buffSize;

	public UDPFileClient() throws UnknownHostException, SocketException {
		buffSize = 256;
		buff = new byte[256];
		inetAddress = InetAddress.getLocalHost();
		datagramPacket = new DatagramPacket(buff, buffSize, inetAddress, 5082);
		datagramSocket = new DatagramSocket();
	}

	public void sendFile() {
		System.out.println(CLIENT_MSG_HEADER + "start send a file");
		try (BufferedInputStream bis = new BufferedInputStream(
				Files.newInputStream(Paths.get("src/main/java/aq/gym/net/udp/file/client/alaska.jpg")))) {
			while (bis.read(buff) != -1) {
				datagramSocket.send(datagramPacket);
			}
			byte[] endBytes = "END".getBytes();
			datagramPacket = new DatagramPacket(endBytes, endBytes.length, inetAddress, 5082);
			datagramSocket.send(datagramPacket);
			System.out.println(CLIENT_MSG_HEADER + "end send a file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
