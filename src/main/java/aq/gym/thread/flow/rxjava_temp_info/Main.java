package aq.gym.thread.flow.rxjava_temp_info;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;

public class Main {

	public static void main(String[] args) {
		Observable<TempInfo> observable = getCelsiusTemperature("New York");
		observable.blockingSubscribe(new TempObserver());
	}

	@SuppressWarnings("unused")
	public static Observable<TempInfo> getTemperature(String town) {
		return Observable.create(emitter -> Observable.interval(1L, TimeUnit.SECONDS).subscribe(i -> {
			if(!emitter.isDisposed()) {
				if(i >= 5) {					
					emitter.onComplete();
				} else {
					try {
						emitter.onNext(TempInfo.fetch(town));
					} catch (Exception e) {
						emitter.onError(e);
					}
				}
			} 
		}));
	}
	
	@SuppressWarnings("unused")
	public static Observable<TempInfo> getCelsiusTemperature(String town) {
		return getTemperature(town).map(temp -> new TempInfo(temp.getTown(), (temp.getTemp() - 32) * 5 / 9));
	}
}
