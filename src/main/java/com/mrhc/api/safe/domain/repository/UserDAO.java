package com.mrhc.api.safe.domain.repository;

public class UserDAO {
	public static boolean validate(String userName, String password)
	{
		return ( userName.equals("admin") && password.equals("admin") );
	}

}
