package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.CurrentValues;
import com.revature.beans.User;
import com.revature.daos.UserDao;

public class LoginScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	
	
	@Override
	public Screen start() {
		CurrentValues currentValues = CurrentValues.getInstance();
		
		System.out.println("Enter Username or type Register to sign up: ");
		String username = scan.nextLine();
		if ("register".equalsIgnoreCase(username)) {
			return new RegisterUserScreen();
		}
		
		System.out.println("Enter Password: ");
		String password = scan.nextLine();

		User curUser = ud.findByUsernameAndPassword(username, password);
		if (curUser != null) {
			currentValues.currentUser = curUser;
			return new HomeScreen();
		}

		System.out.println("unable to login");
		return this;
	}

}
