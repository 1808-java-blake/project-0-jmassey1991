package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revature.beans.Account;

public class AccountDatabase implements AccountDao {
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;

	@Override
	public void createAccount(Account u) {
		// TODO Auto-generated method stub

	}

	@Override
	public Account findByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount(Account u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAccount(Account u) {
		// TODO Auto-generated method stub

	}

}
