package aq.gym.thread.port;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Wharf> wharfs = List.of(new Wharf("A"), new Wharf("B"));
		List<Ship> ships = List.of(
				new Ship("Alpha"),
				new Ship("Beta"),
				new Ship("Gamma"),
				new Ship("Delta"),
				new Ship("Epsilon"),
				new Ship("Zeta"),
				new Ship("Eta"),
				new Ship("Theta"),
				new Ship("Iota"),
				new Ship("Kappa"),
				new Ship("Lambda"));
		Port port = new Port("Main port", wharfs);
		for(Ship ship : ships) {
			port.loadShip(ship);
		}
	}
}
