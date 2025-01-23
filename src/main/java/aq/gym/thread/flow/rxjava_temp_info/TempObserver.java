package aq.gym.thread.flow.rxjava_temp_info;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class TempObserver implements Observer<TempInfo> {

	@Override
	public void onSubscribe(@NonNull Disposable d) {}

	@Override
	public void onNext(@NonNull TempInfo t) {
		System.out.println(t);
	}

	@Override
	public void onError(@NonNull Throwable e) {
		System.err.println("Got problem: " + e.getMessage());
	}

	@Override
	public void onComplete() {
		System.out.println("Done!");
	}

}
