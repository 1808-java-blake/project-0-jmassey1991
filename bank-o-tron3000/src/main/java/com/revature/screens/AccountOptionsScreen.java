package com.revature.screens;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.CurrentValues;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class AccountOptionsScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private CurrentValues currentValues = CurrentValues.getInstance();
	private AccountDao ad = AccountDao.currentAccountDao;
	private UserDao ud = UserDao.currentUserDao;
	
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
		System.out.println("Enter 1 to make a new account");
		System.out.println("Enter 2 to choose account to open");
		System.out.println("Enter 3 withdraw, deposit  or wire money to or from an account");
		System.out.println("Enter 4 to view transaction history of current account");
		System.out.println("Enter 5 add a shared account to your accounts");
		System.out.println("Enter 6 to return to main menu");
		
		String selection = scan.nextLine();
		
		switch (selection) {
		case "1":
			return new RegisterAccountScreen();
		case "2":
			if(currentValues.currentUser.isAdmin()) {
				System.out.println("Enter any account number to switch to the account");
				String acc = scan.nextLine();
				Account a = new Account();
				a = ad.findByAccountNumber(acc);
				if (a == null) {
					System.out.println("account not found");
					return this;
					}
				currentValues.currentAccount = a;
				return this;
			}
			if(currentValues.currentUser.getAccounts() == null) {
				System.out.println("No accounts to display");
				return this;
			}
			ArrayList<String> accounts = currentValues.currentUser.getAccounts();
			System.out.println("Accounts:");
			for(int i = 0; i < accounts.size(); i++) {
				System.out.println( (i+1) + ": " + accounts.get(i));
			}
			System.out.println("enter the number of the account you want to open");
			String selectedAccount = scan.nextLine();
			Account a = new Account();
			if(accounts.contains(selectedAccount)) {
			 a = ad.findByAccountNumber(selectedAccount);
				if (a == null) {
				System.out.println("account file not found");
				return this;
				}
				currentValues.currentAccount = a;
				return this;
			}
			else {
				System.out.println("You did not select one of your accounts");
				return this;
			}
					
		case "3":
			if(currentValues.currentAccount == null) {
				System.out.println("No account opened yet");
				return this;
			}
			return new AddorWithdrawScreen();
			
		case "4":
			if(currentValues.currentAccount == null) {
				System.out.println("No account opened yet");
				return this;
			}
			if(currentValues.currentAccount.getTransHistory() == null) {
				System.out.println("No transactions yet");
				return this;
			}
			System.out.println("Transaction History:");
			for(String history: currentValues.currentAccount.getTransHistory())
				System.out.println(history);
			return this;
		case "5":
			System.out.println("Enter the account number you wish to share and add to your accounts");
			String acc = scan.nextLine();
			Account sa = ad.findByAccountNumber(acc);
			if(sa == null)return this;
			
			currentValues.currentUser.addaccount(sa.getAccountNumber());
			System.out.println("Successfully added account #" + sa.getAccountNumber() + " to your accounts");
			ud.updateUser(currentValues.currentUser);
			ArrayList<String> accs = currentValues.currentUser.getAccounts();
			System.out.println("Accounts:");
			for(int i = 0; i < accs.size(); i++) {
				System.out.println( (i+1) + ": " + accs.get(i));
			}
			return this;
		
		case "6":
			return new HomeScreen();
		}
		
		return this;
	}

}
