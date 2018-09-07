package com.workshop.dao;

import java.util.List;

import com.workshop.model.LoginHistory;

public interface LoginHistoryDao {
	public void insertData(LoginHistory loginHistory);
	public void updateData(LoginHistory loginHistory);
	public LoginHistory getLoginHistory(int userId);
	public List<LoginHistory> getLoginHistoryList();
}
