package aq.gym.thread.store;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;

public class CashDesk {

	@Getter
	private String name;
	@Setter @Getter
	private Store store;
	private ConcurrentLinkedDeque<Customer> customersDeque = new ConcurrentLinkedDeque<Customer>();
	
	public CashDesk(String name, List<Customer> persons) {
		this.name = name;
		customersDeque.addAll(persons);
		customersDeque.forEach(person -> person.setCurrentCashDesk(this));
	}
	
	public void servicePersons() {
		while(customersDeque.size() > 0) {			
			Optional<Customer> optFirstCustomer = Optional.ofNullable(customersDeque.poll());
			optFirstCustomer.ifPresent(firstCustomer -> {
				serviceCustomer(firstCustomer);
				tryToExchangeCashDesk(firstCustomer, store);
			});
			stealCustomersToCurrentEmptyQueueFromAnotherQueue();
		}
	}
	
	private void serviceCustomer(Customer customer) {
		System.out.println("Customer " + customer.getName() 
			+ " was serviced by " + name 
			+ " queue size: " + getQueueSize() + ", customers serviced: " 
			+ store.getCurrentNumberServicedCustomers());
		sleep();
	}
	
	private void tryToExchangeCashDesk(Customer customer, Store store) {
		CashDesk shortestCashDeskQueue = customer.tryToFindCashDeskMinSizeQueue(store);
		if(shortestCashDeskQueue != this) {			
			Customer lastCutomerInQueue = customersDeque.pollLast();
			if(lastCutomerInQueue != null) {
				shortestCashDeskQueue.customersDeque.addLast(lastCutomerInQueue);
				System.out.println("Customer " + lastCutomerInQueue.getName() 
					+ " moved from " + name + "[size of queue: " + getQueueSize() + "]" 
					+ " to "  + shortestCashDeskQueue.name + "[size of queue: " + shortestCashDeskQueue.getQueueSize() + "]");
			}
		} 
	}
	
	private void stealCustomersToCurrentEmptyQueueFromAnotherQueue() {
		if(customersDeque.size() == 0) {
			for(CashDesk cashDesk : store.getCashDesks()) {
				if(cashDesk != this) {
					if(cashDesk.getQueueSize() > 0) {
						Customer anotherLastCustomer = cashDesk.customersDeque.pollLast();
						if(anotherLastCustomer != null)
							customersDeque.addFirst(anotherLastCustomer);
						else		
							break;
					}
				}
			}
		}
	}
	
	private void sleep() {
		try {
			int min = 1000, max = 3000;
			int mills = min + (int) Math.random() * (max - min + 1);
			TimeUnit.MILLISECONDS.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized int getQueueSize() {
		return customersDeque.size();
	}
}
