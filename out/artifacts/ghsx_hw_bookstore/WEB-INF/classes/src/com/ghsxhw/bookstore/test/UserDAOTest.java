package com.ghsxhw.bookstore.test;

import com.ghsxhw.bookstore.domain.User;
import org.junit.Test;

import com.ghsxhw.bookstore.dao.UserDAO;
import com.ghsxhw.bookstore.dao.impl.UserDAOImpl;

public class UserDAOTest {

	private UserDAO userDAO = new UserDAOImpl();
	
	@Test
	public void testGetUser() {
		User user = userDAO.getUser("AAA");
		System.out.println(user); 
	}

}
