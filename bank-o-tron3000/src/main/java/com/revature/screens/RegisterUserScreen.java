package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class RegisterUserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		System.out.println("----------------------------------------------");
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
		
		
		//try {
			ud.createUser(u);
			
//		} catch (NumberFormatException e) {
//			System.out.println("Invalid number");
//		}
		
		return new LoginScreen();
	
	}

}
