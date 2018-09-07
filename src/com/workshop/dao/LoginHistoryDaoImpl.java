package com.workshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.jdbc.LoginHistoryRowMapper;
import com.workshop.model.LoginHistory;

public class LoginHistoryDaoImpl implements LoginHistoryDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void insertData(LoginHistory loginHistory) {

		String sql = "INSERT INTO WS_LOGIN_HISTORY (USER_ID,LOGIN_DATETIME,LOGOUT_DATETIME) VALUES (?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { loginHistory.getUserId(),
				loginHistory.getLoginDateTime(), loginHistory.getLogoutDateTime() });
	}

	@Override
	public void updateData(LoginHistory loginHistory) {
		String sql = "UPDATE WS_LOGIN_HISTORY SET USER_ID=?,LOGIN_DATETIME=?,LOGOUT_DATETIME=?"
				+ " WHERE LOGIN_HIST_ID = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { loginHistory.getUserId(), loginHistory.getLoginDateTime(),
				loginHistory.getLogoutDateTime(), loginHistory.getId() });

	}

	@Override
	public LoginHistory getLoginHistory(int userId) {
		List<LoginHistory> loginHistoryList = new ArrayList<LoginHistory>();
		String sql = "SELECT * FROM WS_LOGIN_HISTORY WHERE USER_ID=" + userId +" ORDER BY LOGIN_HIST_ID DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		loginHistoryList = jdbcTemplate.query(sql, new LoginHistoryRowMapper());
		return loginHistoryList.get(0);
	}

	@Override
	public List<LoginHistory> getLoginHistoryList() {
		List<LoginHistory> loginHistoryList = new ArrayList<LoginHistory>();
		String sql = "SELECT * FROM WS_LOGIN_HISTORY";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		loginHistoryList = jdbcTemplate.query(sql, new LoginHistoryRowMapper());
		return loginHistoryList;
	}

}
