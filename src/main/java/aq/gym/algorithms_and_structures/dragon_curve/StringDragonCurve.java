package aq.gym.algorithms_and_structures.dragon_curve;

public class StringDragonCurve {

	private final static String F = "F"; //move FORWARD
	private final static String R = "R"; //turn RIGHT
	private final static String L = "L"; //turn LEFT
	private String dragonCurve = F;
	
//	DragonCurve = (n - 1) + L + [reverse](n - 1)
//	What is reverse? For example you have the second iteration FLF
//	You have to:
//	1. Take previous value of DragonCurve
//	2. Append at (1) L
//	3. Replace the middle element of (1). L to R or R to L (FRF as result if previous was FLF) 
//	   and append this one at the end of DragonCurve.
	public String makeStringDragonCurve(int ratio) {
		while(ratio-- > 0) { //Just for fun. If you erase decrement, you will get out of memory error (=
			String middle = getMiddleLetter(dragonCurve);
			String reverse = dragonCurve;
			if(middle.equals(L)) {				
				reverse = makeReverse(R);
			} else if(middle.equals(R)) {				
				reverse = makeReverse(L);
			}
//			System.out.println(dragonCurve); //Uncomment to see intermediate result
			dragonCurve = dragonCurve + L + reverse;
		}
		return dragonCurve;
	}
	
	private String getMiddleLetter(String dragonCurve) {
		return dragonCurve.substring((dragonCurve.length() / 2), (dragonCurve.length() / 2) + 1);
	}
	
	private String makeReverse(String turn) {
		return dragonCurve.substring(0, dragonCurve.length() / 2) + turn + dragonCurve.substring(dragonCurve.length() / 2 + 1);
	}
}
