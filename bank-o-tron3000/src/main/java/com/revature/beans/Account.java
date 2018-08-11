package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6151084720858051506L;
	private String accountNumber;
	private int balance;
	private String accountType;
	private ArrayList<String> TransHistory;
	//private CurrentValues currentValues = CurrentValues.getInstance();
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String accountNumber, int balance, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
		
	}
	public ArrayList<String> getTransHistory() {
		return TransHistory;
	}

	public void addTransHistory(String transHistory) {
		if(TransHistory == null) {
			this.TransHistory = new ArrayList<>();
		}
		this.TransHistory.add(transHistory);
		
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + balance;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (balance != other.balance)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", accountType=" + accountType
				+ "]";
	}
	
	public void depositFunds(String depAmount) {
		this.setBalance(this.getBalance() + Integer.valueOf(depAmount));
		this.addTransHistory("New Balance after a deposit of " + depAmount + " = $ " + this.getBalance());
		System.out.println("New Balance after deposit = $ " + this.getBalance());

		
	}
	
	public void withdrawFunds(String withAmount) {
		if(Integer.valueOf(withAmount) > this.getBalance()) {
			System.out.println("Can't withdraw that much, current balance is $ " + this.getBalance());
			return;
		}
		this.setBalance(this.getBalance() - Integer.valueOf(withAmount));
		this.addTransHistory("New Balance after a withdraw of " + withAmount + " = $ " + this.getBalance());
		System.out.println("New Balance after withdraw = $ " + this.getBalance());
		
		
	}
	

}
