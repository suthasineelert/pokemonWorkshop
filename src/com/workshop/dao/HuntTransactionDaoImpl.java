package com.workshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.workshop.jdbc.HuntTransactionRowMapper;
import com.workshop.model.HuntTransaction;

public class HuntTransactionDaoImpl implements HuntTransactionDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void insertData(HuntTransaction huntTransaction) {

		String sql = "INSERT INTO WS_HUNT_TRANSACTION "
				+ "(USER_ID,POKEMON_ID,TXN_DATETIME,EARN_EXP,PLAYER_WIN_COUNT,POKEMON_WIN_COUNT)"
				+ "VALUES (?,?,?,?,?,?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql,
				new Object[] { huntTransaction.getUserId(), huntTransaction.getPokemonId(),
						huntTransaction.getDateTime(), huntTransaction.getEarnExp(), huntTransaction.getPlayerWinCount(),
						huntTransaction.getPokemonWinCount() });
	}

	@Override
	public void updateData(HuntTransaction huntTransaction) {
		String sql = "UPDATE WS_HUNT_TRANSACTION SET USER_ID=?,POKEMON_ID=?,TXN_DATETIME=?,"
				+ "EARN_EXP=?,PLAYER_WIN_COUNT=?,POKEMON_WIN_COUNT=? WHERE HUNT_TXN_ID=?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql,
				new Object[] { huntTransaction.getUserId(), huntTransaction.getPokemonId(), huntTransaction.getDateTime(),
						huntTransaction.getEarnExp(), huntTransaction.getPlayerWinCount(),
						huntTransaction.getPokemonWinCount(), huntTransaction.getId() });

	}

	@Override
	public HuntTransaction getHuntTransaction(int id) {
		List<HuntTransaction> huntTransactionList = new ArrayList<HuntTransaction>();
		String sql = "SELECT * FROM WS_HUNT_TRANSACTION WHERE HUNT_TXN_ID=" + id;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		huntTransactionList = jdbcTemplate.query(sql, new HuntTransactionRowMapper());
		if(huntTransactionList.size() > 0) return huntTransactionList.get(0);
		return null;
	}

	@Override
	public List<HuntTransaction> getHuntTransactionList() {
		List<HuntTransaction> huntTransactionList = new ArrayList<HuntTransaction>();
		String sql = "SELECT * FROM WS_HUNT_TRANSACTION ORDER BY TXN_DATETIME DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		huntTransactionList = jdbcTemplate.query(sql, new HuntTransactionRowMapper());
		return huntTransactionList;
	}
	
	@Override
	public List<HuntTransaction> getHuntTransactionByUserId(int userId) {
		List<HuntTransaction> huntTransactionList = new ArrayList<HuntTransaction>();
		//Get only player win
		String sql = "SELECT * "
				+ "FROM ws_hunt_transaction a INNER JOIN ws_pokemon b ON a.POKEMON_ID = b.POKEMON_ID"
				+ " WHERE USER_ID ="+userId+" AND PLAYER_WIN_COUNT > POKEMON_WIN_COUNT"
						+ " ORDER BY TXN_DATETIME DESC";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		huntTransactionList = jdbcTemplate.query(sql, new HuntTransactionRowMapper());
		return huntTransactionList;
	}


}
