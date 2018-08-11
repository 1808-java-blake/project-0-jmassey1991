package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class RegisterUserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
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
		//CurrentValues currentValues = CurrentValues.getInstance();
		User u = new User();
		System.out.println("Enter new username");
		u.setUsername(scan.nextLine());
		System.out.println("Enter password");
		u.setPassword(scan.nextLine());
		System.out.println("Enter first name");
		u.setFirstName(scan.nextLine());
		System.out.println("Enter last name");
		u.setLastName(scan.nextLine());
		System.out.println("Are you an admin? 'y' for yes or 'n' for no");
		String yesOrNo = scan.nextLine();
		if(yesOrNo.equals("y"))
			u.setAdmin(true);
		else u.setAdmin(false);
		
		
		//try {
			ud.createUser(u);
			
//		} catch (NumberFormatException e) {
//			System.out.println("Invalid number");
//		}
		
		return new LoginScreen();
	
	}

}
