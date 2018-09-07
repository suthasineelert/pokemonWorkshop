package com.workshop.services;

import java.util.List;

import com.workshop.model.LoginHistory;

public interface LoginHistoryService {
	public void insertData(LoginHistory loginHistory);
	public void updateData(LoginHistory loginHistory);
	public LoginHistory getLoginHistory(int userId);
	public List<LoginHistory> getLoginHistoryList();
}
