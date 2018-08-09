package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.CurrentValues;
import com.revature.daos.UserDao;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private CurrentValues currentValues = CurrentValues.getInstance();
	private UserDao ud = UserDao.currentUserDao;
	@Override
	public Screen start() {
		System.out.println("Current User: " + currentValues.currentUser.getUsername() );
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to choose account");
		System.out.println("Enter 2 to edit user data");
		System.out.println("Enter 3 display user data");
//		System.out.println("Enter 4 to sell a weapon");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			//System.out.println("selected 1 not yet implemented that screen");
			break;
		case "2":
			//System.out.println("selected 2 not yet implemented that screen");
			return new UserEditScreen();
			//break;
		case "3":
			System.out.println(currentValues.currentUser);
			break;
//		case "4":
//			System.out.println("selected 4 not yet implemented that screen");
//			break;
		default:
			break;
		}

		return this;
		
		
	}

}
