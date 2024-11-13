package aq.gym.thread.subway;

import java.util.concurrent.Semaphore;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Route {

	@NonNull
	private String name;
	private Semaphore semaphore = new Semaphore(1);
	
	public boolean isAblePassTrain() {
		return semaphore.tryAcquire();
	}
	
	public void setFree() {
		semaphore.release();
	}
}
