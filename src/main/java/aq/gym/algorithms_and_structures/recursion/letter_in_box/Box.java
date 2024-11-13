package aq.gym.algorithms_and_structures.recursion.letter_in_box;

import java.util.List;

public class Box {

	private List<Box> boxes = List.of();
	private Letter letter;
	private String name;
	
	public Box(String name) {
		super();
		this.name = name;
	}

	public Box(String name, List<Box> boxes) {
		super();
		this.boxes = boxes;
		this.name = name;
	}

	public Box(String name, List<Box> boxes, Letter letter) {
		super();
		this.boxes = boxes;
		this.letter = letter;
		this.name = name;
	}

	public Box(String name, Letter letter) {
		super();
		this.letter = letter;
		this.name = name;
	}

	public void setBoxes(List<Box> boxes) {
		this.boxes = boxes;
	}

	public void setLetter(Letter letter) {
		this.letter = letter;
	}

	public List<Box> getBoxes() {
		return boxes;
	}

	public Letter getLetter() {
		return letter;
	}

	@Override
	public String toString() {
		return name;
	}
}
