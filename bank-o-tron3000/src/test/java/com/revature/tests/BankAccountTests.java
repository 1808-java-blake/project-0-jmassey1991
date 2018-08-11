package com.revature.tests;
import org.junit.Assert;
import org.junit.Test;

import com.revature.beans.Account;
import com.revature.beans.CurrentValues;

public class BankAccountTests {
	
	private CurrentValues currentValues = CurrentValues.getInstance();
	
	@Test
	public void withdrawMoreThanAccountBalance() {
		currentValues.currentAccount = new Account("40", 30, "checking");
		currentValues.currentAccount.withdrawFunds("50");
		Assert.assertEquals(30, currentValues.currentAccount.getBalance(), 0);
	}
	
	@Test
	public void withdrawTheEntireAccountBalance() {
		currentValues.currentAccount = new Account("40", 30, "checking");
		currentValues.currentAccount.withdrawFunds("30");
		Assert.assertEquals(0, currentValues.currentAccount.getBalance(), 0);
	}
	
	@Test
	public void withdrawLessThanAccountBalance() {
		currentValues.currentAccount = new Account("40", 30, "checking");
		currentValues.currentAccount.withdrawFunds("20");
		Assert.assertEquals(10, currentValues.currentAccount.getBalance(), 0);
	}
	
	@Test
	public void depositZero() {
		currentValues.currentAccount = new Account("40", 30, "checking");
		currentValues.currentAccount.depositFunds("0");
		Assert.assertEquals(30, currentValues.currentAccount.getBalance(), 0);
	}
	
	@Test
	public void depositPostiveNumber() {
		currentValues.currentAccount = new Account("40", 30, "checking");
		currentValues.currentAccount.depositFunds("50");
		Assert.assertEquals(80, currentValues.currentAccount.getBalance(), 0);
	}
}
