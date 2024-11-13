package aq.gym.collections.practise;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class PairSumSetNumbers {

	public static void main(String[] args) {
		LinkedHashSet<Integer> numbers = IntStream
				.rangeClosed(1, 8)
				.collect(LinkedHashSet::new, LinkedHashSet::add, LinkedHashSet::addAll);
		sum(numbers);
	}
	
	private static void sum(Set<Integer> numbers) {
		System.out.println("Orig: " + numbers);
		while(numbers.size() != 1) {
			Iterator<Integer> numbersIterator = numbers.iterator();
			Set<Integer> numbersTemporalBuffer = new LinkedHashSet<Integer>();
			while(numbersIterator.hasNext()) {
				int first = getNumberAndRemoveOne(numbersIterator);
				if(numbersIterator.hasNext()) {
					int second = getNumberAndRemoveOne(numbersIterator);
					int sum = first + second;
					numbersTemporalBuffer.add(sum);
				} else {
					numbersTemporalBuffer.add(first);
				}
			}
			numbers.addAll(numbersTemporalBuffer);
			System.out.println("Tmp: " + numbers);
		}
		System.out.println("Result: " + numbers);
	}
	
	private static int getNumberAndRemoveOne(Iterator<Integer> iterator) {
		int num = iterator.next();
		iterator.remove();
		return num;
	}
}
