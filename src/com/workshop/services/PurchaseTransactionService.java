package com.workshop.services;

import java.util.List;

import com.workshop.model.PurchaseTransaction;

public interface PurchaseTransactionService {
	public void insertData(PurchaseTransaction purchaseTransaction);
	public void updateData(PurchaseTransaction purchaseTransaction);
	public PurchaseTransaction getPurchaseTransaction(int id);
	public List<PurchaseTransaction> getPurchaseTransactionList();
	List<PurchaseTransaction> getPurchaseTransactionListByUserId(int userId);
}
