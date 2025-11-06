package aq.gym.contests.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {

//	https://leetcode.com/problems/find-median-from-data-stream
	public static void main(String[] args) {
		MedianFinder mf = new MedianFinder();
		mf.addNum(2);
		System.out.println(mf.findMedian());
		mf.addNum(6);
		System.out.println(mf.findMedian());
		mf.addNum(10);
		System.out.println(mf.findMedian());
		mf.addNum(12);
		System.out.println(mf.findMedian());
//		int limit = 50000, max = 100000, min = -100000;
//		for(int i = 0; i < limit; i++) {
//			mf.addNum(min + (int) (Math.random() * (max - min) + 1));
//			mf.findMedian();
//			System.out.println(mf.findMedian());
//			double probability = Math.random();
//			if(probability > 0.625) {
//				System.out.println(mf.findMedian());
//			}
//		}
//		System.out.println(mf.findMedian());
	}

	private static class MedianFinder {

	    private PriorityQueue<Integer> reverseOrderedPQ; 
	    private PriorityQueue<Integer> naturalOrderedPQ; 

	    public MedianFinder() {
	        reverseOrderedPQ = new PriorityQueue<>(Comparator.reverseOrder());
	        naturalOrderedPQ = new PriorityQueue<>();
	    }
	    
	    public void addNum(int num) {
	        reverseOrderedPQ.offer(num);
	        naturalOrderedPQ.offer(reverseOrderedPQ.poll());
	        if (naturalOrderedPQ.size() > reverseOrderedPQ.size()) {
	            reverseOrderedPQ.offer(naturalOrderedPQ.poll());
	        }
	    }
	    
	    public double findMedian() {
	        if (reverseOrderedPQ.size() > naturalOrderedPQ.size()) {
	            return reverseOrderedPQ.peek();
	        } else {
	            return (reverseOrderedPQ.peek() + naturalOrderedPQ.peek()) / 2.0;
	        }
	    }
	}
}
