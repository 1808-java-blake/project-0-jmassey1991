package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.User;
import com.revature.utilities.DAOUtilities;

public class UserDatabase implements UserDao {

	Connection connection = null; // Our connection to the database
	PreparedStatement stmt = null;

	@Override
	public void createUser(User u) {
		if (u == null) {
			System.out.println("No user selected");
			return;
		}
		User u2 = findByUsernameAndPassword(u.getUsername(), u.getPassword());
		if (u2 == null) {
			try {
				connection = DAOUtilities.getConnection();
				String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?);";
				stmt = connection.prepareStatement(sql);

				stmt.setString(1, u.getUsername());
				stmt.setString(2, u.getPassword());
				stmt.setString(3, u.getFirstName());
				stmt.setString(4, u.getLastName());
				if (u.isAdmin() == false)
					stmt.setString(5, "false");
				else
					stmt.setString(5, "true");

				// stmt.executeUpdate();
				if (stmt.executeUpdate() == 0)
					System.out.println("Could not updata database");
				else
					System.out.println("User created successfully");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources();
			}
		}
		else {
			System.out.println("User already exists");
			return;
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		if (username == null || password == null)
			return null;
		User u = new User();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setAdmin(rs.getBoolean("admin"));
				String sql2 = "SELECT accountnumber FROM users_accounts WHERE username = ?;";
				stmt = connection.prepareStatement(sql2);
				stmt.setString(1, u.getUsername());
				ResultSet rs2 = stmt.executeQuery();
				while(rs2.next()) {
					u.addaccount(rs2.getString("accountnumber"));
				}
				return u;
			} else {
				// System.out.println("User not found!");
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
	public void updateUser(User u) {
		if (u == null) {
			System.out.println("No user selected");
			return;
		}
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE users SET username = ?, password =  ?, firstname =  ?, lastname = ?, admin = ? WHERE username = ?;";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			if (u.isAdmin() == false)
				stmt.setString(5, "false");
			else
				stmt.setString(5, "true");
			stmt.setString(6, u.getUsername());
			
			// stmt.executeUpdate();
			if (stmt.executeUpdate() == 0)
				System.out.println("Could not update database");
			if(u.getAccounts() == null) return;
			for(String account: u.getAccounts()) {
			String sql2 = "SELECT * FROM users_accounts WHERE username = ? AND accountnumber = ?;";
			stmt = connection.prepareStatement(sql2);
			stmt.setString(1, u.getUsername());
			stmt.setString(2, account);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				continue;
			}
			else { //  not in the users_account table
				String sql3 = "INSERT INTO users_accounts VALUES (?, ?);";
				stmt = connection.prepareStatement(sql3);
				stmt.setString(1, u.getUsername());
				stmt.setString(2, account);
				if(stmt.executeUpdate() != 0) 
					System.out.println("Successfully added account to user " + u.getUsername());
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
	public void deleteUser(User u) {

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
