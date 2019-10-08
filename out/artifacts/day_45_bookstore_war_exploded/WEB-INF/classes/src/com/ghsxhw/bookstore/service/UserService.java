package com.ghsxhw.bookstore.service;

import java.util.Iterator;
import java.util.Set;

import com.ghsxhw.bookstore.dao.BookDAO;
import com.ghsxhw.bookstore.dao.TradeDAO;
import com.ghsxhw.bookstore.dao.TradeItemDAO;
import com.ghsxhw.bookstore.dao.UserDAO;
import com.ghsxhw.bookstore.dao.impl.BookDAOImpl;
import com.ghsxhw.bookstore.dao.impl.TradeDAOImpl;
import com.ghsxhw.bookstore.dao.impl.TradeItemDAOImpl;
import com.ghsxhw.bookstore.dao.impl.UserDAOImpl;
import com.ghsxhw.bookstore.domain.Trade;
import com.ghsxhw.bookstore.domain.TradeItem;
import com.ghsxhw.bookstore.domain.User;

public class UserService {

	private UserDAO userDAO = new UserDAOImpl();
	
	public User getUserByUserName(String username){
		return userDAO.getUser(username);
	}
	
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
	private BookDAO bookDAO = new BookDAOImpl();
	
	public User getUserWithTrades(String username){
	
//		���� UserDAO �ķ�����ȡ User ����
		User user = userDAO.getUser(username);
		if(user == null){
			return null;
		}
		
//		���� TradeDAO �ķ�����ȡ Trade �ļ��ϣ�����װ��Ϊ User ������
		int userId = user.getUserId();
		
//		���� TradeItemDAO �ķ�����ȡÿһ�� Trade �е� TradeItem �ļ��ϣ�������װ��Ϊ Trade ������
		Set<Trade> trades = tradeDAO.getTradesWithUserId(userId);
		
		if(trades != null){
			Iterator<Trade> tradeIt = trades.iterator();
			
			while(tradeIt.hasNext()){
				Trade trade = tradeIt.next();
				
				int tradeId = trade.getTradeId();
				Set<TradeItem> items = tradeItemDAO.getTradeItemsWithTradeId(tradeId);
				
				if(items != null){
					for(TradeItem item: items){
						item.setBook(bookDAO.getBook(item.getBookId())); 
					}
					
					if(items != null && items.size() != 0){
						trade.setItems(items);						
					}
				}
				
				if(items == null || items.size() == 0){
					tradeIt.remove();	
				}
				
			}
		}
		
		if(trades != null && trades.size() != 0){
			user.setTrades(trades);			
		}
		
		return user;
	}
	
}
