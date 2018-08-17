package com.revature.daos;

import com.revature.beans.Account;

public interface AccountDao {
public static final AccountDao currentAccountDao = new AccountDatabase();
	
	int createAccount(Account a);
	Account findByAccountNumber(String accountNumber);
	void updateAccount(Account a);
	void deleteAccount(Account a);

}


