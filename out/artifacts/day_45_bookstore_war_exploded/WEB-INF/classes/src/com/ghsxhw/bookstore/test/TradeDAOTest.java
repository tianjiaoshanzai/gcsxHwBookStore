package com.ghsxhw.bookstore.test;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.Set;

import com.ghsxhw.bookstore.domain.Trade;
import org.junit.Test;

import com.ghsxhw.bookstore.dao.TradeDAO;
import com.ghsxhw.bookstore.dao.impl.TradeDAOImpl;

public class TradeDAOTest {

	private TradeDAO tradeDAO = new TradeDAOImpl();
	
	@Test
	public void testInsertTrade() {
		Trade trade = new Trade();
		trade.setUserId(3);
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		
		tradeDAO.insert(trade);
	}

	@Test
	public void testGetTradesWithUserId() {
		Set<Trade> trades = tradeDAO.getTradesWithUserId(2);
		System.out.println(trades);
	}

}
