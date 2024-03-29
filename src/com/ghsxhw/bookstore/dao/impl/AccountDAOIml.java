package com.ghsxhw.bookstore.dao.impl;

import com.ghsxhw.bookstore.dao.AccountDAO;
import com.ghsxhw.bookstore.domain.Account;

public class AccountDAOIml extends BaseDAO<Account> implements AccountDAO {

	@Override
	public Account get(Integer accountId) {
		String sql = "SELECT accountId, balance FROM account WHERE accountId = ?";
		return query(sql, accountId); 
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql = "UPDATE account SET balance = balance - ? WHERE accountId = ?";
		update(sql, amount, accountId); 
	}

}
