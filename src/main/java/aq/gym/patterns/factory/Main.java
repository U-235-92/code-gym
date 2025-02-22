package aq.gym.patterns.factory;

public class Main {

	public static void main(String[] args) {
		PlaneFactory airbusFactory = new AirbusFactory();
		Plane a380 = airbusFactory.makePlane(Model.A380);
		PlaneFactory boeingFactory = new BoeingFactory();
		Plane b777 = boeingFactory.makePlane(Model.B777);
		System.out.println(a380);
		System.out.println(b777);
	}

}
