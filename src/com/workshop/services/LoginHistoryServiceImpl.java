package com.workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.workshop.dao.LoginHistoryDao;
import com.workshop.model.LoginHistory;

public class LoginHistoryServiceImpl implements LoginHistoryService {

	@Autowired  
	LoginHistoryDao loginHistorydao; 
	
	@Override
	public void insertData(LoginHistory loginHistory) {
		loginHistorydao.insertData(loginHistory);
	}

	@Override
	public void updateData(LoginHistory loginHistory) {
		loginHistorydao.updateData(loginHistory);
	}

	@Override
	public LoginHistory getLoginHistory(int userId) {
		return loginHistorydao.getLoginHistory(userId);
	}

	@Override
	public List<LoginHistory> getLoginHistoryList() {
		return loginHistorydao.getLoginHistoryList();
	}

}
