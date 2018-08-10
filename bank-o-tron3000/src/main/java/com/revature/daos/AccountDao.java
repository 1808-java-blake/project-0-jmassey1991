package com.revature.daos;

import com.revature.beans.Account;

public interface AccountDao {
public static final AccountDao currentAccountDao = new AccountSerializer();
	
	void createAccount(Account u);
	Account findByAccountNumber(String accountNumber);
	void updateAccount(Account u);
	void deleteAccount(Account u);

}


