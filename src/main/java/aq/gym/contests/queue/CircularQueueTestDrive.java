package aq.gym.contests.queue;

@SuppressWarnings("unused")
public class CircularQueueTestDrive {

	public static void main(String[] args) {
		test5();
	}
	
	private static void test1() {
		MyCircularQueue queue = new MyCircularQueue(3);
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.deQueue();
		queue.enQueue(4);
		queue.deQueue();
		queue.enQueue(5);
		queue.deQueue();
		queue.deQueue();
		queue.deQueue();
		System.out.println(queue);
		System.out.println("Front: " + queue.front());
		System.out.println("Rear: " + queue.rear());
		System.out.println("Is queue full? " + queue.isFull());
		System.out.println("Is queue empty? " + queue.isEmpty());
	}
	
	private static void test2() {
		MyCircularQueue queue = new MyCircularQueue(81);
		queue.enQueue(69);
		queue.deQueue();
		queue.enQueue(92);
		queue.enQueue(12);
		queue.deQueue();
		queue.isFull();
		queue.isFull();
		System.out.println(queue.front());
	}
	
	private static void test3() {
		MyCircularQueue queue = new MyCircularQueue(3);
		System.out.println(queue.deQueue());
		System.out.println(queue.front());
		System.out.println(queue.rear());
		System.out.println(queue.isEmpty());
		System.out.println(queue.isFull());
	}
	
	private static void test4() {
		MyCircularQueue queue = new MyCircularQueue(3);
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(5);
		queue.deQueue();
		System.out.println(queue.front());
		System.out.println(queue.rear());
		System.out.println(queue.isEmpty());
		System.out.println(queue.isFull());
	}
	
	private static void test5() {
		MyCircularQueue queue = new MyCircularQueue(1);
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		System.out.println(queue.front());
		System.out.println(queue.isFull());
		queue.deQueue();
		queue.enQueue(4);
		System.out.println(queue.front());
	}
}
