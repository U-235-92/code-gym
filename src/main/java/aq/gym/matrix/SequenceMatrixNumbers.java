package aq.gym.matrix;

import java.util.Arrays;

public class SequenceMatrixNumbers {

	public static void main(String[] args) {
		printMaxAscNumberSequence();
	}

	private static void printMaxAscNumberSequence() {
		int[][] matrix = { { 1, 1, 3, 4 }, { 1, 5, 6, 6 }, { 7, 8, 9, 10 }, { 11, 4, 5, 4 } };
		int[] array = new int[matrix.length * matrix[0].length];
		fillArray(array, matrix);
		lookForMaxAscNumberSequence(array);
	}

	private static void fillArray(int[] array, int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.arraycopy(matrix[i], 0, array, i * matrix[i].length, matrix[i].length);
		}
	}

	private static void lookForMaxAscNumberSequence(int[] array) {
		int maxSequenceLength = Integer.MIN_VALUE;
		int sequenceLength = 1;
		int startIndexOfSequence = 0;
		int[] sequence = null;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] < array[i + 1]) {
				sequenceLength++;
			} else {
				if (maxSequenceLength < sequenceLength) {
					startIndexOfSequence = i + 1 - sequenceLength;
					maxSequenceLength = sequenceLength;
					sequence = new int[sequenceLength];
					System.arraycopy(array, startIndexOfSequence, sequence, 0, sequenceLength);
					sequenceLength = 1;
				} else {
					sequenceLength = 1;
				}
			}
		}
		System.out.println("Max ASC sequence of numbers: " + Arrays.toString(sequence));
	}

}
