package aq.gym.algorithms_and_structures.compressors.huffman;

public class Main {

	public static void main(String[] args) {
		Huffman huffman = new Huffman();
		String text = "Hello, world of bees!";
		String encode = huffman.encode(text);
		String decode = huffman.decode(encode);
		System.out.println("Orig text: " + text);
		System.out.println("Encode text: " + encode);
		System.out.println("Decode text: " + decode);
	}

}
