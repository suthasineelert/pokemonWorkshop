package com.workshop.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.jdbc.PokemonRowMapper;
import com.workshop.jdbc.UserRowMapper;
import com.workshop.model.Pokemon;
import com.workshop.model.User;

public class PokeballReloadDaoImpl implements PokeballReloadDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void insertData(int userId, String timestamp) {

		String sql = "INSERT INTO WS_POKEBALL_RELOAD (USER_ID, TIMESTAMP) "
				+ "VALUES (?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql,
				new Object[] { userId, timestamp });
	}

	@Override
	public void updateData(int userId, String timestamp) {
		String sql = "UPDATE WS_POKEBALL_RELOAD SET TIMESTAMP=? WHERE USER_ID = ?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { timestamp, userId });
	}

	@Override
	public String getTimestamp(int userId) {
		String sql = "SELECT TIMESTAMP FROM WS_POKEBALL_RELOAD WHERE USER_ID=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String timestamp = "";
		try {
			timestamp = (String) jdbcTemplate.queryForObject(
					sql, new Object[] { userId }, String.class);
			
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
		
		return timestamp;
	}

	@Override
	public void removeData(int userId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("DELETE FROM WS_POKEBALL_RELOAD WHERE USER_ID = ?", 
				new Object[] { userId });
	}

}
