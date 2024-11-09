package aq.gym.thread.store;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Customer {

	@NonNull
	private String name;
	@Setter
	private CashDesk currentCashDesk;
	
	public CashDesk tryToFindCashDeskMinSizeQueue(Store store) {
		int minQueueSize = store.getCashDesks()
				.stream()
				.map(CashDesk::getQueueSize)
				.min(Integer::compareTo)
				.get();
		if(currentCashDesk.getQueueSize() - minQueueSize <= 1) {
			return currentCashDesk;
		} else {			
			return store.getCashDesks()
					.stream()
					.dropWhile(cashDesk -> cashDesk.getQueueSize() != minQueueSize)
					.findFirst()
					.orElse(currentCashDesk);
		}
	}
}
