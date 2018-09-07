package com.workshop.dao;

import java.util.List;

import com.workshop.model.HuntTransaction;

public interface HuntTransactionDao {
	public void insertData(HuntTransaction huntTransaction);
	public void updateData(HuntTransaction huntTransaction);
	public HuntTransaction getHuntTransaction(int id);
	public List<HuntTransaction> getHuntTransactionList();
	List<HuntTransaction> getHuntTransactionByUserId(int userId);
}
