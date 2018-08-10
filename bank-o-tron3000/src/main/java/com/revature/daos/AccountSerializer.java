package com.revature.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.revature.beans.Account;
import com.revature.beans.User;

public class AccountSerializer implements AccountDao {

	@Override
	public void createAccount(Account a) {
		if (a== null) {
			return;
		}
		File f = new File("src/main/resources/accounts/" + a.getAccountNumber() + ".txt");
		System.out.println(f.getName());
		if (f.exists()) {
			System.out.println("Account already exists");
			return;
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(f))) {

			oos.writeObject(a);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Account findByAccountNumber(String accountNumber) {
		if (accountNumber == null) 
			return null;
		
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/resources/accounts/" + accountNumber + ".txt"))) {

			Account a = (Account) ois.readObject(); // retrieve the user if it can
			// verify that the password matches and username
			if (accountNumber.equals(a.getAccountNumber())) {
				return a;
			} else {
				System.out.println("Account Numbers didn't match");
				return null;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Account file Not Found!");
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateAccount(Account a) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAccount(Account a) {
		// TODO Auto-generated method stub

	}

}
