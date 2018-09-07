package com.workshop.dao;

import java.util.List;

import com.workshop.model.PurchaseTransaction;

public interface PurchaseTransactionDao {
	public void insertData(PurchaseTransaction purchaseTransaction);
	public void updateData(PurchaseTransaction purchaseTransaction);
	public PurchaseTransaction getPurchaseTransaction(int id);
	public List<PurchaseTransaction> getPurchaseTransactionList();
	List<PurchaseTransaction> getPurchaseTransactionListByUserId(int userId);
}
