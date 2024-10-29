package aq.gym.algorithms_and_structures.recursion.letter_in_box;

public class Person {

	public void findLetter(Box box) {
		System.out.println("I took the box: " + box);
		if(box.getLetter() != null) {
			System.out.println("I found a letter: " + box.getLetter() + " inside box " + box);
			return;
		}
		System.out.print("There is no letter! ");
		for(Box innerBox : box.getBoxes()) {
			System.out.println("Let's try to find inside: " + innerBox);
			findLetter(innerBox);
		}
	}
}
