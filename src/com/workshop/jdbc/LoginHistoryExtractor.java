package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.workshop.model.LoginHistory;

public class LoginHistoryExtractor implements ResultSetExtractor<LoginHistory> {

	public LoginHistory extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		LoginHistory loginHistory = new LoginHistory();

		loginHistory.setId(resultSet.getInt(1));
		loginHistory.setUserId(resultSet.getInt(2));
		loginHistory.setLoginDateTime(resultSet.getString(3));
		loginHistory.setLogoutDateTime(resultSet.getString(4));

		return loginHistory;
	}
}
