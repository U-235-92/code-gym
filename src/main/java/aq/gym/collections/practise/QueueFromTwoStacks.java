package aq.gym.collections.practise;

import java.util.Deque;
import java.util.LinkedList;

public class QueueFromTwoStacks {

	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		String c = "c";
		TrickyQueue tq = new TrickyQueue();
		tq.add(a);
		tq.add(b);
		tq.add(c);
		while(tq.size() > 0) {
			System.out.println(tq.remove());
		}
	}
	
	private static class TrickyQueue {
		
		private Deque<String> mainStack = new LinkedList<String>();
		private Deque<String> bufferStack = new LinkedList<String>();
		
		public void add(String str) {
			if(mainStack.size() > 0) {
				while(mainStack.size() > 0) {
					bufferStack.push(mainStack.pop());
				}
				mainStack.push(str);
				while(bufferStack.size() > 0) {
					mainStack.push(bufferStack.pop());
				}
			} else {
				mainStack.push(str);
			}
		}
		
		public String remove() {
			return mainStack.pop();
		}
		
		public int size() {
			return mainStack.size();
		}
	}

}
