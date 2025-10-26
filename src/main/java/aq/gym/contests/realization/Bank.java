package aq.gym.contests.realization;

import java.util.HashMap;
import java.util.Map;

public class Bank {

	private Map<Integer, Long> accounts;
	
    public Bank(long[] balance) {
    	accounts = new HashMap<>();
    	for(int i = 0; i < balance.length; i++) {
    		accounts.put(i + 1, balance[i]);
    	}
    }
    
    public boolean transfer(int account1, int account2, long money) {
    	if(account1 != account2) {
    		if(isValidAccount(account1) && isValidAccount(account2)) {
    			if(isAbleToSubstractMoney(account1, money)) {
    				doTransfer(account1, account2, money);
    				return true;
    			} else {
    				return false;
    			}
    		} else {
    			return false;
    		}
    	} else {
    		if(isValidAccount(account1)) {
    			if(isAbleToSubstractMoney(account1, money)) {
    				return true;
    			} else {
    				return false;
    			}
    		} else {
    			return false;
    		}
    	}
    }
    
    private void doTransfer(int account1, int account2, long money) {
    	long accOneBalance = accounts.get(account1);
		long accTwoBalance = accounts.get(account2);
		accOneBalance = accOneBalance - money;
		accTwoBalance = accTwoBalance + money;
		accounts.put(account1, accOneBalance);
		accounts.put(account2, accTwoBalance);
    }
    
    public boolean deposit(int account, long money) {
    	if(isValidAccount(account)) {
    		doDeposit(account, money);
        	return true;
    	} else {
    		return false;
    	}
    }
    
    private void doDeposit(int account, long money) {
    	long accBalance = accounts.get(account);
		accBalance = accBalance + money;
    	accounts.put(account, accBalance);
    }
    
    public boolean withdraw(int account, long money) {
    	if(isValidAccount(account)) {
    		if(isAbleToSubstractMoney(account, money)) {
    			doWithdraw(account, money);
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
    
    private void doWithdraw(int account, long money) {
    	long accBalance = accounts.get(account);
		accBalance = accBalance - money;
		accounts.put(account, accBalance);
    }
    
    private boolean isValidAccount(int account) {
    	if(accounts.containsKey(account) && accounts.get(account) >= 0L) return true;
    	else return false;
    }
    
    private boolean isAbleToSubstractMoney(int account, long money) {
    	return accounts.get(account) - money >= 0L;
    }
    
//	https://leetcode.com/problems/simple-bank-system/description/    
    public static void main(String[] args) {
		Bank bank = new Bank(new long[] {0});
		System.out.print(bank.deposit(1, 2)); System.out.println(bank.accounts);
		System.out.print(bank.transfer(1, 1, 1)); System.out.println(bank.accounts);
		System.out.print(bank.transfer(1, 1, 3)); System.out.println(bank.accounts);
	}  
}
