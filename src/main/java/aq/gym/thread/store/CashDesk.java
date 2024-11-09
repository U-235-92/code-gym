package aq.gym.thread.store;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

import lombok.Getter;

public class CashDesk {

	@Getter
	private String name;
	private ConcurrentLinkedDeque<Customer> customersDeque = new ConcurrentLinkedDeque<Customer>();
	
	public CashDesk(String name, List<Customer> persons) {
		this.name = name;
		customersDeque.addAll(persons);
	}
	
	public void servicePersons() {
		while(customersDeque.size() > 0) {			
			Optional<Customer> optFirstCustomer = Optional.ofNullable(customersDeque.poll());
			optFirstCustomer.ifPresent(firstCustomer -> {
				serviceCustomer(firstCustomer);
			});
		}
	}
	
	private void serviceCustomer(Customer customer) {
		customer.serviceCustomer(this);
		sleep();
	}
	
	private void sleep() {
		try {
			int min = 1000, max = 2000;
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
