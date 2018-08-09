package com.revature.beans;

public class CurrentValues {
	private static CurrentValues singleInstance = null;
	public User currentUser;
	
	
	private CurrentValues() {
		
	}
	
	public static CurrentValues getInstance() {
		if(singleInstance == null)
			singleInstance = new CurrentValues();
		
		return singleInstance;
	}
}
