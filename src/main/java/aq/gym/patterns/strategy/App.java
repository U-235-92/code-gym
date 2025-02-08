package aq.gym.patterns.strategy;

public class App {

	public static void main(String[] args) {
		OpenBehaviour kick = new KickOpen();
		OpenBehaviour  calm = new CalmOpen();
		Door door = new Door(kick);
		door.open();
		door.setOpenBehaviour(calm);
		door.open();
	}
}
