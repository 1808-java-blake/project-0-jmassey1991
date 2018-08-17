package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.beans.Account;
import com.revature.utilities.DAOUtilities;

public class AccountDatabase implements AccountDao {
	private Logger log = Logger.getRootLogger();
	Connection connection = null; // Our connection to the database
	PreparedStatement stmt = null;

	@Override
	public int createAccount(Account a) {
		if (a == null) {
			System.out.println("No account selected");
			return -1;
		}
		Account a2 = findByAccountNumber(a.getAccountNumber());
		if (a2 == null) {
			try {
				connection = DAOUtilities.getConnection();
				String sql = "INSERT INTO accounts VALUES (?, ?, ?);";
				stmt = connection.prepareStatement(sql);

				stmt.setString(1, a.getAccountNumber());
				stmt.setInt(2, a.getBalance());
				stmt.setString(3, a.getAccountType());

				// stmt.executeUpdate();
				if (stmt.executeUpdate() == 0) {
					System.out.println("Could not updata database");
					log.warn("Account doesn't exist but can't add new account to database");
					return -1;
				}

				else {
					System.out.println("Account created successfully");
					log.info("Account stored in database successfully");
					return 1;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources();
			}
		} else {
			System.out.println("Account already exists");
			log.info("tried to create account that already exists");
			return -1;
		}
		return 0;

	}

	@Override
	public Account findByAccountNumber(String accountNumber) {
		if (accountNumber == null) {
			log.info("There was no account number to find the account with when called");
			return null;
		}
		Account a = new Account();
		try {
			connection = DAOUtilities.getConnection();
			log.info("got new connection for find account");
			String sql = "SELECT * FROM accounts WHERE accountnumber = ? ;";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, accountNumber);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				a.setAccountNumber(rs.getString("accountnumber"));
				a.setBalance(rs.getInt("balance"));
				a.setAccountType(rs.getString("accounttype"));
				String sql2 = "SELECT transactionhistory FROM account_transaction_history WHERE accountnumber = ?;";
				stmt = connection.prepareStatement(sql2);
				stmt.setString(1, a.getAccountNumber());
				ResultSet rs2 = stmt.executeQuery();
				while (rs2.next()) {
					a.addTransHistory(rs2.getString("transactionhistory"));
				}
				return a;
			} else {
				log.info("User not found in the database");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return null;
	}

	@Override
	public void updateAccount(Account a) {
		if (a == null) {
			System.out.println("No account selected");
			log.info("Tried to update an account that doesn't exist");
			return;
		}
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE accounts SET accountnumber = ?, balance =  ?, accounttype =  ? WHERE accountnumber = ?;";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, a.getAccountNumber());
			stmt.setInt(2, a.getBalance());
			stmt.setString(3, a.getAccountType());
			stmt.setString(4, a.getAccountNumber());

			// stmt.executeUpdate();
			if (stmt.executeUpdate() == 0)
				System.out.println("Could not update database");

			for (String history : a.getTransHistory()) {
				String sql2 = "SELECT * FROM account_transaction_history WHERE accountnumber = ? AND transactionhistory = ?;";
				stmt = connection.prepareStatement(sql2);
				stmt.setString(1, a.getAccountNumber());
				stmt.setString(2, history);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					continue;
				} else { // not in the users_account table
					String sql3 = "INSERT INTO account_transaction_history VALUES (?, ?);";
					stmt = connection.prepareStatement(sql3);
					stmt.setString(1, a.getAccountNumber());
					stmt.setString(2, history);
					if (stmt.executeUpdate() != 0)
						System.out.println("Successfully added transaction history to account " + a.getAccountNumber());
				}
			}
//			else
//				System.out.println("User updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	@Override
	public void deleteAccount(Account a) {
		log.info("Tried to delete an account without it implemented");
	}

	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
