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
		System.out.println("Current User: " + currentValues.currentUser.getUsername() );
		System.out.println("");
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to for account options");
		System.out.println("Enter 2 to edit user data");
		System.out.println("Enter 3 display user data");
		System.out.println("Enter 4 to switch users");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			return new AccountOptionsScreen();
		case "2":
			//System.out.println("selected 2 not yet implemented that screen");
			return new UserEditScreen();
			//break;
		case "3":
			System.out.println(currentValues.currentUser);
			break;
		case "4":
			return new LoginScreen();
		default:
			break;
		}

		return this;
		
		
	}

}
