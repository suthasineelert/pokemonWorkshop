package com.workshop.services;

import java.util.List;

import com.workshop.model.HuntTransaction;

public interface HuntTransactionService {
	public void insertData(HuntTransaction huntTransaction);
	public void updateData(HuntTransaction huntTransaction);
	public HuntTransaction getHuntTransaction(int id);
	public List<HuntTransaction> getHuntTransactionList();
	List<HuntTransaction> getHuntTransactionInfo(int userId);
}
