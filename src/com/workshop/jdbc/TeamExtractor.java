package com.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.workshop.model.Team;

public class TeamExtractor implements ResultSetExtractor<Team> {

	public Team extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		Team team = new Team();

		team.setId(resultSet.getInt(1));
		team.setName(resultSet.getString(2));
		team.setColor(resultSet.getString(3));
		team.setImage(resultSet.getString(4));

		return team;
	}

}
