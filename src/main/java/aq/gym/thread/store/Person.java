package aq.gym.thread.store;

import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Person {

	@NonNull
	private String name;

	public Optional<CashDesk> lookForBestCashDesk(Store store) {
		int minQueueSize = store.getCashDesks()
				.stream()
				.map(CashDesk::getQueueSize)
				.min(Integer::compareTo)
				.get();
		return store.getCashDesks()
				.stream()
				.dropWhile(cashDesk -> cashDesk.getQueueSize() != minQueueSize)
				.findFirst();
	}
}
