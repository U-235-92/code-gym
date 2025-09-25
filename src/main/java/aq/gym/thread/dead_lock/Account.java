package aq.gym.thread.dead_lock;

public class Account {

	private String name;
	private int balance;
	
	public Account(String name, int balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}
	
	public synchronized void transfer(Account to, int ammount) {
		if(this != to) {			
			try {				
				String thread = Thread.currentThread().getName();
				while(this.balance < ammount) {
					System.out.println(thread + ": " + this.name + " hasn't got enough money :( and has to wait...");
					wait();
				}
				Thread.sleep(150);
				System.out.println(thread + ": " + this.name + " balance is: " + this.balance + "$");
				Thread.sleep(150);
				System.out.println(thread + ": " + to.name + " balance is: " + to.balance + "$");
				Thread.sleep(150);
				System.out.println(thread + ": " + this.name + " is transfering " + ammount + "$ to " + to.name);
				this.balance -= ammount;
				to.balance += ammount;
				System.out.println(thread + ": " + this.name + " have transfered money to " + to.name);
				Thread.sleep(150);
				System.out.println(thread + ": " + this.name + " balance is: " + this.balance + "$");
				Thread.sleep(150);
				System.out.println(thread + ": " + to.name + " balance is: " + to.balance + "$");
				Thread.sleep(150);
			} catch(InterruptedException e) {
				System.err.println("Oups something went wrong...");
			} finally {				
				notifyAll();
			}
		}
	}
}
