package aq.gym.fun.chaos_population;

public class Chaos {

	public static void main(String[] args) {
		testPopulation(0.05, 10000, 3.3);
	}

	private static void testPopulation(double populationNumber, int timeObserve, double fertility) {
		boolean isMaxPopulation = populationNumber >= 1.0;
		boolean isExinctPopulation = populationNumber <= 0.0;
		boolean isOptimalPopulation = populationNumber == 1.0 - (1.0 / fertility);
		for(int t = 0; t <= timeObserve; t++) {
			populationNumber = fertility * populationNumber * (1 - populationNumber);
			System.out.println(populationNumber);
			isMaxPopulation = populationNumber >= 1.0;
			isExinctPopulation = populationNumber <= 0.0;
			isOptimalPopulation = populationNumber == 1.0 - (1.0 / fertility);
			if(isExinctPopulation) {
				System.out.println("Population is extinct");
				return;
			} else if(isMaxPopulation) {
				System.out.println("Population is maximal");
				return;
			}
		}
		if(isOptimalPopulation){
			System.out.println("Optimal population number: " + populationNumber);
		} else {
			System.out.println("Population number: " + populationNumber);
		}
	}
}
