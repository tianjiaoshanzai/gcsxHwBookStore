package com.ghsxhw.bookstore.dao;

import com.ghsxhw.bookstore.domain.User;

public interface UserDAO {

	/**
	 * 根据用户名获取 User 对象
	 * @param username
	 * @return
	 */
	public abstract User getUser(String username);

}

