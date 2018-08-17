package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.CurrentValues;
import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;

public class RegisterAccountScreen implements Screen {
	private CurrentValues currentValues = CurrentValues.getInstance();
	private Scanner scan = new Scanner(System.in);
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
		Account a = new Account();
		System.out.println("Enter new account number");
		a.setAccountNumber(scan.nextLine());
		System.out.println("Enter account type");
		a.setAccountType(scan.nextLine());
		a.setBalance(0);
		
		
		
		//try {
		   if(ad.createAccount(a) >= 0) {
		    //System.out.println(currentValues.currentAccount);
			currentValues.currentAccount = a;
			currentValues.currentUser.addaccount(a.getAccountNumber());
			ud.updateUser(currentValues.currentUser);
			}
		return new AccountOptionsScreen();
	}

}
