package aq.gym.patterns.singleton;

public class Sun {

	private static volatile Sun sun;
//	private static volatile Sun sun = new Sun(); //Or make an object when class loads
	
	private Sun() {}
	
	public static synchronized Sun getInstance() {
		if(sun == null) {
			sun = new Sun();
		}
		return sun;
	}
}
