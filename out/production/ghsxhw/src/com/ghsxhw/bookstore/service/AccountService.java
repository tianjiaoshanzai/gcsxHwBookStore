package com.ghsxhw.bookstore.service;

import com.ghsxhw.bookstore.dao.AccountDAO;
import com.ghsxhw.bookstore.dao.impl.AccountDAOIml;
import com.ghsxhw.bookstore.domain.Account;

public class AccountService {
	
	private AccountDAO accountDAO = new AccountDAOIml();
	
	public Account getAccount(int accountId){
		return accountDAO.get(accountId);
	}
	
}
