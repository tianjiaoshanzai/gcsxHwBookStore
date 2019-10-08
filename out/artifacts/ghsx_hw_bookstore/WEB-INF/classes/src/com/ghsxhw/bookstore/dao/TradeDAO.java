package com.ghsxhw.bookstore.dao;

import java.util.Set;

import com.ghsxhw.bookstore.domain.Trade;

public interface TradeDAO {

	/**
	 * �����ݱ��в��� Trade ����
	 * @param trade
	 */
	public abstract void insert(Trade trade);

	/**
	 * ���� userId ��ȡ��������� Trade �ļ���
	 * @param userId
	 * @return
	 */
	public abstract Set<Trade> getTradesWithUserId(Integer userId);

}