package aq.gym.algorithms_and_structures.recursion.letter_in_box;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Letter letter = new Letter("I love you!");
		Person person = new Person();
		Box littleBox1 = new Box("Little box 1");
		Box littleBox2 = new Box("Little box 2", List.of(littleBox1));
		Box littleBox3 = new Box("Little box 3", List.of(littleBox2));
		Box littleBox4 = new Box("Little box 4", letter);
		Box middleBox1 = new Box("Middle box 1", List.of(littleBox3));
		Box middleBox2 = new Box("Middle box 2");
		Box middleBox3 = new Box("Middle box 3", List.of(littleBox4));
		Box bigBox = new Box("Big box", List.of(middleBox1, middleBox2, middleBox3));
		person.findLetter(bigBox);
	}

}
