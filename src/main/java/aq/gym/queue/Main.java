package aq.gym.queue;

public class Main {

	public static void main(String[] args) {
		Queue queue = new Queue(3);
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);
		queue.remove();
		queue.insert(4);
		queue.remove();
		queue.remove();
		queue.remove();
		queue.insert(5);
		queue.remove();
		queue.remove();
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);
		queue.insert(4);
	}

}
