package aq.gym.net.echo_udp_client_server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		DatagramSocket serverDatagramSocket = new DatagramSocket(5082, InetAddress.getByName("localhost"));
		System.out.println("Server started!");
		while(true) {
			byte[] buff = new byte[256];
			DatagramPacket packet = new DatagramPacket(buff, buff.length);
			serverDatagramSocket.receive(packet);
			String data = new String(packet.getData());
			System.out.println("Server received a message: " + data);
			String responseData = "[Server echo]: " + data;
			packet.setData(responseData.getBytes());
			serverDatagramSocket.send(packet);
		}
	}
}
