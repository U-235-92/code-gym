package aq.gym.thread.store;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Store {

	private List<CashDesk> cashDesks = new ArrayList<CashDesk>();
	private AtomicInteger numberServicedCustomers = new AtomicInteger(0);
	private int totalNumberCustomers;
	
	public Store(List<CashDesk> cashDesks) {
		cashDesks.forEach(cashDesk -> cashDesk.setStore(this));
		totalNumberCustomers = cashDesks.stream().map(CashDesk::getQueueSize).reduce(Integer::sum).get();
		this.cashDesks.addAll(cashDesks);
	}
	
	public void service() {
		for(CashDesk cashDesk : cashDesks) {			
			Thread cashDeskThread = new Thread(() -> cashDesk.servicePersons());
			cashDeskThread.start();
		}
	}
	
	public int getTotalNumberCustomers() {
		return totalNumberCustomers;
	}

	public int getCurrentNumberServicedCustomers() {
		return numberServicedCustomers.incrementAndGet();
	}
	
	public List<CashDesk> getCashDesks() {
		return List.copyOf(cashDesks);
	}
}
