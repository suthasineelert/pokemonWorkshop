package com.workshop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.dao.HuntTransactionDao;
import com.workshop.model.HuntTransaction;

public class HuntTransactionServiceImpl implements HuntTransactionService {

	@Autowired  
	HuntTransactionDao huntTransactiondao; 
	
	@Override
	public void insertData(HuntTransaction huntTransaction) {
		huntTransactiondao.insertData(huntTransaction);
	}

	@Override
	public void updateData(HuntTransaction huntTransaction) {
		huntTransactiondao.updateData(huntTransaction);
	}

	@Override
	public HuntTransaction getHuntTransaction(int id) {
		return huntTransactiondao.getHuntTransaction(id);
	}

	@Override
	public List<HuntTransaction> getHuntTransactionList() {
		return huntTransactiondao.getHuntTransactionList();
	}
	
	@Override
	public List<HuntTransaction> getHuntTransactionInfo(int userId) {
		//retreive hunt history of specific user
		return huntTransactiondao.getHuntTransactionByUserId(userId);
	}

}
