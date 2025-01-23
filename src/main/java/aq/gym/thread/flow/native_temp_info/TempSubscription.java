package aq.gym.thread.flow.native_temp_info;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TempSubscription implements Subscription {

	private static final ExecutorService executor = Executors.newSingleThreadExecutor();
	
	private final Subscriber<? super TempInfo> subscriber;
	private final String town;
	
	@Override
	public void request(long n) {
		executor.submit(() -> {			
			for(long l = 0L; l < n; l++) {
				try {
					subscriber.onNext(TempInfo.fetch(town));
				} catch(Exception ex) {
					subscriber.onError(ex);
					executor.shutdown();
					break;
				}
			}
		});
	}

	@Override
	public void cancel() {
		subscriber.onComplete();
	}

}
