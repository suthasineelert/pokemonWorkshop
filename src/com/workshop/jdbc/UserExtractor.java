package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.workshop.model.User;

public class UserExtractor implements ResultSetExtractor<User> {

	public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		User user = new User();

		user.setId(resultSet.getInt(1));
		user.setUsername(resultSet.getString(2));
		user.setPassword(resultSet.getString(3));
		user.setGender(resultSet.getString(4));
		user.setPlayerName(resultSet.getString(5));
		user.setTeamId(resultSet.getInt(6));
		user.setCurrentLevel(resultSet.getInt(7));
		user.setCurrentExp(resultSet.getInt(8));
		user.setCurrentPocketSlot(resultSet.getInt(9));
		user.setMaxExp(resultSet.getInt(10));
		user.setMaxPocketSlot(resultSet.getInt(11));

		return user;
	}
}
