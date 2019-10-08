package com.ghsxhw.bookstore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.ghsxhw.bookstore.dao.AccountDAO;
import com.ghsxhw.bookstore.dao.BookDAO;
import com.ghsxhw.bookstore.dao.TradeDAO;
import com.ghsxhw.bookstore.dao.TradeItemDAO;
import com.ghsxhw.bookstore.dao.UserDAO;
import com.ghsxhw.bookstore.dao.impl.AccountDAOIml;
import com.ghsxhw.bookstore.dao.impl.BookDAOImpl;
import com.ghsxhw.bookstore.dao.impl.TradeDAOImpl;
import com.ghsxhw.bookstore.dao.impl.TradeItemDAOImpl;
import com.ghsxhw.bookstore.dao.impl.UserDAOImpl;
import com.ghsxhw.bookstore.domain.Book;
import com.ghsxhw.bookstore.domain.ShoppingCart;
import com.ghsxhw.bookstore.domain.ShoppingCartItem;
import com.ghsxhw.bookstore.domain.Trade;
import com.ghsxhw.bookstore.domain.TradeItem;
import com.ghsxhw.bookstore.web.CriteriaBook;
import com.ghsxhw.bookstore.web.Page;

public class BookService {
	
	private BookDAO bookDAO = new BookDAOImpl();
	
	public Page<Book> getPage(CriteriaBook criteriaBook){
		return bookDAO.getPage(criteriaBook);
	}

	public Book getBook(int id) {
		return bookDAO.getBook(id);
	}

	public boolean addToCart(int id, ShoppingCart sc) {
		Book book = bookDAO.getBook(id);
		
		if(book != null){
			sc.addBook(book);
			return true;
		}
		
		return false;
	}

	public void removeItemFromShoppingCart(ShoppingCart sc, int id) {
		sc.removeItem(id);
	}

	public void clearShoppingCart(ShoppingCart sc) {
		sc.clear();
	}

	public void updateItemQuantity(ShoppingCart sc, int id, int quantity) {
		sc.updateItemQuantity(id, quantity);
	}
	
	private AccountDAO accountDAO = new AccountDAOIml();
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();

	//业务方法. 
	public void cash(ShoppingCart shoppingCart, String username,
			String accountId) {
		
		//1. 更新 mybooks 数据表相关记录的 salesamount 和 storenumber
		bookDAO.batchUpdateStoreNumberAndSalesAmount(shoppingCart.getItems());
		
		//int i = 10 / 0;
		
		//2. 更新 account 数据表的 balance
		accountDAO.updateBalance(Integer.parseInt(accountId), shoppingCart.getTotalMoney());
		
		//3. 向 trade 数据表插入一条记录
		Trade trade = new Trade();
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		trade.setUserId(userDAO.getUser(username).getUserId());
		tradeDAO.insert(trade);
		
		//4. 向 tradeitem 数据表插入 n 条记录
		Collection<TradeItem> items = new ArrayList<>();
		for(ShoppingCartItem sci: shoppingCart.getItems()){
			TradeItem tradeItem = new TradeItem();
			
			tradeItem.setBookId(sci.getBook().getId());
			tradeItem.setQuantity(sci.getQuantity());
			tradeItem.setTradeId(trade.getTradeId());
			
			items.add(tradeItem);
		}
		tradeItemDAO.batchSave(items);
		
		//5. 清空购物车
		shoppingCart.clear();
	}
	
}
