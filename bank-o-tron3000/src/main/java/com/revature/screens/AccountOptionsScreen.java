package com.revature.screens;

import java.util.Scanner;

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
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to make a new account");
		System.out.println("Enter 2 withdraw or deposit to or from an account");
		System.out.println("Enter 3 delete an account");
		System.out.println("Enter 4 to return to main menu");
		
		String selection = scan.nextLine();
		
		switch (selection) {
		case "1":
			return new RegisterAccountScreen();
		case "2":
			break;
		case "3":
			break;
		case "4":
			return new HomeScreen();
		}
		
		return this;
	}

}
