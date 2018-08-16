package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.beans.User;
import com.revature.utilities.DAOUtilities;

public class UserDatabase implements UserDao {

	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;
	@Override
	public void createUser(User u) {
		if (u== null) {
			return;
		}
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SET SCHEMA 'bank_o_tron'; INSERT INTO users VALUES (?, ?, ?, ?, ?);";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			if(u.isAdmin() == false)
			stmt.setString(5, "false");
			else stmt.setString(5, "true");
			
//			if (stmt.executeUpdate() == 0)
//				System.out.println("Could not updata database");
//			else
//				System.out.println("User created successfully");
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	
	@Override
	public User findByUsernameAndPassword(String username, String password) {
		
		return null;
	}

	@Override
	public void updateUser(User u) {
		

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
