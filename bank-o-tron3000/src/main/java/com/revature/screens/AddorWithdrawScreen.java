package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.CurrentValues;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class AddorWithdrawScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private CurrentValues currentValues = CurrentValues.getInstance();
	private AccountDao ad = AccountDao.currentAccountDao;
	//private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		System.out.println("---------------------------------------------------------------------------------------------------------------------------------- \n");
		System.out.println("__________                __             _______          ___________                       ._______________  _______  _______   ");
		System.out.println("\\______   \\_____    ____ |  | __         \\   _  \\         \\__    ___/______  ____   ____    |   ____/\\   _  \\ \\   _  \\ \\   _  \\  ");
		System.out.println(" |    |  _/\\__  \\  /    \\|  |/ /  ______ /  /_\\  \\   ______ |    |  \\_  __ \\/  _ \\ /    \\   |____  \\ /  /_\\  \\/  /_\\  \\/  /_\\  \\ ");
		System.out.println(" |    |   \\ / __ \\|   |  \\    <  /_____/ \\  \\_/   \\ /_____/ |    |   |  | \\(  <_> )   |  \\  /       \\\\  \\_/   \\  \\_/   \\  \\_/   \\");
		System.out.println(" |______  /(____  /___|  /__|_ \\          \\_____  /         |____|   |__|   \\____/|___|  / /______  / \\_____  /\\_____  /\\_____  /");
		System.out.println("        \\/      \\/     \\/     \\/                \\/                                     \\/         \\/        \\/       \\/       \\/ ");
		System.out.println("");
		System.out.println("");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("");
		if(currentValues.currentAccount == null)System.out.println("Current Account: NO ACCOUNT");
		else System.out.println("| Current Account: " + currentValues.currentAccount.getAccountNumber() + " | Type: " + currentValues.currentAccount.getAccountType() + " | Balance: $" + currentValues.currentAccount.getBalance());
		System.out.println("");
		System.out.println("Please choose from following options:");
		System.out.println("Enter 1 to make a deposit");
		System.out.println("Enter 2 to make a withdraw");
		System.out.println("Enter 3 to wire money to another account");
		System.out.println("Enter 4 return to Account Options");
		
		String selection = scan.nextLine();
		
		switch (selection) {
		
		case "1":
			System.out.println("Enter amount to deposit");
			String depAmount = scan.nextLine();
			currentValues.currentAccount.depositFunds(depAmount);
			ad.updateAccount(currentValues.currentAccount);
			return this;
			
		case "2":
			System.out.println("Enter amount to withdraw");
			String withAmount = scan.nextLine();
			currentValues.currentAccount.withdrawFunds(withAmount);
			ad.updateAccount(currentValues.currentAccount);
			return this;
		case "3":
			System.out.println("Enter the account number you wish to wire money to");
			String acc = scan.nextLine();
			Account a = ad.findByAccountNumber(acc);
			if(a == null) {
				System.out.println("Account with that account number not found");
				return this;
			}
			System.out.println("Enter the amount you wish to wire to account " + a.getAccountNumber());
			String wireAmount = scan.nextLine();
			if(Integer.valueOf(wireAmount) > currentValues.currentAccount.getBalance()) {
				System.out.println("Can't withdraw that much, current balance is $ " + currentValues.currentAccount.getBalance());
				return this;
			}
			currentValues.currentAccount.setBalance(currentValues.currentAccount.getBalance() - Integer.valueOf(wireAmount));
			currentValues.currentAccount.addTransHistory("New Balance after a wire of " + wireAmount + " = $ " + currentValues.currentAccount.getBalance());
			System.out.println("New Balance after wire = $ " + currentValues.currentAccount.getBalance());
			a.setBalance(a.getBalance() + Integer.valueOf(wireAmount));
			a.addTransHistory("New Balance after a wire of " + wireAmount + " from account " + currentValues.currentAccount.getAccountNumber() + " = $ " + a.getBalance());
			ad.updateAccount(a);
			ad.updateAccount(currentValues.currentAccount);
			//System.out.println("New Balance after wire for account is " + a.getAccountNumber() + " = $ " + a.getBalance());
			return this;
		case "4":
			return new AccountOptionsScreen();
		
		}
		
		
		
		return this;
	}

}
