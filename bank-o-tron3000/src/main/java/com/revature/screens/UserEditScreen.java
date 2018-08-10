package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.CurrentValues;
import com.revature.daos.UserDao;

public class UserEditScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private CurrentValues currentValues = CurrentValues.getInstance();
	private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		System.out.println("----------------------------------------------");
		System.out.println("Current User: " + currentValues.currentUser.getUsername() );
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to delete current user");
		System.out.println("Enter 2 to edit user name");
		
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			//System.out.println("selected 1 not yet implemented that screen");
			ud.deleteUser(currentValues.currentUser);
			return new LoginScreen();
			//break;
		case "2":
			System.out.println("Enter new first name");
			String fname = scan.nextLine();
			System.out.println("Enter new last name");
			String lname = scan.nextLine();
			currentValues.currentUser.setFirstName(fname);
			currentValues.currentUser.setLastName(lname);
			ud.updateUser(currentValues.currentUser);
			return new HomeScreen();
			//break;
		
		}
		return null;
	}

}
