package aq.gym.brokers.kafka.stream;

public class MainStreamer {

	public static void main(String[] args) {
		Streamer streamer = new Streamer();
		streamer.doToUpperCaseMessagesStream();
//		streamer.showKTableContent();
	}
}
