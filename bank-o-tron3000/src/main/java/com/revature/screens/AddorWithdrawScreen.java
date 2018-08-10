package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.CurrentValues;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class AddorWithdrawScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private CurrentValues currentValues = CurrentValues.getInstance();
	private AccountDao ad = AccountDao.currentAccountDao;
	private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		System.out.println("----------------------------------------------");
		if(currentValues.currentAccount == null)System.out.println("Current Account: NO ACCOUNT");
		else System.out.println("| Current Account: " + currentValues.currentAccount.getAccountNumber() + " | Type: " + currentValues.currentAccount.getAccountType());
		System.out.println("Please choose from following options:");
		System.out.println("Enter 1 to make a deposit");
		System.out.println("Enter 2 to make a withdraw");
		System.out.println("Enter 3 return to Account Options");
		
		String selection = scan.nextLine();
		
		switch (selection) {
		
		case "1":
			String depAmount = scan.nextLine();
			currentValues.currentAccount.setBalance(currentValues.currentAccount.getBalance() + Integer.valueOf(depAmount));
			currentValues.currentAccount.addTransHistory("New Balance after a deposit of " + depAmount + " = $ " + currentValues.currentAccount.getBalance());
			System.out.println("New Balance after deposit = $ " + currentValues.currentAccount.getBalance());
			ad.updateAccount(currentValues.currentAccount);
			return this;
			
		case "2":
			String withAmount = scan.nextLine();
			if(Integer.valueOf(withAmount) > currentValues.currentAccount.getBalance()) {
				System.out.println("Can't withdraw that much, current balance is $ " + currentValues.currentAccount.getBalance());
				return this;
			}
			currentValues.currentAccount.setBalance(currentValues.currentAccount.getBalance() - Integer.valueOf(withAmount));
			currentValues.currentAccount.addTransHistory("New Balance after a withdraw of " + withAmount + " = $ " + currentValues.currentAccount.getBalance());
			System.out.println("New Balance after withdraw = $ " + currentValues.currentAccount.getBalance());
			ad.updateAccount(currentValues.currentAccount);
			return this;
			
		case "3":
			return new AccountOptionsScreen();
		
		}
		
		
		
		return this;
	}

}
