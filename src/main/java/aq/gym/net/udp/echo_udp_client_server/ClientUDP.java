package aq.gym.net.udp.echo_udp_client_server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Scanner;

public class ClientUDP {

	public static void main(String[] args) throws IOException {
		DatagramSocket clientDatagramSocket = new DatagramSocket();
		InetAddress clientInetAddress = InetAddress.getByName("localhost");
		System.out.println("Client started!");
		System.out.println("Write a line:");
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String line = null;
			if(scanner.hasNextLine()) {
				line = scanner.nextLine();
				if(line.equals("end"))
					break;
			}
			int buffSize = 256;
			byte[] sendingBuffer = Arrays.copyOf(line.getBytes(), buffSize);
			int port = 5082;
			DatagramPacket sendDatagramPacket = new DatagramPacket(sendingBuffer, sendingBuffer.length, clientInetAddress, port);
			clientDatagramSocket.send(sendDatagramPacket);
			byte[] receiveBuffer = new byte[buffSize];
			DatagramPacket receiveDatagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
			clientDatagramSocket.receive(receiveDatagramPacket);
			System.out.println(new String(receiveDatagramPacket.getData()));
		}
		clientDatagramSocket.close();
		scanner.close();
	}
}
