package aq.gym.patterns.builder;

public class BuilderTestDrive {

	public static void main(String[] args) {
		Car lamborghini = Car.builder()
				.mark("Lamborghini")
				.model("LP 560-4 Gallardo")
				.color("Yellow")
				.numSeats(2)
				.topSeed(325)
				.leftSteeringWheel(true)
				.build();
		System.out.println(lamborghini);
	}
}
