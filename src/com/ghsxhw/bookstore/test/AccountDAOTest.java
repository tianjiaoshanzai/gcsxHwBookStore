package com.ghsxhw.bookstore.test;

import com.ghsxhw.bookstore.domain.Account;
import org.junit.Test;

import com.ghsxhw.bookstore.dao.AccountDAO;
import com.ghsxhw.bookstore.dao.impl.AccountDAOIml;

public class AccountDAOTest {

	AccountDAO accountDAO = new AccountDAOIml();
	
	@Test
	public void testGet() {
		Account account = accountDAO.get(1);
		System.out.println(account.getBalance()); 
	}

	@Test
	public void testUpdateBalance() {
		accountDAO.updateBalance(1, 50);
	}

}
