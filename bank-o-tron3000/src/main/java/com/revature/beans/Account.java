package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6151084720858051506L;
	private String accountNumber;
	private int balance;
	private String accountType;
	private List<String> TransHistory;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String accountNumber, int balance, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
		this.TransHistory = new ArrayList<>();
	}
	public List<String> getTransHistory() {
		return TransHistory;
	}

	public void addTransHistory(String transHistory) {
		TransHistory.add(transHistory);
		
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
	
	
	
	
	

}
