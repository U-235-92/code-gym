package aq.gym.thread.store;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class CashDesk {

	private Store store;
	private String name;
	private ConcurrentLinkedDeque<Person> personsDeque = new ConcurrentLinkedDeque<Person>();
	
	public CashDesk(String name, List<Person> persons, Store store) {
		this.name = name;
		this.store = store;
		personsDeque.addAll(persons);
	}
	
	public void servicePersons() {
		while(personsDeque.size() > 0) {			
			Optional<Person> optFirstPerson = Optional.ofNullable(personsDeque.poll());
			optFirstPerson.ifPresent(firstPerson -> {
				System.out.println("Service person " + firstPerson.getName() + " by cash desk " + name);
				sleep();
				store.incrementCountServicedPersons();
				System.out.println("Person " + firstPerson.getName() 
					+ " serviced by cash desk " + name + " size of queue: " + getQueueSize() 
					+ ", serviced " + store.getCountServicedPersons() + " persons");
				sleep();
				if(personsDeque.size() > 1) {					
					Optional<Person> optLastPerson = Optional.ofNullable(personsDeque.peekLast());
					if(optLastPerson.isPresent()) {
						CashDesk bestCashDesk = optLastPerson
								.flatMap(person -> person.lookForBestCashDesk(store))
								.get();
						if(bestCashDesk != this) {
							Person lastPerson = personsDeque.pollLast();
							bestCashDesk.personsDeque.addLast(lastPerson);
							System.out.println("Person " + lastPerson.getName() + " moved to " + bestCashDesk.name 
									+ "[size of queue: " + bestCashDesk.getQueueSize() + "]" 
									+ " from " + name + "[size of queue: " + getQueueSize() + "]");
						}
					}
				}
				System.out.println();
			});
		}
	}
	
	private void sleep() {
		try {
			int min = 500, max = 1500;
			int mills = min + (int) Math.random() * (max - min + 1);
			TimeUnit.MILLISECONDS.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized int getQueueSize() {
		return personsDeque.size();
	}
}
