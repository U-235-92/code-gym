package aq.gym.collections.practise;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class NumberRemover {

	public static void main(String[] args) {
		removeUntilOneNumberRemainArrayList(1, 100_000);
		removeUntilOneNumberRemainLinkedList(1, 100_000);
	}

	private static void removeUntilOneNumberRemainLinkedList(int from, int to) {
		LinkedList<Integer> numbers = IntStream.rangeClosed(from, to).collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
		Instant start = Instant.now();
		while(numbers.size() != 1) {
			for(int i = 0; i < numbers.size(); i += 2) {
				numbers.remove(i);
			}
		}
		Instant finish = Instant.now();
		long workTimeSeconds = Duration.between(start, finish).toSeconds();
		System.out.println("[LinkedList] One element remains: " + numbers + " Work time: " + workTimeSeconds + " seconds");
	}

	private static void removeUntilOneNumberRemainArrayList(int from, int to) {
		ArrayList<Integer> numbers = IntStream.rangeClosed(from, to).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		Instant start = Instant.now();
		while(numbers.size() != 1) {
			for(int i = 0; i < numbers.size(); i += 2) {
				numbers.remove(i);
			}
		}
		Instant finish = Instant.now();
		long workTimeSeconds = Duration.between(start, finish).toSeconds();
		System.out.println("[ArrayList] One element remains: " + numbers + " Work time: " + workTimeSeconds + " seconds");
	}
}
