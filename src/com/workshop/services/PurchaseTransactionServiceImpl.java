package com.workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.dao.PurchaseTransactionDao;
import com.workshop.model.PurchaseTransaction;

public class PurchaseTransactionServiceImpl implements PurchaseTransactionService {

	@Autowired  
	PurchaseTransactionDao purchaseTransactiondao; 
	
	@Override
	public void insertData(PurchaseTransaction purchaseTransaction) {
		purchaseTransactiondao.insertData(purchaseTransaction);
	}

	@Override
	public void updateData(PurchaseTransaction purchaseTransaction) {
		purchaseTransactiondao.updateData(purchaseTransaction);
	}

	@Override
	public PurchaseTransaction getPurchaseTransaction(int id) {
		return purchaseTransactiondao.getPurchaseTransaction(id);
	}

	@Override
	public List<PurchaseTransaction> getPurchaseTransactionList() {
		return purchaseTransactiondao.getPurchaseTransactionList();
	}
	
	@Override
	public List<PurchaseTransaction> getPurchaseTransactionListByUserId(int userId) {
		return purchaseTransactiondao.getPurchaseTransactionListByUserId(userId);
	}

}
