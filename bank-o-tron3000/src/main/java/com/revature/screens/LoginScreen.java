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
			currentValues.currentAccount = null;
			return new HomeScreen();
		}

		System.out.println("unable to login");
		return this;
	}

}
