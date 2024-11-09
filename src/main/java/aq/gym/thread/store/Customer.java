package aq.gym.thread.store;

import java.util.Optional;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Customer {

	@NonNull
	private String name;
	private CashDesk currentCashDesk;

	public Optional<CashDesk> lookForBestCashDesk(Store store) {
		return null;
	}
	
	public void serviceCustomer(CashDesk cashDesk) {
		System.out.println(name + " was serviced by " + cashDesk.getName() 
				+ " queue size: " + cashDesk.getQueueSize());
	}
}
